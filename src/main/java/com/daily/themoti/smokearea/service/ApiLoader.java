package com.daily.themoti.smokearea.service;

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

    @Value("${apikey.kwangjin}")
    private String KWANGJIN;

    @Value("${apikey.kakaomap}")
    private String KAKAO_MAP_KEY;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        load_yongsan();
        load_kwangjin();
    }
    //Point
    private void load_yongsan(){
        String result = loadFromApi(YONGSAN);
        JSONObject jsonObject  = parseJSON(result);

        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject object = (JSONObject) jsonArray.get(i);
            String longitude = (String) object.get("경도");
            String latitude = (String) object.get("위도");
            SaveAreaRequestDto saveAreaRequestDto = new SaveAreaRequestDto(longitude, latitude);
            smokeAreaRepository.save(saveAreaRequestDto.toEntity());
        }
    }

    private String loadFromApi(String area){
        StringBuffer result = new StringBuffer();
        try{
            URL url = new URL(area);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
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
    private void load_kwangjin(){
        String result = loadFromApi(KWANGJIN);
        JSONObject jsonObject  = parseJSON(result);

        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject object = (JSONObject) jsonArray.get(i);
            String addr = (String) object.get("영업소소재지(도로 명)");
            smokeAreaRepository.save(changeAddressToPoint(addr).toEntity()); // 바껴진 것을 통해서 save를 실행한다.
        }
    }

    private SaveAreaRequestDto changeAddressToPoint(String addr){
        String result = loadFromKakaoApi(addr);
        try{
            JSONObject jsonObject = parseJSON(result.toString());
            JSONArray documents = (JSONArray) jsonObject.get("documents");
            JSONObject address = (JSONObject) documents.get(0);
            String longitude = (String) address.get("x");
            String latitude = (String) address.get("y");

            SaveAreaRequestDto saveAreaRequestDto = new SaveAreaRequestDto(longitude,latitude);
            return saveAreaRequestDto;
        } catch(Exception e){
            e.printStackTrace();
            throw new ParseFailedException();
        }
    }

    private String loadFromKakaoApi(String addr){
        try {
            String str = addr.replace("~", "");
            String encodedAddr = URLEncoder.encode(str, "UTF-8");

            URL url = new URL(KAKAO_URL + encodedAddr);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

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
            throw new ParseFailedException();
        }
    }
}
