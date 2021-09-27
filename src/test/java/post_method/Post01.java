package post_method;
import static org.testng.AssertJUnit.assertEquals;
import base_urls.JsonPlaceHolderApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class Post01 extends JsonPlaceHolderApiBaseUrl {
      /*
        Given    https://jsonplaceholder.typicode.com/comments
            {
                "name": "This class has smart people",
                "postId": 82,
                "id": 501,
                "body": "Congratulations Everyone",
                "email": "techproedstudents@gmail.com"
            }
            When I send Post Request to the URL
            Then the status code should be 201
            Response should be like
                 {
                "name": "This class has smart people",
                "postId": 82,
                "id": 501,
                "body": "Congratulations Everyone",
                "email": "techproedstudents@gmail.com"
            }
         */

    @Test
    public void post01(){
        spec.pathParam("first","comments");
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("name","This class has smart people");
        expectedData.put("postId",82);
        expectedData.put("id",501);
        expectedData.put("body","Congratulations Everyone");
        expectedData.put("email","techproedstudents@gmail.com");
        Response response  = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("expectedData => "+expectedData);
        System.out.println("actualData => "+actualData);
        assertEquals(expectedData,actualData);
        assertEquals(expectedData.get("name"),actualData.get("name"));

    }
}
