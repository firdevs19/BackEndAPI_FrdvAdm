@frdv1
Feature: US017 Bir yönetici (admin) olarak API bağlantısı üzerinden Merchant List'e erişebilmek istiyorum.

  Background: Token al
    * API testing icin "admin08email" ile token alinir

  Scenario: TC001 api/merchant/list endpoint'ine gecerli authorization bilgileri ile
  bir GET request gönderildiginde dönen status code'un 200 oldugu dogrulanmali
  Response body icindeki cod_charges data lists icerigi (inside_city,sub_city,outside_city) doğrulanmalı.

    * API endpointi: "api/merchant/list" icin path parametreleri set edilir
    * Endpointe bir GET request gonderilir
    * Donen kod'un 200 oldugu dogrulanir
    * Response body icerigindeki cod_charges datalari dogrulanmalidir

  Scenario:  TC002 api/merchant/list endpoint'ine gecersiz authorization bilgileri ile bir GET Request gönderildiginde
  dönen status code'un 401 oldugu ve response message bilgisinin "Unauthenticated." oldugu dogrulanmali

    * API endpointi: "api/merchant/list" icin path parametreleri set edilir
    * Endpointe gecersiz authorization datasi ile "Get" request gonderildiginde status code'in 401 ve message: "Unauthenticated." oldugu dogrulanmalidir
