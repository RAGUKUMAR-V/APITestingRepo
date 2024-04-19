import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LibraryApi {

    @Test(dataProvider = "bookdata")
    public void LibraryJson(String isbn,String aisle){
        RestAssured.baseURI="http://216.10.245.166";

        given().log().all().header("Content-Type", "application/json")
                .body(Payload.books(isbn,aisle))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200);


    }
@DataProvider(name="bookdata")
public Object[][] bookdata(){

       return new Object[][]{{"abc","123"},
               {"def","456"},{"ghi","789"},{"jkl","321"},{"mno","654"}};
}



}
