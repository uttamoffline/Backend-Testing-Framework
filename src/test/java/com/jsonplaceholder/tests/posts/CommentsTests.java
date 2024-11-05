package com.jsonplaceholder.tests.posts;

import com.jsonplaceholder.models.CommentDTO;
import com.jsonplaceholder.tests.BaseTest;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.jsonplaceholder.common.Connectors.setServiceConnection;
import static com.jsonplaceholder.services.posts.PostsRequests.getCommentsByPostId;
import static com.jsonplaceholder.services.posts.PostsRequests.getCommentsToSpecificPost;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CommentsTests extends BaseTest {
    @BeforeClass
    public static void setConnection() {
        setServiceConnection();
    }

    @Test
    public void getCommentsToPost() {
        final int postId = 2;

        Response response = getCommentsByPostId(postId);

        response
                .then()
                .statusCode(200);

        List<CommentDTO> receivedComments = response
                .getBody()
                .jsonPath()
                .getList("", CommentDTO.class);

        assertTrue("Expected to receive non-empty list of comments", receivedComments.size() > 0);

        response = getCommentsToSpecificPost(postId);

        response
                .then()
                .statusCode(200);

        List<CommentDTO> receivedComments2 = response
                .getBody()
                .jsonPath()
                .getList("", CommentDTO.class);

        assertEquals("Expected to receive the same amount of comments by all methods",
                receivedComments.size(),
                receivedComments2.size());
    }
}
