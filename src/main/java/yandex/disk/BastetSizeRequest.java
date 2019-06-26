package yandex.disk;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BastetSizeRequest {

    public static int basketSizeRequest () {
        Integer basketSize = RestAssured.given()
                .log().all()
                .baseUri("https://cloud-api.yandex.net")
                .basePath("/v1/disk/")
                .queryParam("fields", "trash_size")
                .header("Authorization", "OAuth AQAAAAAzPuCGAADLWywjbAZlS0BLgdiuGx5RDF0")
                .contentType(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getInt("trash_size");
        return basketSize;
    }
}
