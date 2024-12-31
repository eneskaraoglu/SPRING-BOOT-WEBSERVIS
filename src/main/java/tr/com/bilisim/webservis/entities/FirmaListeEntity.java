package tr.com.bilisim.webservis.entities;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "V_B2B_FIRMA_LISTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FirmaListeEntity {

    @Id
    @Column(name = "FIRMA_ID")
    private Long firmaId;

    @Column(name = "TEXT", length = 1077)
    private String text;

    @Column(name = "ACIKLAMA", length = 0)
    private String aciklama;

    @Column(name = "LAST_UPDATE_DATE")
    private Timestamp lastUpdateDate;

    @Column(name = "KAYIT_YAPAN_TARH")
    private Date kayitYapanTarh;

    @Column(name = "FABRIKA_KOD")
    private Integer fabrikaKod;

    @Column(name = "KAYIT_YAPAN_KULLANCI", length = 30)
    private String kayitYapanKullanci;

    @Column(name = "FIRMA_ADI", length = 1000)
    private String firmaAdi;

    @Column(name = "FIRMA_KODU", length = 50)
    private String firmaKodu;

    @Column(name = "FIRMA_DETAY_TURU", length = 2)
    private String firmaDetayTuru;

    @Column(name = "VERGI_NO", length = 30)
    private String vergiNo;

    @Column(name = "ETKGST")
    private Boolean etkgst;

    @Column(name = "JSON_DATA", length = 0)
    private String jsonData;
}
