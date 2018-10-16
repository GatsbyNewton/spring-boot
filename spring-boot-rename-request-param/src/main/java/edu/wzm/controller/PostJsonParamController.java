package edu.wzm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.wzm.entity.Person;
import edu.wzm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jimmy Wong
 * @description 重命名 POST 请求传 JSON 的参数
 */
@RestController
public class PostJsonParamController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getPeople(){
        List<Person> people = personService.getPeople();
        return JSON.toJSONString(people, SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addPerson(@RequestBody Person person){
        System.out.println(person);
        int result = personService.addPerson(person);
        return result;
    }
}
