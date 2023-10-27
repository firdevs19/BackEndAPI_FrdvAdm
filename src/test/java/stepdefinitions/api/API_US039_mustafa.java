package stepdefinitions.api;

import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import pojos.Todolist;
import utilities.ConfigReader;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class API_US039_mustafa {
    public static Response response;
    static String fullPath;
    String message;
    JSONObject expectedData;
    JSONObject reqBody;
    Todolist todolist;

    //--------------Test Case01--------------
    @Given("{string} endpointi icin path parametre ayarlamalari yapilir")
    public void endpointi_icin_path_parametre_ayarlamalari_yapilir(String rawPaths) {
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

    @Given("api todo edit id endpointi icin request body olusturulur")
    public void api_todo_edit_id_endpointi_icin_request_body_olusturulur() {
        reqBody=new JSONObject();
        reqBody.put("title","Update edildi mi");
        reqBody.put("description", "team1'e selamlar2");
        reqBody.put("user_id",107);
        reqBody.put("date","2014-09-05");

    }
    @Given("api todo edit id endpointi icin expected result  olusturulur")
    public void api_todo_edit_id_endpointi_icin_expected_result_olusturulur() {

        /*
        {
            "id": 3,
            "title": "Update edildi mi",
            "description": "Task New",
            "user_id": 4,
            "date": "2023-08-28",
            "status": 3,
            "note": "tamam",
            "created_at": "2023-09-04T14:15:47.000000Z",
            "updated_at": "2023-10-16T16:58:46.000000Z"
        }
         */
        expectedData=new JSONObject();
        expectedData.put("id", 3);
        expectedData.put("title","Update edildi mi");
        expectedData.put("description","team1'e selamlar2");
        expectedData.put("user_id",107);
        expectedData.put("date","2014-09-05");
        expectedData.put("status", 3);
        expectedData.put("note","tamam");

    }
    @Given("api todo edit id endpointi icin response body kaydedilir")
    public void api_todo_edit_id_endpointi_icin_response_body_kaydedilir() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .body(reqBody.toString())
                .patch(fullPath);
        response.prettyPrint();

    }
    @Given("api todo edit id endpointi icin donen response'un status  codunun {int} ve responsetaki mesaj bilgisinin {string} oldugu dogrulanir")
    public void donen_response_un_status_codunun_ve_responsetaki_mesaj_bilgisinin_oldugu_dogrulanir(int code, String message) {
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message));
    }


    //--------------Test Case02--------------
    @Given("api todo edit id endpointi icin eksik request body olusturulur")
    public void api_todo_edit_id_endpointi_icin_eksik_request_body_olusturulur() {
        reqBody=new JSONObject();
        reqBody.put("title","kargo hk duzeltme");
        reqBody.put("description", "team1'e selamlar2");
        reqBody.put("date","2014-09-05");

    }

    @Given("api todo edit 107 endpointi icin gecersiz tokenla donen response'un status  codunun {int} ve mesaj bilgisinin {string} oldugu dogrulanir")
    public void api_todo_id_endpointi_icin_donen_response_un_status_codunun_ve_mesaj_bilgisinin_oldugu_dogrulanir(int code, String gecersizToken) {

        //--------------gecersiz token----------
        try {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + ConfigReader.getProperty("invalidToken"))
                    .when()
                    .body(reqBody.toString())
                    .patch(fullPath);
        } catch (Exception e) {
            message=e.getMessage();
        }

       Assert.assertTrue(message.contains("status code: 401, reason phrase: Unauthorized"));

    }

    @Given("api todo edit 107 endpointi icin gecersiz data ile donen response'un status kodunun {int} ve mesaj bilgisinin {string}  oldugu dogrulanir")
    public void api_todo_edit_endpointi_icin_gecersiz_data_ile_donen_response_un_status_kodunun_ve_mesaj_bilgisinin_oldugu_dogrulanir(int code, String gecersizData) {
        //--------gecersiz data---------
        HooksAPI.spec.pathParams("pp0","api","pp1","todo","pp2","edit","pp3","107");

        try {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + HooksAPI.token)
                    .when()
                    .body(reqBody.toString())
                    .log().all()
                    .patch("/{pp0}/{pp1}/{pp2}/{pp3}");
        } catch (Exception e) {
            message=e.getMessage();
        }

       Assert.assertTrue(message.contains("status code: 400, reason phrase: Bad Request"));
    }
    @Given("api todo edit 107 endpointi  icin data gonderilmediğinde status kodunun {int} ve mesaj bilgisinin {string} oldugu dogrulanir")
    public void api_todo_edit_endpointi_icin_data_gonderilmediginde_status_kodunun_ve_mesaj_bilgisinin_oldugu_dogrulanir(int code, String noData) {
        //------------eksik data------------

        HooksAPI.spec.pathParams("pp0","api","pp1","todo","pp2","edit");
        try {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + HooksAPI.token)
                    .when()
                    .body(reqBody.toString())
                    .log().all()
                    .patch(fullPath);
        } catch (Exception e) {
            message=e.getMessage();
        }

        Assert.assertTrue(message.contains("status code: 400, reason phrase: Bad Request"));

    }




    //--------------Test Case03--------------

    @Given("response body icindeki list datalarinin icerikleri dogrulanir")
    public void response_body_icindeki_list_datalarinin_icerikleri_dogrulanir() {


        JsonPath resBody=response.jsonPath();

        Assert.assertEquals(expectedData.get("id"),resBody.get("data[0].id"));
        Assert.assertEquals(expectedData.get("title"),resBody.get("data[0].title"));
        Assert.assertEquals(expectedData.get("description"),resBody.get("data[0].description"));
        Assert.assertEquals(expectedData.get("user_id"),resBody.get("data[0].user_id"));
        Assert.assertEquals(expectedData.get("date"),resBody.get("data[0].date"));
        Assert.assertEquals(expectedData.get("status"),resBody.get("data[0].status"));
        Assert.assertEquals(expectedData.get("note"),resBody.get("data[0].note"));

    }


}
