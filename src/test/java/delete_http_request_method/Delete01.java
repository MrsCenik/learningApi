package delete_http_request_method;

import base_urls.JsonPlaceHolderApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderApiBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos/198
    When
            I send DELETE Request to the Url
    Then
            Status code is 200
            And Response body is {}
     */
    @Test
    public void del01() {
        //1.Step: Set the URL
        spec.pathParams("first", "todos", "second", 198);

        //2.StepL Set the expected Data
        Map<String, Object> expectedMap = new HashMap<>();

        //3.Step: Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).delete("/{first}/{second}");
        response.prettyPrint();

        //4.Step: Do the assertion
              //GSON for converting data types from json to map
        Map<String, Object> actualMap = response.as(HashMap.class);
        System.out.println(actualMap);
        //Now we can do the assertion
        response.then().assertThat().statusCode(200);
        assertEquals(expectedMap, actualMap);// it did not work before converting because they were in different data type.Now it works.
//        assertTrue(actualMap.size() == 0); // An other way to get the same result
    }
}
