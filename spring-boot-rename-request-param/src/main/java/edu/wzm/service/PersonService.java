package edu.wzm.service;

import edu.wzm.dao.PersonDao;
import edu.wzm.entity.Person;
import edu.wzm.query.PersonPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;

    public int addPerson(PersonPost param){
        Person person = new Person();
        person.setFirstName(param.getFirstName());
        person.setLastName(param.getLastName());
        person.setAge((int)(Math.random() * 100));

        return personDao.addPerson(person);
    }

    public List<Person> getPeople(){
        return personDao.findAll();
    }
}
