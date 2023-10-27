package utilities;

import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static hooks.HooksAPI.spec;
import static io.restassured.RestAssured.given;
import static hooks.HooksAPI.fullPath;
import static hooks.HooksAPI.response;

public class ReusableMethods {

    public static String fullPathOlustur(String rawPaths) { // hub/1
        RequestSpecification specPrivate =new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();
        String[] paths = rawPaths.split("/"); // [ "hub" , "1" ]

        StringBuilder tempPath = new StringBuilder("/{");   //     /{

        for (int i = 0; i < paths.length; i++) {

            String key = "pp" + (i+1);    // pp1   pp2
            String value = paths[i].trim();   //  hub   1

            specPrivate.pathParam(key,value);

            tempPath.append( key + "}/{" );  //   /{pp1}/{pp2}/{
        }

        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));    //   /{pp1}/{pp2}/
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));    //   /{pp1}/{pp2}

        fullPath = tempPath.toString();
        spec=specPrivate;
        return fullPath;
    }

    public static Response getRequest(String token, String fullPath) {

     response = given()
                    .spec(spec)
                    .headers("Authorization", "Bearer " + token, "Accept", "application/json")
                .when()
                    .get(fullPath);

     return response;
    }

    public static Response postRequest(String token,Object reqBody) {

        response = given()
                    .spec(spec)
                    .headers("Authorization", "Bearer " + token, "Accept", "application/json")
                    .contentType(ContentType.JSON)
                .when()
                    .body(reqBody)
                    .post(fullPath);

        return response;
    }
    public static Response patchRequest(String token,Object reqBody) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + token)
                .when()
                .body(reqBody)
                .patch(fullPath);

        return response;
    }


    public static Response deleteRequest(String token,String fullPath ) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + token)
                .when()
                .delete(fullPath);
        return response;

    }

}
