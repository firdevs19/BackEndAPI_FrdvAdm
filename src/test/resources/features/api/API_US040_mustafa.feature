Feature: Bir yönetici (admin) olarak API baglantisi üzerinden sistemdeki todo kaydini silebilmeliyim.


  @us40testcase1
  Scenario: api/todo/delete/id endpoint'ine gecerli authorization bilgileri ve dogru data
            (id) iceren bir DELETE body gönderildiginde dönen status code'in 200 oldugu ve
            response body'deki message bilgisinin "Deleted" oldugu dogrulanmali


    * "api/todo/delete/54" endpointi icin path parametre ayarlamasi yapilir
    * api todo delete id endpointi icin expected result  olusturulur
    * api todo delete id endpointi icin response body kaydedilir
    * api todo delete id endpointi icin donen response'un status  codunun 200 ve responsetaki mesaj bilgisinin "Deleted" oldugu dogrulanir


  @us40testcase2
  Scenario: api/todo/delete/id endpoint'ine gecersiz authorization bilgileri veya yanlis data
            (id) iceren bir DELETE body gönderildiginde dönen status code'in 400 oldugu ve response message
            bilgisinin bilgisinin geçersiz data icin "there is no ToDo with this id", data gönderilmediğinde
            "No id.",gecersiz authorization bilgilerinde ise "Unauthenticated." olduğu doğrulanmali

    * "api/todo/delete" endpointi icin path parametre ayarlamasi yapilir
    * api todo delete endpointi icin gecersiz data ile donen response'un status kodunun 400 ve mesaj bilgisinin "there is no ToDo with this id"  oldugu dogrulanir
    * api todo delete endpointi icin gecersiz tokenla donen response'un status  codunun 401 ve mesaj bilgisinin "Unauthenticated." oldugu dogrulanir


  @us40testcase3
  Scenario: API uzerinden silinmek istenen visitor kaydinin silindigi, API uzerinden
            dogrulanmali. (Response body'de dönen DeletedId ile api/ticket/delete/id
            endpoint'ine POST body gönderilerek kaydın silindiği doğrulanabilir.)


    * "api/todo/delete/55" endpointi icin path parametre ayarlamasi yapilir
    * api todo delete id endpointi icin expected result  olusturulur
    * api todo delete id endpointi icin response body kaydedilir
    * api todo delete id endpointi icin visitor kaydinin silindigi dogrulanir