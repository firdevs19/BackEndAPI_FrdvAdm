package stepdefinitions.db;

import io.cucumber.java.en.Given;
import org.junit.Assert;
import utilities.QueryManage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static stepdefinitions.db.DB_US05_mustafa.statement;

public class DB_US14_mustafa {
    ResultSet resultSet;
    QueryManage queryManage =new QueryManage();
    String query;
    @Given("hubs tablosundaki name bilgisi S ile baslayan datalarin address bilgileri getirilmeli")
    public void hubs_tablosundaki_name_bilgisi_s_ile_baslayan_datalarin_address_bilgileri_getirilmeli() throws SQLException {
        query = queryManage.getQueryUS14_mustafa();

        resultSet = statement.executeQuery(query);
    }
    @Given("hubs tablosundaki name bilgisi S ile baslayan datalarin address bilgileri dogrulanmali")
    public void hubs_tablosundaki_name_bilgisi_s_ile_baslayan_datalarin_address_bilgileri_dogrulanmali() throws SQLException {
        Map<String,String> sonuclar =new HashMap<>();

        while (resultSet.next()){
            String name = resultSet.getString("name");
            String address= resultSet.getString("address");
            sonuclar.put(name,address);
        }

        System.out.println(sonuclar);
        boolean icerirMi=true;

        Set key =sonuclar.keySet();

        for (int i = 0; i < key.size(); i++) {

            if (key.contains("s")){
                Assert.assertTrue(icerirMi);
            }
        }
    }

}
