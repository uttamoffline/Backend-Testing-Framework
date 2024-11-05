package com.jsonplaceholder.tests.posts;

import com.jsonplaceholder.models.PostDTO;
import com.jsonplaceholder.tests.BaseTest;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.jsonplaceholder.common.Connectors.setServiceConnection;
import static com.jsonplaceholder.common.DataGeneration.createNewPost;
import static com.jsonplaceholder.services.posts.PostsRequests.addPost;
import static com.jsonplaceholder.services.posts.PostsRequests.getPosts;
import static com.jsonplaceholder.services.posts.PostsRequests.getPostsById;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PostsSmokeTests extends BaseTest {
    @BeforeClass
    public static void setConnection() {
        setServiceConnection();
    }

    @Test
    public void getAllPosts() {
        Response response = getPosts();

        response
                .then()
                .statusCode(200);

        List<PostDTO> postsList = response
                .getBody()
                .jsonPath()
                .getList("", PostDTO.class);

        assertTrue("Non-empty list of posts is expected", postsList.size() > 0);
    }

    @Test
    public void getSpecificPost() {
        Response response = getPostsById(1);

        response
                .then()
                .statusCode(200);

        PostDTO receivedPost = response
                .getBody()
                .jsonPath()
                .getObject("", PostDTO.class);

        assertEquals("Post ID is expected to be 1", 1, receivedPost.getId());
    }

    @Test
    public void addNewPost() {
        PostDTO newPost = createNewPost();
        Response response = addPost(newPost);

        response
                .then()
                .statusCode(201);

        PostDTO receivedPost = response
                .getBody()
                .jsonPath()
                .getObject("", PostDTO.class);

        assertEquals("Post titles should match", newPost.getTitle(), receivedPost.getTitle());
        assertEquals("Post bodies should match", newPost.getBody(), receivedPost.getBody());
        assertEquals("Post UserId's should match", newPost.getUserId(), receivedPost.getUserId());
    }
}
