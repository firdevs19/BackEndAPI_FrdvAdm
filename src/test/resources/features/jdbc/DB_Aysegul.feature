Feature: Database üzerinden activity_log tablosundaki log_name: User olan dataların log_name: Upload olan datalardan fazla olduğunu doğrulayınız.

  @US03
  Scenario: Database üzerinden activity_log tablosundaki log_name: User olan dataların log_name: Upload olan datalardan fazla olduğunu doğrulayınız
    * Database baglantisi kurulur
    * "SELECT COUNT(*)  AS user_logs, COUNT(*)  AS upload_logs FROM u168183796_qaagiles.activity_log WHERE log_name IN ('User', 'Upload');" icin query hazirla
    * log_name: Upload olan datalardan fazla olduğunu doğrula.
    * Database baglantisi kapatilir.

  @US12
  Scenario: Database üzerinden faqs tablosundaki en eski uptade işlemi yapılan 5  datanın Questions değerlerini doğrulayınız.
    * Database baglantisi kurulur
    * "SELECT id FROM u168183796_qaagiles.faqs ORDER BY updated_at ASC LIMIT 5;" icin query hazirla
    * Alınan Questions değerleri kontrol edilir.
    * Database baglantisi kapatilir.

  @US21
  Scenario: Database üzerinden news_offers tablosuna en eski girilen offer bilgisinin hangi gün ve kim tarafından girildiğini doğrulayınız
    * Database baglantisi kurulur
    * "SELECT title,created_at FROM news_offers ORDER BY created_at ASC LIMIT 1;" icin query hazirla
    * Sorgulama sonucu teyit edilir
    * Database baglantisi kapatilir.