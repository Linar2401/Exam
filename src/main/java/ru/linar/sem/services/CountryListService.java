package ru.linar.sem.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;



import static java.util.Map.Entry.comparingByKey;

@Component
public class CountryListService {
    private OkHttpClient httpClient = new OkHttpClient();
    private JsonParser springParser = JsonParserFactory.getJsonParser();
    private final String URL = "https://restcountries.eu/rest/v2/all?fields=name;alpha3Code";
    private final String NAME_ATTR = "name";
    private final String CODE_ATTR = "alpha3Code";

    public Map<String, String> getList() throws IOException {
        Request request = new Request.Builder()
                .url(URL)
                .build();
        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            List<Object> list = springParser.parseList(response.body().string());
            Map<String, String> map = new HashMap<>();
            for (Object item: list){
                String country = item.toString();
                map.put(country.substring( country.lastIndexOf(',')+13, country.length()-1),country.substring(6, country.lastIndexOf(',')));
            }
            map = map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                    (e1, e2) -> e2, LinkedHashMap::new));
            return map;
        }
    }
}
