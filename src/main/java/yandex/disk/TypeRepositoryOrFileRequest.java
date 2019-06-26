package yandex.disk;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class TypeRepositoryOrFileRequest {
    public static String typeFile(String repository) {
        String fileType = RestAssured.given()
                .log().all()
                .baseUri("https://cloud-api.yandex.net:443")
                .basePath("/v1/disk/resources")
                .queryParam("path", repository)
                .queryParam("fields", "type")
                .header("Authorization", "OAuth AQAAAAAzPuCGAADLWywjbAZlS0BLgdiuGx5RDF0")
                .contentType(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getString("type");
        return fileType;
    }
}

