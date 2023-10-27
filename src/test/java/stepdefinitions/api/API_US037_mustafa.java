package stepdefinitions.api;
import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import pojos.Todolist;
import utilities.ConfigReader;

import java.util.Arrays;
import static io.restassured.RestAssured.given;

public class API_US037_mustafa {

    public static Response response;
    static String fullPath;
    int expectedKod;
    String message;
    JSONObject expectedData;


    //-------------Test Case 01-----------------
    @Given("{string} endpointi icin path param ayarlamalari yapilir")
    public void endpointi_icin_path_param_ayarlamalari_yapilir(String rawPaths) {

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

    @Given("api todo id endpointi icin expected result olusturulur")
    public void api_todo_id_endpointi_icin_expected_result_olusturulur() {
        expectedKod=200;

    }
    @Given("api todo id endpointi icin response body kaydedilir")
    public void api_todo_id_endpointi_icin_response_body_kaydedilir() {
        response = given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .log().all()
                .get(fullPath);
        response.prettyPrint();

    }
    @Given("api todo id endpointi icin donen response'un status  codunun {int} oldugu dogrulanir")
    public void donen_response_un_status_codunun_ve_mesaj_bilgisinin_succes_dogrulanir(int code) {
        response
                .then()
                .assertThat()
                .statusCode(code);
    }


    //-------------Test Case 02-----------------

    @Given("api todo id endpointi icin expected result bilgileri olusturulur")
    public void api_todo_id_endpointi_icin_expected_result_bilgileri_olusturulur() {
        expectedKod=400;
        message="there is no ToDo with this id";

    }

    @Given("api todo id endpointi icin gecersiz tokenla donen response'un status  codunun {int} ve mesaj bilgisinin {string} oldugu dogrulanir")
    public void api_todo_id_endpointi_icin_donen_response_un_status_codunun_ve_mesaj_bilgisinin_oldugu_dogrulanir(int code, String gecersizToken) {

       //--------------gecersiz token----------
        try {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + ConfigReader.getProperty("invalidToken"))
                    .when()
                    .log().all()
                    .get(fullPath);
        } catch (Exception e) {
            message=e.getMessage();
        }

       Assert.assertTrue(message.contains("status code: 401, reason phrase: Unauthorized"));





    }

    @Given("api todo id endpointi icin gecersiz data ile donen response'un status kodunun {int} ve mesaj bilgisinin {string}  oldugu dogrulanir")
    public void api_todo_id_endpointi_icin_gecersiz_data_ile_donen_response_un_status_kodunun_ve_mesaj_bilgisinin_oldugu_dogrulanir(int code, String gecersizData) {
        //--------------gecersiz Data---------------
        //RequestSpecification spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();
        //spec.pathParams("pp0","api","pp1","to do","pp2",10000);
        HooksAPI.spec.pathParams("pp0","api","pp1","todo","pp2",10000);

        try {
            response = given()
                    .spec(HooksAPI.spec)
                    .contentType(ContentType.JSON)
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + HooksAPI.token)
                    .when()
                    .log().all()
                    .get("/{pp0}/{pp1}/{pp2}");
        } catch (Exception e) {
            message=e.getMessage();
        }

        Assert.assertTrue(message.contains("status code: 400, reason phrase: Bad Request"));

    }

    @Given("api todo id endpointi icin data gonderilmediğinde status kodunun {int} ve mesaj bilgisinin {string} oldugu dogrulanir")
    public void api_todo_id_endpointi_icin_data_gonderilmediğinde_status_kodunun_ve_mesaj_bilgisinin_oldugu_dogrulanir(int code, String noData) {
        //------------eksik data------------
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();
        spec.pathParams("pp0","api","pp1","todo");

        try {
            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + HooksAPI.token)
                    .when()
                    .log().all()
                    .get("/{pp0}/{pp1}/");
        } catch (Exception e) {
            message=e.getMessage();
        }

       Assert.assertTrue(message.contains("status code: 400, reason phrase: Bad Request"));
    }



    //-------------Test Case 03-----------------
    @Given("api todo id endpointi icin expected result  hazirlanir")
    public void api_todo_id_endpointi_icin_expected_result_hazirlanir() {

        /*{
                "id": 3,
                "title": "Updated Task",
                "description": "Task New",
                "user_id": 3,
                "date": "2023-08-28",
                "status": 3,
                "note": "tamam",
                "created_at": "2023-09-04T14:15:47.000000Z",
                "updated_at": "2023-10-15T12:27:56.000000Z"
            }

         */
        expectedData=new JSONObject();
        expectedData.put("id", 3);
        expectedData.put("title","Updated Task");
        expectedData.put("description","Task New");
        expectedData.put("user_id",4);
        expectedData.put("date","2023-08-28");
        expectedData.put("status", 3);
        expectedData.put("note","tamam");
        expectedData.put("created_at","2023-09-04T14:15:47.000000Z");
        expectedData.put("updated_at","2023-10-17T07:40:53.000000Z");

    }

    @Given("api todo id endpointi icin donen response'un list iceriginin beklenen result ile ayni oldugu dogrulanir")
    public void api_todo_id_endpointi_icin_donen_response_un_list_iceriginin_beklenen_result_ile_ayni_oldugu_dogrulanir() {

        Todolist todolist=response.as(Todolist.class);

        Assert.assertEquals(expectedData.get("id"),todolist.id);
        Assert.assertEquals(expectedData.get("title"),todolist.title);
        Assert.assertEquals(expectedData.get("description"),todolist.description);
        Assert.assertEquals(expectedData.get("user_id"),todolist.userId);
        Assert.assertEquals(expectedData.get("date"),todolist.date);
        Assert.assertEquals(expectedData.get("status"),todolist.status);
        Assert.assertEquals(expectedData.get("note"),todolist.note);
        Assert.assertEquals(expectedData.get("created_at"),todolist.createdAt);
        Assert.assertEquals(expectedData.get("updated_at"),todolist.updatedAt);
    }


}
