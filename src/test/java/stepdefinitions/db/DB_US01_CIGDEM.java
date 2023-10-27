package stepdefinitions.db;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.JDBCReusableMethods;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_US01_CIGDEM {

    static ResultSet resultSet;
    static Statement statement;

//    @Given("Database baglantisi kurulur.")
//    public void database_baglantisi_kurulur2() {
//        JDBCReusableMethods.getConnection();
//        statement= JDBCReusableMethods.getStatement();
//
//    }
    @Then("Query olusturulur ve statement ile gönderilir")
    public void query_olusturulur_ve_statement_ile_gönderilir() throws SQLException {
        String query1 = "select count(*)from u168183796_qaagiles.accounts where gateway = 2";
        resultSet=statement.executeQuery(query1);


    }
    @Then("Donen datalar dogrulanir")
    public void donen_datalar_dogrulanir() throws SQLException {
        resultSet.next();
        int expdata=33;
        int actdata= resultSet.getInt(1);

        Assert.assertEquals(expdata,actdata);
        System.out.println("Test basarili");

    }
//    @Then("Database baglantisi kapatilir")
//    public void database_baglantisi_kapatilir2() {
//        JDBCReusableMethods.closeConnection();
//
//    }

}
