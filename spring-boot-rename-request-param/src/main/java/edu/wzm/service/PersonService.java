package edu.wzm.service;

import edu.wzm.dao.PersonDao;
import edu.wzm.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;

    public int addPerson(Person person){
        return personDao.addPerson(person);
    }

    public List<Person> getPeople(){
        return personDao.findAll();
    }
}
