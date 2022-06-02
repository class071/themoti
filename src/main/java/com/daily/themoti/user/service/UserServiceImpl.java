
package com.daily.themoti.user.service;

import com.daily.themoti.jwt.TokenService;
import com.daily.themoti.jwt.config.CustomUserDetails;
import com.daily.themoti.smokearea.exception.ParseFailedException;
import com.daily.themoti.user.domain.User;
import com.daily.themoti.user.dto.KakaoUserInfo;
import com.daily.themoti.user.dto.LoginResponse;
import com.daily.themoti.user.dto.RefreshTokenResponse;
import com.daily.themoti.user.dto.UserResponseDto;
import com.daily.themoti.user.exception.AuthenticationEntryPointException;
import com.daily.themoti.user.exception.UnauthorizedUserException;
import com.daily.themoti.user.exception.UserNotExistException;
import com.daily.themoti.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Value("${apikey.kakao.rest.api.key}")
    private String KAKAO_REST_API_KEY;

    @Override
    public String getAccessToken(String code){
        String kakaoURL = "https://kauth.kakao.com/oauth/token";
        String access_token = "";
        String refresh_token = "";
        try{
            URL url = new URL(kakaoURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + KAKAO_REST_API_KEY);
            sb.append("&redirect_uri=http://localhost:8080/user/kakao/oauth");
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            int responseCode = connection.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            String result = "";

            while((line = br.readLine()) != null){
                result += line;
            }
            System.out.println("response body = " + result);

            JSONObject object = parseJSON(result);
            access_token = (String) object.get("access_token");
            refresh_token = (String) object.get("refresh_token");

        } catch(IOException e){
            e.printStackTrace();
        }
        return access_token;
    }

    @Override
    public LoginResponse loginWithAccessToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        RestTemplate rt = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);
        rt.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        rt.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus statusCode = response.getStatusCode();
                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
            }
        });

        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        String str = response.getBody();
        JSONObject kakao_response = parseJSON(str);
        JSONObject kakao_account = (JSONObject) kakao_response.get("kakao_account");
        JSONObject profile = (JSONObject) kakao_account.get("profile");
        String email = (String) kakao_account.get("email");
        String nickname = (String) profile.get("nickname");

        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(email, nickname);
        if(!userRepository.findByEmail(email).isPresent()){
            userRepository.save(kakaoUserInfo.toEntity());
        }

        return createJwtToken(email);
    }

    private JSONObject parseJSON(String result){
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);

            return jsonObject;
        } catch(ParseException e){
            throw new ParseFailedException();
        }
    }

    public LoginResponse createJwtToken(String email){
        String accessToken = tokenService.createAccessToken(email);
        String refreshToken = tokenService.createRefreshToken(email);

        LoginResponse loginResponse = new LoginResponse(accessToken, refreshToken);
        return loginResponse;
    }

    @Override
    public RefreshTokenResponse refreshToken(String rToken) {
        validateRefreshToken(rToken);
        String subject = tokenService.extractRefreshTokenSubject(rToken);
        String accessToken = tokenService.createAccessToken(subject);
        return new RefreshTokenResponse(accessToken);
    }

    private void validateRefreshToken(String rToken) {
        if (!tokenService.validateRefreshToken(rToken)) {
            throw new AuthenticationEntryPointException("유효하지 않은 refresh token 입니다.");
        }
    }

    @Override
    public UserResponseDto getUserInfo() {
        try {
            CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String email = customUserDetails.getEmail();
            User user = userRepository.findByEmail(email).orElseThrow(UserNotExistException::new);
            return new UserResponseDto(user);
        } catch(IllegalStateException e){
            throw new UnauthorizedUserException("권한에 맞지 않는 요청이거나 로그인 되어 있지 않습니다.");
        }
    }

    public boolean checkUser(Long id){
        try {
            CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String email = customUserDetails.getEmail();
            Long storedId = userRepository.findByEmail(email).get().getId();
            if (id != storedId){
                throw new UnauthorizedUserException("요청된 유저의 ID와 현재 유저의 ID가 일치하지 않습니다.");
            }
            return true;
        } catch(IllegalStateException e){
            throw new UnauthorizedUserException("권한에 맞지 않는 요청이거나 로그인 되어 있지 않습니다.");
        }
    }
}
