package stepdefinitions.db;

import io.cucumber.java.en.Given;
import org.testng.Assert;
import utilities.DB_Utilities;
import utilities.JDBCReusableMethods;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_US06_Sevda {

    static Statement statement;
    static ResultSet resultSet;

//    @Given("Database baglantisi kurulur")
//    public void database_baglantisi_kurulur() {
//        JDBCReusableMethods.getConnection();
//        statement=JDBCReusableMethods.getStatement();
//    }
    @Given("Query olusturulur ve statement araciligi ile gönderilir")
    public void query_olusturulur_ve_statement_araciligi_ile_gönderilir() throws SQLException {

        String Query6="SELECT parcel_id, COUNT(*) AS unique_couriers\n" +
               "FROM courier_statements\n" +
               "WHERE parcel_id = 19\n" +
               "GROUP BY parcel_id";

       resultSet=statement.executeQuery(Query6);

    }
    @Given("Donen data dogrulanir")
    public void donen_data_dogrulanir() throws SQLException {
        resultSet.next();
        int expectedData= 5;
        int actualData=resultSet.getInt(2);
        Assert.assertEquals(expectedData, actualData);
        System.out.println("Test basarili");

    }
//    @Given("Database baglantisi kapatilir")
//    public void database_baglantisi_kapatilir() {
//        DB_Utilities.closeConnection();
//    }
}
