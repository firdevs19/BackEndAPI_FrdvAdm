package stepdefinitions.api;

import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import utilities.ConfigReader;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class API_US040_mustafa {
    public static Response response;
    static String fullPath;
    int expectedKod;
    String message;
    //---------------Test Case 01--------------
    @Given("{string} endpointi icin path parametre ayarlamasi yapilir")
    public void endpointi_icin_path_parametre_ayarlamasi_yapilir(String rawPaths) {
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

    @Given("api todo delete id endpointi icin expected result  olusturulur")
    public void api_todo_elete_id_endpointi_icin_expected_result_olusturulur() {
        expectedKod=200;
        message="Deleted";
    }
    @Given("api todo delete id endpointi icin response body kaydedilir")
    public void api_todo_elete_id_endpointi_icin_response_body_kaydedilir() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .log().all()
                .delete(fullPath);
        response.prettyPrint();

    }
    @Given("api todo delete id endpointi icin donen response'un status  codunun {int} ve responsetaki mesaj bilgisinin {string} oldugu dogrulanir")
    public void api_todo_elete_id_endpointi_icin_donen_response_un_status_codunun_ve_responsetaki_mesaj_bilgisinin_deleted_oldugu_dogrulanir(int code, String message) {
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));
    }
    //---------------Test Case 02--------------

    @Given("api todo delete id endpointi icin yeni expected result  olusturulur")
    public void api_todo_elete_id_endpointi_icin_yeni_expected_result_olusturulur() {
        expectedKod=400;
        message="there is no ToDo with this id";

    }

    @Given("api todo delete endpointi icin gecersiz data ile donen response'un status kodunun {int} ve mesaj bilgisinin {string}  oldugu dogrulanir")
    public void api_todo_delete_endpointi_icin_gecersiz_data_ile_donen_response_un_status_kodunun_ve_mesaj_bilgisinin_oldugu_dogrulanir(int code, String gecersizData) {

        try {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + HooksAPI.token)
                    .when()
                    .log().all()
                    .delete(fullPath);
        } catch (Exception e) {
            message=e.getMessage();
        }

        Assert.assertTrue(message.contains("status code: 400, reason phrase: Bad Request"));

       /* response
                .then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(gecersizData));

        */
    }
    @Given("api todo delete endpointi  icin data gonderilmediğinde status kodunun {int} ve mesaj bilgisinin {string} oldugu dogrulanir")
    public void api_todo_delete_endpointi_icin_data_gonderilmediğinde_status_kodunun_ve_mesaj_bilgisinin_oldugu_dogrulanir(int code, String noData) {
        //------------eksik data------------


        try {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + HooksAPI.token)
                    .when()
                    .log().all()
                    .delete(fullPath);
        } catch (Exception e) {
            message=e.getMessage();
        }


        Assert.assertTrue(message.contains("status code: 400, reason phrase: Bad Request"));

        /* response
                .then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(noData));

         */


    }
    @Given("api todo delete endpointi icin gecersiz tokenla donen response'un status  codunun {int} ve mesaj bilgisinin {string} oldugu dogrulanir")
    public void api_todo_delete_endpointi_icin_gecersiz_tokenla_donen_response_un_status_codunun_ve_mesaj_bilgisinin_oldugu_dogrulanir(int code, String gecersizToken) {
        //--------------gecersiz token----------
        try {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + ConfigReader.getProperty("invalidToken"))
                    .when()
                    .log().all()
                    .delete(fullPath);
        } catch (Exception e) {
            message =e.getMessage();
        }
        System.out.println(message);

        Assert.assertTrue(message.contains("status code: 401, reason phrase: Unauthorized"));
        /*
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(gecersizToken));

         */
    }


    //---------------Test Case 03--------------
    @Given("api todo delete id endpointi icin visitor kaydinin silindigi dogrulanir")
    public void api_todo_elete_id_endpointi_icin_visitor_kaydinin_silindigi_dogrulanir() {
        expectedKod=200;
        message="Deleted";

        response
                .then()
                .assertThat()
                .statusCode(expectedKod)
                .body("message", Matchers.equalTo(message));


    }

}
