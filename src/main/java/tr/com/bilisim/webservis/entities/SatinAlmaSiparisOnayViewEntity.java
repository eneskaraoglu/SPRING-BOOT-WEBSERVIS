package tr.com.bilisim.webservis.entities;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "V_B2B_SATIN_ALMA_SIPARIS_ONAY")
public class SatinAlmaSiparisOnayViewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SAT_SIPARIS_DT_ID", nullable = false)
    private Long satSiparisDtId;

    @Column(name = "TEXT", length = 650)
    private String text;

    @Column(name = "ACIKLAMA", length = 4000)
    private String aciklama;

    @Column(name = "TARIH")
    private Date tarih;

    @Column(name = "SAT_SIP_DURUM", length = 2, nullable = false)
    private String satSipDurum;

    @Column(name = "LAST_UPDATE_DATE")
    private LocalDateTime lastUpdateDate;

    @Column(name = "SIP_ONAYCI_ID")
    private Long sipOnayciId;

    @Column(name = "SAT_SIPARIS_ID", nullable = false)
    private Long satSiparisId;

    @Column(name = "KULLANICI_ROL_ID", length = 30)
    private String kullaniciRolId;

    @Column(name = "FABRIKA_KOD", precision = 6, nullable = false)
    private Long fabrikaKod;

    @Column(name = "ISLEM_TARIHI", nullable = false)
    private Date islemTarih;
    
    @Column(name = "JSON_DATA", length = 989)
    private String jsonData;
}

