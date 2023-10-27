
Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden id'si verilen
         kullanicinin todo bilgilsini güncellemek istiyorum.


  @us39TestCase1
  Scenario: api/todo/edit/id endpoint'ine gecerli authorization bilgileri ve dogru datalar
            (title, description, user_id,date,status,note) iceren bir PATCH body gönderildiginde
            dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Updated"
            oldugu dogrulanmali

    * "api/todo/edit/3" endpointi icin path parametre ayarlamalari yapilir
    * api todo edit id endpointi icin request body olusturulur
    * api todo edit id endpointi icin expected result  olusturulur
    * api todo edit id endpointi icin response body kaydedilir
    * api todo edit id endpointi icin donen response'un status  codunun 200 ve responsetaki mesaj bilgisinin "Updated" oldugu dogrulanir


  @us39TestCase2
  Scenario: api/todo/edit/id endpoint'ine gecersiz authorization bilgileri veya eksik/yanlış
            data (id) iceren bir PATCH body (title, description, user_id,date,status,note)
            iceren bir PATCH body gönderildiginde dönen status code'in 400 oldugu ve
            response body'deki message bilgisinin geçersiz data
            icin "there is no ToDo with this id", data gönderilmediğinde "No shop id."
            gecersiz authorization bilgilerinde ise "Unauthenticated." olduğu doğrulanmali


    * "api/todo/edit/3" endpointi icin path parametre ayarlamalari yapilir
    * api todo edit id endpointi icin eksik request body olusturulur
    * api todo edit 107 endpointi icin gecersiz data ile donen response'un status kodunun 400 ve mesaj bilgisinin "there is no ToDo with this id"  oldugu dogrulanir
    * api todo edit 107 endpointi  icin data gonderilmediğinde status kodunun 400 ve mesaj bilgisinin "No id." oldugu dogrulanir
    * api todo edit 107 endpointi icin gecersiz tokenla donen response'un status  codunun 401 ve mesaj bilgisinin "Unauthenticated." oldugu dogrulanir

  @us39TestCase3
  Scenario: Response body icindeki list datalarının (title, description, user_id,date,status,note)
            içerikleri doğrulanmali.

    * "api/todo/edit/3" endpointi icin path parametre ayarlamalari yapilir
    * api todo edit id endpointi icin request body olusturulur
    * api todo edit id endpointi icin expected result  olusturulur
    * api todo edit id endpointi icin response body kaydedilir
    * response body icindeki list datalarinin icerikleri dogrulanir
