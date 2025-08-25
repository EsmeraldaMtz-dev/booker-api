package base;

import helpers.models.Login;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;

public class BaseApiTest {


    public String token;
    public LocalDateTime tokenExpiration;
    public RequestSpecBuilder requestSpecBuilder;

    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = "https://automationintesting.online/api";
        requestSpecBuilder= new RequestSpecBuilder();
        requestSpecBuilder.addHeader("x-api-key", "reqres-free-v1");
        requestSpecBuilder.addHeader("Content-Type", "application/json");

        RestAssured.requestSpecification = requestSpecBuilder.build();
        this.generateToken();
    }

    public void generateToken (){
        Login loginPO = new Login("admin", "password");
        Response response = given()
                .body(loginPO)
                .when()
                .log().all() //Request Info
                .post("/auth/login")
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response(); //Response Info

        JsonPath responseBody = new JsonPath(response.asString());
        token = responseBody.getString("token");

        tokenExpiration = LocalDateTime.now().plusHours(1);

    }
    @BeforeMethod
    public void settingUpAuthConfig() {
        if(LocalDateTime.now().isAfter(tokenExpiration.minusMinutes(5))) generateToken();
        requestSpecBuilder.addHeader("Authorization", "Bearer " + token);
        RestAssured.requestSpecification =requestSpecBuilder.build();
    }
}
