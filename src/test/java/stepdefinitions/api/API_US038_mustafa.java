package stepdefinitions.api;

import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import utilities.ConfigReader;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class API_US038_mustafa {
    public static Response response;
    static String fullPath;
    int expectedKod;
    String message;
    JSONObject reqBody;

    //-------------Test Case01----------
    @Given("{string} endpointi icin gerekli path parametreleri ayarlamalari yapilir")
    public void endpointi_icin_gerekli_path_parametreleri_ayarlamalari_yapilir(String rawPaths) {

        String[] paths = rawPaths.split("/");
        System.out.println(Arrays.toString(paths));

        StringBuilder tempPath = new StringBuilder("/{");

        for (int i = 0; i < paths.length; i++) {

            String key = "pp" + i;
            String value = paths[i].trim();
            HooksAPI.spec.pathParams(key, value);

            tempPath.append(key + "}/{");
        }
        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));
        tempPath.deleteCharAt(tempPath.indexOf("/"));

        fullPath = tempPath.toString(); // {pp1}/{pp2}/{pp3}
        System.out.println("fullPath = " + fullPath);


    }

    @Given("api todo add endpointi icin request body olusturulur")
    public void api_todo_add_endpointi_icin_request_body_olusturulur() {
        /*

        {
        "title": "mustafa",
        "description": "ne yapıyon bea",
        "user_id": 101,
        "date": "2023-09-05"
        }
         */
        reqBody=new JSONObject();
        reqBody.put("title","kargo hk");
        reqBody.put("description", "team1'e selamlar");
        reqBody.put("user_id",107);
        reqBody.put("date","2016-09-09");


    }
    @Given("api todo add endpointi icin expected result  olusturulur")
    public void api_todo_add_endpointi_icin_expected_result_olusturulur() {
       expectedKod=200;
       message="New ToDo Added";

    }
    @Given("api todo add endpointi icin response body kaydedilir")
    public void api_todo_add_endpointi_icin_response_body_kaydedilir() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .body(reqBody.toString())
                .post(fullPath);
        response.prettyPrint();

    }
    @Given("api todo add endpointi icin donen response'un status  codunun {int} ve mesaj bilgisinin {string} oldugu dogrulanir")
    public void api_todo_add_endpointi_icin_donen_response_un_status_codunun_ve_mesaj_bilgisinin_oldugu_dogrulanir(int code, String message) {
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));
    }



    //-------------Test Case02----------

    @Given("api todo add endpoint'ine gecersiz authorization bilgileri ile  POST methodu gonderilir")
    public void api_todo_add_endpoint_ine_gecersiz_authorization_bilgileri_ile_post_methodu_gonderilir() {

    }
    @Given("api todo add endpointi icin eksik request body olusturulur")
    public void api_todo_add_endpointi_icin_eksik_request_body_olusturulur() {
        reqBody=new JSONObject();
        reqBody.put("title","kargo hk");
        reqBody.put("description", "team1'e selamlar");
        reqBody.put("user_id",107);
        reqBody.put("date","2016-09-09");

    }
    @Given("api todo add endpointi icin expected result bilgileri  olusturulur")
    public void api_todo_add_endpointi_icin_expected_result_bilgileri_olusturulur() {
        expectedKod=401;
        message="Unauthenticated.";


    }
    @Given("api todo add endpointi icin response body bilgileri kaydedilir")
    public void api_todo_add_endpointi_icin_response_body_bilgileri_kaydedilir() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + ConfigReader.getProperty("invalidToken"))
                .when()
                .body(reqBody.toString())
                .post(fullPath);
        response.prettyPrint();

    }
    @Given("api todo add endpointi icin donen response'un status  codunun {int} ve bodydeki mesaj bilgisinin {string} oldugu dogrulanir")
    public void api_todo_add_endpointi_icin_donen_response_un_status_codunun_ve_bodydeki_mesaj_bilgisinin_oldugu_dogrulanir(Integer code, String message) {
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));
    }



    //-------------Test Case03----------

    @Given("api todo add endpointi icin expected result hazirlanir")
    public void api_todo_add_endpointi_icin_expected_result_hazirlanir() {
        expectedKod=200;
        message="New ToDo Added";


    }

    @Given("API uzerinden yeni notice kaydinin olustugu dogrulanir")
    public void api_uzerinden_yeni_notice_kaydinin_olustugu_dogrulanir() {
        response
                .then()
                .assertThat()
                .statusCode(expectedKod)
                .body("message", Matchers.equalTo(message));
    }

}
