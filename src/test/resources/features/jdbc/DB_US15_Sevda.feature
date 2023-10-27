Feature: Database üzerinden merchant_delivery_charges tablosundan "merchant_id" değeri 1 olan ve farklı "delivery_charge_id" değerlerine sahip olanların "sub_city" değerlerini toplayıp her bir "delivery_charge_id" için sonuçları listeleyip doğrulayınız.

  @query15

    Scenario: Merchant_delivery_charges tablosundaki sub_city değerlerini topla ve listele
    * Database baglantisi kurulur
    * "SELECT delivery_charge_id, SUM(sub_city) AS total_sub_city, FROM merchant_delivery_charges, WHERE merchant_id = 1, GROUP BY delivery_charge_id, HAVING COUNT(DISTINCT delivery_charge_id) > 1" icin Query hazirla
    * Listelenen sonuclar dogrulanir
    * Database baglantisi kapatilir




