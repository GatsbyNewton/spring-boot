package edu.wzm.dao.master;

import edu.wzm.entity.Person;
import org.apache.ibatis.annotations.Param;

public interface PersonDao {

    Person findById(@Param("id") long id);
}
