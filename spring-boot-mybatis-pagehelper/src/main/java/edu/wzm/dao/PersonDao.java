package edu.wzm.dao;

import edu.wzm.entity.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonDao {

    List<Person> getByPage(@Param("pageSize") int pageSize, @Param("pageNum") int pageNum);

    List<Person> getAll();
}
