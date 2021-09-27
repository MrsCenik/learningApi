package get_http_request_method;

import base_urls.HerOkuAppApibaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetWithObjectMapper02 extends HerOkuAppApibaseUrl {
    /*
            Given
                 https://restful-booker.herokuapp.com/booking/2
            When
                I send GET Request to the URL
            Then
                Status code is 200
                And response body is like
                {
                "firstname": "Mark",
                "lastname": "Ericsson",
                "totalprice": 726,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2015-08-07",
                    "checkout": "2020-10-25"
                 }
              }
     */
    @Test
    public void getWithObjectMapper02(){
        //1.Step
        spec.pathParams("first", "booking", "second",2);
        //2.Step
            //1.Way
        String expectedData = "{\n" +
                "\"firstname\": \"Eric\",\n" +
                "\"lastname\": \"Smith\",\n" +
                "\"totalprice\": 638,\n" +
                "\"depositpaid\": false,\n" +
                "\"bookingdates\": {\n" +
                "\"checkin\": \"2017-04-20\",\n" +
                "\"checkout\": \"2020-10-25\"\n" +
                "}\n" +
                "}";
        HashMap<String, Object> expectedDataMap = JsonUtil.convertJsonToJava(expectedData, HashMap.class);
            //2.Way: Create a set up method to convert Json data to String dynamically =>Homework
        //3.Step: Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        HashMap<String, Object> actualDataMap = JsonUtil.convertJsonToJava(response.asString(),HashMap.class);

        //4.Step: Do assertions
        assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"), ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"), ((Map)actualDataMap.get("bookingdates")).get("checkout"));
    }
}
