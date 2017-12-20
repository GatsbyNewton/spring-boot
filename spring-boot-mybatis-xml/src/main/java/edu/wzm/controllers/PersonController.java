package edu.wzm.controllers;

import edu.wzm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gatsbynewton on 2017/10/8.
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/one")
    public String getById(@RequestParam("id") int id){
        return personService.getById(id).toString();
    }
}
