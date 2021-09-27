package get_method;

import base_urls.DummyApiBaseUrl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class DummyGet01 extends DummyApiBaseUrl {

  /*
        Test Case ID: test01
         When
             I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employees
         Then
             HTTP Status Code should be 200
         And
             Content Type should be JSON
         And
             Status Line should be HTTP/1.1 200 OK
         And
             There are 24 employees in total
     */
    @Test
    public void test01(){

        spec.pathParams("first","api","second", "v1","third","employees");
        Response response= given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200).statusLine("HTTP/1.1 200 OK").body("data.id",hasSize(24));
    }





}
