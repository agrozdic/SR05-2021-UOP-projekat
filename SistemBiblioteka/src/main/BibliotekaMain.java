package main;

import java.time.LocalDate;

import biblioteka.Biblioteka;
import osobe.Administrator;
import osobe.Clan;
import osobe.Pol;
import osobe.TipClanarine;
import osobe.Zaposleni;

public class BibliotekaMain {
	
	private static String fZaposleni = "zaposleni.txt";
	private static String fClanovi = "clanovi.txt";
	private static String fKnjige = "knjige.txt";
	private static String fZanrovi = "zanrovi.txt";
	private static String fPrimerci = "primerci.txt";

	public static void main(String[] args) {
		
		Biblioteka biblioteka = new Biblioteka("BIBLIOTEKA-001", "Gradska biblioteka", "Bulevar Oslobodjenja 1", "0213456789", "08-16");
		biblioteka.ucitajZaposlene(fZaposleni);
		biblioteka.ucitajClanove(fClanovi);
		biblioteka.ucitajZanrove(fZanrovi);
		biblioteka.ucitajKnjige(fKnjige);
		biblioteka.ucitajPrimerke(fPrimerci);
		System.out.println("\n----- UCITAVANJE BIBLIOTEKARA -----\n");
		System.out.println(biblioteka.getBibliotekari());
		System.out.println("\n----- UCITAVANJE ADMINISTRATORA -----\n");
		System.out.println(biblioteka.getAdministratori());
		System.out.println("\n----- UCITAVANJE CLANOVA -----\n");
		System.out.println(biblioteka.getClanovi());
		System.out.println("\n----- UCITAVANJE ZANROVA -----\n");
		System.out.println(biblioteka.getZanrovi());
		System.out.println("\n----- UCITAVANJE KNJIGA -----\n");
		System.out.println(biblioteka.getKnjige());
		System.out.println("\n----- UCITAVANJE PRIMERAKA -----\n");
		System.out.println(biblioteka.getPrimerci());
		
		Clan noviClan = new Clan("CLA05", "Milenko", "Milenkovic", "0506993860987", "Novosadska 1, Novi Sad", Pol.NEIZJASNJENI, "BCK005", TipClanarine.OSNOVNA, LocalDate.parse("2022-01-01"), 1, true);
		biblioteka.getBibliotekari().get(0).dodajClana(noviClan);
		System.out.println("\n----- UCITAVANJE CLANOVA SA DODATIM CLANOM -----\n");
		biblioteka.ucitajClanove(fClanovi);
		System.out.println(biblioteka.getClanovi());
		
		Zaposleni noviZaposleni = new Administrator("ADM03", "Milenko", "Milenkovic", "0506993860987", "Novosadska 1, Novi Sad", Pol.NEIZJASNJENI, 55000.0, "mmilenkovic03", "30civoknelimm");
		biblioteka.getAdministratori().get(0).registrujZaposlene(noviZaposleni);
		System.out.println("\n----- UCITAVANJE ADMINISTRATORA SA DODATIM ADMINISTRATOROM -----\n");
		biblioteka.ucitajZaposlene(fZaposleni);
		System.out.println(biblioteka.getAdministratori());
		
		
	}

}
