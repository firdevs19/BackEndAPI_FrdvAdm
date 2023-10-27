package stepdefinitions.db;

import io.cucumber.java.en.Given;
import org.junit.Assert;
import utilities.DB_Utilities;
import utilities.JDBCReusableMethods;
import utilities.QueryManage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DB_US05_mustafa {
    QueryManage queryManage =new QueryManage();
    static Statement statement;
    static ResultSet resultSet;
    static String query;
    Connection connection=null;

    @Given("Database connection kurulmali")
    public void database_connection_kurulmali() {

        DB_Utilities.createConnection();
        statement= JDBCReusableMethods.getStatement();



    }
    @Given("bank_transactions tablosundaki belirtilen tarihler arasindaki datalarin amount bilgileri getirilmeli ve siralanmali")
    public void bank_transactions_tablosundaki_belirtilen_tarihler_arasindaki_datalarin_amount_bilgileri_getirilmeli_ve_siralanmali() throws SQLException {

        query = queryManage.getQueryUS05_mustafa();

        resultSet = statement.executeQuery(query);
    }
    @Given("bank_transactions tablosundaki belirtilen tarihler arasindaki datalarin amount bilgilerinin kucukten buyuge dogru siralandigi dogrulanir")
    public void bank_transactions_tablosundaki_belirtilen_tarihler_arasindaki_datalarin_amount_bilgilerinin_kucukten_buyuge_dogru_siralandigi_dogrulanir() throws SQLException {
        List sonuclar = new ArrayList();
       while (resultSet.next()){
            double i =resultSet.getDouble("amount");
            sonuclar.add(i);
       }

        System.out.println("list:  " +sonuclar.get(24));
        boolean isSort=false;

        for (int i = 1; i < sonuclar.size(); i++) {
            if ((double)sonuclar.get(i-1) < (double)sonuclar.get(i)){
                isSort=true;
                System.out.print(sonuclar.get(i)+" - ");

            }
        }
        Assert.assertTrue(isSort);
    }
    @Given("Database connection kapatilmali")
    public void database_connection_kapatilmali() throws SQLException, ParseException {
           JDBCReusableMethods.closeConnection();
        //'2023-08-23 11:30:34'


    }
}
