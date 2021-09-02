package org.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @email $1602205111@qq.com
 * @author: mayifan
 * @date: 2021/9/2
 * @time: 12:57
 */
public class TEST {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> car = new HashMap<>();
        car.put("BMW",12312);
        car.put("BNE",312321);
        car.put("audi",312312);

       String JsonCar = objectMapper.writeValueAsString(car);

       List<Integer> strs = new ArrayList<>();
        strs.add(1);
        strs.add(2);
        strs.add(3);
        strs.add(4);

        //类转JSON
        String JsonStrs = objectMapper.writeValueAsString(strs);

        //Json转List
        List<String> ListType = objectMapper.readValue(JsonStrs,List.class);
        System.out.printf(ListType.toString());

        //Json转Map
        Map<String,Object> claim = new HashMap<>();
        Map<String,Object> car2 = objectMapper.readValue(JsonCar,Map.class);
        System.out.printf(car2.toString());


    }
}
