@DB_US19
Feature: US_19 Database uzerinden merchants tablosundaki business_name sutunundaki verilerden icinde 'm' ve 'n'
         harflerini iceren datalarin address bilgilerini dogrulayiniz

  Scenario: Database uzerinden merchants tablosundaki business_name sutunundaki verilerden icinde 'm' ve 'n'
            harflerini iceren datalarin address bilgilerini dogrulama testi

    Given Database baglantisi kurulur
    Then merchants tablosu icin select Querysi olusturulur ve statement araciligi ile gönderilir
    Then Select Querysinden donen veriler dogrulanir
    And Database baglantisi kapatilir