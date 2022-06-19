package main;

import java.time.LocalDate;

import biblioteka.Biblioteka;
import gui.LoginProzor;
import osobe.Administrator;
import osobe.Clan;
import osobe.Pol;
import osobe.TipClanarine;

public class BibliotekaMain {
	
	public static String fZaposleni = "zaposleni.txt";
	public static String fClanovi = "clanovi.txt";
	public static String fKnjige = "knjige.txt";
	public static String fZanrovi = "zanrovi.txt";
	public static String fPrimerci = "primerci.txt";
	public static String fIznajmljivanja = "iznajmljivanja.txt";

	public static void main(String[] args) {
		
		Biblioteka biblioteka = new Biblioteka("BIBLIOTEKA-001", "Gradska biblioteka", "Bulevar Oslobodjenja 1", "0213456789", "08-16");
		biblioteka.ucitajZaposlene(fZaposleni);
		biblioteka.ucitajClanove(fClanovi);
		biblioteka.ucitajZanrove(fZanrovi);
		biblioteka.ucitajKnjige(fKnjige);
		biblioteka.ucitajPrimerke(fPrimerci);
		biblioteka.ucitajIznajmljivanja(fIznajmljivanja);
		
		// Clan noviClan = new Clan("CLA05", "Milenko", "Milenkovic", "0506993860987", "Novosadska 1, Novi Sad", Pol.NEIZJASNJENI, "BCK005", TipClanarine.OSNOVNA, LocalDate.parse("2022-01-01"), 1, true);
		// if(biblioteka.getBibliotekari().get(0).dodajClana(biblioteka, noviClan)){
		// 	biblioteka.snimiClanove(fClanovi);
		// }
		
		// Administrator noviAdm = new Administrator("ADM03", "Milenko", "Milenkovic", "0506993860987", "Novosadska 1, Novi Sad", Pol.NEIZJASNJENI, 55000.0, "mmilenkovic03", "30civoknelimm");
		// if(biblioteka.getAdministratori().get(0).registrujZaposlene(biblioteka, noviAdm)){
		// 	biblioteka.snimiZaposlene(fZaposleni);
		// }

		LoginProzor lp = new LoginProzor(biblioteka);
		lp.setVisible(true);
	}

}
