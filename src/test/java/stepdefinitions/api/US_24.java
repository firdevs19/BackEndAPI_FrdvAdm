package stepdefinitions.api;

import static hooks.HooksAPI.*;

import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.Assert;
import utilities.ConfigReader;

import java.util.Arrays;
import static io.restassured.RestAssured.given;

public class US_24 {

    @Given("Gerekli {string} path parametreler set edilmeli.")
    public void gerekli_path_parametreler_set_edilmeli(String pathParameters) {
        String[] paths = pathParameters.split("/"); // ["api","pickuprequest","regular","edit","id"]

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

    @Given("Request body olusturulur")
    public void request_body_olusturulur() {
        /*
        "note":"Note....",
        "parcel_quantity":25
         */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("note", "Note....");
        jsonObject.put("parcel_quantitiy", 25);
    }

    @Given("Endpointe {int} ID no'lu PATCH request gonderilmeli")
    public void endpointe_id_no_lu_get_request_gonderilmeli(Integer id) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .patch(fullPath);
        response.prettyPrint();
    }

    @Given("Donen kod'un {int} oldugu ve mesaj bilgisinin {string} oldugu dogrulanmali dogrulanmali")
    public void donen_kod_un_oldugu_ve_mesaj_bilgisinin_oldugu_dogrulanmali_dogrulanmali(Integer code, String message) {
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body(Matchers.containsString(message));
    }

    @Given("Response body icindeki updateId bilgisinin {string} endpoint'ine gönderilen PATCH request body icindeki id bilgisi ile ayni oldugu dogrulanmali.")
    public void response_body_icindeki_update_id_bilgisinin_api_pickuprequest_id_endpoint_ine_gonderilen_patch_request_body_icindeki_id_bilgisi_ile_ayni_oldugu_dogrulanmali(String update) {
        String updateIdFromPreviousResponse = response.jsonPath().getString("updateId");
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .patch(fullPath);

        String idInPatchResponse = response.jsonPath().getString("id");
        Assert.assertEquals(updateIdFromPreviousResponse, idInPatchResponse,"Updated");
        response.prettyPrint();
    }

    @Given("API uzerinden guncellenmek istenen Pickup kaydinin guncellendigi, API uzerinden dogrulanmali.")
    public void api_uzerinden_guncellenmek_istenen_pickup_kaydinin_guncellendigi_api_uzerinden_dogrulanmali() {
        JSONObject update= new JSONObject();
        update.put("note","Note....");
        update.put("parcel_quantity",25);
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .body(update.toString())
                .when()
                .patch(fullPath);
        response.then()
                .assertThat()
                .statusCode(400);
    }
    @Given("Endpointe gecersiz authorization bilgileri girildiginde kodun {int} ve mesaj bilgisinin {string} oldugu dogrulanmali")
    public void endpointe_gecersiz_authorization_bilgileri_girildiginde_kodun_ve_mesaj_bilgisinin_oldugu_dogrulanmali(Integer code, String message) {
        JSONObject update= new JSONObject();
        update.put("note","Note....");
        update.put("parcel_quantity",25);
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + ConfigReader.getProperty("invalidToken"))
                .body(update.toString())
                .when()
                .patch(fullPath);
        response.then()
                .assertThat()
                .statusCode(code)
                .body(Matchers.containsString(message));
        response.prettyPrint();

    }



}