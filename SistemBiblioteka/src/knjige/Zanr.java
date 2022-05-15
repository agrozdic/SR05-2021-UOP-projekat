package knjige;

public class Zanr {

	private String naziv;
	private String oznaka;
	private String opis;
	
	private Zanr() {
		this.naziv = "";
		this.oznaka = "";
		this.opis = "";
	}

	public Zanr(String naziv, String oznaka, String opis) {
		super();
		this.naziv = naziv;
		this.oznaka = oznaka;
		this.opis = opis;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
}
