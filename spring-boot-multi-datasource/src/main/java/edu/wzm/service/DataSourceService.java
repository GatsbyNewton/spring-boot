package edu.wzm.service;

import edu.wzm.dao.master.PersonDao;
import edu.wzm.dao.replica.ProductDao;
import edu.wzm.dao.slaver.CityDao;
import edu.wzm.entity.City;
import edu.wzm.entity.Person;
import edu.wzm.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DataSourceService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private ProductDao productDao;

    public Map<String, Object> find(long personId, int cityId, int productId){
        Map<String, Object> map = new HashMap<>();
        Person person = personDao.findById(personId);
        City city = cityDao.findById(cityId);
        Product product = productDao.findById(productId);

        map.put("person", person);
        map.put("city", city);
        map.put("product", product);

        return map;
    }
}
