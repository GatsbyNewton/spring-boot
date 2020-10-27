package edu.wzm.controller;

import edu.wzm.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatasourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @RequestMapping("/source")
    public Object get(@RequestParam("personId") long personId,
                      @RequestParam("cityId") int cityId,
                      @RequestParam("productId") int productId){
        return dataSourceService.find(personId, cityId, productId);
    }
}
