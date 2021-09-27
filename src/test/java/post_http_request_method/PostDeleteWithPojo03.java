package post_http_request_method;

import base_urls.JsonPlaceHolderApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class PostDeleteWithPojo03 extends JsonPlaceHolderApiBaseUrl {
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
           I send Delete Request to the Url
       Then
           response body is like { }

    */

    @Test
    public void postDeleteWithPojo03() {

        //*****************CREATING NEW DATA*************************//
        //1.Step: Set the Url
        spec.pathParam("first", "todos");
        //2.Step: Set the request body
        JsonPlaceHolderPojo requestBody = new JsonPlaceHolderPojo(55, "Tidy your room", false);

        //3.Step: Send the request get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();
        //Response body has "id", I need "id" value to use "delete()"
        //Get the id of newly created data
        JsonPath json = response.jsonPath();
        Integer id = json.getInt("id");

        spec.pathParams("first", "todos", "second", id);
        //********************DELETE THE DATA**********************
        Response response2 = given().spec(spec).when().delete("/{first}/{second}");
        Map<String, Object> actualDataMap = response2.as(HashMap.class);

        assertTrue(actualDataMap.size()==0);
    }
}
