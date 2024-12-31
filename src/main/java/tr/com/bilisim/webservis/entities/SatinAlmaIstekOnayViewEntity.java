package tr.com.bilisim.webservis.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SatinAlmaIstekOnayViewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long istekDtId;

    private String text;

    private String aciklama;

    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    @Column(name = "KAYIT_GIR_TAR")
    private Date kayitTarihi;

    @Column(name = "ONAY_TARIHI")
    private Date onayTarihi;

    @Column(name = "RED_GEREKCESI")
    private String redAciklama;

    @Column(name = "ISTEK_DRM")
    private String istekDurum;

    @Column(name = "ONAY_DRM")
    private String onayDurum;

    @Column(name = "FABRIKA_KOD")
    private Long fabrikaKod;

    @Column(name = "KAYIT_GIR_KULLANICI") 
    private String kayitGirenKullanici;

    @Column(name = "ONAYLAYAN") 
    private String onaylayan;
    
    @Column(name = "JSON_DATA", length = 989)
    private String jsonData;

	
	
    // No-arg constructor
    public SatinAlmaIstekOnayViewEntity() {
    }

    // Updated All-args constructor
    public SatinAlmaIstekOnayViewEntity(Long istekDtId, String text, String aciklama, Date lastUpdateDate, Date kayitTarihi, 
                              Date onayTarihi, String redAciklama, String istekDurum, String onayDurum, 
                              Long fabrikaKod, String kayitGirenKullanici, String onaylayan) {
        this.istekDtId = istekDtId;
        this.text = text;
        this.aciklama = aciklama;
        this.lastUpdateDate = lastUpdateDate;
        this.kayitTarihi = kayitTarihi;
        this.onayTarihi = onayTarihi;
        this.redAciklama = redAciklama;
        this.istekDurum = istekDurum;
        this.onayDurum = onayDurum;
        this.fabrikaKod = fabrikaKod;
        this.kayitGirenKullanici = kayitGirenKullanici;
        this.onaylayan = onaylayan;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getRedAciklama() {
        return redAciklama;
    }

    public void setRedAciklama(String redAciklama) {
        this.redAciklama = redAciklama;
    }

    public Date getKayitTarihi() {
        return kayitTarihi;
    }

    public void setKayitTarihi(Date kayitTarihi) {
        this.kayitTarihi = kayitTarihi;
    }

	public String getOnaylayan() {
		return onaylayan;
	}

	public void setOnaylayan(String onaylayan) {
		this.onaylayan = onaylayan;
	}

	public Date getOnayTarihi() {
		return onayTarihi;
	}

	public void setOnayTarihi(Date onayTarihi) {
		this.onayTarihi = onayTarihi;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Long getIstekDtId() {
		return istekDtId;
	}

	public void setIstekDtId(Long istekDtId) {
		this.istekDtId = istekDtId;
	}

	public String getIstekDurum() {
		return istekDurum;
	}

	public void setIstekDurum(String istekDurum) {
		this.istekDurum = istekDurum;
	}

	public String getOnayDurum() {
		return onayDurum;
	}

	public void setOnayDurum(String onayDurum) {
		this.onayDurum = onayDurum;
	}

	public Long getFabrikaKod() {
		return fabrikaKod;
	}

	public void setFabrikaKod(Long fabrikaKod) {
		this.fabrikaKod = fabrikaKod;
	}

	public String getKayitGirenKullanici() {
		return kayitGirenKullanici;
	}

	public void setKayitGirenKullanici(String kayitGirenKullanici) {
		this.kayitGirenKullanici = kayitGirenKullanici;
	}
}
