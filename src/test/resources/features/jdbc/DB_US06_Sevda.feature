Feature: Database üzerinden courier_statements tablosundaki parcel_id numarası verilen parcellerden her bir parselin benzersiz bir şekilde kaç farklı teslimatçı tarafından taşındığını listeleyip doğrulayınız.

  @query06

  Scenario: courier_statements tablosunda parcel_id'si verilen parcelleri test etme
    * Database baglantisi kurulur
    * Query olusturulur ve statement araciligi ile gönderilir
    * Donen data dogrulanir
    * Database baglantisi kapatilir




