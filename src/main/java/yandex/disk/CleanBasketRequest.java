package yandex.disk;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CleanBasketRequest {
    public static void BasketClean() {
        RestAssured.given()
                .log().all()
                .baseUri("https://cloud-api.yandex.net:443")
                .basePath("/v1/disk/trash/resources")
                .header("Authorization", "OAuth AQAAAAAzPuCGAADLWywjbAZlS0BLgdiuGx5RDF0")
                .contentType(ContentType.JSON)
                .delete()
                .then()
                .statusCode(202);
    }

}
