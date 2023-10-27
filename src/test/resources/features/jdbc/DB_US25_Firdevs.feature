@frdv
Feature: Database uzerinden salary_generates tablosundaki 2018 yili mayis ayindaki maas odemelerinde amount degeri sifir olan kullanicilara 10000 odeme yapin ve odeme yapildigini dogrulayiniz

  Scenario:salary_generates tablosundaki 2018 yili mayis ayindaki maas odemelerinde amount degeri sifir olan kullanicilara 10000 odeme yapildigi test edilir

    Given Database ile baglanti kurulur
    Then Update oncesi ay: "2023-11" ve amount: 0 olan verilerin sayisi hesaplanir ve expectedData olarak kaydedilir, sonraki amount:10000
    Then Update query olusturulur ve statement araciligi ile gonderilerek etkilenen satir sayisi dogrulanir
    Then Tarihe gore Update edilen amount sayisi hesaplanir ve actualdata olarak kaydedilir
    Then ExpectedData ve actualData dogrulanir ve veriler tekrar eski haline getirilir
    And Database ile baglanti kapatilir