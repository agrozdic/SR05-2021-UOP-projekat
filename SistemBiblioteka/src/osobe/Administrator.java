package osobe;

public class Administrator extends Zaposleni {
	
	public Administrator() {
		super();
	}
	
	public Administrator(String id, String ime, String prezime, String jmbg, String adresa, Pol pol, double plata, String korisnickoIme, String lozinka) {
		super(id, ime, prezime, jmbg, adresa, pol, plata, korisnickoIme, lozinka);
	}

	public void registrujZaposlene(Zaposleni zaposleni) {
		
	}
	
	@Override
	public String toString() {
		return "\nADMINISTRATOR " + super.toString();
	}
	
}
