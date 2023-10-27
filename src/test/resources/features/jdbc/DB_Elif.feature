Feature: US09/US18/US27

  @US09
  Scenario: Database uzerindendelivery_man tablosundaki id:13 olan deliveryman'in delivery_charge bilgisini 333.33 olarak guncelleyip gincellendigini dogrulayiniz.

    * Database baglantisi kurulmalidir
    * Deliveryman delivery_charge icin Query olusturulur ve statement araciligi ile gonderilir
    * id:13 olan deliveryman'in delivery_charge bilgisini 333.33 olarak guncelleyip guncellendigi dogrulanmali
    * Database baglantisi kapatilmalidir

  @US18
  Scenario:
    * Database baglantisi kurulmalidir
    * Merchant statements tablosundaki tarihe gore tasinacak parcalari gun gun grupla
    * Yapilan gruplandirmayi dogrula
    * Database baglantisi kapatilmalidir

  @US27
  Scenario:Database uzerinden supports tablosundaki service degeri odeme ile ilgili olan supportlarin priority degerlerini highest yaparak, islemin gerceklestigini dogrulayiniz
    * Database baglantisi kurulmalidir
    * Service degeri odeme ile ilgili olan support degerlerinin priority degerleri highest yapilmali
    * Degerlerin highest yapildigi dogrulanmalidir
    * Database baglantisi kapatilmalidir