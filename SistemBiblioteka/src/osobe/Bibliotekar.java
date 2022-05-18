package osobe;

public class Bibliotekar extends Zaposleni {

	public Bibliotekar() {
		super();
	}
	
	public Bibliotekar(String id, String ime, String prezime, String jmbg, String adresa, Pol pol, double plata, String korisnickoIme, String lozinka) {
		super(id, ime, prezime, jmbg, adresa, pol, plata, korisnickoIme, lozinka);
	}

	@Override
	public String toString() {
		return "\nBIBLIOTEKAR " + super.toString();
	}
	
}