package edu.wzm.dao;

import edu.wzm.entity.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gatsbynewton on 2017/10/8.
 */
public interface PersonDao {
    void insert(@Param("person") Person person);

    Person getById(@Param("id") int id);

    List<Person> getAll();

    void update(@Param("person") Person person);

    void delete(@Param("id") int id);
}
