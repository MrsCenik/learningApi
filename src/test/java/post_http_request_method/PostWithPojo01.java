package post_http_request_method;

import base_urls.JsonPlaceHolderApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class PostWithPojo01 extends JsonPlaceHolderApiBaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 66,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */
@Test
    public void postWithPojo01(){
    //1.Step: Set the Url
    spec.pathParam("first", "todos");
    //2.Step: Set the request body
    JsonPlaceHolderPojo requestBody = new JsonPlaceHolderPojo(55, "Tidy your room", false );

    //3.Step: Send the request get the response
    Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
    response.prettyPrint();

    //4.Step: Do assertions
        //1.Way:
    response.then().statusCode(201).body("userId", equalTo(requestBody.getUserId()),
                                    "title", equalTo(requestBody.getTitle()),
                                    "completed", equalTo(requestBody.getCompleted()));

        //2.Way: De-serialization
    JsonPlaceHolderPojo actualData= response.as(JsonPlaceHolderPojo.class);
    assertEquals(requestBody.getUserId(), actualData.getUserId());
    assertEquals(requestBody.getCompleted(), actualData.getCompleted());
    assertEquals(requestBody.getTitle(),actualData.getTitle());

}

}
