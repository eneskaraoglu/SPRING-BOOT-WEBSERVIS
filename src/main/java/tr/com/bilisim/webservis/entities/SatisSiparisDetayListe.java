package tr.com.bilisim.webservis.entities;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "V_B2B_SATIS_SIPARIS_DETAY_LISTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SatisSiparisDetayListe {

    @Id
    @Column(name = "SIPURUN_ID")
    private Long sipurunId;

    @Column(name = "SIP_ID")
    private Long sipId;

    @Column(name = "TEXT", length = 1077)
    private String text;

    @Column(name = "ACIKLAMA", length = 0)
    private String aciklama;

    @Column(name = "LAST_UPDATE_DATE")
    private Timestamp lastUpdateDate;

    @Column(name = "KAYIT_GIR_TAR")
    private Date kayitGirTar;

    @Column(name = "FABRIKA_KOD")
    private Integer fabrikaKod;

    @Column(name = "KAYIT_GIR_USER", length = 30)
    private String kayitGirUser;

    @Column(name = "SIPURUN_KARSI_KOD", length = 50)
    private String sipurunKarsiKod;

    @Column(name = "SIP_SIRA")
    private Integer sipSira;

    @Column(name = "SIPURUN_TIP", length = 2)
    private String sipurunTip;

    @Column(name = "SIP_KOD", length = 50)
    private String sipKod;

    @Column(name = "FIRMA_ID")
    private Long firmaId;

    @Column(name = "MLZ_ID")
    private Long mlzId;
    
    @Column(name = "SIPURUN_DURUM", length = 50)
    private String sipurunDurum;
    
    @Column(name = "SIP_DURUM", length = 50)
    private String sipDurum;

    @Column(name = "JSON_DATA", length = 0)
    private String jsonData;
}
