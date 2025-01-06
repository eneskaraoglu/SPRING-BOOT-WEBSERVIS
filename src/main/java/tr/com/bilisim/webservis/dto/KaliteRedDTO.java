package tr.com.bilisim.webservis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KaliteRedDTO {
    
    // Referans Kodları (ID'ler yerine)
    @NotBlank(message = "Firma kodu boş olamaz")
    @Size(max = 20, message = "Firma kodu 20 karakterden uzun olamaz")
    private String firmaKodu;
    
    @NotBlank(message = "Malzeme kodu boş olamaz")
    @Size(max = 50, message = "Malzeme kodu 50 karakterden uzun olamaz")
    private String malzemeKodu;
    
    @Size(max = 20)
    private String personelKodu;
    
    @Size(max = 20)
    private String operasyonKodu;
    
    private String sorumluBolumKodu;
    private String hataParcaKodu;
    private String urunKarakterKodu;
    
    // Temel Alanlar
    private String redRaporNo;
    
    @NotNull(message = "Tarih alanı zorunludur")
    private Date tarih;
    
    @Size(max = 100)
    private String firmaRaporNo;
    
    private Date firmaRaporTar;
    
    @Size(max = 5)
    private String hataYakalanmaYeri;
    
    @Size(max = 5)
    private String hataYakaDetayYeri;
    
    @Size(max = 5)
    private String ustuAlti;
    
    @Size(max = 200)
    private String olculenDeger;
    
    @Size(max = 4000)
    private String redAciklama;
    
    @DecimalMin(value = "0.0", message = "Raslanma yüzdesi 0'dan küçük olamaz")
    @DecimalMax(value = "100.0", message = "Raslanma yüzdesi 100'den büyük olamaz")
    private BigDecimal raslanmaYuzde;
    
    private BigDecimal hataninBasParcaAdedi;
    private BigDecimal ayrilanParcaAdedi;
    private BigDecimal ayiklananMiktar;
    private BigDecimal ayiklamadaAyrilanMiktar;
    private BigDecimal redKatsayi;
    
    @Size(max = 200)
    private String nedenAciklama;
    
    private BigDecimal iadeEdilenMalTutar;
    private BigDecimal duzeltmeFaturaTutar;
    
    @Size(max = 5)
    private String paraBirim;
    
    @Size(max = 15)
    private String redTip;
    
    @Size(max = 9)
    private String redAltTip;
    
    private BigDecimal gerceklesenMiktar;
    
    @Size(max = 100)
    private String cikilanUretimNo;
    
    @Size(max = 1000)
    private String belgeYolu;
    
    @Size(max = 4000)
    private String hataAciklama;
    
    // Audit Alanları
    private Date kayitDegTar;
    private Date kayitGirTar;
    
    @Size(max = 30)
    private String kayitDegKullanici;
    
    @Size(max = 100)
    private String kayitGirKullanici;

    // Kontrol Alanları
    private Boolean asiriNavlun;
    private Boolean etkGst;
    private Boolean gkkUretimElle;
}