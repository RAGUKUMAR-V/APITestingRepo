import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class RequestValidation {
@Test
public void test() throws InterruptedException {
    RestAssured.baseURI="https://rahulshettyacademy.com";

    String response= given().log().all().queryParam("key","qaclick123")
            .header("Content-Type","application/json")
            .body("{\r\n"
                    + "  \"location\": {\r\n"
                    + "    \"lat\": -38.383494,\r\n"
                    + "    \"lng\": 33.427362\r\n"
                    + "  },\r\n"
                    + "  \"accuracy\": 50,\r\n"
                    + "  \"name\": \"Frontline house\",\r\n"
                    + "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
                    + "  \"address\": \"29, side layout, cohen 09\",\r\n"
                    + "  \"types\": [\r\n"
                    + "    \"shoe park\",\r\n"
                    + "    \"shop\"\r\n"
                    + "  ],\r\n"
                    + "  \"website\": \"http://google.com\",\r\n"
                    + "  \"language\": \"French-IN\"\r\n"
                    + "}\r\n"
                    + "").when().post("maps/api/place/add/json")
            .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();

    JsonPath js=new JsonPath(response);
    String placeid=js.getString("place_id");
    System.out.println(placeid);

    Thread.sleep(2000);

    //Update method
    String expectedaddress="70 Ragu, USA";

    given().log().all().queryParam("key","qaclick123")
            .header("Content-Type","application/json")
            .body("{\r\n"
                    + "\"place_id\":\""+placeid+"\",\r\n"
                    + "\"address\":\""+expectedaddress+"\",\r\n"
                    + "\"key\":\"qaclick123\"\r\n"
                    + "}\r\n"
                    + "").when().put("maps/api/place/add/json")
            .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));


    //Get method

    String getresponse=given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeid)
            .when().get("maps/api/place/add/json").
            then().log().all().assertThat().statusCode(200).extract().response().toString();

    JsonPath json=new JsonPath(getresponse);
    String address=json.getString("address");
    System.out.println(address);
}




}
