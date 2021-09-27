package get_method;

import base_urls.JsonPlaceHolderApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Comments;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItems;

public class Get03 extends JsonPlaceHolderApiBaseUrl {
    /*
         When
             I send a GET Request to the URL https://jsonplaceholder.typicode.com/comments
         Then
             HTTP Status Code should be 200
         And
             Content Type should be JSON
         And
             User can see following emails in the system
             Zola@lizzie.com, Dolly@mandy.co.uk and Davion@eldora.net
     */

    @Test
    public void get03(){
        spec.pathParam("first", "comments");
        Response response = given().spec(spec).when().get("/{first}");
       response.prettyPrint();
       response.then().assertThat().statusCode(200).contentType(ContentType.JSON).body("email", hasItems("Zola@lizzie.com", "Dolly@mandy.co.uk",  "Davion@eldora.net"));

    }
}
