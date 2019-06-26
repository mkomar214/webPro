package yandex.disk;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteFileRequest {

        public static void deleteFile(String repository, String fileName){
            RestAssured.given()
                    .log().all()
                    .baseUri("https://cloud-api.yandex.net:443")
                    .basePath("/v1/disk/resources/upload")
                    .queryParam("path", "/" + repository +"/" +fileName)
                    .header("Authorization","OAuth AQAAAAAzPuCGAADLWywjbAZlS0BLgdiuGx5RDF0")
                    .contentType(ContentType.JSON)
                    .delete()
                    .then()
                    .statusCode(201);
        }

}

