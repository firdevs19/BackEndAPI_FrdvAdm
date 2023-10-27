    Feature: Database uzerinden bank_transactions tablosundaki datalardan
            '2023-08-23 11:30:34' ile '2023-10-01 20:44:08' tarihleri arasinda
             olusturulan datalarin amount bilgilerini kucukten buyuge dogru siralayarak
             listeleyip dogrulayiniz.

      @us05
      Scenario:  Database uzerinden bank_transactions tablosundaki datalardan '2023-08-23 11:30:34'
                 ile '2023-10-01 20:44:08' tarihleri arasinda olusturulan datalarin amount bilgilerini
                 kucukten buyuge dogru siralama testi

        * Database connection kurulmali
        * bank_transactions tablosundaki belirtilen tarihler arasindaki datalarin amount bilgileri getirilmeli ve siralanmali
        * bank_transactions tablosundaki belirtilen tarihler arasindaki datalarin amount bilgilerinin kucukten buyuge dogru siralandigi dogrulanir
        * Database connection kapatilmali











