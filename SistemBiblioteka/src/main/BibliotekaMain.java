package main;

import biblioteka.Biblioteka;

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
		System.out.println(biblioteka.getKnjige());
	}

}
