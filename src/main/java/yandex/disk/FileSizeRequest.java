package yandex.disk;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class FileSizeRequest {
    public static int fileSizeRequest (String repository, String fileName) {
        Integer fileSize = RestAssured.given()
                .log().all()
                .baseUri("https://cloud-api.yandex.net:443")
                .basePath("/v1/disk/resources")
                .queryParam("path", "/" + repository + "/" + fileName)
                .queryParam("fields", "size")
                .header("Authorization", "OAuth AQAAAAAzPuCGAADLWywjbAZlS0BLgdiuGx5RDF0")
                .contentType(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getInt("size");
        return fileSize;
    }
}
