@frdv11
  Feature: US021 Bir yönetici olarak API baglantisi üzerinden sistemdeki merchant kaydini silebilmeliyim.

    Background: Token al
      * API testing icin "admin08email" ile token alinir

    Scenario: TC001 api/merchant/delete/id endpoint'ine gecerli authorization bilgileri ve dogru data (id) iceren bir DELETE body gönderildiginde
              dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Deleted" oldugu dogrulanmali
    API uzerinden silinmek istenen merchant kaydinin silindigi, API uzerinden dogrulanmali.
    (Response body'de dönen DeletedId ile api/merchant/id endpoint'ine GET body gönderilerek kaydın silindiği doğrulanabilir.)

      * API endpointi: "api/merchant/delete/363" icin path parametreleri set edilir
      # Yukaridaki Adimda girlen ID daha onceden silinmis olabilir.
          # Bu durumda US019'da yeni olusturulan ID kullanilabilir veya bilinen baska bir ID.
      * Endpointe silme islemi icin DELETE request gonderilir
      * Donen kod'un 200 ve "message" nin "Deleted" oldugu dogrulanir
      * API endpointi: "api/merchant/363" icin path parametreleri set edilir
      * Endpointe silinen id ile Get request gonderildiginde status code'in 400 ve message: "there is no merchant with this id" oldugu dogrulanmalidir

  Scenario: TC002 api/merchant/delete/id endpoint'ine gecersiz authorization bilgileri veya yanlis data (id) iceren
  bir DELETE body gönderildiginde dönen status code'in 400 oldugu ve response body'deki
  message bilgisinin data gönderilmediğinde "No id.",
  gecersiz authorization bilgilerinde ise status code'in 401 ve "Unauthenticated." olduğu doğrulanmali

    * API endpointi: "api/merchant/delete/363" icin path parametreleri set edilir
    * Endpointe gecersiz authorization datasi ile "Delete" request gonderildiginde status code'in 401 ve message: "Unauthenticated." oldugu dogrulanmalidir
    * API endpointi: "api/merchant/delete/15" icin path parametreleri set edilir
    * Endpointe gecersiz id ile "Delete" request gonderildiginde status code'in 400 ve message: "there is no merchant with this id" oldugu dogrulanmalidir
    * API endpointi: "api/merchant/delete/" icin path parametreleri set edilir
    * Endpointe id olmadan "Delete" request gonderildiginde status code'in 400 ve message: "No id." oldugu dogrulanmalidir