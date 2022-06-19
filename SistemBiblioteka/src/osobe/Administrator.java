package osobe;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import biblioteka.Biblioteka;

public class Administrator extends Zaposleni {
	
	public Administrator() {
		super();
	}
	
	public Administrator(String id, String ime, String prezime, String jmbg, String adresa, Pol pol, double plata, String korisnickoIme, String lozinka) {
		super(id, ime, prezime, jmbg, adresa, pol, plata, korisnickoIme, lozinka);
	}

	public boolean registrujZaposlene(Biblioteka biblioteka, Zaposleni zaposleni) {
		if(zaposleni.getId().contains("ADM")){
			ArrayList<Administrator> zaposleniList = biblioteka.getAdministratori();
			int indicator = 1;
			for(Administrator adm : zaposleniList){
				if(adm.getId().equals(zaposleni.getId())){
					indicator = 0;
					break;
				}
			}
			if(indicator == 1){
				biblioteka.dodajAdministratora((Administrator) zaposleni);
				return true;
			}
			else{
				return false;
			}
		}
		else{
			ArrayList<Bibliotekar> zaposleniList = biblioteka.getBibliotekari();
			int indicator = 1;
			for(Bibliotekar bib : zaposleniList){
				if(bib.getId().equals(zaposleni.getId())){
					indicator = 0;
					break;
				}
			}
			if(indicator == 1){
				biblioteka.dodajBibliotekara((Bibliotekar) zaposleni);
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	@Override
	public String toString() {
		return "\nADMINISTRATOR " + super.toString();
	}

    public static void addActionListener(ActionListener actionListener) {
    }
	
}
