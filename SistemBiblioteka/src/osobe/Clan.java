package osobe;

import java.util.Date;

public class Clan extends Osoba {

	private String brojClanskeKarte;
	private TipClanarine tipClanarine;
	private Date datumPoslednjeUplateClanarine;
	private int brojUplacenihMeseci;
	private boolean aktivnost;
	
	public Clan() {
		super();
		this.brojClanskeKarte = "";
		this.tipClanarine = TipClanarine.OSNOVNA;
		this.datumPoslednjeUplateClanarine = new Date();
		this.brojUplacenihMeseci = 0;
		this.aktivnost = false;
	}
	
	public Clan(String id, String ime, String prezime, String jmbg, Pol pol, double plata, String korisnickoIme, String lozinka, String brojClanskeKarte, TipClanarine tipClanarine, Date datumPoslednjeUplateClanarine,
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

	public Date getDatumPoslednjeUplateClanarine() {
		return datumPoslednjeUplateClanarine;
	}

	public void setDatumPoslednjeUplateClanarine(Date datumPoslednjeUplateClanarine) {
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
	
}

