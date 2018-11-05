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

    public List<Person> findPerson(int pageSize, int pageNum){
        List<Person> userByPage = personDao.getByPage(pageSize, pageNum);
        List<Person> users = personDao.getAll();
        System.out.println("Find by page: " + userByPage);
        System.out.println("Find all: " + users);

        return userByPage;
    }
}
