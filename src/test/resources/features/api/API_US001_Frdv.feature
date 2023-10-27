@frdv0
  Feature: US001  Bir yönetici (admin) olarak API bağlantısı üzerinden Hub List'e erişebilmek istiyorum.

    Background: Token alma islemi yapilir
      * API testing icin "admin08email" ile token alinir

      Scenario: TC001 api/hub/list endpoint'ine gecerli authorization bilgileri ile bir GET request
                gönderildiginde dönen status code'un 200 oldugu ve response message bilgisinin "Success" oldugu dogrulanmali
                Response body icindeki data icerigi (id, name, phone, address, current_balance, status, created_at, updated_at) doğrulanmalı.

        * API endpointi: "api/hub/list" icin path parametreleri set edilir
        * Endpointe bir GET request gonderilir
        * Donen kod'un 200 ve mesajin "Success" oldugu dogrulanir
        * Response body icerigindeki data tablonun verileri dogrulanmalidir

    Scenario: TC002 api/hub/list endpoint'ine gecersiz authorization bilgileri ile bir GET Request
                gönderildiginde dönen status code'un 401 oldugu ve response message bilgisinin "Unauthenticated." oldugu dogrulanmali

      * API endpointi: "api/hub/list" icin path parametreleri set edilir
      * Endpointe gecersiz authorization datasi ile "Get" request gonderildiginde status code'in 401 ve message: "Unauthenticated." oldugu dogrulanmalidir
