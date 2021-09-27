package get_http_request_method;

import base_urls.JsonPlaceHolderApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class Get08 extends JsonPlaceHolderApiBaseUrl {

        /*
        The biggest challenge in API testing is data type
        1) Java uses Objects and Primitives as data type, API uses XML, Json, etc.
            Java and API ara using different data type, but they should communicate with eachother
            Communication is impossible with different data types.

            We have 2 options:;
            i) Convert Json to Java Object ==> De-Serialization
            ii) Convert Java Object to Json ==> Serialization

            for Serialization and De Serialization, we have 2 option
            a) GSON --> Google Created it
            b) Object Mapper --> More popular
         */

        /*
        Test Case ID: get_http_request_method.Get08
            Given
             https://jsonplaceholder.typicode.com/todos/2
            When
                I send GET Request to the URL
            Then
                Status code is 200
                And "completed" is false
                And "userId" is 1
                And "title" is "quis ut nam facilis et officia qui"
                And header "Via" is "1.1 vegur"
                And header "Server" is "cloudflare"

               data format
                {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
                }
         */
    @Test
    public void get08(){
        //1.Step: Set the url
        spec.pathParams("first", "todos", "second", 2);

        //2.Step: Set the excepted data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",2);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        expectedData.put("Status Code", 200);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server", "cloudflare");
        System.out.println(expectedData);

        //3.Step: Send the request and get the response

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        //By using GSON we are able to convert Json Data which is coming from API to Java Object
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        //4.Step: Do assertions

        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("id"),actualData.get("id"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("Status Code"),response.getStatusCode());
        assertEquals(expectedData.get("Via"), response.getHeader("Via"));
        assertEquals(expectedData.get("Server"), response.getHeader("Server"));
    }
}
