package com.jsonplaceholder.services.albums;

import io.restassured.response.Response;
import com.jsonplaceholder.models.AlbumDTO;

import static com.jsonplaceholder.services.albums.AlbumsEndpoints.ALBUMS;
import static com.jsonplaceholder.services.albums.AlbumsEndpoints.ALBUMS_BY_ID;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static com.jsonplaceholder.utils.JsonPOJOConverter.serializeToJson;

public class AlbumsRequests {
    public static Response getAlbums() {
        return given()
                .contentType(JSON)
                .get(ALBUMS);
    }

    public static Response getAlbumById(int albumId) {
        return given()
                .contentType(JSON)
                .get(ALBUMS_BY_ID, albumId);
    }

    public static Response postAlbum(AlbumDTO album) {
        return given()
                .contentType(JSON)
                .body(serializeToJson(album))
                .post(ALBUMS);
    }
}
