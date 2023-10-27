package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class Authentication {

    private static RequestSpecification spec;


    public static String generateToken(String email){

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

        spec.pathParams("pp", "api", "pp1", "gettoken");


        JSONObject reqBody = new JSONObject();

        reqBody.put("email", ConfigReader.getProperty(email));
        reqBody.put("password", ConfigReader.getProperty("password"));

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .body(reqBody.toString())
                .post("/{pp}/{pp1}");

        JsonPath resJP = response.jsonPath();

        String token=resJP.getString("data.token");

        return token;
    }
}