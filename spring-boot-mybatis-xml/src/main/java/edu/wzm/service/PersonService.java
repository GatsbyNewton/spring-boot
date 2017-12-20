package edu.wzm.service;

import edu.wzm.dao.PersonDao;
import edu.wzm.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gatsbynewton on 2017/10/8.
 */
@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;

    public Person getById(int id){
        return personDao.getById(id);
    }

    public List<Person> getAll(){
        return personDao.getAll();
    }
}
