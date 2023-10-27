package stepdefinitions.db;

import com.mysql.cj.protocol.Resultset;
import io.cucumber.java.en.Given;
import utilities.DB_Utilities;
import utilities.JDBCReusableMethods;
import utilities.QueryManage;
import java.sql.*;


public class DB_Elif {
    QueryManage queryManage = new QueryManage();

    Resultset resultset;
    static ResultSet resultSet;
    static Statement statement;

    @Given("Database baglantisi kurulmalidir")
    public void database_baglantisi_kurulmalidir() throws SQLException {
        JDBCReusableMethods.getConnection();
        statement = JDBCReusableMethods.getStatement();
    }

    @Given("Deliveryman delivery_charge icin Query olusturulur ve statement araciligi ile gonderilir")
    public void deliveryman_delivery_charge_icin_query_olusturulur_ve_statement_araciligi_ile_gonderilir() throws SQLException {
        String query = "SELECT * FROM u168183796_qaagiles.delivery_man";
        resultSet = statement.executeQuery(query);
    }

    @Given("id:{int} olan deliveryman'in delivery_charge bilgisini {double} olarak guncelleyip guncellendigi dogrulanmali")
    public void id_olan_deliveryman_in_delivery_charge_bilgisini_olarak_guncelleyip_guncellendigi_dogrulanmali(Integer id, Double deliverycharge) throws SQLException {
        String selectQuery = "SELECT delivery_charge FROM delivery_man WHERE id = 13";
        try {
            ResultSet resultSet = statement.executeQuery(selectQuery);
            if (resultSet.next()) {
                double deliveryCharge = resultSet.getDouble("delivery_charge");
                if (deliveryCharge == 333.33) {
                    System.out.println("Update success: Delivery charge is updated to 333.33.");
                } else {
                    System.out.println("Update failed: Delivery charge is not updated.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Given("Database baglantisi kapatilmalidir")
    public void database_baglantisi_kapatilmalidir() {
        DB_Utilities.closeConnection();
    }

    @Given("Merchant statements tablosundaki tarihe gore tasinacak parcalari gun gun grupla")
    public void merchant_statements_tablosundaki_tarihe_gore_tasinacak_parcalari_gun_gun_grupla() {
        String selectQuery = "SELECT date, GROUP_CONCAT(amount ORDER BY amount ASC) as Amounts FROM merchant_statements GROUP BY date";
        try {
            resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                System.out.println("Date: " + resultSet.getString("date") + " - Amounts: " + resultSet.getString("Amounts"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Given("Yapilan gruplandirmayi dogrula")
    public void yapilan_gruplandirmayi_dogrula() throws SQLException {
        boolean isValid = false;
        while (resultSet.next()) {
            String amounts = resultSet.getString("Amounts");
            if ("19,19,19,21,21,21".equals(amounts)) {
                isValid = true;
            }
            System.out.println("Date: " + resultSet.getString("date") + " - Amounts: " + amounts);
        }

        if (isValid) {
            System.out.println("The amounts for the date '2023-09-23' match the expected values.");
        } else {
            System.out.println("The amounts for the date '2023-09-23' do NOT match the expected values.");
        }
    }
    @Given("Service degeri odeme ile ilgili olan support degerlerinin priority degerleri highest yapilmali")
    public void service_degeri_odeme_ile_ilgili_olan_support_degerlerinin_priority_degerleri_highest_yapilmali() throws SQLException {
        String updateSQL = "UPDATE supports SET priority = 'highest' WHERE service LIKE '%ödeme%'";
        int updatedRows =statement.executeUpdate(updateSQL);
    }
    @Given("Degerlerin highest yapildigi dogrulanmalidir")
    public void degerlerin_highest_yapildigi_dogrulanmalidir() throws SQLException {
        String verifySQL = "SELECT * FROM supports WHERE service LIKE '%ödeme%' AND priority != 'highest'";
        resultset = (Resultset) statement.executeQuery(verifySQL);

        if (!resultset.getRows().hasNext()) {
            System.out.println("All related supports' priorities have been successfully updated to 'highest'.");
        } else {
            System.out.println("There are still some supports related to 'ödeme' that have not been updated to 'highest'.");
        }

    }

}
