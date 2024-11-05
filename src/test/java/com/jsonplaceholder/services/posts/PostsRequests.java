package com.jsonplaceholder.services.posts;

import com.jsonplaceholder.models.PostDTO;
import io.restassured.response.Response;

import static com.jsonplaceholder.services.posts.PostsEndpoints.*;
import static com.jsonplaceholder.utils.JsonPOJOConverter.serializeToJson;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class PostsRequests {
    public static Response getPosts() {
        return given()
                .contentType(JSON)
                .get(POSTS);
    }

    public static Response getPostsById(int postId) {
        return given()
                .contentType(JSON)
                .get(POSTS_BY_POST_ID, postId);
    }

    public static Response getCommentsByPostId(int postId) {
        return given()
                .contentType(JSON)
                .param("postId", postId)
                .get(COMMENTS);
    }

    public static Response getCommentsToSpecificPost(int postId) {
        return given()
                .contentType(JSON)
                .get(POSTS_ID_COMMENTS, postId);

    }

    public static Response addPost(PostDTO newPost) {
        return given()
                .contentType(JSON)
                .body(serializeToJson(newPost))
                .post(POSTS);
    }
}
