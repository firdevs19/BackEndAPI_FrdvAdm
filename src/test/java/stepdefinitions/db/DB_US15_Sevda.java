package stepdefinitions.db;

import io.cucumber.java.en.Given;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static utilities.JDBCReusableMethods.connection;

public class DB_US15_Sevda {
    static Statement statement;
    static ResultSet resultSet;
    static HashMap<Integer, Integer> subCityToplamMap;

    @Given("{string} icin Query hazirla")
    public void icin_query_hazirla(String string) throws SQLException {
        statement = connection.createStatement();

        String Query15="SELECT delivery_charge_id, SUM(sub_city) AS total_sub_city\n" +
                "FROM merchant_delivery_charges\n" +
                "WHERE merchant_id = 1\n" +
                "GROUP BY delivery_charge_id\n" +
                "HAVING COUNT(DISTINCT delivery_charge_id) > 1;\n";

        resultSet=statement.executeQuery(Query15);

        // subCityToplamMap değişkenini hesapla
        subCityToplamMap = new HashMap<>();
        while (resultSet.next()) {
            int deliveryChargeId = resultSet.getInt("delivery_charge_id");
            int subCityToplam = resultSet.getInt("sub_city_toplam");

            subCityToplamMap.put(deliveryChargeId, subCityToplam);
        }
        statement.close();
    }

    @Given("Listelenen sonuclar dogrulanir")
    public void listelenen_sonuclar_dogrulanir() throws SQLException {

        // subCityToplamMap değişkenindeki sonuçları doğrula
        for (Map.Entry<Integer, Integer> entry : subCityToplamMap.entrySet()) {
            int deliveryChargeId = entry.getKey();
            int subCityToplam = entry.getValue();

            // Beklenen sonuçları hesapla
            int expectedSubCityToplam = 0;
            for (int i = 1; i <= deliveryChargeId; i++) {
                expectedSubCityToplam += i;
            }

            // Listelenen sonuçların beklenen sonuçlarla aynı olup olmadığını kontrol et
            Assert.assertEquals(expectedSubCityToplam, subCityToplam);
        }

        // Tüm testlerin başarılı olduğunu yazdır
        System.out.println("Test basarili");
    }
}
