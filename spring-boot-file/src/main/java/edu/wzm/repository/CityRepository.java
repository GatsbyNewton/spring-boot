package edu.wzm.repository;

import edu.wzm.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gatsbynewton on 2017/10/15.
 */
@Repository
public interface CityRepository extends JpaRepository<City, Integer>, JpaSpecificationExecutor<City> {

    @Query(value = "select c.id, c.name, c.state, c.country, c.map from city c", nativeQuery = true)
    List<City> find();

    @Query(value = "SELECT c.id, c.country, c.name, c.state, c.map " +
            " FROM city c " +
            " WHERE c.id = :id", nativeQuery = true)
    City findBy(@Param(value = "id") int id);
}
