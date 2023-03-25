package booking.services;

import booking.models.Auth;
import booking.models.Booking;
import booking.models.BookingDates;
import booking.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingService extends BaseTest{

    public String generateToken(){
        Auth authBody = new Auth("admin","password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(authBody)
                .post("/auth");
        response
                .then()
                .statusCode(200);
        return response.jsonPath().getJsonObject("token");
    }

    public BookingResponse createBooking(){
        BookingDates bookingDates = new BookingDates("2023-03-18","2023-03-25");
        Booking bookingBody = new Booking("Yusuf","Caliskan",400,true,bookingDates,"Ücretsiz Wifi");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(bookingBody)
                .post("/booking");

        response
                .then()
                .statusCode(200);

        return response.as(BookingResponse.class);
    }


    public void deleteBooking(String token,int bookingId) {

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingId);

        response
                .then()
                .statusCode(201);
    }
}
