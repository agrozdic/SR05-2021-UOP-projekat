package knjige;

import java.time.LocalDate;

import osobe.Clan;
import osobe.Zaposleni;

public class Iznajmljivanje {

	private String id;
	private Zaposleni zaposleni;
	private Clan clan;
	private LocalDate datum;
	private Primerak primerak;
	
	public Iznajmljivanje() {
		this.id = null;
		this.zaposleni = null;
		this.clan = null;
		this.datum = null;
		this.primerak = null;
	}

	public Iznajmljivanje(String id, Zaposleni zaposleni, Clan clan, LocalDate datum, Primerak primerak) {
		super();
		this.id = id;
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

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public Primerak getPrimerak() {
		return primerak;
	}

	public void setPrimerak(Primerak primerak) {
		this.primerak = primerak;
	}
	
	@Override
	public String toString() {
		return "\nIZNAJMLJIVANJE"
				 + "\nID: " + this.id
				 + "\nZaposleni: " + this.zaposleni.getId()
				 + "\nClan: " + this.clan.getId()
				 + "\nDatum iznajmljivanja: " + this.datum
				 + "\nPrimerak: " + this.primerak.getId();
	}
	
}
