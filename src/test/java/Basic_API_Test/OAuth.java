package Basic_API_Test;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class OAuth {


    @Test
    public void get_Token(){


        String response=given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type","client_credentials")
                .when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();


        JsonPath js=new JsonPath(response);
        String token=js.getString("access_token");

      String response2=  given().queryParam("access_token",token)
                .when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();


      System.out.println(response2);

    }


}
