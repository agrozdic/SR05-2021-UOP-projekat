package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

import biblioteka.Biblioteka;
import gui.LoginProzor;
//import osobe.Administrator;
//import osobe.Clan;
//import osobe.Pol;
//import osobe.TipClanarine;

public class BibliotekaMain {
	
	public static String fZaposleni = "zaposleni.txt";
	public static String fClanovi = "clanovi.txt";
	public static String fKnjige = "knjige.txt";
	public static String fZanrovi = "zanrovi.txt";
	public static String fPrimerci = "primerci.txt";
	public static String fIznajmljivanja = "iznajmljivanja.txt";
	public static String fBiblioteke = "biblioteke.txt";
	private static ArrayList<Biblioteka> biblioteke = new ArrayList<Biblioteka>();

	public static void main(String[] args) {
		
		ucitajBiblioteke(fBiblioteke);
		Biblioteka biblioteka = biblioteke.get(0);
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

	public static void ucitajBiblioteke(String fajl) {
		try {
			File file = new File("src/fajlovi/" + fajl);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String id = split[0];
				String naziv = split[1];
				String adresa = split[2];
				String telefon = split[3];
				String radnoVreme = split[4];
				Biblioteka biblioteka = new Biblioteka(id, naziv, adresa, telefon, radnoVreme);
				biblioteke.add(biblioteka);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja podataka o knjigama");
			e.printStackTrace();
		}
	}

	public static void snimiBiblioteke(String fajl) {
		try {
			File file = new File("src/fajlovi/" + fajl);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			String text = "";
			for (Biblioteka bib : biblioteke) {
				text +=
					bib.getId() + "|" +
					bib.getNaziv() + "|" +
					bib.getAdresa() + "|" +
					bib.getTelefon() + "|" +
					bib.getRadnoVreme() + "\n";
			}
			bw.write(text);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
