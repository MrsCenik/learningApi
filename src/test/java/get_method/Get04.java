package get_method;

import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class Get04 extends DummyApiBaseUrl {
    /*
           When
             I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employees
             Make sure these people exist in the system
      {
            "id": 23,
            "employee_name": "Caesar Vance",
            "employee_salary": 106450,
            "employee_age": 21,
            "profile_image": ""
        },
        {
            "id": 24,
            "employee_name": "Doris Wilder",
            "employee_salary": 85600,
            "employee_age": 23,
            "profile_image": ""
        }
     */

    @Test
    public void get04(){
        spec.pathParams("first", "api","second","v1","third","employees");

        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");

        response.then().assertThat().statusCode(200).body("data.id",hasItems(23, 24));
    }


}
