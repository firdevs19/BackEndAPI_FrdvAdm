@DB_US01
Feature: US_01 Database üzerinden accounts tablosundaki gateway = 2 olan kullanıcı sayısının 32 olduğunu dogrulayiniz

  Scenario: Accounts tablosundaki gateway = 2 olan kullanıcı sayısıni test etmek

    Given Database baglantisi kurulur
    Then Query olusturulur ve statement ile gönderilir
    Then Donen datalar dogrulanir
    And Database baglantisi kapatilir