package stepdefinitions.api;

import static hooks.HooksAPI.*;

import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.openqa.selenium.json.Json;
import org.testng.Assert;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class US_25 {

    @Given("Gerekli {string} path parametreler ayarlanir.")
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

    @Given("Endpointe DELETE request gonderilir")
    public void endpointe_delete_request_gonderilir() {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .delete(fullPath);

    }

    @Given("Donen kod {int} ve mesaj bilgisi {string} olmali")
    public void donen_kod_ve_mesaj_bilgisi_olmali(Integer code, String message) {
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body(Matchers.containsString(message));
        response.prettyPrint();
    }

    @Given("Response body icindeki deletedData bilgisinin {string} endpoint'ine gonderilen DELETE request body icindeki id bilgisi ile ayni oldugu dogrulanmali.")
    public void response_body_icindeki_deleted_data_bilgisinin_api_pickuprequest_delete_id_endpoint_ine_gonderilen_delete_request_body_icindeki_id_bilgisi_ile_ayni_oldugu_dogrulanmali(String parameters) {

        String deleteIdFromPreviousResponse = response.jsonPath().getString("data.'deleted data'");
        System.out.println("Bir onceki id cevabı:"+deleteIdFromPreviousResponse);
        String expectedId = "88";
        Assert.assertEquals(deleteIdFromPreviousResponse, expectedId, "Deleted");
    }
    @Given("API uzerinden silinmek istenen pickup kaydinin silindigi, API uzerinden dogrulanmali.")
    public void api_uzerinden_silinmek_istenen_pickup_kaydinin_silindigi_api_uzerinden_dogrulanmali() {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .delete(fullPath);
        response
                .then()
                .assertThat()
                .statusCode(400);
        response.prettyPrint();
    }

    @Given("Endpointe gecersiz id {int} bilgisi gonderilir")
    public void endpointe_gecersiz_id_bilgisi_gonderilir(Integer id) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/html")
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .get(fullPath);
    }

    @Given("Gecersiz id {int} bilgisi gonderildiginde kodun {int} mesaj bilgisinin {string} oldugu dogrulanir")
    public void gecersiz_id_bilgisi_gonderildiginde_kodun_mesaj_bilgisinin_oldugu_dogrulanir(Integer invalidId, Integer code, String message) {
        /*
        {"success":false,"message":"there is no shop with this id","data":[]}
         */
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));
        response.prettyPrint();
    }
}