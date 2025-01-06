package tr.com.bilisim.webservis.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_KAL_RED", schema = "ERPKALITE")
public class KaliteRed {

    @Id
    @Column(name = "RED_ID", nullable = false)
    private Long redId;

    @Column(name = "RED_RAPOR_NO")
    private Long redRaporNo;

    @Column(name = "TARIH")
    private LocalDate tarih;

    @Column(name = "MLZ_ID")
    private Long malzemeId;

    @Column(name = "IS_EMRI_NO")
    private Long isEmriNo;

    @Column(name = "FIRMA_ID")
    private Long firmaId;

    @Column(name = "FIRMA_RAPOR_NO", length = 100)
    private String firmaRaporNo;

    @Column(name = "FIRMA_RAPOR_TAR")
    private LocalDate firmaRaporTarihi;

    @Column(name = "HATA_YAKALANMA_YERI", length = 5)
    private String hataYakalanmaYeri;

    @Column(name = "HATA_YAKA_DETAY_YERI", length = 5)
    private String hataYakaDetayYeri;

    @Column(name = "SORUMLU_ANA_BOLUM_ID")
    private Long sorumluAnaBolumId;

    @Column(name = "STKGIR_GKK_ID")
    private Long stokGirisGkkId;

    @Column(name = "ASIRI_NAVLUN")
    private Integer asiriNavlun;

    @Column(name = "HATA_ID")
    private Long hataId;

    @Column(name = "URUN_KARAKTER_ID")
    private Long urunKarakterId;

    @Column(name = "USTU_ALTI", length = 5)
    private String ustuAlti;

    @Column(name = "OLCULEN_DEGER", length = 200)
    private String olculenDeger;

    @Column(name = "RED_ACIKLAMA", length = 4000)
    private String redAciklama;

    @Column(name = "RASLANMA_YUZDE", precision = 6, scale = 2)
    private BigDecimal raslanmaYuzde;

    @Column(name = "HATANIN_BAS_PARCA_ADEDI", precision = 15, scale = 5)
    private BigDecimal hataninBasParcaAdedi;

    @Column(name = "AYRILAN_PARCA_ADEDI", precision = 15, scale = 5)
    private BigDecimal ayrilanParcaAdedi;

    @Column(name = "AYIKLANAN_MIKTAR", precision = 15, scale = 5)
    private BigDecimal ayiklananMiktar;

    @Column(name = "AYIKLAMADA_AYRILAN_MIKTAR", precision = 15, scale = 5)
    private BigDecimal ayiklamadaAyrilanMiktar;

    @Column(name = "RED_KATSAYI", precision = 15, scale = 5)
    private BigDecimal redKatsayi;

    @Column(name = "NEDEN_ID")
    private Long nedenId;

    @Column(name = "NEDEN_ACIKLAMA", length = 200)
    private String nedenAciklama;

    @Column(name = "IADE_EDILEN_MAL_TUTAR", precision = 15, scale = 5)
    private BigDecimal iadeEdilenMalTutar;

    @Column(name = "DUZELTME_FATURA_TUTAR", precision = 15, scale = 5)
    private BigDecimal duzeltmeFaturaTutar;

    @Column(name = "PARA_BIRIM", length = 5)
    private String paraBirim;

    @Column(name = "BELGE_ID")
    private Long belgeId;

    @Column(name = "BELGE_REF_ID")
    private Long belgeRefId;

    @Column(name = "ETKGST")
    private Integer etkgst;

    @Column(name = "FABRIKA_KOD")
    private Integer fabrikaKod;

    @Column(name = "RED_TIP", length = 15)
    private String redTip;

    @Column(name = "RED_ALT_TIP", length = 9)
    private String redAltTip;

    @Column(name = "SORUMLU_ALT_MAK_ID")
    private Long sorumluAltMakId;

    @Column(name = "SORUMLU_PERSONEL_ID")
    private Long sorumluPersonelId;

    @Column(name = "OPERASYON_ID")
    private Long operasyonId;

    @Column(name = "OPERATOR_ID")
    private Long operatorId;

    @Column(name = "GERCEKLESEN_MIKTAR", precision = 15, scale = 5)
    private BigDecimal gerceklesenMiktar;

    @Column(name = "KAYIT_DEG_TAR")
    private LocalDate kayitDegTar;

    @Column(name = "KAYIT_GIR_TAR")
    private LocalDate kayitGirTar;

    @Column(name = "KAYIT_DEG_KULLANICI", length = 30)
    private String kayitDegKullanici;

    @Column(name = "KAYIT_GIR_KULLANICI", length = 100)
    private String kayitGirKullanici;

    @Column(name = "LAST_UPDATE_DATE")
    private LocalDateTime lastUpdateDate;

    @Column(name = "CREATE_DATE")
    private LocalDate createDate;

    @Column(name = "OPTLOCK")
    private Long optlock;

    @Column(name = "TEST", length = 100)
    private String test;

    @Column(name = "HATA_ACIKLAMA", length = 4000)
    private String hataAciklama;

    @Column(name = "SIPURUN_ID")
    private Long sipurunId;

    @Column(name = "OPERASYON_ID2")
    private Long operasyonId2;

    @PrePersist
    protected void onCreate() {
        lastUpdateDate = LocalDateTime.now();
        createDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdateDate = LocalDateTime.now();
    }
}