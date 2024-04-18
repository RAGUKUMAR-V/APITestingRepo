import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.reporters.XMLConstants;

public class SchemaValidation {


    @Test
    public void validation(){
        JsonPath js=new JsonPath(Payload.shop());
       int count= js.getInt("courses.size()");

       int total=0;

       for (int i=0;i<count;i++){

           int price= js.getInt("courses["+i+"].price");
           int copy= js.getInt("courses["+i+"].copies");
           int p=price*copy;
           total=total+p;

       }
     int purchaseAmount=  js.getInt("dashboard.purchaseAmount");

        Assert.assertEquals(total,purchaseAmount);

        System.out.println("total price  " + total + "  is equal to purchaseAmount "+purchaseAmount);




    }

}
