@frdv0
  Feature: US003 Bir yönetici (admin) olarak API bağlantısı üzerinden yeni bir hub kaydı olusturabilmek istiyorum.
    Background: Token al
      * API testing icin "admin08email" ile token alinir

  Scenario: TC001 api/hub/add endpoint'ine gecerli authorization bilgileri ve dogru datalar (name,phone,address,current balance,status) iceren
          bir POST body gönderildiginde dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Hub is added" oldugu dogrulanmali
          API uzerinden olusturulmak istenen yeni hub kaydinin olustugu API uzerinden dogrulanmali.
          (Response bodyde dönen New Hub ID ile api/hub/id endpoint'ine GET body gönderilerek kayıt oluşturulduğu doğrulanabilir.

    * API endpointi: "api/hub/add" icin path parametreleri set edilir
    * Endpointe request icin name:"Firdevs",phone:"Team01",address:"505050505050",current_balance:100000,status:1,olan Body olusturulur
    * Endpointe Hub postBody ile POST request gonderilir
    * Hub requestten donen kod'un 200 ve "message" nin "Hub is added" oldugu dogrulanir
    * Olusturulan yeni Hub IDsi ile Hub list sorgulanir ve name dogrulanir


  Scenario: TC002 api/hub/add endpoint'ine eksik datalar (name,phone,address,current balance,status) iceren bir POST body
        gönderildiginde dönen status code'in 400 oldugu ve response body'deki message bilgisinin "Name, phone and adress required", dogru datalar (name,phone,address,current balance,status) iceren
        fakat gecersiz authorization bilgileri ile bir POST body gönderildiğinde ise donen statusCode= 401 message bilgisinin ise "Unauthenticated." oldugu dogrulanmali.

    * API endpointi: "api/hub/add" icin path parametreleri set edilir
    * Endpointe gecersiz authorization datasi ile Post request gonderildiginde status code'in 401 ve message: "Unauthenticated." oldugu dogrulanmalidir
    * Endpointe gecersiz data ile Post request gonderildiginde status code'in 400 ve message: "Name, phone and adress required" oldugu dogrulanmalidir

