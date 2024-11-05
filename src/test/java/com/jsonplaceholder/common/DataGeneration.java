package com.jsonplaceholder.common;

import com.jsonplaceholder.models.AlbumDTO;
import com.jsonplaceholder.models.PostDTO;

public class DataGeneration {
    public static AlbumDTO createNewAlbum() {
        AlbumDTO newAlbum = new AlbumDTO();

        newAlbum.setTitle("Title");
        newAlbum.setUserId(4);

        return newAlbum;
    }

    public static PostDTO createNewPost() {
        PostDTO newPost = new PostDTO();

        newPost.setUserId(1);
        newPost.setTitle("Title01");
        newPost.setBody("Post body");

        return newPost;
    }
}
