package stepdefinitions.db;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.ConfigReader;
import utilities.DB_Utilities;
import utilities.JDBCReusableMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DB_US07_16_25_Firdevs {

    protected ResultSet resultSet;
    protected Statement statement;
    String expectedData;
    String actualData;
    String activeDate;
    int firstAmount;
    int secondAmount;

    //------------------------DB_US16-------------------------

    @Given("Database ile baglanti kurulur")
    public void database_ile_baglanti_kurulur() {
        JDBCReusableMethods.getConnection();
        statement= JDBCReusableMethods.getStatement();

    }
    @Then("Query olusturulur ve statement araciligiyla gönderilir")
    public void query_olusturulur_ve_statement_araciligiyla_gönderilir() throws SQLException {
        String query1 = "SELECT SUM(amount * 0.8) AS toplam FROM  u168183796_qaagiles.merchant_online_payment_receiveds WHERE account_id = 8";
        System.out.println("Query yollandi!!");
        resultSet=statement.executeQuery(query1);


    }
    @Then("Donen veriler dogrulanir")
    public void donen_veriler_dogrulanir() throws SQLException {
        resultSet.next();
        int expdata= (int) 1916;
        int actdata= (int) resultSet.getDouble(1);
        System.out.println("expdata = " + expdata);
        System.out.println("actdata = " + actdata);

        Assert.assertEquals(expdata,actdata);
        System.out.println("Test basarili");

    }

//------------------------DB_US07-------------------------

    @Then("Query07 olusturulur ve statement araciligi ile gönderilir")
    public void query07_olusturulur_ve_statement_araciligi_ile_gönderilir() throws SQLException {

        String query7 = "SELECT *\n" +
                "FROM currencies\n" +
                "WHERE name = 'Dollars' AND code LIKE '%A%'\n" +
                "ORDER BY code DESC;\n";
        System.out.println("Query yollandi!!");
        resultSet=statement.executeQuery(query7);


    }
    @Then("Query07 den Donen veriler dogrulanir")
    public void query07_den_donen_veriler_dogrulanir() throws SQLException {
        char ilkHarf;
        char ikinciIlkharf='Z';
        while (resultSet.next()) {
            String code = resultSet.getString("code");
            ilkHarf = code.charAt(0);
            System.out.println("ilkHarf = " + ilkHarf);
            Assert.assertTrue(ikinciIlkharf>ilkHarf);
            ikinciIlkharf=ilkHarf;
        }
    }

    //------------------------DB_US25-------------------------

    @Then("Update oncesi ay: {string} ve amount: {int} olan verilerin sayisi hesaplanir ve expectedData olarak kaydedilir, sonraki amount:{int}")
    public void update_oncesi_ay_ve_amount_olan_verilerin_sayisi_hesaplanir_ve_expected_data_olarak_kaydedilir(String month, Integer amount,Integer amount2) throws SQLException {
        // once 2018-05 te amount =0 olanlari saydiriyoruz ve expectedData'ya atiyoruz
        String query25a = "select count(*) as toplam from u168183796_qaagiles.salary_generates WHERE month = '"+month+"' AND amount = "+amount+";";
        resultSet = statement.executeQuery(query25a);
        resultSet.next();
        expectedData = resultSet.getString(1);
        System.out.println("Update edilecek Data sayisi = " + expectedData);
        activeDate=month;
        firstAmount=amount;
        secondAmount=amount2;
    }
    @Then("Update query olusturulur ve statement araciligi ile gonderilerek etkilenen satir sayisi dogrulanir")
    public void update_query_olusturulur_ve_statement_araciligi_ile_gonderilerek_etkilenen_satir_sayisi_dogrulanir() throws SQLException {
        // amount =0 olanlari 10000 olarak degistiriyoruz:
        String query25b = "UPDATE u168183796_qaagiles.salary_generates SET amount = "+secondAmount+" WHERE month = '"+activeDate+"' AND amount ="+firstAmount+";";
        int rowEffected = statement.executeUpdate(query25b); // Update isleminden etkilenen satir sayisi int olarak alinir(rowEffected).
        int expectedIntData=(Integer.parseInt(expectedData)); // expectedData String oldugundan Integer a cevrilir ve Dogrulanir:
        Assert.assertEquals(expectedIntData,rowEffected);
        System.out.println("Update islemi yapildi ve etkilenen satir sayisi : "+rowEffected+ " oldugu dogrulandi");

    }
    @Then("Tarihe gore Update edilen amount sayisi hesaplanir ve actualdata olarak kaydedilir")
    public void tarihe_gore_update_edilen_amount_sayisi_hesaplanir_ve_actualdata_olarak_kaydedilir() throws SQLException {
        // month =2018-05 ve amount=10000 olanlari saydiriyoruz ve actualData'ya atiyoruz
        String query25c = "select count(*) as toplam from u168183796_qaagiles.salary_generates WHERE month = '"+activeDate+"' AND amount = "+secondAmount+";";
        resultSet=statement.executeQuery(query25c);
        resultSet.next();
        actualData=resultSet.getString(1);

    }
    @Then("ExpectedData ve actualData dogrulanir ve veriler tekrar eski haline getirilir")
    public void expected_data_ve_actual_data_dogrulanir_ve_veriler_tekrar_eski_haline_getirilir() throws SQLException {
        Assert.assertEquals(expectedData,actualData);
        System.out.println("Update edilen Data sayisi : "+actualData+" oldugu dogrulandi");

        // veriler tekrar eski haline getir(Update islemi tersine cevrilir):
        String query25b = "UPDATE u168183796_qaagiles.salary_generates SET amount = "+firstAmount+" WHERE month = '"+activeDate+"' AND amount ="+secondAmount+";";
        int rowEffected = statement.executeUpdate(query25b);
        System.out.println("Verileri eski haline getirme Update islemi yapildi. Etkilenen satir sayisi : " + rowEffected);

    }

    //------------------------DB_Close-------------------------
    @Then("Database ile baglanti kapatilir")
    public void database_ile_baglanti_kapatilir() {
        JDBCReusableMethods.closeConnection();

    }
}
