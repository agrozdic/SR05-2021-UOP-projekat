package knjige;

public class Primerak {

	private String id;
	private Knjiga knjiga;
	private TipPoveza tipPoveza;
	private int godinaStampe;
	private boolean izdat;
	
	public Primerak() {
		this.id = "";
		this.knjiga = null;
		this.tipPoveza = TipPoveza.MEKI;
		this.godinaStampe = 0;
		this.izdat = false;
	}

	public Primerak(String id, Knjiga knjiga, TipPoveza tipPoveza, int godinaStampe, boolean izdat) {
		super();
		this.id = id;
		this.knjiga = knjiga;
		this.tipPoveza = tipPoveza;
		this.godinaStampe = godinaStampe;
		this.izdat = izdat;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKjniga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}

	public TipPoveza getTipPoveza() {
		return tipPoveza;
	}

	public void setTipPoveza(TipPoveza tipPoveza) {
		this.tipPoveza = tipPoveza;
	}

	public int getGodinaStampe() {
		return godinaStampe;
	}

	public void setGodinaStampe(int godinaStampe) {
		this.godinaStampe = godinaStampe;
	}

	public boolean isIzdat() {
		return izdat;
	}

	public void setIzdat(boolean izdat) {
		this.izdat = izdat;
	}
	
	@Override
	public String toString() {
		return "\nPRIMERAK"
				 + "\nID: " + this.id
				 + "\nKnjiga: " + this.knjiga
				 + "\nTip poveza: " + this.tipPoveza
				 + "\nGodina stampe: " + this.godinaStampe
				 + "\nIzdat: " + this.izdat;
	}

}
