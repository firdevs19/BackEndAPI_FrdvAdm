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

public class US_23 {

    @Given("Gerekli {string} path parametreleri set edilmeli.")
    public void gerekli_path_parametreleri_set_edilmeli(String pathParameters) {
        String[] paths = pathParameters.split("/"); // ["api","pickuprequest","id"]

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

    @Given("Endpointe {int} id numarali GET request gonderilmeli")
    public void endpointe_id_numarali_get_request_gonderilmeli(Integer id) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .get(fullPath);
        response.prettyPrint();
    }

    @Given("Donen kod'un {int} oldugu dogrulanmali")
    public void donen_kod_un_oldugu_dogrulanmali(Integer code) {
        response
                .then()
                .assertThat()
                .statusCode(code);
    }

    @Given("Response body icindeki id icerigi dogrulanir")
    public void response_body_icindeki_id_icerigi_dogrulanir() {
        //expected body
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 9);
        jsonObject.put("request_type", "1");
        jsonObject.put("merchant_id", 105);
        jsonObject.put("address", "New York 12456");
        jsonObject.put("note","pazartesil gunleri saat 17:00 da gel");
        jsonObject.put("parcel_quantity",2);

        JsonPath jsonPath = response.jsonPath();
        //Assertion
        Assert.assertEquals(jsonObject.get("id"), jsonPath.getInt("id"));
        Assert.assertEquals(jsonObject.get("request_type"), jsonPath.getString("request_type"));
        Assert.assertEquals(jsonObject.get("merchant_id"), jsonPath.getInt("merchant_id"));
        Assert.assertEquals(jsonObject.getString("address"), jsonPath.getString("address"));
        Assert.assertEquals(jsonObject.getString("note"),jsonPath.getString("note"));
        Assert.assertEquals(jsonObject.get("parcel_quantity"),jsonPath.getInt("parcel_quantity"));
        response.prettyPrint();
    }

    @Given("Endpointe gecersiz authorization bilgileriyle bir GET request gonderilmeli")
    public void endpointe_gecersiz_authorization_bilgileriyle_bir_get_request_gonderilmeli() {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + ConfigReader.getProperty("invalidToken"))
                .when()
                .get(fullPath);
    }

    @Given("Donen kodun {int} oldugu mesaj bilgisinin {string} oldugu dogrulanmali")
    public void donen_kodun_oldugu_mesaj_bilgisinin_oldugu_dogrulanmali(Integer code, String message) {
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body(Matchers.containsString(message));
        response.prettyPrint();
    }

    @Given("Endpoint'e gecersiz data bilgileriyle bir GET request gonderilmeli")
    public void endpoint_e_gecersiz_data_bilgileriyle_bir_get_request_gonderilmeli() {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + token)
                .when()
                .get(fullPath);
        response.prettyPrint();
    }


    @Given("Donen kodun {int} oldugu, mesaj bilgisinin {string} oldugu dogrulanmalidir")
    public void donen_kodun_oldugu_mesaj_bilgisinin_oldugu_dogrulanmalidir(Integer code, String message) {
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body(Matchers.containsString(message));
    }

    @Given("Endpoint'e {string} data gonderilmeden bir GET request gonderilmeli")
    public void endpoint_e_data_gonderilmeden_bir_get_request_gonderilmeli(String endPoint) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + token)
                .when()
                .get(fullPath);
        response.prettyPrint();

    }
    @Given("Donen kodun {int} oldugu, mesaj bilgisinin {string} oldugu dogrulanmalidirr")
    public void donen_kodun_oldugu_mesaj_bilgisinin_oldugu_dogrulanmalidirr (Integer code, String message){
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body(Matchers.containsString(message));
        response.prettyPrint();
    }


}
