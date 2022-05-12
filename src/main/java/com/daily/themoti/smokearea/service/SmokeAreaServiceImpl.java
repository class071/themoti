package com.daily.themoti.smokearea.service;

import com.daily.themoti.smokearea.dto.LoadAreaResponseDto;
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
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class SmokeAreaServiceImpl implements SmokeAreaService{

    private final SmokeAreaRepository smokeAreaRepository;

    @Value("${apikey.yongsan}")
    private String yongsan;

    public LoadAreaResponseDto saveWithPoint(){
        String result = loadFromApi();
        JSONObject jsonObject  = parseJSON(result);

        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject object = (JSONObject) jsonArray.get(i);
            String longitude = (String) object.get("경도");
            String latitude = (String) object.get("위도");
            SaveAreaRequestDto saveAreaRequestDto = new SaveAreaRequestDto(longitude, latitude);
            smokeAreaRepository.save(saveAreaRequestDto.toEntity());
        }

        int loadDataAmount = Integer.parseInt(String.valueOf(jsonObject.get("totalCount")));
        int savedDataAmount = (int) smokeAreaRepository.count();

        return new LoadAreaResponseDto(loadDataAmount == savedDataAmount);
    }

    private String loadFromApi(){
        StringBuffer result = new StringBuffer();
        try{
            URL url = new URL(yongsan);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result.append(bf.readLine());

            return result.toString();
        }catch(Exception e){
            throw new DataLoadFailedException();
        }
    }

    private JSONObject parseJSON(String result){
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result.toString());

            checkURLKey(jsonObject);

            return jsonObject;
        }catch(ParseException e){
            throw new ParseFailedException();
        }
    }

    private void checkURLKey(JSONObject jsonObject){
        if(jsonObject.get("code") != null && (int) jsonObject.get("code") == -4){
            throw new WrongURLException();
        }
    }
}
