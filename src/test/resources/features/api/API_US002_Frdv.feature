@frdv0
  Feature: US002 Bir yönetici (admin) olarak API bağlantısı üzerinden id'si verilen kullanicinin Hub bilgilerine erisebilmek istiyorum.

    Background: Token al
      * API testing icin "admin08email" ile token alinir

    Scenario: TC001 api/hub/id endpoint'ine gecerli bir id bilgisi eklenerek ve gecerli authorization bilgisi ile bir GET request
              gönderildiginde dönen status code'un 200 oldugu doğrulanmali.
              Response body icindeki data keyleri (id, name, phone, address, current_balance, status, created_at, updated_at)  doğrulanmalı.

      * API endpointi: "api/hub/9" icin path parametreleri set edilir
      * Endpointe bir GET request gonderilir
      * Donen kod'un 200 oldugu dogrulanir
      * Response body icerigindeki data keyleri dogrulanmalidir

    Scenario: TC002 api/hub/id  endpoint'ine  gecersiz (id) iceren bir GET gönderildiğinde
              statusCode=400, message bilgisinin "there is no hub with this id",
              endpoint´e id eklenmeden bir GET gönderildiğinde "No id." oldugu,
              gecersiz authorization bilgileri ile bir GET gonderildiginde ise statusCode=401, message bilgisinin "Unauthenticated." olduğu doğrulanmali

      * API endpointi: "api/hub/346" icin path parametreleri set edilir
      * Endpointe gecersiz authorization datasi ile "Get" request gonderildiginde status code'in 401 ve message: "Unauthenticated." oldugu dogrulanmalidir
      * API endpointi: "api/hub/21" icin path parametreleri set edilir
      * Endpointe gecersiz id ile "Get" request gonderildiginde status code'in 400 ve message: "there is no hub with this id" oldugu dogrulanmalidir
      * API endpointi: "api/hub/ " icin path parametreleri set edilir
      * Endpointe id olmadan "Get" request gonderildiginde status code'in 400 ve message: "No id." oldugu dogrulanmalidir
