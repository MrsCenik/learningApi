package get_http_request_method;

import base_urls.JsonPlaceHolderApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get03 extends JsonPlaceHolderApiBaseUrl {

    /*
       Test Case ID: get03
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET request to the URL
        Then
            HTTP Status code should be 200
        And
            Response format shopuld be "application/json"
        And
            "title" is "et itaque necessitatibus maxime molestiae qui quas velit"
        And
            "completed" is false
        And
            "userId" is 2
     */
    @Test
    public void get03(){
        //1.Step: seet the url
        spec.pathParams("first", "todos", "second", 23);
        //2.Step: Set the expected Data
        //3.Step: Set he request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.Step:
        //Instead of "application/json" you can type ContentType.JSON

        //1.way
//        response.
//                then().
//                assertThat().
//                statusCode(200).
//                contentType(ContentType.JSON).
//                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
//                body("completed", equalTo(false)).
//                body("userId", equalTo(2));

        //2.Way
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false),
                        "userId", equalTo(2));

    }


}
