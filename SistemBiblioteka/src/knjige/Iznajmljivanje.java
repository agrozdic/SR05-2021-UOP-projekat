package knjige;

import java.util.Date;

import osobe.Clan;
import osobe.Zaposleni;

public class Iznajmljivanje {

	private osobe.Zaposleni zaposleni;
	private osobe.Clan clan;
	private Date datum;
	private Primerak primerak;
	
	public Iznajmljivanje() {
		this.zaposleni = null;
		this.clan = null;
		this.datum = null;
		this.primerak = null;
	}

	public Iznajmljivanje(Zaposleni zaposleni, Clan clan, Date datum, Primerak primerak) {
		super();
		this.zaposleni = zaposleni;
		this.clan = clan;
		this.datum = datum;
		this.primerak = primerak;
	}

	public osobe.Zaposleni getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(osobe.Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}

	public osobe.Clan getClan() {
		return clan;
	}

	public void setClan(osobe.Clan clan) {
		this.clan = clan;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Primerak getPrimerak() {
		return primerak;
	}

	public void setPrimerak(Primerak primerak) {
		this.primerak = primerak;
	}
	
}
