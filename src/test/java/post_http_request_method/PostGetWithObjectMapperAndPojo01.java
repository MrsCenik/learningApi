package post_http_request_method;
import static org.junit.Assert.assertEquals;
import base_urls.HerOkuAppApibaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerOkuAppPostResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class PostGetWithObjectMapperAndPojo01 extends HerOkuAppApibaseUrl {
                 /*
        Given
	        https://restful-booker.herokuapp.com/booking
                                            {
                                            "firstname": "Selim",
                                            "lastname": "Ak",
                                            "totalprice": 11111,
                                            "depositpaid": true,
                                            "bookingdates": {
                                                "checkin": "2021-09-09",
                                                "checkout": "2021-09-21"

                                                 },
                                                 "additionalneeds":"Breakfast"
                                            }
        When
	 		I send POST Request to the Url
	   And
	        I send GET Request to the Url
	 	Then
	 		Status code is 200
	 	And
	 	    GET response body should be like
                                            {
                                                "firstname": "Selim",
                                                "lastname": "Ak",
                                                "totalprice": 11111,
                                                "depositpaid": true,
                                                "bookingdates": {
                                                    "checkin": "2020-09-09",
                                                    "checkout": "2020-09-21"
                                                },
                                                 "additionalneeds":"Breakfast"
                                            }
     */

    @Test
    public void postGetWithObjectMapperAndPojo01(){
        //1.Ste: set the URL
        spec.pathParam("first", "booking");

        //2.Step: Set the request body
        BookingDatesPojo bookingDates = new BookingDatesPojo("2020-09-09","2020-09-21");
        BookingPojo requestBody = new BookingPojo("Selim","Ak",11111,true, bookingDates,"Breakfast");
        System.out.println(requestBody);
        //3.Step: Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();

        HerOkuAppPostResponseBodyPojo postResponseBodyInPojo = JsonUtil.convertJsonToJava(response.asString(), HerOkuAppPostResponseBodyPojo.class);
        System.out.println(postResponseBodyInPojo);

        Integer bookingid= postResponseBodyInPojo.getBookingid();

        //**GET REQUEST**

        //1.Step: Set the URL for GET request
        spec.pathParams("first","booking","second",bookingid);

        //2.Step: Set the expected data
        //No need to create expected data because the data which is send in the POST request will be expected data

        //3.Step: I will send the request and get the response
        Response response2 = given().spec(spec).when().get("/{first}/{second}");
        response2.prettyPrint();

              //I CONVERT GET RESPONSE BODY TO JAVA OBJECT BY USING OBJECT MAPPER
        BookingPojo getResponseBodyPojo = JsonUtil.convertJsonToJava(response2.asString(), BookingPojo.class);

       //4.Step: Do assertion

        assertEquals(requestBody.getFirstname(),getResponseBodyPojo.getFirstname());
        assertEquals(requestBody.getLastname(),getResponseBodyPojo.getLastname());
        assertEquals(requestBody.getTotalprice(), getResponseBodyPojo.getTotalprice());
        assertEquals(requestBody.getDepositpaid(), getResponseBodyPojo.getDepositpaid());
        assertEquals(requestBody.getBookingdates().getCheckout(), getResponseBodyPojo.getBookingdates().getCheckout());
        assertEquals(requestBody.getBookingdates().getCheckout(), getResponseBodyPojo.getBookingdates().getCheckout());
        assertEquals(requestBody.getAdditionalneeds(),getResponseBodyPojo.getAdditionalneeds());
    }
}
