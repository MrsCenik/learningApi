package get_method;

import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class DummyGet04 extends DummyApiBaseUrl {
    /*
    Test Case ID: test04
         When
             I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employees
         Then
             HTTP Status Code should be 200
         And
             Content Type should be JSON
         And
            This user exists in the system
            "status": "success",
            "data": {
                "id": 5,
                "employee_name": "Airi Satou",
                "employee_salary": 162700,
                "employee_age": 33,
                "profile_image": ""
            },
    "message": "Successfully! Record has been fetched."
        },
     */

    @Test
    public void test03() {

        spec.pathParams("first", "api", "second", "v1", "third", "employee", "final",5);
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}/{final}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("status",equalTo("success")).body("data.id", equalTo(5)).
                body("data.employee_name", equalTo("Airi Satou")).body("data.employee_salary", equalTo(162700)).
                body("data.employee_age", equalTo(33)).body("message",equalTo("Successfully! Record has been fetched."));
    }
}
