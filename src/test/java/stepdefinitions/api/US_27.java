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

public class US_27 {


    @Given("Gerekli {string} path parametreler ayarlanir..")
    public void gerekli_path_parametreler_ayarlanir(String parameters) {
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
    @Given("Endpointe {string} gecerli authorization bilgileri ile bir GET request gosterilir")
    public void endpointe_gecerli_authorization_bilgileri_ile_bir_get_request_gosterilir(String id) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .get(fullPath);
        response.prettyPrint();
    }
    @Given("Response body kaydedilmeli")
    public void response_body_kaydedilmeli() {
        String responseBody = response.getBody().asString();
    }
    @Given("Donen kod {int} olmalidir.")
    public void donen_kod_olmalidir(Integer code) {
        response
                .then()
                .assertThat()
                .statusCode(code);
    }
    @Given("Response body list iceriginin {string} {int} {string} {string} {string} {int} oldugunu dogrula")
    public void response_body_list_iceriginin_oldugunu_dogrula(String id, Integer idNo, String title, String titleName, String image_id, Integer imageNo) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(id, idNo);
        jsonObject.put(title, titleName);
        jsonObject.put(image_id, imageNo);
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonObject.getInt("id"), jsonPath.getInt("id"));
        Assert.assertEquals(jsonObject.getString("title"), jsonPath.getString("title"));
        Assert.assertEquals(jsonObject.getInt("image_id"), jsonPath.getInt("image_id"));
    }
    @Given("Endpointe {string} gecersiz authorization bilgileri ile bir GET request gonderilir")
    public void endpointe_gecersiz_authorization_bilgileri_ile_bir_get_request_gonderilir(String string) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + ConfigReader.getProperty("invalidToken"))
                .when()
                .get(fullPath);
        response.prettyPrint();
    }

    @Given("Donen koda {int} mesaj {string} olmalidir.")
    public void donen_koda_mesaj_olmalidir(Integer code, String message) {
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body(Matchers.containsString(message));
    }
    @Given("Endpointe {string} gecersiz data ile bir GET request gonderilir")
    public void endpointe_gecersiz_data_ile_bir_get_request_gonderilir(String string) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .get(fullPath);
        response.prettyPrint();
    }

    @Given("Donen kod {int} mesaj {string} olmalidir.")
    public void donen_kod_mesaj_olmalidir(Integer code, String message) {
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body(Matchers.containsString(message));
    }
    @Given("Endpointe {string} data gonderilmeden bir GET request gonderilir")
    public void endpointe_data_gonderilmeden_bir_get_request_gonderilir(String data) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .get(fullPath);
        response.prettyPrint();
    }

    @Given("Donen koda {int} mesaj {string} olmalidirr..")
    public void donen_koda_mesaj_olmalidirr(Integer code, String message) {
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body(Matchers.containsString(message));
    }





}
