Feature: Bir yönetici (admin) olarak API bağlantisi üzeriden sisteme kayitli Pickup bilgilerini güncelleyebilmek istiyorum.

  @wip6
  Scenario: api/pickuprequest/regular/edit/id endpoint'ine gecerli authorization bilgileri ve dogru datalar iceren bir PATCH body gönderildiginde dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Updated" oldugu dogrulanmali
    * Gerekli "api/pickuprequest/regular/edit/14" path parametreler set edilmeli.
    * Request body olusturulur
    * Endpointe 14 ID no'lu PATCH request gonderilmeli
    * Donen kod'un 200 oldugu ve mesaj bilgisinin "Updated" oldugu dogrulanmali dogrulanmali
    * Response body icindeki updateId bilgisinin "api/pickuprequest/1" endpoint'ine gönderilen PATCH request body icindeki id bilgisi ile ayni oldugu dogrulanmali.
    * API uzerinden guncellenmek istenen Pickup kaydinin guncellendigi, API uzerinden dogrulanmali.
  @wip7
  Scenario:
    * Gerekli "api/pickuprequest/regular/edit/id" path parametreler set edilmeli.
    * Endpointe gecersiz authorization bilgileri girildiginde kodun 401 ve mesaj bilgisinin "Unauthenticated." oldugu dogrulanmali