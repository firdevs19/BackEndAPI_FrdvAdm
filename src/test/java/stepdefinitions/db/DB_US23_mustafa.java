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

public class DB_US23_mustafa {
    QueryManage queryManage =new QueryManage();
    String querySutunEkle;
    String query1Yap;
    String query0Yap;
    String querySutunSil;
    ResultSet resultSet;
    @Given("parcels tablosundaki ismi_ali_olanlar adli  bir column create edilmeli")
    public void parcels_tablosundaki_ismi_ali_olanlar_adli_bir_column_create_edilmeli() throws SQLException {

            //queryler
            querySutunEkle =queryManage.getQueryUS23_alter_mustafa();
            query1Yap=queryManage.getQueryUS23_update1_mustafa();
            query0Yap=queryManage.getQueryUS23_update0_mustafa();

            //execute
          boolean eklendiMi =statement.execute(querySutunEkle);
           int birler= statement.executeUpdate(query1Yap);
           int sifirlar = statement.executeUpdate(query0Yap);

        System.out.println("sutun eklendi ve isminde ali gecenler 1  yapıldı : " +birler
                        +  "isminde ali gecmeyenler 0 yapıldı                 : " +sifirlar);




    }
    @Given("hubs tablosundaki customer_name icinde ali gecenler create edilen columnda 1 olmayanlar 0 ile gosterildigi dogrulanmali")
    public void hubs_tablosundaki_customer_name_icinde_ali_gecenler_create_edilen_columnda_olmayanlar_ile_gosterildigi_dogrulanmali() throws SQLException {
        String querytumSonuclar ="select customer_name, ismi_ali_olanlar from parcels;";

        resultSet=statement.executeQuery(querytumSonuclar);
        Map<String,Integer> sonuclar =new HashMap<>();

        while (resultSet.next()){
            String name =resultSet.getString("customer_name");
            int birSifir =resultSet.getInt("ismi_ali_olanlar");
            sonuclar.put(name,birSifir);
        }
        System.out.println(sonuclar);
        Set<Map.Entry<String,Integer>> keyValues =sonuclar.entrySet();
        boolean dogruMu =false;

        for (Map.Entry<String,Integer> each: keyValues
             ) {
            int birSifir=each.getValue();
            String name = each.getKey();
            System.out.println("bir sıfır :"+birSifir+"       "+
                               "name : "+name);

            if (name!=null){
                if (name.contains("ali") && birSifir==1){
                    dogruMu =true;
                }

                if (!name.contains("ali") && birSifir==0){
                    dogruMu =true;
                }

            }



        }


        Assert.assertTrue(dogruMu);
        querySutunSil =queryManage.getQueryUS23_delete_mustafa();
        boolean silindiMi=statement.execute(querySutunSil);
        System.out.println("create edilen sutun silindi : "+silindiMi);

    }

}
