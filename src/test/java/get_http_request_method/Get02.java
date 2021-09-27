package get_http_request_method;

import base_urls.HerOkuAppApibaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get02 extends HerOkuAppApibaseUrl {
    /*
    Test Case ID: get02
    Given
        https://restful-booker.herokuapp.com/booking/1001
    When
        Send a GET Request to the URL
    Then
        HTTP Status should be 404
    And
        Status Line should be HTTP/1.1 404 Not Found
    And
        Response body contains “Not Found”
    And
        Response body does not contain “TechProEd”
    And
        Server is "Cowboy"
     */
    @Test
    public void get02(){
        //1.Step: Set the url
        spec.pathParams("first", "booking","second", 1001);
        //2.Step: Set the excepted data
        //3.Step: Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        //4.Step: Do assertions
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        //assertTrue(true)==> gives you passed    assertTrue(false)==> gives you failed
//        assertTrue("Body is not containing expected text",response.asString().contains("Not Found?"));//java.lang.AssertionError: Body is not containing expected text
        assertTrue(response.asString().contains("Not Found")); //Not Found (it is true)

        //assertFalse(false)==>  gives you passed    assertFalse(true)==> gives you failed
        assertFalse(response.asString().contains("TechProEd")); // Not Found
//        assertFalse("Even the text does not exist in response body, it says exist",response.asString().contains("Not Found")); //java.lang.AssertionError: Even the text does not exist in response body, it says exist

        //assertEquals(p1, p2) if p1 and p2 are same then you will get passed, otherwise you will get failed
        assertEquals("Cowboy", response.getHeader("Server")); //passed
//        assertEquals("Server name is not matching","CowboyX", response.getHeader("Server"));//org.junit.ComparisonFailure: Server name is not matching

    }
}
