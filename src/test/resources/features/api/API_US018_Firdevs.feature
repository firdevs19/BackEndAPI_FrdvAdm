@frdv1
Feature: US018 Bir yonetici (admin) olarak API baglantisi uzerinden id si verilen  Merchant'a  erisebilmek istiyorum.

    Background: Token al
      * API testing icin "admin08email" ile token alinir

    Scenario: TC001 api/merchant/id endpoint'ine gecerli authorization bilgileri ile bir GET request
              gönderildiginde dönen status code'un 200 oldugu dogrulanmali
    Response body icindeki userdata lists icerigi (id,name,email,email_verified_at,mobile,nid_number,
    designation_id,department_id,hub_id,user_type,image_id,joining_date,address,role_id,permissions) doğrulanmalı.

      * API endpointi: "api/merchant/9" icin path parametreleri set edilir
      * Endpointe bir GET request gonderilir
      * Donen kod'un 200 oldugu dogrulanir
      * Response body icerigindeki istenilen datalar dogrulanmalidir


    Scenario: TC002 api/merchant/id endpoint'ine gecersiz authorization bilgileri ile bir GET Request
    gönderildiginde dönen status code'un 400 oldugu ve response message bilgisinin
    geçersiz data icin "there is no merchant with this id",
    data gönderilmediğinde "No id.",
    gecersiz authorization bilgilerinde ise  status code'in 401 ve "Unauthenticated." olduğu doğrulanmali

      * API endpointi: "api/merchant/346" icin path parametreleri set edilir
      * Endpointe gecersiz authorization datasi ile "Get" request gonderildiginde status code'in 401 ve message: "Unauthenticated." oldugu dogrulanmalidir
      * API endpointi: "api/merchant/15" icin path parametreleri set edilir
      * Endpointe gecersiz id ile "Get" request gonderildiginde status code'in 400 ve message: "there is no merchant with this id" oldugu dogrulanmalidir
      * API endpointi: "api/merchant/ " icin path parametreleri set edilir
      * Endpointe id olmadan "Get" request gonderildiginde status code'in 400 ve message: "No id." oldugu dogrulanmalidir
