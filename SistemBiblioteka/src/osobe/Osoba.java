package osobe;

public abstract class Osoba {
	
	private String id;
	private String ime;
	private String prezime;
	private String jmbg;
	private Pol pol;
	
	public Osoba() {
		super();
		this.id = "";
		this.ime = "";
		this.prezime = "";
		this.jmbg = "";
		this.pol = POL.NEIZJASNJENI;
	}

	public Osoba(String id, String ime, String prezime, String jmbg, Pol pol) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}
	
}
