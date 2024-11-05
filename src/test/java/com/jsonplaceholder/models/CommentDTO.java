package com.jsonplaceholder.models;

import lombok.Data;

@Data
public class CommentDTO {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
