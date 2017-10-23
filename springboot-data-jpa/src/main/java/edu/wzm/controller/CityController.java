package edu.wzm.controller;

import edu.wzm.entity.City;
import edu.wzm.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by gatsbynewton on 2017/10/15.
 */
@RestController
@RequestMapping(value = "/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/query", method= RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String find(){
        return cityService.findAl().stream().findFirst().get().toString();
    }


    /**
     * 使用URL参数
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Page<City> findWithParams(@RequestParam(value = "page", defaultValue = "1") int page,
                                     @RequestParam(value = "size", defaultValue = "2") int size){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        return cityService.findAll(pageable);
    }

    /**
     * 使用 @PageableDefault 设置默认分页参数，即在URL中可以不输入参数
     */
    @RequestMapping(value = "/pageable", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Page<City> findWithDefaultValue(@RequestParam("country") String country,
                                           @PageableDefault(value = 1, size = 2, sort = {"id"},
                                                   direction = Sort.Direction.DESC) Pageable pageable){
        return cityService.findAllWithCountry(country, pageable);
    }
}
