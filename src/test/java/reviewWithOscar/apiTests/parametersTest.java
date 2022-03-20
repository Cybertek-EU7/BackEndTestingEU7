package reviewWithOscar.apiTests;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;





import org.testng.annotations.Test;

public class parametersTest {
    /*
Given Accept application/json
And path zipcode is 22031
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
    post code is 22031
    country  is United States
    country abbreviation is US
    place name is Fairfax
    state is Virginia
    latitude is 38.8604
     */
    String zipUrl = "http://api.zippopotam.us";
    @Test
    public void pathTest(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when().get(zipUrl+"/us/{zip}");

       // response.prettyPrint();

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

    }
}
