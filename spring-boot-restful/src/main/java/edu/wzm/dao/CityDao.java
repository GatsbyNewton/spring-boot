package edu.wzm.dao;

import edu.wzm.entity.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gatsbynewton on 2017/12/20.
 */
public interface CityDao {
    int addOne(@Param("city") City city);

    int addBatch(@Param("cities") List<City> cities);
}
