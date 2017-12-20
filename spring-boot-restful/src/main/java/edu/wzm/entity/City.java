package edu.wzm.entity;

import java.io.Serializable;

/**
 * Created by gatsbynewton on 2017/10/15.
 */
public class City implements Serializable {

    private int id;
    private String name;
    private String state;
    private String country;
    private String map;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("City (")
                .append("id=" + id)
                .append("name=" + name)
                .append("state=" + state)
                .append("country=" + country)
                .append("map=" + map)
                .append(")");

        return sb.toString();
    }
}
