@frdv1
Feature: US019 Bir yönetici (admin) olarak API bağlantısı üzerinden yeni bir Merchant kaydı olusturabilmek istiyorum.

    Background: Token al
      * API testing icin "admin08email" ile token alinir

Scenario: TC001 api/merchant/add endpoint'ine gecerli authorization bilgileri ve dogru datalar (name,business_name,mobile,email,password,address,hub_id,status )
iceren bir POST body gönderildiginde dönen status code'in 200 oldugu ve response body'deki message bilgisinin "New Merchant Added" oldugu dogrulanmali

  * API endpointi: "api/merchant/add" icin path parametreleri set edilir
  * Endpointe POST icin name:"Firdevs",business_name:"Team01",mobile:"505050505050",email:"team01@gmail.com",password:"123654789",address:"NY 55",hub_id:"2",status:"1",olan postBody olusturulur
  * Endpointe postBody ile POST request gonderilir
  * Donen kod'un 200 ve "message" nin "New Merchant Added" oldugu dogrulanir
  * Olusturulan yeni Merchant IDsi ile Merchant list sorgulanir ve name dogrulanir

Scenario: TC002 api/merchant/add endpoint'ine gecersiz authorization bilgileri iceren bir POST body gönderildiğinde
dönen status code'in 401 oldugu ve response body'deki message bilgisinin "Unauthenticated." olduğu doğrulanmali
  
  * API endpointi: "api/merchant/add" icin path parametreleri set edilir
  * Endpointe gecersiz authorization datasi ile "Post" request gonderildiginde status code'in 401 ve message: "Unauthenticated." oldugu dogrulanmalidir
  