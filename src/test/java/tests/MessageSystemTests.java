package tests;

import base.BaseApiTest;
import helpers.models.Message;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MessageSystemTests extends BaseApiTest {
    @Test
    public void sendNewMessage() {
        Message messageModel = new Message("John Doe", "john.doe@example.com", "345234234566", "Inquiry about booking", "I would like to inquire about room availability");
        given()
                .body(messageModel)
                .when()
                .post("message")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test
    public void getMessageById() {
        given()
                .pathParam("messageId", 4)
                .when()
                .get("message/{messageId}")
                .then()
                .statusCode(200)
                //.body("")
                .log().all();
    }

    @Test
    public void getAllMessages() {
        given()
                .when()
                .get("message")
                .then()
                .statusCode(200)
                .log().all();
    }

}
