package edu.wzm.controller;

import com.alibaba.fastjson.JSONObject;
import edu.wzm.entity.City;
import edu.wzm.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gatsbynewton on 2017/12/20.
 */
@RestController
@RequestMapping("/restful")
public class RestfulController {
    @Autowired
    private CityService cityService;

    /**
     * curl -i -X POST -H "Content-type:application/json" -d '{"country": "Japan", "name": "Tokyo", "state": "", "map": "35.689488, 139.691706"}' http://localhost:8080/restful/object
     */
    @RequestMapping(value = "/object", method = RequestMethod.POST)
    public int addOneByObject(@RequestBody City city){
        return cityService.addOne(city);
    }

    /**
     * curl -i -X POST -H "Content-type:application/json" -d '{"country": "Japan", "name": "Tokyo", "state": "", "map": "35.689488, 139.691706"}' http://localhost:8080/restful/map
     */
    @RequestMapping(value = "/map", method = RequestMethod.POST)
    public int addOneByMap(@RequestBody Map<String, String> map){
        City city = new City();
        city.setCountry(map.getOrDefault("country", null));
        city.setName(map.getOrDefault("name", null));
        city.setState(map.getOrDefault("state", null));
        city.setMap(map.getOrDefault("map", null));
        return cityService.addOne(city);
    }

    /**
     * curl -i -X POST -H "Content-type:application/json" -d '{"country": "Japan", "name": "Tokyo", "state": "", "map": "35.689488, 139.691706"}' http://localhost:8080/restful/json
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public int addOneByJSONObject(@RequestBody JSONObject json){
        City city = new City();
        city.setCountry(json.getString("country"));
        city.setName(json.getString("name"));
        city.setState(json.getString("state"));
        city.setMap(json.getString("map"));
        System.out.println(city);
        return cityService.addOne(city);
    }

    /**
     * curl -i -X POST -H "Content-type:application/json" -d '[{"country": "Japan", "name": "Tokyo", "state": "", "map": "35.689488, 139.69176"}, {"country": "Spain", "name": "Barcelona", "state": "Catalunya", "map": "41.387917, 2.169919"}]' http://localhost:8080/restful/list
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public int addOneByListMap(@RequestBody List<Map<String, String>> list){
        List<City> cities = new ArrayList<>();
        for (Map<String, String> map : list) {
            City city = new City();
            city.setCountry(map.getOrDefault("country", null));
            city.setName(map.getOrDefault("name", null));
            city.setState(map.getOrDefault("state", null));
            city.setMap(map.getOrDefault("map", null));
            cities.add(city);
        }
        return cityService.addBatch(cities);
    }

    /**
     * curl -i -X POST -H "Content-type:application/json" -d '["one", "two", "three"]' http://localhost:8080/restful/array
     */
    @RequestMapping(value = "/array", method = RequestMethod.POST)
    public int addOneByList(@RequestBody List<String> list){
        System.out.println(list);
        return 1;
    }
}
