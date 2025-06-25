package com.learening.testing.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "subjects") 
public class Subject {
    private String id;
    private String name;
    private String code;
    private String description;
   

}
