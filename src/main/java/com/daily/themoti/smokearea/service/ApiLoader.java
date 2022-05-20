package com.daily.themoti.smokearea.service;

import com.daily.themoti.smokearea.AddressApi;
import com.daily.themoti.smokearea.dto.SaveAreaRequestDto;
import com.daily.themoti.smokearea.exception.DataLoadFailedException;
import com.daily.themoti.smokearea.exception.ParseFailedException;
import com.daily.themoti.smokearea.exception.WrongURLException;
import com.daily.themoti.smokearea.repository.SmokeAreaRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

@Component
@RequiredArgsConstructor
public class ApiLoader implements ApplicationRunner {

    private final SmokeAreaRepository smokeAreaRepository;

    private static String KAKAO_URL = "https://dapi.kakao.com/v2/local/search/address.json?analyze_type=similar&page=1&size=10&query=";

    @Value("${apikey.yongsan}")
    private String YONGSAN;

    @Value("${apikey.yeongdeungpo}")
    private String YEONGDEUNGPO;

    @Value("${apikey.kwangjin}")
    private String KWANGJIN;

    @Value("${apikey.joong}")
    private String JOONG;

    @Value("${apikey.joongrang}")
    private String JOONGRANG;

    @Value("${apikey.dongjak}")
    private String DONGJAK;

    @Value("${apikey.songpa}")
    private String SONGPA;

    @Value("${apikey.seodaemoon}")
    private String SEODAEMOON;

    @Value("${apikey.dongdaemoon}")
    private String DONGDAEMOON;

    @Value("${apikey.kakaomap}")
    private String KAKAO_MAP_KEY;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        call_PointApi(YONGSAN, 1);
        call_PointApi(YEONGDEUNGPO, 2);

        call_AddressApi(KWANGJIN, AddressApi.KWANGJIN.getStoredAt(), 3);
        call_AddressApi(JOONG, AddressApi.JOONG.getStoredAt(), 4);
        call_AddressApi(JOONGRANG, AddressApi.JOONGRANG.getStoredAt(), 5);
        call_AddressApi(DONGJAK, AddressApi.DONGJAK.getStoredAt(), 6);
        call_AddressApi(SONGPA, AddressApi.SONGPA.getStoredAt(), 7);
        call_AddressApi(SEODAEMOON, AddressApi.SEODAEMOON.getStoredAt(), 8);
        call_AddressApi(DONGDAEMOON, AddressApi.DONGDAEMOON.getStoredAt(), 9);
    }

    private void call_PointApi(String area, long areaNumber){
        String result = loadFromApi(area);
        JSONObject jsonObject  = parseJSON(result);

        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject object = (JSONObject) jsonArray.get(i);
            String longitude = (String) object.get("경도");
            String latitude = (String) object.get("위도");
            SaveAreaRequestDto saveAreaRequestDto = new SaveAreaRequestDto(longitude, latitude, areaNumber);
            smokeAreaRepository.save(saveAreaRequestDto.toEntity());
        }
    }

    private String loadFromApi(String area){
        StringBuffer result = new StringBuffer();
        try{
            HttpURLConnection connection = getConnection(area);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");

            Charset charset = Charset.forName("UTF-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));

            String inputLine;
            while((inputLine = in.readLine()) !=null){
                result.append(inputLine);
            }

            return result.toString();
        } catch(Exception e){
            throw new DataLoadFailedException();
        }
    }

    private JSONObject parseJSON(String result){
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);

            checkURLKey(jsonObject);

            return jsonObject;
        } catch(ParseException e){
            throw new ParseFailedException();
        }
    }

    private void checkURLKey(JSONObject jsonObject){
        if(jsonObject.get("code") != null && (int) jsonObject.get("code") == -4){
            throw new WrongURLException();
        }
    }
    //Address To Point
    private void call_AddressApi(String area, String storedAt, long areaNumber){
        String result = loadFromApi(area);
        JSONObject jsonObject  = parseJSON(result);

        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject object = (JSONObject) jsonArray.get(i);
            String addr = (String) object.get(storedAt);
            if(changeAddressToPoint(addr, areaNumber).getLatitude().equals("-1")){
                continue;
            } else {
                smokeAreaRepository.save(changeAddressToPoint(addr, areaNumber).toEntity()); // 바껴진 것을 통해서 save를 실행한다.
            }
        }
    }

    private SaveAreaRequestDto changeAddressToPoint(String addr, long areaNumber){
        String result = loadFromKakaoApi(addr);
        try {
            JSONObject jsonObject = parseJSON(result);
            String longitude;
            String latitude;
            JSONArray documents = (JSONArray) jsonObject.get("documents");
            if (!ObjectUtils.isEmpty(documents)) {
                JSONObject address = (JSONObject) documents.get(0);
                longitude = (String) address.get("x");
                latitude = (String) address.get("y");
            } else {
                longitude = "-1";
                latitude = "-1";
            }
            return new SaveAreaRequestDto(longitude, latitude, areaNumber);
        } catch (Exception e){
            e.printStackTrace();
            throw new ParseFailedException();
        }
    }

    private String loadFromKakaoApi(String addr){
        try {
            String str = addr.replace("~", "");
            String encodedAddr = URLEncoder.encode(str, "UTF-8");

            HttpURLConnection connection = getConnection(KAKAO_URL + encodedAddr);

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", KAKAO_MAP_KEY);
            connection.setRequestProperty("content-type", "application/json");

            Charset charset = Charset.forName("UTF-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));

            String inputLine;
            StringBuffer result = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }
            return result.toString();
        } catch (Exception e){
            e.printStackTrace();
            throw new ParseFailedException();
        }
    }

    private HttpURLConnection getConnection(String addr){
        try {
            URL url = new URL(addr);
            return (HttpURLConnection) url.openConnection();
        } catch (Exception e){
            throw new WrongURLException();
        }
    }
}
