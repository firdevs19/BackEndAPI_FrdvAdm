Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden bir todo oluşturmak istiyorum.

  Scenario: api/todo/add endpoint'ine gecerli authorization bilgileri ve
            dogru datalar (title, description, user_id,date,status,note) iceren
            bir POST body gönderildiginde dönen status code'in 200 oldugu ve response
            body'deki message bilgisinin "New ToDo Added" oldugu dogrulanmali


    * "api/todo/add" endpointi icin gerekli path parametreleri ayarlamalari yapilir
    * api todo add endpointi icin request body olusturulur
    * api todo add endpointi icin response body kaydedilir
    * api todo add endpointi icin expected result  olusturulur
    * api todo add endpointi icin donen response'un status  codunun 200 ve mesaj bilgisinin "New ToDo Added" oldugu dogrulanir


  Scenario: api/todo/add endpoint'ine gecersiz authorization bilgileri iceren bir POST body
            gönderildiğinde dönen status code'in 401 oldugu ve response body'deki message bilgisinin
            "Unauthenticated." olduğu doğrulanmali


    * "api/todo/add" endpointi icin gerekli path parametreleri ayarlamalari yapilir
    * api todo add endpointi icin eksik request body olusturulur
    * api todo add endpoint'ine gecersiz authorization bilgileri ile  POST methodu gonderilir
    * api todo add endpointi icin expected result bilgileri  olusturulur
    * api todo add endpointi icin response body bilgileri kaydedilir
    * api todo add endpointi icin donen response'un status  codunun 401 ve bodydeki mesaj bilgisinin "Unauthenticated." oldugu dogrulanir



  Scenario: API uzerinden olusturulmak istenen yeni notice kaydinin olustugu API uzerinden
            dogrulanmali. (Response bodyde dönen addId ile api/getNoticeById endpoint'ine POST body
            gönderilerek kayıt oluşturulduğu doğrulanabilir.)


    * "api/todo/add" endpointi icin gerekli path parametreleri ayarlamalari yapilir
    * api todo add endpointi icin request body olusturulur
    * api todo add endpointi icin expected result hazirlanir
    * api todo add endpointi icin response body kaydedilir
    * API uzerinden yeni notice kaydinin olustugu dogrulanir

