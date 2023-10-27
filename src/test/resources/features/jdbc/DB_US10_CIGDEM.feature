@DB_US10
Feature: US_10 Database üzerinden deliverycategories tablosuna birden fazla veri ekleyip (tek sorguda birden fazla veri)
         yeni veri kaydının eklenebildiğini doğrulayınız.

  Scenario: deliverycategories tablosuna birden fazla veri ekleyip (tek sorguda birden fazla veri)
            eklenen kayitlari dogrulama testi.

    Given Database baglantisi kurulur
    Then Deliverycategories tablosuna insert Querysi olusturulur ve statement araciligi ile gönderilir
    Then Insert Querysinden donen veriler dogrulanir
    And Database baglantisi kapatilir