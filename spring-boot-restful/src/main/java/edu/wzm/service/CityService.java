package edu.wzm.service;

import edu.wzm.dao.CityDao;
import edu.wzm.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gatsbynewton on 2017/12/20.
 */
@Service
public class CityService {
    @Autowired
    private CityDao cityDao;

    public int addOne(City city){
        return cityDao.addOne(city);
    }

    public int addBatch(List<City> cities){
        return cityDao.addBatch(cities);
    }
}
