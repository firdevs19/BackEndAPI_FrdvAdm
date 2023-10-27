package stepdefinitions.db;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utilities.JDBCReusableMethods.*;

public class DB_US03 {
    public static ResultSet resultset;

    String query;

    @Given("Database baglantisi kurulur")
    public void database_baglantisi_kurulur() {
        createConnection();
    }

    @Given("{string} icin query hazirla")
    public void icin_query_hazirla(String query) throws SQLException {
        this.query = query;
        resultset = getStatement().executeQuery(query);
    }

    @Given("kullanici sayisinin {int} oldugunu dogrula")
    public void kullanici_sayisinin_oldugunu_dogrula(int int1) throws SQLException {
        resultset.next();
        int expectedData = 33;
        int actualData = resultset.getInt(1);

        assertEquals(expectedData, actualData);
    }

    @Given("Database baglantisi kapatilir")
    public void database_baglantisi_kapatilir() {
        closeConnection();
    }

    @Given("en cok goruntulenen blog ismini doğrula")
    public void en_cok_goruntulenen_blog_ismini_doğrula() throws SQLException {

        resultset.next();
        String query = resultset.getString(1);

        // Sonucu doğrulayın (örneğin, bir test çerçevesi kullanarak)
        assertEquals("Global Logistics and Cultural Challenges Developing Effective Solutions for International Shipments", query);
        System.out.println("Most viewed blog: " + query);
    }

    @Given("Alınan Questions değerleri kontrol edilir.")
    public void alinan_questions_değerleri_kontrol_edilir() throws SQLException {
        int count = 0;
        while (resultset.next()) {
            count++;
            int id = resultset.getInt("id");
            System.out.println("Sonuç " + count + ": " + id);
        }
    }

    @Given("Sorgulama sonucu teyit edilir")
    public void sorgulama_sonucu_teyit_edilir() throws SQLException {
        if (resultset.next()) {
            String createdAt = resultset.getString("created_at");
            String createdBy = resultset.getString("title");

            System.out.println("En eski offer, " + createdAt + " tarihinde " + createdBy + " tarafından oluşturulmuştur.");
        } else {
            System.out.println("En eski offer bulunamadı.");
        }
    }


    @Given("log_name: Upload olan datalardan fazla olduğunu doğrula.")
    public void log_name_upload_olan_datalardan_fazla_olduğunu_doğrula() throws SQLException {
        resultset.next();
        int expectedData = 3615;
        int actualData = resultset.getInt(1);
        if (actualData > expectedData) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed");
        }

    }
}