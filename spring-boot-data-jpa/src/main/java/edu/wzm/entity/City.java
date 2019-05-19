package edu.wzm.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by gatsbynewton on 2017/10/15.
 */
@Entity
@Table(name = "city")
@Data
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "map", nullable = false)
    private String map;
}
