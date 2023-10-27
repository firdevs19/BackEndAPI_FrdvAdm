package stepdefinitions.api;

import hooks.HooksAPI;
import io.cucumber.java.en.Given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import pojos.US019_PostBody;
import pojos.US022_PatchBody;
import utilities.ReusableMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static hooks.HooksAPI.*;
import static io.restassured.RestAssured.given;

public class API_US017_18_19_20_21_Firdevs {
public static String fullPath;
public static Response response;

    //  ************** US_020 --- TC_001 ********************
public static US022_PatchBody reqPatchBody;
public static US019_PostBody postBody;
public static int responseBodyId;
    public static JsonPath resJP_US17;

    @Given("API testing icin {string} ile token alinir")
    public void api_testing_icin_ile_token_alinir(String email) {
        HooksAPI.beforeGenerateToken(email);
    }


    @Given("Endpointe bir GET request gonderilir")
    public void endpointe_bir_get_request_gonderilir() {

        response= ReusableMethods.getRequest(token,fullPath);
        response.prettyPrint();
        resJP_US17=response.jsonPath();
    }
    @Given("Response body icerigindeki cod_charges datalari dogrulanmalidir")
    public void response_body_icerigindeki_cod_charges_datalari_dogrulanmalidir() {

        Map<String, Object> codMap = resJP_US17.getMap("find { it.id == 2 }");
        Object list=codMap.get("cod_charges");
        System.out.println("cod_charges datalari = " + list.toString());
        Assert.assertTrue(list.toString().contains("inside_city"));
        Assert.assertTrue(list.toString().contains("sub_city"));
        Assert.assertTrue(list.toString().contains("outside_city"));
    }
    @Given("Response body icerigindeki istenilen datalar dogrulanmalidir")
    public void response_body_icerigindeki_istenilen_datalar_dogrulanmalidir() {

        resJP_US17.prettyPrint();
        String listIcerik=resJP_US17.getString("user");
        System.out.println("listIcerik = " + listIcerik);

        //      id, name,   email,  email_verified_at,  mobile, nid_number,
        //    designation_id,   department_id,  hub_id, user_type,  image_id,
        //    joining_date, address,    role_id,    permissions
        Assert.assertTrue(listIcerik.toString().contains("id"));
        Assert.assertTrue(listIcerik.toString().contains("name"));
        Assert.assertTrue(listIcerik.toString().contains("email"));
        Assert.assertTrue(listIcerik.toString().contains("email_verified_at"));
        Assert.assertTrue(listIcerik.toString().contains("mobile"));
        Assert.assertTrue(listIcerik.toString().contains("nid_number"));
        Assert.assertTrue(listIcerik.toString().contains("designation_id"));
        Assert.assertTrue(listIcerik.toString().contains("department_id"));
        Assert.assertTrue(listIcerik.toString().contains("hub_id"));
        Assert.assertTrue(listIcerik.toString().contains("user_type"));
        Assert.assertTrue(listIcerik.toString().contains("image_id"));
        Assert.assertTrue(listIcerik.toString().contains("joining_date"));
        Assert.assertTrue(listIcerik.toString().contains("address"));
        Assert.assertTrue(listIcerik.toString().contains("role_id"));
        Assert.assertTrue(listIcerik.toString().contains("permissions"));

    }


    @Given("API endpointi: {string} icin path parametreleri set edilir")
    public void api_endpointi_icin_path_parametreleri_set_edilir(String rawPath) {

        fullPath= ReusableMethods.fullPathOlustur(rawPath);
        System.out.println("fullPath = " + fullPath);
    }

    @Given("Endpointe PATCH icin business_name: {string}, mobile: {string}, email: {string} olan patchBody olusturulur")
    public void endpointe_patch_icin_business_name_mobile_email_olan_patch_body_olusturulur(String business_name, String mobile, String email) {
        /*
            {"business_name":"Haiger AŞ",       "mobile":"9999999990",      "email":"fellerdilln@gmail.com"}
        */
        reqPatchBody=new US022_PatchBody(business_name,mobile,email);
    }
    @Given("Endpointe POST icin name:{string},business_name:{string},mobile:{string},email:{string},password:{string},address:{string},hub_id:{string},status:{string},olan postBody olusturulur")
    public void endpointe_post_icin_name_business_name_mobile_email_password_address_hub_id_status_olan_post_body_olusturulur(String name, String business_name, String mobile, String email,String password,String address,String hub_id,String status) {
        postBody=new US019_PostBody(name,business_name,mobile,email,password,address,hub_id,status);
    }
    @Given("Endpointe PATCH request gonderilir")
    public void endpointe_patch_request_gonderilir() {
        response= ReusableMethods.patchRequest(token,reqPatchBody);
    }
    @Given("Endpointe postBody ile POST request gonderilir")
    public void endpointe_post_body_ile_post_request_gonderilir() {
        response= ReusableMethods.postRequest(token,postBody);
        JsonPath respPost=response.jsonPath();
        String newAddedMessage=respPost.getString("data").toString();
        String newId=newAddedMessage.substring(newAddedMessage.lastIndexOf(" ")+4,newAddedMessage.length()-1);
        responseBodyId =Integer.parseInt(newId);
        System.out.println("newId = " + newId);
    }
    @Given("Olusturulan yeni Merchant IDsi ile Merchant list sorgulanir ve name dogrulanir")
    public void olusturulan_yeni_merchant_i_dsi_ile_merchant_list_sorgulanir_ve_name_dogrulanir() {
       fullPath=ReusableMethods.fullPathOlustur("api/merchant/"+ responseBodyId);
        response=ReusableMethods.getRequest(token,fullPath);
        response.prettyPrint();
        JsonPath newPostRespJP=response.jsonPath();
        System.out.println("newPostRespJP.getString(\"user.name\") = " + newPostRespJP.getString("user.name"));
        System.out.println("postBody Name = " + postBody.getName());
        Assert.assertEquals(newPostRespJP.getString("user.name"),postBody.getName());
    }
    @Given("Donen kod'un {int} ve mesajin {string} oldugu dogrulanir")
    public void donen_kod_un_ve_mesajin_oldugu_dogrulanir(int statusCode, String messageIcerik) {
        response.then().assertThat().statusCode(statusCode);
        response.prettyPrint();
        JsonPath donenPatchReqJP = response.jsonPath();
        Assert.assertEquals(donenPatchReqJP.get("message"), messageIcerik);
    }
    @Given("Donen kod'un {int} ve {string} nin {string} oldugu dogrulanir")
    public void donen_kod_un_ve_nin_oldugu_dogrulanir(int statusCode, String message, String messageIcerik) {
        response.then().assertThat().statusCode(statusCode);
        response.prettyPrint();
        JsonPath donenPatchReqJP=response.jsonPath();
        Assert.assertEquals(donenPatchReqJP.get(message),messageIcerik);
        String responseJP=response.asPrettyString();
        boolean dataVarMi=responseJP.contains("success");

if (!dataVarMi){
    int idStart=responseJP.indexOf("id");
    int idEnd=responseJP.substring(idStart).indexOf(',')+idStart;
    String newId=response.asPrettyString().substring(idStart+5,idEnd);
    responseBodyId =Integer.parseInt(newId);
    System.out.println("newId = " + newId);
}else {
    String newAddedMessage = donenPatchReqJP.getString("data").toString();

    String newId = newAddedMessage.substring(newAddedMessage.lastIndexOf(" ") + 4, newAddedMessage.length() - 1);
    responseBodyId = Integer.parseInt(newId);
    System.out.println("newId = " + newId);
}
    }
    @Given("Response Bodyde islem yapilan ID {int} var oldugu dogrulanir")
    public void response_bodyde_process_id_var_oldugu_dogrulanir(int int1) {
        System.out.println("ResponseBodydeki ID = " + responseBodyId);
        System.out.println("Islem yapilan ID = "+int1);
        Assert.assertEquals(responseBodyId,int1);
    }

    @Given("Merchant kaydinin guncellendigi, API uzerinden dogrulanir")
    public void merchant_kaydinin_guncellendigi_api_uzerinden_dogrulanir() {
        response = given()
                .spec(spec)
                .headers("Authorization", "Bearer " + token, "Accept", "application/json")
                .when()
                .get(fullPath);
        response.prettyPrint();
        JsonPath pathRespJP=response.jsonPath();
        Assert.assertEquals(reqPatchBody.getBusiness_name(),pathRespJP.get("business_name"));
        Assert.assertEquals(reqPatchBody.getEmail(),pathRespJP.get("user.email"));
        Assert.assertEquals(reqPatchBody.getMobile(),pathRespJP.get("user.mobile"));
        System.out.print("Exp_business_name = " + reqPatchBody.getBusiness_name()); System.out.println("  Act_business_name = " + pathRespJP.get("business_name"));
        System.out.print("Exp_email = " + reqPatchBody.getEmail()); System.out.println("  Act_email = " + pathRespJP.get("user.email"));
        System.out.print("Exp_mobile = " + reqPatchBody.getMobile()); System.out.println("  Act_mobile = " + pathRespJP.get("user.mobile"));

    }

        //  ************** US_020 --- TC_002 ********************

    @Given("Endpointe gecersiz authorization datasi ile {string} request gonderildiginde status code'in {int} ve message: {string} oldugu dogrulanmalidir")
    public void endpointe_gecersiz_authorization_datasi_ile_request_gonderildiginde_status_code_in_ve_message_oldugu_dogrulanmalidir(String requestType,int statusCode, String message) {

        if (requestType.equalsIgnoreCase("get")){
            response=ReusableMethods.getRequest(token+1,fullPath);
        } else if (requestType.equalsIgnoreCase("post")) {
            response=ReusableMethods.postRequest(token+1,postBody);
        } else if (requestType.equalsIgnoreCase("patch")) {
            response=ReusableMethods.patchRequest(token+1,reqPatchBody);
        } else if (requestType.equalsIgnoreCase("delete")) {
            response=ReusableMethods.deleteRequest(token+1,fullPath);
        }
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(statusCode)
                .body(Matchers.containsString(message));
    }

    @Given("Endpointe gecersiz id ile {string} request gonderildiginde status code'in {int} ve message: {string} oldugu dogrulanmalidir")
    public void endpointe_gecersiz_id_ile_request_gonderildiginde_status_code_in_ve_message_oldugu_dogrulanmalidir(String requestType,int statusCode, String message) {

        if (requestType.equalsIgnoreCase("get")){
            response=ReusableMethods.getRequest(token,fullPath);
        } else if (requestType.equalsIgnoreCase("post")) {
            response=ReusableMethods.postRequest(token,postBody);
        } else if (requestType.equalsIgnoreCase("patch")) {
            response=ReusableMethods.patchRequest(token,reqPatchBody);
        } else if (requestType.equalsIgnoreCase("delete")) {
            response=ReusableMethods.deleteRequest(token,fullPath);
        }
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(statusCode)
                .body(Matchers.containsString(message));

    }
    @Given("Endpointe id olmadan {string} request gonderildiginde status code'in {int} ve message: {string} oldugu dogrulanmalidir")
    public void endpointe_id_olmadan_request_gonderildiginde_status_code_in_ve_message_oldugu_dogrulanmalidir(String requestType,int statusCode, String message) {


        if (requestType.equalsIgnoreCase("get")){
            response=ReusableMethods.getRequest(token,fullPath);
        } else if (requestType.equalsIgnoreCase("post")) {
            response=ReusableMethods.postRequest(token,postBody);
        } else if (requestType.equalsIgnoreCase("patch")) {
            response=ReusableMethods.patchRequest(token,reqPatchBody);
        } else if (requestType.equalsIgnoreCase("delete")) {
            response=ReusableMethods.deleteRequest(token,fullPath);
        }
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(statusCode)
                .body(Matchers.containsString(message));

    }


    @Given("Endpointe gecersiz authorization datasi ile Patch request gonderildiginde status code'in {int} ve message: {string} oldugu dogrulanmalidir")
    public void endpointe_gecersiz_authorization_datasi_ile_patch_request_gonderildiginde_status_code_in_ve_message_oldugu_dogrulanmalidir(int statusCode, String message) {

        reqPatchBody=new US022_PatchBody("Team001","1255","aa@aa.aa");
        response=ReusableMethods.patchRequest(token+1,reqPatchBody);
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(statusCode)
                .body(Matchers.containsString(message));

    }
    @Given("Endpointe gecersiz id ile Patch request gonderildiginde status code'in {int} ve message: {string} oldugu dogrulanmalidir")
    public void endpointe_gecersiz_id_ile_patch_request_gonderildiginde_status_code_in_ve_message_oldugu_dogrulanmalidir(int statusCode, String message) {

        reqPatchBody=new US022_PatchBody("Team001","1255","aa@aa.aa");
        response=ReusableMethods.patchRequest(token,reqPatchBody);
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(statusCode)
                .body(Matchers.containsString(message));

    }
    @Given("Endpointe id olmadan Patch request gonderildiginde status code'in {int} ve message: {string} oldugu dogrulanmalidir")
    public void endpointe_id_olmadan_patch_request_gonderildiginde_status_code_in_ve_message_oldugu_dogrulanmalidir(int statusCode, String message) {

        reqPatchBody=new US022_PatchBody("Team001","1255","aa@aa.aa");
        response=ReusableMethods.patchRequest(token,reqPatchBody);
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(statusCode)
                .body(Matchers.containsString(message));

    }

    @Given("Endpointe silme islemi icin DELETE request gonderilir")
    public void endpointe_silme_islemi_icin_delete_request_gonderilir() {
       response=ReusableMethods.deleteRequest(token,fullPath);

    }

    @Given("Endpointe silinen id ile Get request gonderildiginde status code'in {int} ve message: {string} oldugu dogrulanmalidir")
    public void endpointe_silinen_id_ile_get_request_gonderildiginde_status_code_in_ve_message_oldugu_dogrulanmalidir(int statusCode, String message) {


        response=ReusableMethods.getRequest(token,fullPath);
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(statusCode)
                .body(Matchers.containsString(message));

    }

}
