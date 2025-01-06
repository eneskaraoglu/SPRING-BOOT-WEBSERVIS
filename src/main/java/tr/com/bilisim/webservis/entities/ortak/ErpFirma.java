package tr.com.bilisim.webservis.entities.ortak;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ERP_FIRMA", schema = "ERPORT")
public class ErpFirma {

    @Id
    @Column(name = "FIRMA_ID")
    private Long firmaId;

    @Column(name = "FIRMA_KODU", length = 50)
    private String firmaKodu;

    @Column(name = "FIRMA_ADI", length = 1000)
    private String firmaAdi;

    @Column(name = "FIRMA_TURU")
    private Integer firmaTuru;

    @Column(name = "FIRMA_SEKTORU", length = 4)
    private String firmaSektoru;

    @Column(name = "SORUMLU", length = 100)
    private String sorumlu;

    @Column(name = "SORUMLU_UNVAN", length = 100)
    private String sorumluUnvan;

    @Column(name = "ETKGST")
    private Integer etkgst;

    @Column(name = "SORUNGST")
    private Integer sorungst;

    @Column(name = "ACIKLAMA", length = 50)
    private String aciklama;

    @Column(name = "VERGI_DAIRESI", length = 30)
    private String vergiDairesi;

    @Column(name = "VERGI_NO", length = 30)
    private String vergiNo;

    @Column(name = "TIC_SICIL_NO", length = 100)
    private String ticSicilNo;

    @Column(name = "KALITE_SORUMLU", length = 30)
    private String kaliteSorumlu;

    @Column(name = "KALITE_SORUMLU_UNVAN", length = 30)
    private String kaliteSorumluUnvan;

    @Column(name = "FABRIKA_KOD", nullable = false)
    private Integer fabrikaKod;

    @Column(name = "FIRMA_DETAY_TURU", length = 2)
    private String firmaDetayTuru;

    @Column(name = "URETICI_KOD", length = 100)
    private String ureticiKod;

    @Column(name = "GERCEK_OR_TUZEL")
    private Integer gercekOrTuzel;

    @Column(name = "ALIS_VADE")
    private Integer alisVade;

    @Column(name = "SATIS_VADE")
    private Integer satisVade;

    @Column(name = "DOVIZ_ID")
    private Long dovizId;

    @Column(name = "KURTUR_ID")
    private Long kurturId;

    @Column(name = "KURYER_ID")
    private Long kuryerId;

    @Column(name = "ASIL_FIRMA")
    private Integer asilFirma;

    @Column(name = "KARSI_KOD_ARANIR")
    private Integer karsiKodAranir;

    @Column(name = "YURT_DISI")
    private Integer yurtDisi;

    @Column(name = "FIRMA_MUH_ID")
    private Long firmaMuhId;

    @Column(name = "GIRIS_MUAYENE_METOD", length = 5)
    private String girisMuayeneMetod;

    @Column(name = "LAST_UPDATE_DATE")
    private LocalDateTime lastUpdateDate;

    @Column(name = "SIPARIS_SEKLI", length = 5)
    private String siparisSekli;

    @Column(name = "TESLIMAT_ONCELIK", precision = 6, scale = 2)
    private BigDecimal teslimatOncelik;

    @Column(name = "YET_SATICI_GOST")
    private Integer yetSaticiGost;

    @Column(name = "YET_SERVIS_GOST")
    private Integer yetServisGost;

    @Column(name = "YEDEK_PARCA_BAYI_GOST")
    private Integer yedekParcaBayiGost;

    @Column(name = "SERVIS_DIS_HIZ_GOST")
    private Integer servisDisHizGost;

    @Column(name = "FIRMA_KATEGORI", length = 5)
    private String firmaKategori;

    @Column(name = "BULUNDUGU_IL")
    private Integer bulunduguIl;

    @Column(name = "BULUNDUGU_ILCE")
    private Integer bulunduguIlce;

    @Column(name = "SATIS_BOLGE_ID")
    private Long satisBolgeId;

    @Column(name = "STD_SIP_BAS_KOD", length = 10)
    private String stdSipBasKod;

    @Column(name = "SIPNO_VERME_TIP", length = 10)
    private String sipnoVermeTip;

    @Column(name = "STD_SIP_BAS_KOD2", length = 10)
    private String stdSipBasKod2;

    @Column(name = "KAYIT_DEGISTRN_TARH")
    @Temporal(TemporalType.DATE)
    private Date kayitDegistrnTarh;

    @Column(name = "KAYIT_YAPAN_TARH")
    @Temporal(TemporalType.DATE)
    private Date kayitYapanTarh;

    @Column(name = "KAYIT_YAPAN_KULLANCI", length = 30)
    private String kayitYapanKullanci;

    @Column(name = "KAYIT_DEGISTRN_KULLANCI", length = 30)
    private String kayitDegistrnKullanci;

    @Column(name = "ODEME_YAPTIGI_GUN")
    private Integer odemeYaptigiGun;

    @Column(name = "FIRMAYA_ODEME_YAPILAN_GUN")
    private Integer firmayaOdemeYapilanGun;

    @Column(name = "SERMAYESI", length = 30)
    private String sermayesi;

    @Column(name = "ALIS_VADE_TURU", length = 1)
    private String alisVadeTuru;

    @Column(name = "SATIS_VADE_TURU", length = 1)
    private String satisVadeTuru;

    @Column(name = "ULKE", length = 3)
    private String ulke;

    @Column(name = "NAK_FIRMA_ID")
    private Long nakFirmaId;

    @Column(name = "POT_MUSTERI_ID")
    private Long potMusteriId;

    @Column(name = "MUSTERI_ANKET_NOT", length = 1000)
    private String musteriAnketNot;

    @Column(name = "RISK_TUTARI", precision = 15, scale = 5)
    private BigDecimal riskTutari;

    @Column(name = "ANA_FIRMA_ID")
    private Long anaFirmaId;

    @Column(name = "DIGER_FAB_FIRMA_ID")
    private Long digerFabFirmaId;

    @Column(name = "FIRMA_SEKTORU_2", length = 4)
    private String firmaSektoru2;

    @Column(name = "FIRMA_SEKTORU_3", length = 4)
    private String firmaSektoru3;

    @Column(name = "FIRMA_SEKTORU_4", length = 4)
    private String firmaSektoru4;

    @Column(name = "SATIS_TEMSILCISI_ID")
    private Long satisTemsilcisiId;

    @Column(name = "MUHASEBE_ILISKILI")
    private Integer muhasebeIliskili;

    @Column(name = "KISI_ID")
    private Long kisiId;

    @Column(name = "FATURA_DIP_NOT", length = 1000)
    private String faturaDipNot;

    @Column(name = "EFAT_GONDERILECEK")
    private Integer efatGonderilecek;

    @Column(name = "TC_KIMLIK_NO", length = 30)
    private String tcKimlikNo;

    @Column(name = "MERSIS_NO", length = 20)
    private String mersisNo;

    @Column(name = "SERMAYE", length = 100)
    private String sermaye;

    @Column(name = "FIRMA_EPOSTA_KODU", length = 1000)
    private String firmaEpostaKodu;

    @Column(name = "FATURA_PROFILE", length = 20)
    private String faturaProfile;

    @Column(name = "EARSIV_GONDERILECEK")
    private Integer earsivGonderilecek;

    @Column(name = "SORUMLU_2", length = 100)
    private String sorumlu2;

    @Column(name = "SORUMLU_UNVAN_2", length = 100)
    private String sorumluUnvan2;

    @Column(name = "SATIS_TEMSILCISI_ID_2")
    private Long satisTemsilcisiId2;

    @Column(name = "DEGER", precision = 6, scale = 2)
    private BigDecimal deger;

    @Column(name = "EIRSALIYE_GONDERILECEK")
    private Integer eirsaliyeGonderilecek;

    @Column(name = "COK_DOVIZLI")
    private Integer cokDovizli;

    @Column(name = "TEST", length = 100)
    private String test;

    @Column(name = "EIRSALIYE_ETIKET", length = 1000)
    private String eirsaliyeEtiket;

    @Column(name = "IKINCI_VKN", length = 15)
    private String ikinciVkn;

    @Column(name = "KUR_FARKI_HESAPLAMA")
    private Integer kurFarkiHesaplama;

    @Column(name = "OTOMATIK_KAPATMA_TIP", length = 5)
    private String otomatikKapatmaTip;

    @PrePersist
    protected void onCreate() {
        lastUpdateDate = LocalDateTime.now();
        kayitYapanTarh = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdateDate = LocalDateTime.now();
        kayitDegistrnTarh = new Date();
    }
}