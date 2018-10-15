package edu.wzm.dao;

import edu.wzm.entity.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonDao {

    int addPerson(@Param("person") Person person);

    List<Person> findAll();
}
