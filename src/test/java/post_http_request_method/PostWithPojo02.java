package post_http_request_method;

import base_urls.HerOkuAppApibaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerOkuAppPostResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PostWithPojo02 extends HerOkuAppApibaseUrl {
     /*
    Given
        https://restful-booker.herokuapp.com/booking/
         Status code is 200
        And response body is like
        {
        "firstname": "Hatice",
        "lastname": "Cenik",
        "totalprice": 677,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2021-09-21",
            "checkout": "2021-12-21"
         },
         "additionalneeds": "Breakfast with white tea, newspaper, Drogan juice"
      }
    When
        I send POST Request to the URL
        -I send Get requesr to the URL to get the newly created data- from daytime class
    Then
        Status code is 200
    And response body is like
        {
        "firstname": "Mary",
        "lastname": "Smith",
        "totalprice": 647,
        "depositpaid": false,
        "bookingdates": {
            "checkin": "2016-02-05",
            "checkout": "2021-01-16"
         }
         "additionalneeds": "Breakfast"
      }
     */
    @Test
    public void postWithPojo02(){
        //set Url
        spec.pathParam("first", "booking");
        //set Request body
        BookingDatesPojo bookinDates = new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo requetBody = new BookingPojo("Hatice", "Cenik" , 677, true, bookinDates,"Breakfast with white tea, newspaper, Drogan juice" );

        //sent the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requetBody).when().post("/{first}");
        response.prettyPrint();


        HerOkuAppPostResponseBodyPojo actualData = response.as(HerOkuAppPostResponseBodyPojo.class);
        System.out.println(actualData);

        //do assertion
        assertEquals(200, response.getStatusCode());
        assertEquals(requetBody.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(requetBody.getLastname(),actualData.getBooking().getLastname());
        assertEquals(requetBody.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(requetBody.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(requetBody.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());
        assertEquals(requetBody.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(requetBody.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
    }
    @Test
    public void postWithPojo03(){
        //set Url
        spec.pathParam("first", "booking");
        //set Request body
        BookingDatesPojo bookinDates = new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo requetBody = new BookingPojo("Hatice", "Cenik" , 677, true, bookinDates,"Breakfast with white tea, newspaper, Drogan juice" );

        //sent the request and get the response
        Response response1 = given().spec(spec).contentType(ContentType.JSON).body(requetBody).when().post("/{first}");
        response1.prettyPrint();

        //Note: After creating new data in DB, ypou will need "bookingid" to be able to use GET Method
        JsonPath json = response1.jsonPath();
        Integer bookingId = json.getInt("bookingid");
//        System.out.println(bookingId);
        // Set the url for GET Request
        spec.pathParams("first","booking", "second",bookingId);

        //Send the request and get the response
        Response response2 = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
        response2.prettyPrint();

//        //do assertion
        BookingPojo actualData = response2.as(BookingPojo.class);
        assertEquals(200, response2.getStatusCode());
        assertEquals(requetBody.getFirstname(),actualData.getFirstname());
        assertEquals(requetBody.getLastname(),actualData.getLastname());
        assertEquals(requetBody.getTotalprice(),actualData.getTotalprice());
        assertEquals(requetBody.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(requetBody.getAdditionalneeds(),actualData.getAdditionalneeds());
        assertEquals(requetBody.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(requetBody.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
    }
}
