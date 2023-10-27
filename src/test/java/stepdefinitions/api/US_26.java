package stepdefinitions.api;

import static hooks.HooksAPI.*;

import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.Assert;
import utilities.ConfigReader;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class US_26 {

    @Given("Gerekli {string} path param ayarlari yapilir")
    public void gerekli_path_param_ayarlari_yapilir(String parameters) {
        String[] paths = parameters.split("/"); // ["api","pickuprequest","delete","id"]

        System.out.println(Arrays.toString(paths));
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

    @Given("Gecerli authorization bilgileri ile GET request gonderilir")
    public void gecerli_authorization_bilgileri_ile_get_request_gonderilir() {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .get(fullPath);
        response.prettyPrint();

    }

    @Given("Response body kaydedilir")
    public void response_body_kaydedilir() {
        String responseBody = response.getBody().asString();
    }
    @Given("Donen kodun {int} oldugu dogrulanir")
    public void donen_kodun_oldugu_dogrulanir(Integer code) {
        response
                .then()
                .assertThat()
                .statusCode(code);
    }
    @Given("List iceriginin {string} nin {int} {string} in {string} {string} nin {int} oldugu dogrulanir")
    public void list_iceriginin_nin_in_nin_oldugu_dogrulanir(String id, Integer idNo, String title, String titleName, String image_id, Integer imageNo) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(id, idNo);
        jsonObject.put(title, titleName);
        jsonObject.put(image_id, imageNo);
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonObject.getInt("id"), jsonPath.getInt("id[0]"));
        Assert.assertEquals(jsonObject.getString("title"), jsonPath.getString("title[0]"));
        Assert.assertEquals(jsonObject.getInt("image_id"), jsonPath.getInt("image_id[0]"));
        response.prettyPrint();
    }
    @Given("Endpoint {string} gecersiz authorization bilgileri ile bir GET Request gonderildiginde donen status code'un {int}  mesajin {string} oldugu dogrulanir")
    public void endpoint_gecersiz_authorization_bilgileri_ile_bir_get_request_gonderildiginde_donen_status_code_un_mesajin_oldugu_dogrulanir(String parameters, Integer code, String message) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + ConfigReader.getProperty("invalidToken"))
                .when()
                .get(fullPath);
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body(Matchers.containsString(message));
        response.prettyPrint();
    }
}
