package osobe;

public class Administrator extends Zaposleni {
	
	public Administrator() {
		super();
	}
	
	public Administrator(String id, String ime, String prezime, String jmbg, String adresa, Pol pol, double plata, String korisnickoIme, String lozinka) {
		super();
	}

	public void registrujZaposlene(Zaposleni zaposleni) {
		
	}
	
	@Override
	public String toString() {
		return "\nADMINISTRATOR " + super.toString();
	}
	
}
