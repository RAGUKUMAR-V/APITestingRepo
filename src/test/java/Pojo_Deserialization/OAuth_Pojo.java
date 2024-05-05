package Pojo_Deserialization;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuth_Pojo {


    @Test
    public void get_Token(){


        String response=given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type","client_credentials")
                .when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();


        JsonPath js=new JsonPath(response);
        String token=js.getString("access_token");

      GetCourse gc=  given().queryParam("access_token",token)
                .when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);

       String price=  gc.getCourses().getApi().get(1).getPrice();


       System.out.println(price);

        List<WebAutomation>list=gc.getCourses().getWebAutomation();
        for(WebAutomation lis:list){
           if( lis.getCourseTitle().equalsIgnoreCase("Cypress")){

               System.out.println( lis.getPrice());
           }
        }

        List<WebAutomation> Title_list=gc.getCourses().getWebAutomation();
        for(WebAutomation li:Title_list){

            System.out.println(  li.getCourseTitle());
        }





    }


}
