package hooks;

import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import utilities.Authentication;
import utilities.ConfigReader;


public class HooksAPI {

    public static RequestSpecification spec;
    public static String token;
    public static Response response;
    public static String fullPath;
    @Before (order=0)
    public void setUp(){

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

    }

//    @Before (order=1)
//    public void beforeGenerateToken(){
//
//        token = Authentication.generateToken();
//
//    }
    //@Before (order=1)
    public static void beforeGenerateToken(String email){

        token = Authentication.generateToken(email);

    }


}
