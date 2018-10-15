package edu.wzm.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @description Jackson的注解@JsonProperty作用于请求传入的参数；
 * Fastjson的注解@JSONField作用于返回结果，由于返回结果用到了JSON.toJSONString()方法序列化，
 */
public class Person {

    private int id;

    @JsonProperty(value = "first_name")
    @JSONField(name = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    @JSONField(name = "last_name")
    private String lastName;

    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
