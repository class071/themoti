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

}
