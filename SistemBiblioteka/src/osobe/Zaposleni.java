package osobe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
	
	public void dodajClana(Clan clan) {
		String content = "";
		try(FileWriter fw = new FileWriter("src/fajlovi/clanovi.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
				String pol = clan.getPol().toString();
				if(pol == "NEIZJASNJENI") pol = " ";
			    content += "\n"
			    		+ clan.getId() + '|'
			    		+ clan.getIme() + '|'
			    		+ clan.getPrezime() + '|'
			    		+ clan.getJmbg() + '|'
			    		+ pol.substring(0, 1).toUpperCase() + '|'
			    		+ clan.getAdresa() + '|'
			    		+ clan.getBrojClanskeKarte() + '|'
			    		+ clan.getTipClanarine().toString().toLowerCase() + '|'
			    		+ clan.getDatumPoslednjeUplateClanarine() + '|'
			    		+ clan.getBrojUplacenihMeseci() + '|'
			    		+ clan.isAktivnost();
			    out.println(content);
			} catch (IOException e) {
				System.out.println("Greska prilikom dodavanja clana!");
				e.printStackTrace();
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
