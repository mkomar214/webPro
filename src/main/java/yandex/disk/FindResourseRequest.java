package yandex.disk;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class FindResourseRequest {

        public static void findResourse(String repository, int statusCode) {
                    RestAssured.given().log().all()
                    .baseUri("https://cloud-api.yandex.net:443")
                    .basePath("/v1/disk/resources")
                    .param("path", repository)
                    .header("Authorization", "OAuth AQAAAAAzPuCGAADLWywjbAZlS0BLgdiuGx5RDF0")
                    .contentType(ContentType.JSON)
                    .when()
                    .get()
                    .then().statusCode(statusCode);
        }
}
