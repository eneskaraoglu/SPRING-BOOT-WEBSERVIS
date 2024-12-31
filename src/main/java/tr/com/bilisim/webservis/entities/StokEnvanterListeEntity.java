package tr.com.bilisim.webservis.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "V_B2B_STOK_ENVANTER_LISTE", schema = "ERPKULLANICI")
public class StokEnvanterListeEntity {

    @Id
    @Column(name = "MLZ_ID")
    private Long mlzId;

    @Column(name = "TEXT", length = 989)
    private String text;

    @Column(name = "ACIKLAMA", length = 0)
    private String aciklama;

    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "KAYIT_GIR_TAR")
    private Date kayitGirTar;

    @Column(name = "FABRIKA_KOD")
    private Integer fabrikaKod;

    @Column(name = "KAYIT_GIR_KULLANICI", length = 30)
    private String kayitGirKullanici;

    @Column(name = "MALHIZ_ADI", length = 500)
    private String malhizAdi;

    @Column(name = "MALHIZ_KODU", length = 300)
    private String malhizKodu;

    @Column(name = "ENVANTER_TURU", length = 2)
    private String envanterTuru;

    @Column(name = "MALZEME_SEVIYESI", length = 5)
    private String malzemeSeviyesi;

    @Column(name = "ELDEKI_MIKTAR", precision = 15, scale = 5)
    private BigDecimal eldekiMiktar;

    @Column(name = "ETKGST", precision = 1)
    private Integer etkgst;
    
    @Column(name = "JSON_DATA", length = 989)
    private String jsonData;
}
