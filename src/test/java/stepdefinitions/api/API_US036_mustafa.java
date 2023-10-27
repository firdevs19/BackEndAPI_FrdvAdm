package stepdefinitions.api;
import hooks.HooksAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import pojos.Todolist;

import java.util.Arrays;

import static hooks.HooksAPI.spec;
import static io.restassured.RestAssured.given;


public class API_US036_mustafa {

    public static Response response;
    static String fullPath;
    int expectedKod;
    String message;
    String mesaj;
    JSONObject expectedBody =new JSONObject();



    //-------------------------Test Case 01---------------
    @Given("{string} endpointi icin gerekli path param ayarlamalari yapilir")
    public void endpointi_icin_gerekli_path_param_ayarlamalari_yapilir(String rawPaths) {


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

    @Then("api todo list endpointi icin expected result olusturulur")
    public void endpointi_icin_expected_result_olusturulur() {
        expectedKod = 200;
        /*
        {
                "id": 7,
                "title": "New route",
                "description": "The destination of parcel number 123456, which will go to Chicago, will be changed to Washington.",
                "user_id": 86,
                "date": "2023-09-24",
                "status": 3,
                "note": "Congrat!",
                "created_at": "2023-09-22T13:51:40.000000Z",
                "updated_at": "2023-09-22T13:52:32.000000Z"
        }

         */

        expectedBody.put("id",7);
        expectedBody.put("title","Updated Task");
        expectedBody.put("description","Task New");
        expectedBody.put("user_id",4);
        expectedBody.put("date","2023-08-28");
        expectedBody.put("status",3);
        expectedBody.put("note","Congrat!");
        expectedBody.put("created_at","2023-09-22T13:51:40.000000Z");
        expectedBody.put("updated_at","2023-10-15T12:22:35.000000Z");

    }

    @And("api todo list endpointi icin response body kaydedilir")
    public void endpointi_icin_response_body_kaydedilir() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .get(fullPath);
        response.prettyPrint();


    }

    @When("api todo list endpoint icin donen response'un status kodunun {int} oldugu dogrulanir")
    public void api_todo_list_endpoint_icin_donen_response_un_status_kodunun_message_iceriginin_success_oldugu_dogrulanir(int code) {

        response
                .then()
                .assertThat()
                .statusCode(code);

    }


    //---------------------Test Case 02---------------------
    @Given("api todo list endpointi icin request body olusturulur ve endpointe gecersiz authorization bilgileri GET methodu gonderilir ve gelen response dogrulanir")
    public void endpointi_icin_request_body_olusturulur_ve_endpointe_gecersiz_authorization_bilgileri_get_methodu_gonderilir() {

        try {
            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + "HooksAPI.token")
                    .when()
                    .log().all()
                    .get(fullPath);
        } catch (Exception e) {
            mesaj=e.getMessage();

        }

        System.out.println("Mesaj :"+mesaj);
        Assert.assertTrue(mesaj.contains("status code: 401, reason phrase: Unauthorized"));

    }

    @Given("api todo list endpointi icin expected result kaydedilir")
    public void endpointi_icin_expected_result_kaydedilir() {
        expectedKod = 401;
        message = "Unauthenticated.";

    }

    @Given("api todo list endpoint icin donen response'un status kodunun {int} ve message bilgisinin {string} oldugu dogrulanir")
    public void api_todo_list_endpoint_icin_donen_response_un_status_kodunun_ve_message_bilgisinin_oldugu_dogrulanir(int code, String message1) {
        response
                .then()
                .assertThat()
                .statusCode(code)
                .body("message", Matchers.equalTo(message1));

    }


    //-----------------------Test Case 03------------------------
    @Given("api todo list endpointi icin donen response'un list icerigi dogrulanir")
    public void endpointi_icin_donen_response_un_list_icerigi_dogrulanir() {
        spec.pathParams("pp0","api","pp1","todo","pp2",7);
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .get(fullPath);
       response.prettyPrint();


        Todolist responseBody=response.as(Todolist.class);

        Assert.assertEquals(expectedBody.get("id"),responseBody.id);
        Assert.assertEquals(expectedBody.get("title"),responseBody.title);
        Assert.assertEquals(expectedBody.get("description"),responseBody.description);
        Assert.assertEquals(expectedBody.get("user_id"),responseBody.userId);
        Assert.assertEquals(expectedBody.get("date"),responseBody.date);
        Assert.assertEquals(expectedBody.get("status"),responseBody.status);
        Assert.assertEquals(expectedBody.get("note"),responseBody.note);
        Assert.assertEquals(expectedBody.get("created_at"),responseBody.createdAt);
        Assert.assertEquals(expectedBody.get("updated_at"),responseBody.updatedAt);

    }



}
