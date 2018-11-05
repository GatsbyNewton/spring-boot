package edu.wzm.controller;

import edu.wzm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Object find(@RequestParam("pageSize") int pageSize,
                       @RequestParam("pageNum") int pageNum){
        return personService.findPerson(pageSize, pageNum);
    }
}
