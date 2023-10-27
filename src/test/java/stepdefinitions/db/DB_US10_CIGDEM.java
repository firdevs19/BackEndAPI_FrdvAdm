package stepdefinitions.db;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.QueryManage;
import java.sql.SQLException;
import static stepdefinitions.db.DB_US01_CIGDEM.statement;
public class DB_US10_CIGDEM {

    QueryManage queryManage = new QueryManage();
    int actualResult;

    @Then("Deliverycategories tablosuna insert Querysi olusturulur ve statement araciligi ile gönderilir")
          public void deliverycategories_tablosuna_insert_querysi_olusturulur_ve_statement_araciligi_ile_gonderilir()
            throws SQLException {

        String query = queryManage.getQueryUS10_CIGDEM();
        actualResult = statement.executeUpdate(query);


    }
    @Then("Insert Querysinden donen veriler dogrulanir")
    public void insert_querysinden_donen_veriler_dogrulanir() {

        int expectedResult = 3;
        Assert.assertEquals(expectedResult,actualResult);
        System.out.println("Test basarili");

    }

}
