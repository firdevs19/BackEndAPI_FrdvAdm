Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden id si verilen pickup ın bilgilierine erişebilmek istiyorum.

  Background: Token al
    * API testing icin "admin08email" ile token alinir

  @wip2
  Scenario: api/pickuprequest/all endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde dönen status code'un 200 oldugu,
  Response body icindeki lists icerigi ("id": 42, "request_type": "1", "merchant_id": 49, "address": "Orlando") olduğu doğrulanmalı.
    * Gerekli "api/pickuprequest/12" path parametreleri set edilmeli.
    * Endpointe 12 id numarali GET request gonderilmeli
    * Donen kod'un 200 oldugu dogrulanmali
    * Response body icindeki id icerigi dogrulanir
  @wip3
  Scenario: api/pickuprequest/id endpoint'ine gecersiz authorization bilgileri ile bir GET Request gönderildiginde dönen status code'un 401 oldugu ve mesaj bilgisinin "Unauthenticated." olduğu doğrulanmali
    * Gerekli "api/pickuprequest/12" path parametreler set edilmeli.
    * Endpointe gecersiz authorization bilgileriyle bir GET request gonderilmeli
    * Donen kodun 401 oldugu mesaj bilgisinin "Unauthenticated" oldugu dogrulanmali
  @wip4
  Scenario:
    * Gerekli "api/pickuprequest/444" path parametreler set edilmeli.
    * Endpoint'e gecersiz data bilgileriyle bir GET request gonderilmeli
    * Donen kodun 400 oldugu, mesaj bilgisinin "there is no shop with this id" oldugu dogrulanmalidir
  @wip5
  Scenario:
    * Gerekli "api/pickuprequest" path parametreler set edilmeli.
    * Endpoint'e "api/pickuprequest" data gonderilmeden bir GET request gonderilmeli
    * Donen kodun 400 oldugu, mesaj bilgisinin "No id." oldugu dogrulanmalidirr