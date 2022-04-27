package com.daily.themoti.smokearea.controller;

import com.daily.themoti.global.api.ApiResponse;
import com.daily.themoti.smokearea.dto.SaveAreaRequestDto;
import com.daily.themoti.smokearea.service.SmokeAreaService;

import lombok.RequiredArgsConstructor;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequiredArgsConstructor
@RequestMapping("/area")
public class SmokeAreaController {

    private final SmokeAreaService smokeAreaService;

    @GetMapping("/api/load")
    public ApiResponse<?> loadJsonFromApi(){
        StringBuffer result = new StringBuffer();
        try{
            URL url = new URL("https://api.odcloud.kr/api/15073796/v1/uddi:17fbd06c-45bb-48aa-9be7-b26dbc708c9c?page=1&perPage=100&serviceKey=K3Rg9G3TMgSqBpVkmaTE5iLvLegNzBRpU6woyF8u6AkT%2Bie5EnKQQk8FR9uKcOHPnk7Y6MqT1azZz21d63YXrQ%3D%3D");
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result.append(bf.readLine());

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result.toString());
            JSONArray jsonArray = (JSONArray) jsonObject.get("data");
            for(int i=0 ; i<jsonArray.size() ; i++){
                JSONObject object = (JSONObject) jsonArray.get(i);
                String longitude = (String) object.get("경도");
                String latitude = (String) object.get("위도");
                SaveAreaRequestDto saveAreaRequestDto = new SaveAreaRequestDto(longitude, latitude);
                smokeAreaService.save(saveAreaRequestDto);
            }
            // return ApiResonse 성공 코드
            return ApiResponse.success(null,null,null,null);

        }catch(Exception e){
            // exceptionHandler -> ApiResonse error 코드
            return ApiResponse.error(null,null,null);
        }
    }
}
