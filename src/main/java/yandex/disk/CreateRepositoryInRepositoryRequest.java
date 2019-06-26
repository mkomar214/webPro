package yandex.disk;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateRepositoryInRepositoryRequest {
    public static void createRepositoryInRepository(String repository_1, String repository_2) {
        RestAssured.given()
                .log().all()
                .baseUri("https://cloud-api.yandex.net:443")
                .basePath("/v1/disk/resources")
                .param("path","/" + repository_1 + "/" + repository_2)
                .header("Authorization","OAuth AQAAAAAzPuCGAADLWywjbAZlS0BLgdiuGx5RDF0")
                .contentType(ContentType.JSON)
                .when()
                .put()
                .then()
                .statusCode(201);
    }
}
