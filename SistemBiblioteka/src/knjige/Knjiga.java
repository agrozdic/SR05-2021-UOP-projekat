package knjige;

public class Knjiga {

	private String id;
	private String naslov;
	private String originalniNaslov;
	private String pisac;
	private int godinaObjave;
	private String jezikOriginala;
	private String opis;
	private Zanr zanr;
	
	public Knjiga() {
		this.id = "";
		this.naslov = "";
		this.originalniNaslov = "";
		this.pisac = "";
		this.godinaObjave = 0;
		this.jezikOriginala = "";
		this.opis = "";
		this.zanr = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getOriginalniNaslov() {
		return originalniNaslov;
	}

	public void setOriginalniNaslov(String originalniNaslov) {
		this.originalniNaslov = originalniNaslov;
	}

	public String getPisac() {
		return pisac;
	}

	public void setPisac(String pisac) {
		this.pisac = pisac;
	}

	public int getGodinaObjave() {
		return godinaObjave;
	}

	public void setGodinaObjave(int godinaObjave) {
		this.godinaObjave = godinaObjave;
	}

	public String getJezikOriginala() {
		return jezikOriginala;
	}

	public void setJezikOriginala(String jezikOriginala) {
		this.jezikOriginala = jezikOriginala;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Zanr getZanr() {
		return zanr;
	}

	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
	}
	
}
