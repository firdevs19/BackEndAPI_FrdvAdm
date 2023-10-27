Feature: US022_Bir yönetici (admin) olarak API bağlantısı üzerinden tüm pickup listesine erişebilmek istiyorum.

  Background: Token al
    * API testing icin "admin08email" ile token alinir

  @wip
  Scenario: api/pickuprequest/all endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde dönen status code'un 200 oldugu dogrulanmali
    * Gerekli "api/pickuprequest/all" path parametreleri set edilir.
    * Endpointe GET request gonderilir
    * Donen kod'un 200 oldugu dogrulanir
    * Response body icindeki lists icerigi doğrulanmalı.
  @wip1
  Scenario: api/pickuprequest/id endpoint'ine gecersiz authorization bilgileri ile bir GET Request gönderildiginde dönen status code'un 401 oldugu ve mesaj bilgisinin "Unauthenticated." olduğu doğrulanmali
    * Gerekli "api/pickuprequest/all" path parametreleri set edilir.
    * Endpointe gecersiz authorization bilgileriyle bir GET request gonderilir
    * Donen kodun 401 oldugu mesaj bilgisinin "Unauthenticated" oldugu dogrulanir