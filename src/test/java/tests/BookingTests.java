package tests;

import base.BaseApiTest;
import helpers.models.Booking;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class BookingTests extends BaseApiTest {

    @Test
    public void createBooking() throws IOException {
        //Booking bookingModel = new Booking();
        String requestBody = Files.readString(Paths.get("src/test/resources/bookingTest.json"));
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
