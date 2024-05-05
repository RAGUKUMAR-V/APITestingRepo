package Pojo_Serialization;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Serialization_demo {
 @Test
    public void add_place_request(){
        Location loc=new Location();
        loc.setLng(33.427362);
        loc.setLat(-38.383494);

        List<String> list=new ArrayList<String>();
        list.add("ragu");
        list.add("kumar");


        Add_Place_Api api=new Add_Place_Api();
        api.setAccuracy(50);
        api.setAddress("29, side layout, cohen 09");
        api.setLanguage("French-IN");
        api.setLocation(loc);
        api.setTypes(list);
        api.setPhone_number("(+91) 983 893 3937");
        api.setName("Frontline house");
        api.setWebsite("http://google.com");

     RequestSpecification request=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();

     ResponseSpecification response= new ResponseSpecBuilder().
             expectStatusCode(200).build();

          given().spec(request).body(api).when().post("maps/api/place/add/json")
                .then().log().all().spec(response);

    }

}
