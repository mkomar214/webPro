import com.sun.org.glassfish.gmbal.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ApiTest {

@Test
@Description( "Проверить, что поле count равно 61")
    public void count61(){
    RestAssured
            .given().contentType(ContentType.JSON)
            .when().get("https://swapi.co/api/planets/")
            .then().body("count",Matchers.equalTo(61)).extract().response().prettyPrint();
    }

@Test
@Description("Проверить что status code 200")
    public void code200(){

    List<String> plannet = RestAssured
            .given().contentType(ContentType.JSON)
            .when().get("https://swapi.co/api/planets/")
            .then().extract().body().jsonPath().getList("results.residents[0]");
    String url = plannet.get(0);
    RestAssured
            .given().contentType(ContentType.JSON)
            .when().get(url)
            .then().statusCode(200);
    }
@Test
@Description("Названия фильмов")
    public void films(){
    List<String> titles = RestAssured
            .given().contentType(ContentType.JSON)
            .when().get("https://swapi.co/api/films/")
            .then().extract().body().jsonPath().getList("results.title");
}
@Test
@Description("Выполнить пост с любым логином")
    public void post(){
    Map<String,String> login = new HashMap<String, String>();
    login.put("login", "max");
    login.put("password", "123321");

    RestAssured
            .given().baseUri("https://openapi.appcenter.ms").basePath("/v0.1/user/invitations/orgs/arsenal/reject").header("X-API-Token","arsenal").contentType(ContentType.JSON).body(login)
            .when().post()
            .then().statusCode(204);

}
}
