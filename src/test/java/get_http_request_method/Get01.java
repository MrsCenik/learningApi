package get_http_request_method;

import io.restassured.response.Response;
import io.swagger.v3.oas.models.servers.Server;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {
    /*
    We use Gherkin Language to create test cases
    Gherkin Language has 4 main keywords: Given, When, Then, And
    Given: It is used for pre-requisites.
    When: It is used for action
    Then: it is used for outputs
    And: It is used for multiple Given, When, Then
     */

    /*
   Test Case
       Test Case Id: get01
            Given
                https://restful-booker.herokuapp.com/booking/3
            When
                User send GET Request to the URL
            Then
                Http Status Code should be 200
            And
                Content type should be application/Json
            And
                Status line should be like HTTP/1.1 200 OK
     */
    //For every test cases we should create new test method. We uses test case id for the method name
    @Test
    public void get01(){
        //1.Step: Setting url
        String url ="https://restful-booker.herokuapp.com/booking/3";

        //2.Step: Set the expected data

        //3.Step: Send Request and get response from API
        Response response= given().when().get(url);
        response.prettyPrint();

        //4.Step: Do assertion
        /*
             If you have multiple errors in your functionality, Java will stop execution after the first error.
             Because of that you can not see any error message for the second, third etc errors.

             If the execution stopped after the first error, it is "Hard Assertion"
             There is "Soft Assertion" as well, it does not stop execution after errors. It executes all codes then gives error messages for all errors.

         */
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        //How to print status code, statuse line, headers, etc on the console
        System.out.println("Statuse code is "+response.getStatusCode());
        System.out.println("Statuse line is "+response.getStatusLine());
        System.out.println("Content type is "+ response.getContentType());
        System.out.println("Headers are: \n"+ response.getHeaders());
        System.out.println("Server header is "+ response.getHeader("Server"));

    }
}
