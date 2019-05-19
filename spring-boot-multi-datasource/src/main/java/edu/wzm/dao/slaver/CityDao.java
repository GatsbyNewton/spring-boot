package edu.wzm.dao.slaver;

import edu.wzm.entity.City;
import org.apache.ibatis.annotations.Param;

public interface CityDao {
    City findById(@Param("id") int id);
}
