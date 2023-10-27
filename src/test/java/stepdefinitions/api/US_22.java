package stepdefinitions.api;

import hooks.HooksAPI;

import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.Assert;
import utilities.ConfigReader;

import java.util.Arrays;

import static hooks.HooksAPI.*;
import static io.restassured.RestAssured.given;

public class US_22 {

    @Given("Gerekli {string} path parametreleri set edilir.")
    public void gerekli_path_parametreleri_set_edilir(String pathParameters) {

        // https://qa.agileswiftcargo.com/api/pickuprequest/all

        // spec.pathParams("pp","api","pp1","pickuprequest","pp2","all");

        String[] paths = pathParameters.split("/"); // ["api","pickuprequest","all"]
        System.out.println(Arrays.toString(paths));
       /*
        spec.pathParam("pp","api");
        spec.pathParam("pp1","pickuprequest");
        spec.pathParam("pp2","all");
        */

        StringBuilder tempPath = new StringBuilder("/{");

        for (int i = 0; i < paths.length; i++) {
            String key = "pp" + i;
            String value = paths[i].trim();
            HooksAPI.spec.pathParam(key, value);
            tempPath.append(key + "}/{");
        }
        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));

        fullPath = tempPath.toString();
        System.out.println("fullPath = " + fullPath);
    }
    @Given("Endpointe GET request gonderilir")
    public void endpointe_get_request_gonderilir() {

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .get(fullPath);
        response.prettyPrint();
    }
    @Given("Donen kod'un {int} oldugu dogrulanir")
    public void donen_kod_un_oldugu_dogrulanir(Integer code) {
        response
                .then()
                .assertThat()
                .statusCode(code);
    }
    @Given("Response body icindeki lists icerigi doğrulanmalı.")
    public void response_body_icindeki_lists_icerigi_oldugu_dogrulanmali() {

        //expected body
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id",91);
        jsonObject.put("request_type","2");
        jsonObject.put("merchant_id",2);
        jsonObject.put("address","Arizona");
        jsonObject.put("parcel_quantity",0);
        jsonObject.put("name","Test Express Pickup Request 2");
        jsonObject.put("phone","14785236987");
        jsonObject.put("exchange",0);
        jsonObject.put("created_at","2023-10-20T15:38:42.000000Z");
        jsonObject.put("updated_at","2023-10-20T15:38:42.000000Z");


        JsonPath jsonPath=response.jsonPath();
        //Assertion
        Assert.assertEquals(jsonObject.get("id"),jsonPath.getInt("id[0]"));
        Assert.assertEquals(jsonObject.get("request_type"),jsonPath.getString("request_type[0]"));
        Assert.assertEquals(jsonObject.get("merchant_id"),jsonPath.getInt("merchant_id[0]"));
        Assert.assertEquals(jsonObject.getString("address"),jsonPath.getString("address[0]"));
        Assert.assertEquals(jsonObject.get("parcel_quantity"),jsonPath.getInt("parcel_quantity[0]"));
        Assert.assertNotNull(jsonObject.get("name"),jsonPath.getString("name[0]"));
        Assert.assertNotNull(jsonObject.getString("phone"),jsonPath.getString("phone[0]"));
        Assert.assertEquals(jsonObject.get("exchange"),jsonPath.getInt("exchange[0]"));
        Assert.assertEquals(jsonObject.getString("created_at"),jsonPath.getString("created_at[0]"));
        Assert.assertEquals(jsonObject.getString("updated_at"),jsonPath.getString("updated_at[0]"));
        System.out.println(jsonObject);
    }
    @Given("Endpointe gecersiz authorization bilgileriyle bir GET request gonderilir")
    public void endpointe_gecersiz_authorization_bilgileriyle_bir_get_request_gonderilir() {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer "+ ConfigReader.getProperty("invalidToken"))
                .when()
                .get(fullPath);
        response.prettyPrint();
    }
    @Given("Donen kodun {int} oldugu mesaj bilgisinin {string} oldugu dogrulanir")
    public void donen_kodun_oldugu_mesaj_bilgisinin_oldugu_dogrulanir(Integer code, String message) {
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body(Matchers.containsString(message));
    }
}