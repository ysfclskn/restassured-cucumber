package booking.steps;

import booking.models.Auth;
import booking.models.BookingResponse;
import booking.services.BookingService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class bookingSteps {

    BookingService bookingService = new BookingService();
    BookingResponse bookingResponse = new BookingResponse();

    String token;

    @Given("create token")
    public void createToken() {
        token = bookingService.generateToken();
    }

    @Given("create new booking")
    public void createNewBooking() {
        bookingResponse = bookingService.createBooking();
    }


    @Then("delete new booking")
    public void deleteNewBooking() {
        bookingService.deleteBooking(token, bookingResponse.getBookingid());
    }
}
