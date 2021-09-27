package get_http_request_method;

import base_urls.HerOkuAppApibaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppApibaseUrl {

    /*
        Given
           https://restful-booker.herokuapp.com/booking/1
        When
            I send GET Request to the url
        Then
            Response body should be like that;

                {
                    "firstname": "Eric",
                    "lastname": "Smith",
                    "totalprice": 555,
                    "depositpaid": false,
                    "bookingdates": {
                        "checkin": "2016-09-09",
                        "checkout": "2017-09-21"
                     }
     */

    @Test
    public void get09(){

        //1.Step: Set the url
        spec.pathParams("first", "booking", "second", 1);
        //2.Step: Set the expected Data
        Map<String, String> expectedBookingDates = new HashMap<>();
        expectedBookingDates.put("checkin", "2020-08-18");
        expectedBookingDates.put("checkout", "2021-05-03");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Mark");
        expectedData.put("lastname", "Jones");
        expectedData.put("totalprice", 398);
        expectedData.put("depositpaid", false);
        expectedData.put("bookingdates", expectedBookingDates);
        expectedData.put("additionalneeds", null);

        System.out.println(expectedData);
        //3.Step: Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        //4.Step: Do assertion
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
        assertEquals(expectedBookingDates.get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(expectedBookingDates.get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));

    }
}
