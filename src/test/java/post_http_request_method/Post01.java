package post_http_request_method;

import base_urls.HerOkuAppApibaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends HerOkuAppApibaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            I send POST Request to the Url
            with the request body
            {
            `"firstname": "Selim",
            "lastname": "Ak",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2020-09-09",
                "checkout": "2020-09-21"
             }
          }`
        Then
            Status code is 200
            And response body should be like
            {
            "bookingid": 11,
            "booking": {
                "firstname": "Selim",
                "lastname": "Ak",
                "totalprice": 11111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2020-09-09",
                    "checkout": "2020-09-21"
                }
             }
          }
     */


    @Test
    public void post01(){
        //1.Step: Set the url

        spec.pathParam("first","booking");

        //2.Step: Set the expected Data
        Map<String, String> expectedBookingDates = new HashMap<>();
        expectedBookingDates.put("checkin", "2020-09-09");
        expectedBookingDates.put("checkout", "2020-09-21");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Selim");
        expectedData.put("lastname", "Ak");
        expectedData.put("totalprice", 11111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", expectedBookingDates);

        //3.Step: Sent the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //4.Step: Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(expectedData.get("firstname"), ((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"), ((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"), ((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositepaid"), ((Map)actualData.get("booking")).get("depositepaid"));

        assertEquals(expectedBookingDates.get("checkin"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(expectedBookingDates.get("checkout"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));
    }
}
