package tests;

import base.BaseApiTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class RoomManagementTests extends BaseApiTest {
    @Test
    public void getListOfRooms() throws IOException {
        given()
                .when()
                .get("room")
                .then()
                .statusCode(200)
                .log().all()
                .body("rooms", hasSize(3));
    }

    @Test
    public void getListOfRoomsByAvailability() throws IOException {
        given()
                .queryParam("checkin", "2026-03-01")
                .queryParam("checkout", "2026-03-05")
                .when()
                .get("room")
                .then()
                .statusCode(200)
                .log().all()
                .body("rooms", hasSize(2))
        ;
    }

    @Test
    public void createNewRoom() throws IOException {
        String requestBody = Files.readString(Paths.get("src/test/resources/newRoom.json"));
        given()
                .body(requestBody)
                .when()
                .post("room")
                .then()
                .log().all()
                .statusCode(201);
    }
}
