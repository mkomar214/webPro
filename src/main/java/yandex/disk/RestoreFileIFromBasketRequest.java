package yandex.disk;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestoreFileIFromBasketRequest {
    public static void restoreFileFromBusket(String fileName) {
        RestAssured.given()
                .log().all()
                .baseUri("https://cloud-api.yandex.net:443")
                .basePath("/v1/disk/trash/resources/restore")
                .queryParam("path", fileName)
                .header("Authorization","OAuth AQAAAAAzPuCGAADLWywjbAZlS0BLgdiuGx5RDF0")
                .contentType(ContentType.JSON)
                .put()
                .then()
                .statusCode(201);
    }
}
