package osobe;

public abstract class Zaposleni extends Osoba{

	private double plata;
	private String korisnickoIme;
	private String lozinka;
	
	public Zaposleni() {
		super();
		this.plata = 0.0;
		this.korisnickoIme = "";
		this.lozinka = "";
	}

	public Zaposleni(String id, String ime, String prezime, String jmbg, String adresa, Pol pol, double plata, String korisnickoIme, String lozinka) {
		super();
		this.plata = plata;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	public void dodajClana(Clan clan) {
		
	}
	
	@Override
	public String toString() {
		return super.toString() + 
				"\nPlata: " + String.valueOf(this.plata) +
				"\nKorisnicko ime: " + this.korisnickoIme + 
				"\nLozinka: " + this.lozinka;
	}
}
