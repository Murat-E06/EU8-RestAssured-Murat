package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@JsonIgnoreProperties(value = "id",allowSetters = true)//while serializiation we want to api to ignore id
//it does not come

public class Spartan {

    //getter
    //setter
    private int id;
    private String name;
    private  String gender;
    private long phone;

}

