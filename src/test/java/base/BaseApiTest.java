package base;

import helpers.models.Login;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;

public class BaseApiTest {


    public static String token;
    public static LocalDateTime tokenExpiration;

    @BeforeSuite(alwaysRun = true)
    public void setup() {
        RestAssured.baseURI = "https://automationintesting.online/api";
        this.generateToken();
    }

    public void generateToken (){
        Login loginPO = new Login("admin", "password");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(loginPO)
                .when()
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

    @BeforeClass(alwaysRun = true)
    public void settingUpAuthConfig() {
        if(LocalDateTime.now().isAfter(tokenExpiration.minusMinutes(5))){
            generateToken();
        }

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addCookie("token", token)
                .build();
    }
}
