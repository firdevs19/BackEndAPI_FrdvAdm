package stepdefinitions.db;

import io.cucumber.java.en.Given;
import org.junit.Assert;

import java.sql.SQLException;

import static stepdefinitions.db.DB_US15_Sevda.resultSet;
import static stepdefinitions.db.DB_US15_Sevda.statement;
import static utilities.JDBCReusableMethods.connection;

public class DB_US24_Sevda {

    private String oldestPartnerName;

    @Given("Database uzerinden partners tablosundaki en eski partnerimizin ismini dogrulayiniz")
    public void database_uzerinden_partners_tablosundaki_en_eski_partnerimizin_ismini_dogrulayiniz() throws SQLException {

        statement = connection.createStatement();
        String oldestPartnerName = null;

        String Query16="SELECT name, created_at\n" +
                "FROM partners\n" +
                "ORDER BY created_at ASC\n" +
                "LIMIT 1;\n";

        resultSet=statement.executeQuery(Query16);

        if (resultSet.next()) {
            oldestPartnerName = resultSet.getString("name");
        }
    }

    @Given("En eski partnerimizin ismi dogrulanmalidir")
    public void en_eski_partnerimizin_ismi_dogrulanmalidir() {
        Assert.assertNotNull(oldestPartnerName);
    }

}