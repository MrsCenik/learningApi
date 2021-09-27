package get_http_request_method;

import base_urls.JsonPlaceHolderApiBaseUrl;
import io.restassured.http.ContentType;
import org.junit.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class get04 extends JsonPlaceHolderApiBaseUrl {
    /*
    Test Case ID: get_http_request_method.get04

        Given
            https://jsonplaceholder.typicode.com/todos
        When
            I send a GET request to the Url
        And
            Accept type is “application/json”
        Then
            HTTP Status Code should be 200
        And
            Response format should be "application/json"
        And
            There should be 200 todos
        And
            "quis eius est sint explicabo" should be one of the todos
        And
            2, 7, and 9 should be among the userIds
     */

    @Test
    public void get04() {
        spec.pathParams("first", "todos");
        Response response = given().spec(spec).accept(ContentType.JSON).when().get("/{first}");
        response.prettyPrint();

        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id", hasSize(200)).
                body("title", hasItem("quis eius est sint explicabo")).
                body("userId", hasItems(2, 7, 9));
    }
}