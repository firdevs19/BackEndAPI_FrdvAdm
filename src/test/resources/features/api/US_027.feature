Feature: Gecerli bilgilerle response dondurme

  @wip12
  Scenario: Gecerli auth. bilgileriyle response dondurme
    * Gerekli "api/blog/1" path parametreler ayarlanir..
    * Endpointe "api/blog/1" gecerli authorization bilgileri ile bir GET request gosterilir
    * Response body kaydedilmeli
    * Donen kod 200 olmalidir.
    * Response body list iceriginin "id" 1 "title" "Cargo Security and Risk Management Crucial Aspects in Transportation" "image_id" 35 oldugunu dogrula
  @wip13
  Scenario: Gecersiz auth bilgileriyle response dondurme
    * Gerekli "api/blog/1" path parametreler ayarlanir..
    * Endpointe "api/blog/1" gecersiz authorization bilgileri ile bir GET request gonderilir
    * Response body kaydedilmeli
    * Donen koda 401 mesaj "Unauthenticated" olmalidir.

  @wip14
  Scenario: Eksik data ile response dondurme
    * Gerekli "api/blog/9898" path parametreler ayarlanir..
    * Endpointe "api/blog/9898" gecersiz data ile bir GET request gonderilir
    * Response body kaydedilmeli
    * Donen kod 400 mesaj "there is no blog with this id" olmalidir.
  @wip15
  Scenario: Data gonderilmediginde donen response
    * Gerekli "api/blog/" path parametreler ayarlanir..
    * Endpointe "api/blog/" data gonderilmeden bir GET request gonderilir
    * Response body kaydedilmeli
    * Donen koda 400 mesaj "No id." olmalidirr..