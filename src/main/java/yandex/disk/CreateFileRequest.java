package yandex.disk;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateFileRequest {
    public static void createFile(String repository, String fileName){
        RestAssured.given()
                .log().all()
                .baseUri("https://cloud-api.yandex.net")
                .basePath("/v1/disk/resources/copy")
                .queryParam("from", fileName)
                .queryParam("path", "/" + repository +"/" +fileName)
                .header("Authorization","OAuth AQAAAAAzPuCGAADLWywjbAZlS0BLgdiuGx5RDF0")
                .contentType(ContentType.JSON)
                .post()
                .then()
                .statusCode(201);
    }

}
