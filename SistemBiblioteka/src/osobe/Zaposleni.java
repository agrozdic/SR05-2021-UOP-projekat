package osobe;

import java.util.ArrayList;

import biblioteka.Biblioteka;

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
		super(id, ime, prezime, jmbg, adresa, pol);
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
	
	public boolean dodajClana(Biblioteka biblioteka, Clan clan) {
		ArrayList<Clan> clanovi = biblioteka.getClanovi();
		int indicator = 1;
		for(Clan cl : clanovi){
			if(cl.getId().equals(clan.getId())){
				indicator = 0;
				break;
			}
		}
		if(indicator == 1){
			biblioteka.dodajClana(clan);
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + 
				"\nPlata: " + String.valueOf(this.plata) +
				"\nKorisnicko ime: " + this.korisnickoIme + 
				"\nLozinka: " + this.lozinka;
	}
}
