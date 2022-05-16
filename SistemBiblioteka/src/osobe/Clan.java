package osobe;

import java.time.LocalDate;

public class Clan extends Osoba {

	private String brojClanskeKarte;
	private TipClanarine tipClanarine;
	private LocalDate datumPoslednjeUplateClanarine;
	private int brojUplacenihMeseci;
	private boolean aktivnost;
	
	public Clan() {
		super();
		this.brojClanskeKarte = "";
		this.tipClanarine = TipClanarine.OSNOVNA;
		this.datumPoslednjeUplateClanarine = null;
		this.brojUplacenihMeseci = 0;
		this.aktivnost = false;
	}
	
	public Clan(String id, String ime, String prezime, String jmbg, String adresa, Pol pol, String brojClanskeKarte, TipClanarine tipClanarine, LocalDate datumPoslednjeUplateClanarine,
			int brojUplacenihMeseci, boolean aktivnost) {
		super();
		this.brojClanskeKarte = brojClanskeKarte;
		this.tipClanarine = tipClanarine;
		this.datumPoslednjeUplateClanarine = datumPoslednjeUplateClanarine;
		this.brojUplacenihMeseci = brojUplacenihMeseci;
		this.aktivnost = aktivnost;
	}

	public String getBrojClanskeKarte() {
		return brojClanskeKarte;
	}

	public void setBrojClanskeKarte(String brojClanskeKarte) {
		this.brojClanskeKarte = brojClanskeKarte;
	}

	public TipClanarine getTipClanarine() {
		return tipClanarine;
	}

	public void setTipClanarine(TipClanarine tipClanarine) {
		this.tipClanarine = tipClanarine;
	}

	public LocalDate getDatumPoslednjeUplateClanarine() {
		return datumPoslednjeUplateClanarine;
	}

	public void setDatumPoslednjeUplateClanarine(LocalDate datumPoslednjeUplateClanarine) {
		this.datumPoslednjeUplateClanarine = datumPoslednjeUplateClanarine;
	}

	public int getBrojUplacenihMeseci() {
		return brojUplacenihMeseci;
	}

	public void setBrojUplacenihMeseci(int brojUplacenihMeseci) {
		this.brojUplacenihMeseci = brojUplacenihMeseci;
	}

	public boolean isAktivnost() {
		return aktivnost;
	}

	public void setAktivnost(boolean aktivnost) {
		this.aktivnost = aktivnost;
	}
	
	@Override
	public String toString() {
		return "\nCLAN " + super.toString() +
				"\nTipClanarine: " + this.tipClanarine +
				"\nDatum poslednje uplate clanarine: " + this.datumPoslednjeUplateClanarine + 
				"\nbrojUplacenihMeseci: " + this.brojUplacenihMeseci +
				"\nAktivnost: " + this.aktivnost;
	}
	
}

