package yandex.disk;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateRepositoryRequest {

    public static void createRepository(String repository){
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .baseUri("https://cloud-api.yandex.net:443")
                    .basePath("/v1/disk/resources")
                    .param("path", repository)
                    .header("Authorization", "OAuth AQAAAAAzPuCGAADLWywjbAZlS0BLgdiuGx5RDF0")
                .when()
                    .put()
                .then()
                    .statusCode(201);


    }

}
