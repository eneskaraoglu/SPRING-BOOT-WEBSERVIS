package tr.com.bilisim.webservis.entities;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema="ERPSAT", name = "T_STN_SATICI_SIPARIS_ONAY")
public class SatinAlmaSaticiSiparisDtOnayEntity {

    @Id
    @GenericGenerator(name = "bls_table_id_gen", strategy = "tr.com.bilisim.webservis.util.StnTableIdGenerator")
    @GeneratedValue(generator = "bls_table_id_gen")
    @Column(name = "ONAY_ID", nullable = false)
    private Long onayId;

    @Column(name = "TEKLIF_NO", nullable = true)
    private Integer teklifNo;

    @Column(name = "ILK_SIPARIS_NO", nullable = true)
    private Integer ilkSiparisNo;

    @Column(name = "FABRIKA_KOD", nullable = false)
    private Long fabrikaKod;

    @Column(name = "SIP_ONAYCI_ID", nullable = false)
    private Long sipOnayciId;

    @Column(name = "SAT_SIPARIS_ID", nullable = false)
    private Long satSiparisId;

    @Column(name = "ONAY_DURUM", length = 5, nullable = true)
    private String onayDurum;

    @Column(name = "ACIKLAMA", length = 100, nullable = true)
    private String aciklama;

    @Column(name = "KULLANICI", length = 30, nullable = true)
    private String kullanici;

    @Column(name = "VEKIL_KULLANICI", length = 30, nullable = true)
    private String vekilKullanici;

    @Column(name = "ISLEM_TARIHI", nullable = true)
    private Date islemTarihi;

    @Column(name = "CREATE_DATE", nullable = true)
    private Date createDate;

    @Column(name = "LAST_UPDATE_DATE", nullable = true)
    private Date lastUpdateDate;

    @Column(name = "OPTLOCK", nullable = true)
    private Long optlock;
}
