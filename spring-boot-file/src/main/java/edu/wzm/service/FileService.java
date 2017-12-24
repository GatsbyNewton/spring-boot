package edu.wzm.service;

import edu.wzm.entity.City;
import edu.wzm.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gatsbynewton on 2017/12/24.
 */
@Service
public class FileService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> findAll(){
        return cityRepository.find();
    }
}
