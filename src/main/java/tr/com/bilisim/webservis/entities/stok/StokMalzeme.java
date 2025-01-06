package tr.com.bilisim.webservis.entities.stok;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "T_STK_MLZ", schema = "ERPSTOK")
public class StokMalzeme {
    
    @Id
    @Column(name = "MLZ_ID")
    private Long id;
    
    @Column(name = "MALHIZ_KODU", length = 300, nullable = false)
    private String malzemeKodu;
    
    @Column(name = "ULUSLARARASI_KODU", length = 100)
    private String uluslararasiKod;
    
    @Column(name = "MALHIZ_ADI", length = 500)
    private String malzemeAdi;
    
    @Column(name = "MALHIZ_ING_ADI", length = 4000)
    private String malzemeIngilizceAdi;
    
    @Column(name = "FABRIKA_KOD", nullable = false)
    private Integer fabrikaKodu;
    
    @Column(name = "KAYIT_GIR_TAR")
    private LocalDateTime kayitGirisTarihi;
    
    @Column(name = "KAYIT_GIR_USER", length = 30)
    private String kayitGirisKullanici;
    
    @Column(name = "KAYIT_DEG_TAR")
    private LocalDateTime kayitDegisiklikTarihi;
    
    @Column(name = "KAYIT_DEG_USER", length = 30)
    private String kayitDegisiklikKullanici;
    
    @Column(name = "LAST_UPDATE_DATE")
    private LocalDateTime sonGuncellenmeTarihi;
    
    @Column(name = "SERI", length = 100)
    private String seri;
    
    @Column(name = "RESIM_NO", length = 100)
    private String resimNo;
    
    @Column(name = "OTO_STKGIRPARTI_NO2", length = 4000)
    private String otomatikStokGirisPartiNo;
    
    @PrePersist
    protected void onCreate() {
        sonGuncellenmeTarihi = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        sonGuncellenmeTarihi = LocalDateTime.now();
    }
}
