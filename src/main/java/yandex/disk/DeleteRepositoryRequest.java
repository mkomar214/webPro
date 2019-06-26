package yandex.disk;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteRepositoryRequest {

    public static void deleteRepository(String repository){
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .baseUri("https://cloud-api.yandex.net:443")
                    .basePath("/v1/disk/resources")
                    .param("path", repository)
                    .header("Authorization", "OAuth AQAAAAAzPuCGAADLWywjbAZlS0BLgdiuGx5RDF0")
                .when()
                    .delete()
                .then()
                    .statusCode(204);


    }
}
