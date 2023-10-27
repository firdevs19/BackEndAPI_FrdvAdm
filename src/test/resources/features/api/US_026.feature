Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden tüm pickup listesine erişebilmek istiyorum.

  @wip10
  Scenario: api/blog/list endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde kodun 200 oldugu dogrulanmali

    * Gerekli "api/blog/list" path param ayarlari yapilir
    * Gecerli authorization bilgileri ile GET request gonderilir
    * Response body kaydedilir
    * Donen kodun 200 oldugu dogrulanir
    * List iceriginin "id" nin 1 "title" in "Cargo Security and Risk Management Crucial Aspects in Transportation" "image_id" nin 35 oldugu dogrulanir
  @wip11
  Scenario:
    * Gerekli "api/blog/list" path param ayarlari yapilir
    * Endpoint "api/blog/id" gecersiz authorization bilgileri ile bir GET Request gonderildiginde donen status code'un 401  mesajin "Unauthenticated" oldugu dogrulanir