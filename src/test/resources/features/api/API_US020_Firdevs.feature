@frdv1
  Feature: US020 Bir yönetici (admin) olarak API bağlantisi üzeriden sisteme kayitli Delivery man bilgilerini güncelleyebilmek istiyorum.

    Background: Token al
      * API testing icin "admin08email" ile token alinir


    Scenario: TC001 api/mercahnt/edit/id endpoint'ine gecerli authorization bilgileri ve dogru datalar business_name,mobile,email) iceren
    bir PATCH body gönderildiginde dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Updated" oldugu dogrulanmali

      * API endpointi: "api/merchant/edit/344" icin path parametreleri set edilir
      * Endpointe PATCH icin business_name: "Abuzer", mobile: "55550505055", email: "kadayif@gmail.com" olan patchBody olusturulur
      * Endpointe PATCH request gonderilir
      * Donen kod'un 200 ve "message" nin "Updated" oldugu dogrulanir
      * Response Bodyde islem yapilan ID 344 var oldugu dogrulanir
      * API endpointi: "api/merchant/344" icin path parametreleri set edilir
      * Merchant kaydinin guncellendigi, API uzerinden dogrulanir

      Scenario: TC002 api/mercahnt/edit/id endpoint'ine gecersiz authorization bilgileri veya eksik/yanlış data (id) iceren
      bir PATCH body (name, email, hub_id, mobile, address, status) gönderildiginde dönen status code'un 400 oldugu ve
      response message bilgisinin geçersiz data icin "there is no merchant with this id", data gönderilmediğinde "No id.",
      gecersiz authorization bilgilerinde ise  status code'in 401 ve "Unauthenticated." olduğu doğrulanmali

        * API endpointi: "api/merchant/edit/346" icin path parametreleri set edilir
        * Endpointe gecersiz authorization datasi ile "Patch" request gonderildiginde status code'in 401 ve message: "Unauthenticated." oldugu dogrulanmalidir
        * API endpointi: "api/merchant/edit/15" icin path parametreleri set edilir
        * Endpointe gecersiz id ile "Patch" request gonderildiginde status code'in 400 ve message: "there is no merchant with this id" oldugu dogrulanmalidir
        * API endpointi: "api/merchant/edit/" icin path parametreleri set edilir
        * Endpointe id olmadan "Patch" request gonderildiginde status code'in 400 ve message: "No id." oldugu dogrulanmalidir
