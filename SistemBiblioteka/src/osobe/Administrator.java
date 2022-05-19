package osobe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Administrator extends Zaposleni {
	
	public Administrator() {
		super();
	}
	
	public Administrator(String id, String ime, String prezime, String jmbg, String adresa, Pol pol, double plata, String korisnickoIme, String lozinka) {
		super(id, ime, prezime, jmbg, adresa, pol, plata, korisnickoIme, lozinka);
	}

	public void registrujZaposlene(Zaposleni zaposleni) {
		String content = "";
		try(FileWriter fw = new FileWriter("src/fajlovi/zaposleni.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
				String pol = zaposleni.getPol().toString();
				if(pol == "NEIZJASNJENI") pol = " ";
			    content += "\n"
			    		+ zaposleni.getId() + '|'
			    		+ zaposleni.getIme() + '|'
			    		+ zaposleni.getPrezime() + '|'
			    		+ zaposleni.getJmbg() + '|'
			    		+ pol.substring(0, 1).toUpperCase() + '|'
			    		+ zaposleni.getAdresa() + '|'
			    		+ zaposleni.getPlata() + '|'
			    		+ zaposleni.getKorisnickoIme() + '|'
			    		+ zaposleni.getLozinka();
			    out.println(content);
			} catch (IOException e) {
				System.out.println("Greska prilikom registracije zaposlenog!");
				e.printStackTrace();
			}
	}
	
	@Override
	public String toString() {
		return "\nADMINISTRATOR " + super.toString();
	}
	
}
