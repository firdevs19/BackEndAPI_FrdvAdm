@frdv
Feature: US_01 Database üzerinden merchant_online_payment_receiveds tablosundaki account_id:8 olan datanın amount değerlerinden %20 kesinti yaparak toplayıp çıkan sonucu doğrulayınız.

  Scenario: merchant_online_payment_receiveds tablosundaki account_id:8 olan datayi test etmek
    Given Database ile baglanti kurulur
    Then Query olusturulur ve statement araciligiyla gönderilir
    Then Donen veriler dogrulanir
    And Database ile baglanti kapatilir