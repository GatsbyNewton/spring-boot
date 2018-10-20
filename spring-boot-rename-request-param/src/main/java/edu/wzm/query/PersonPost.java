package edu.wzm.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Description;

@Description("POST的JSON型下划线请求参数转成之后的驼峰命名属性的POJO")
public class PersonPost {

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

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

    @Override
    public String toString() {
        return "PersonPost{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
