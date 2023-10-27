Feature: Database uzerinden partners tablosundaki en eski partnerimizin ismini dogrulayiniz.
  @query24

  Scenario: En eski partnerimizin ismi dogrulanir
    * Database baglantisi kurulur
    * Database uzerinden partners tablosundaki en eski partnerimizin ismini dogrulayiniz
    * En eski partnerimizin ismi dogrulanmalidir
    * Database baglantisi kapatilir