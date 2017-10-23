package edu.wzm.service;

import edu.wzm.entity.City;
import edu.wzm.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by gatsbynewton on 2017/10/15.
 */
@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public void insert(City city){
        cityRepository.save(city);
    }

    public List<City> findAl(){
        return cityRepository.find();
    }

    public City findById(int id){
        return cityRepository.findBy(id);
    }

    /**
     * 做分页的时候，需要使用 findAll() 方法，多条件是需要实现 Specification 接口
     */
    public Page<City> findAllWithCountry(String country, Pageable pageable){

        return cityRepository.findAll((Root<City> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {

            Predicate p1 = criteriaBuilder.equal(root.get("country").as(String.class), country);
            query.where(criteriaBuilder.and(p1));
            return query.getRestriction();
        }, pageable);
    }

    public Page<City> findAll(Pageable pageable){
        return cityRepository.findAll(pageable);
    }

}
