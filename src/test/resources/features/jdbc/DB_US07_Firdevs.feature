@frdv
Feature: database uzerinden currencies tablosundaki name=Dollars olan iceriklerin code bilgisi icinde 'A' olan datalari ters siralayarak dogrulayiniz.

  Scenario:Currencies  tablosundakiname=Dollars olan bilgilerin icinde 'A' olan datalari ters olarak siralamak
    Given Database ile baglanti kurulur
    Then Query07 olusturulur ve statement araciligi ile gönderilir
    Then Query07 den Donen veriler dogrulanir
    And Database ile baglanti kapatilir