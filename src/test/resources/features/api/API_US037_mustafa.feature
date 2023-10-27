Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden id si verilen ticket ın bilgilierine erişebilmek istiyorum.

  @us37testcase1
  Scenario: api/todo/id endpoint'ine gecerli authorization bilgileri ve id bilgileri
            ile bir GET request gönderildiginde dönen status code'un 200 oldugu dogrulanmali

    * "api/todo/3" endpointi icin path param ayarlamalari yapilir
    * api todo id endpointi icin expected result olusturulur
    * api todo id endpointi icin response body kaydedilir
    * api todo id endpointi icin donen response'un status  codunun 200 oldugu dogrulanir


    @us37testcase2
  Scenario: api/todo/id endpoint'ine gecersiz authorization bilgileri ve id bilgileri ile
            bir GET Request gönderildiginde dönen status code'un 401 oldugu ve response
            message bilgisinin bilgisinin geçersiz data icin "there is no ToDo with this id",
            data gönderilmediğinde "No id.",gecersiz authorization bilgilerinde ise "Unauthenticated."
            olduğu doğrulanmali

    * "api/todo/3" endpointi icin path param ayarlamalari yapilir
    * api todo id endpointi icin expected result bilgileri olusturulur
    * api todo id endpointi icin gecersiz data ile donen response'un status kodunun 400 ve mesaj bilgisinin "there is no ToDo with this id"  oldugu dogrulanir
    * api todo id endpointi icin data gonderilmediğinde status kodunun 400 ve mesaj bilgisinin "No id." oldugu dogrulanir
    * api todo id endpointi icin gecersiz tokenla donen response'un status  codunun 401 ve mesaj bilgisinin "Unauthenticated." oldugu dogrulanir

  @us37testcase3
  Scenario: Response body icindeki lists icerigi ("id": 3, "title": "Fragile Items", "description":
            "Double-check that fragile items are securely packed.","user_id": 36, "date": "2023-09-04",
            "status": 3, "note": "tamam",) olduğu doğrulanmalı.

    * "api/todo/3" endpointi icin path param ayarlamalari yapilir
    * api todo id endpointi icin expected result  hazirlanir
    * api todo id endpointi icin response body kaydedilir
    * api todo id endpointi icin donen response'un list iceriginin beklenen result ile ayni oldugu dogrulanir

