    @DBuser23
    Feature: Database üzerinden parcels tablosunda 'ismi_ali_olanlar' adında
             bir column create edilip customer_name içinde 'ali' gecenler olusturulan
             columnda 1 gecmeyenler de 0 ile gösterildiği doğrulanmalı.

      Scenario: Database üzerinden parcels tablosunda 'ismi_ali_olanlar'
                adında bir column create edilip customer_name içinde 'ali' gecenler
                lusturulan columnda 1 gecmeyenler de 0 ile gösterildiği doğrulanir


        * Database connection kurulmali
        * parcels tablosundaki ismi_ali_olanlar adli  bir column create edilmeli
        * hubs tablosundaki customer_name icinde ali gecenler create edilen columnda 1 olmayanlar 0 ile gosterildigi dogrulanmali
        * Database connection kapatilmali











