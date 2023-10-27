package stepdefinitions.db;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.QueryManage;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static stepdefinitions.db.DB_US01_CIGDEM.resultSet;
import static stepdefinitions.db.DB_US01_CIGDEM.statement;

public class DB_US19_CIGDEM {

     QueryManage queryManage = new QueryManage();

    Map<String,String> sonuclar;

    @Then("merchants tablosu icin select Querysi olusturulur ve statement araciligi ile gönderilir")
    public void merchants_tablosu_icin_select_querysi_olusturulur_ve_statement_araciligi_ile_gonderilir() throws SQLException {

        String query = queryManage.getQueryUS19_CIGDEM();
        resultSet= statement.executeQuery(query);
        sonuclar = new HashMap<>();
        while (resultSet.next()){

            String businessName = resultSet.getString("business_name");
            String address = resultSet.getString("address");
            sonuclar.put(businessName,address);

        }
        System.out.println(sonuclar);
        System.out.println(sonuclar.size());
        System.out.println(sonuclar.get("company"));

    }
    @Then("Select Querysinden donen veriler dogrulanir")
    public void select_querysinden_donen_veriler_dogrulanir() {

        Set <Map.Entry<String,String>> keyValues = sonuclar.entrySet();

        boolean dogruMu = false;
        for (Map.Entry<String,String>each:keyValues) {

            String businessName = each.getKey();
            String address = each.getValue();

            if (businessName.contains("m") && businessName.contains("n") && sonuclar.containsValue(address)){

                dogruMu = true;
                System.out.println(businessName + "  :  "+ address);

            };

        }

        Assert.assertTrue(dogruMu);
        System.out.println(keyValues.size());
        System.out.println("Adres bilgileri dogrulandi");

    }

}
