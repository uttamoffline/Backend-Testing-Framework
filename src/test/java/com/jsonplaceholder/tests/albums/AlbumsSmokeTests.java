package com.jsonplaceholder.tests.albums;

import com.jsonplaceholder.models.AlbumDTO;
import com.jsonplaceholder.tests.BaseTest;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.jsonplaceholder.common.Connectors.setServiceConnection;
import static com.jsonplaceholder.common.DataGeneration.createNewAlbum;
import static com.jsonplaceholder.services.albums.AlbumsRequests.getAlbumById;
import static com.jsonplaceholder.services.albums.AlbumsRequests.getAlbums;
import static com.jsonplaceholder.services.albums.AlbumsRequests.postAlbum;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class AlbumsSmokeTests extends BaseTest {
    @BeforeClass
    public static void setConnection() {
        setServiceConnection();
    }

    @Test
    public void getAllAlbums() {
        Response response = getAlbums();

        response
                .then()
                .statusCode(200);

        final List<AlbumDTO> albumsList = response
                .getBody()
                .jsonPath()
                .getList("", AlbumDTO.class);

        assertTrue("Non-empty list of albums is expected", albumsList.size() > 0);

    }

    @Test
    public void getAlbmById() {
        Response response = getAlbumById(1);

        response
                .then()
                .statusCode(200);

        AlbumDTO receivedAlbum = response
                .getBody()
                .jsonPath()
                .getObject("", AlbumDTO.class);

        assertEquals("Album with id 1 is expected", 1, receivedAlbum.getId());
    }

    @Test
    public void postAnAlbum() {
        AlbumDTO newAlbum = createNewAlbum();
        Response response = postAlbum(newAlbum);

        response
                .then()
                .statusCode(201);

        AlbumDTO receivedAlbum = response
                .getBody()
                .jsonPath()
                .getObject("", AlbumDTO.class);

        assertEquals("Titles of albums are expected to match", newAlbum.getTitle(), receivedAlbum.getTitle());
        assertEquals("UserIds of albums are expected to match", newAlbum.getUserId(), receivedAlbum.getUserId());
    }
}
