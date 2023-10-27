Feature: api/pickuprequest/delete/id endpoint'ine gecerli authorization bilgileri ve dogru data (id)
  iceren bir DELETE body gönderildiginde
  dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Deleted" oldugu dogrulanmali
  @wip8
  Scenario:
    * Gerekli "api/pickuprequest/delete/88" path parametreler ayarlanir.
    * Endpointe DELETE request gonderilir
    * Donen kod 200 ve mesaj bilgisi "Deleted" olmali
    * Response body icindeki deletedData bilgisinin "api/pickuprequest/delete/id" endpoint'ine gonderilen DELETE request body icindeki id bilgisi ile ayni oldugu dogrulanmali.
    * API uzerinden silinmek istenen pickup kaydinin silindigi, API uzerinden dogrulanmali.
  @wip9
  Scenario:
    * Gerekli "api/pickuprequest/9898" path parametreler ayarlanir.
    * Endpointe gecersiz id 9898 bilgisi gonderilir
    * Gecersiz id 9898 bilgisi gonderildiginde kodun 400 mesaj bilgisinin "there is no shop with this id" oldugu dogrulanir