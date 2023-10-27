@frdv0
  Feature: US004 Bir yönetici (admin) olarak API bağlantisi üzeriden sisteme kayitli visitor purpose bilgilerini güncelleyebilmek istiyorum.

    Background: Token al
      * API testing icin "admin08email" ile token alinir

   Scenario: TC001 api/hub/edit/id endpoint'ine gecerli authorization bilgileri ve dogru datalar (name,phone,address,current balance,status)
   iceren bir PATCH body gönderildiginde dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Updated" oldugu dogrulanmali
   Response body icindeki "id'' bilgisinin api/hub/edit/id endpoint'ine eklenerek gonderilen "id'' bilgisi ile ayni oldugu dogrulanmali.
   Guncellenmek istenen HUB kaydinin guncellendigi, API uzerinden dogrulanmali.


     * API endpointi: "api/hub/85" icin path parametreleri set edilir
     * Endpointe bir GET request gonderilir
     * API endpointi: "api/hub/edit/85" icin path parametreleri set edilir
     * Endpointe request icin name:"Abuzer",phone:"11111111",address:"Ankara 55",current_balance:55,status:0,olan Body olusturulur
     * Hub Endpointe PATCH request gonderilir
     * Hub requestten donen kod'un 200 ve "message" nin "Updated" oldugu dogrulanir
     * Guncellenen 85 nolu Hub IDsi ile Hub list sorgulanir ve veriler dogrulanir

   Scenario: TC002 api/hub/edit/id endpoint'ine eksik/yanlış (id) eklenerek bir PATCH body (name,phone,address,current balance,status)
   gönderildiginde dönen status code'in 400 oldugu ve response body'deki message bilgisinin
   geçersiz data'da "There is no Hub with this id",
   veya endpoint´e herhangi bir id eklenmeden PATCH body gönderildiğinde status code'in 400 oldugu ve
   message bilgisinin "there is no hub with this id" oldugu dogrulanmali

     * API endpointi: "api/hub/edit/346" icin path parametreleri set edilir
     * Endpointe gecersiz authorization datasi ile Hub Patch request gonderildiginde status code'in 401 ve message: "Unauthenticated." oldugu dogrulanmalidir
     * API endpointi: "api/hub/edit/22" icin path parametreleri set edilir
     * Endpointe gecersiz id datasi ile Hub Patch request gonderildiginde status code'in 400 ve message: "There is no Hub with this id" oldugu dogrulanmalidir
     * API endpointi: "api/hub/edit/" icin path parametreleri set edilir
     * Endpointe id olmadan Hub Patch request gonderildiginde status code'in 400 ve message: "No shop id." oldugu dogrulanmalidir
