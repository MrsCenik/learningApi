package get_method;

import base_urls.JsonPlaceHolderApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Comments;
import pojos.JsonPlaceHolderPojo;

import java.util.List;

import static io.restassured.RestAssured.*;

public class Get02 extends JsonPlaceHolderApiBaseUrl {

        /*
             When
                 I send a GET Request to the URL https://jsonplaceholder.typicode.com/comments
             Then
                 HTTP Status Code should be 200
            And
                 Search all ids that are less than 30
                 the number of ids less than 30 should be 29
         */
    @Test
    public void get02(){
        spec.pathParam("first","comments");
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();
        JsonPath json = response.jsonPath();
        List<Integer> actualIds = json.getList("findAll{it.id<30}.id");
        System.out.println("Actual Id List " + actualIds);

        Assert.assertTrue("The expected data does not Match!", actualIds.size()==29);

    }

        /*
             When
                 I send a GET Request to the URL https://jsonplaceholder.typicode.com/comments
             Then
                 HTTP Status Code should be 200
            And
                 Search all and make sure there are 500 total records
                 Use POJO and deserialize them to Java
         */
    @Test
    public void test2(){
        spec.pathParam("first", "comments");

        Response response = given().spec(spec).when().get("/{first}");

        Comments[] comments = response.as(Comments[].class);

        for(int i = 0; i<comments.length; i++){
            System.out.println(i+" Name => "+ comments[i].getName());
        }
        Assert.assertTrue("The expected data does not match!", comments.length == 500);
    }
}
