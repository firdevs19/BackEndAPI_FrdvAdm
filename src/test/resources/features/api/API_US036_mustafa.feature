  Feature: Bir yonetici (admin) olarak API baglantisi uzerinden todo List'e erisebilmek istiyorum.

    @testcase1
    Scenario: api/todo/list endpoint'ine gecerli authorization bilgileri
              ile bir GET request gonderildiginde donen status code'un 200 oldugu dogrulanmali

      * "api/todo/list" endpointi icin gerekli path param ayarlamalari yapilir
      * api todo list endpointi icin expected result olusturulur
      * api todo list endpointi icin response body kaydedilir
      * api todo list endpoint icin donen response'un status kodunun 200 oldugu dogrulanir


    @testcase2
    Scenario: api/todo/list endpoint'ine gecersiz authorization bilgileri ile bir
              GET Request gonderildiginde donen status code'un 401 oldugu ve
              response message bilgisinin "Unauthenticated." oldugu dogrulanmali

      * "api/todo/list" endpointi icin gerekli path param ayarlamalari yapilir
      * api todo list endpointi icin expected result kaydedilir
      * api todo list endpointi icin request body olusturulur ve endpointe gecersiz authorization bilgileri GET methodu gonderilir ve gelen response dogrulanir



    @testcase3
    Scenario: Response body icindeki lists icerigi ("id": 7, "title": "New route", "description":
              "The destination of parcel number 123456, which will go to Chicago, will be changed to Washington.",
              "user_id": 86, "date": "2023-09-24", "status": 3, "note": "Congrat!",) olduğu doğrulanmalı.

      * "api/todo/list" endpointi icin gerekli path param ayarlamalari yapilir
      * api todo list endpointi icin expected result olusturulur
      * api todo list endpointi icin response body kaydedilir
      * api todo list endpointi icin donen response'un list icerigi dogrulanir