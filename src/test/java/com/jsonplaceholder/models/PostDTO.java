package com.jsonplaceholder.models;

import lombok.Data;

@Data
public class PostDTO {
    private int userId;
    private int id;
    private String title;
    private String body;
}
