package tests;

import base.BaseApiTest;
import helpers.models.Booking;
import org.hamcrest.Matcher;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class BookingTests extends BaseApiTest {

    @Test(dependsOnMethods = "tests.RoomManagementTests.createNewRoom")
    public void createBooking(ITestContext context) throws IOException {
        Integer roomId = (Integer) context.getAttribute("createdRoomId");
        Assert.assertNotNull(roomId, "Room Id not found in context");
        byte[] encoded = Files.readAllBytes(Paths.get("src/test/resources/bookingTest.json"));
        String requestBodyTemplate = new String(encoded, StandardCharsets.UTF_8);
        String requestBody = requestBodyTemplate.replace("{{roomid}}", String.valueOf(roomId));

        given()
                .body(requestBody)
                .when()
                .post("booking")
                .then()
                .log().all()
                .statusCode(200)
                .log().all()
                .body("bookingid", notNullValue());

                //.body("booking.lastname", equalTo(bookingModel.getLastName()))
                //.body("depositpaid", equalTo(true));
    }

    @Test
    public void deleteBooking(){
        given()
                .pathParams("bookingid",1)
                .when()
                .put("booking/{bookingId}")
                .then()
                .statusCode(200);
    }

    @Test
    public void getBookingById(){
        given()
                .pathParam("bookingId", 19320964)
                .when()
                .get("booking/{bookingId}")
                .then()
                .statusCode(200);
    }
}
