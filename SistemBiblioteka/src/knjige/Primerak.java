package knjige;

public class Primerak {

	private String id;
	private Knjiga kniga;
	private TipPoveza tipPoveza;
	private int godinaStampe;
	private boolean izdat;
	
	public Primerak() {
		this.id = "";
		this.kniga = null;
		this.tipPoveza = TipPoveza.MEKI;
		this.godinaStampe = 0;
		this.izdat = false;
	}

	public Primerak(String id, Knjiga kniga, TipPoveza tipPoveza, int godinaStampe, boolean izdat) {
		super();
		this.id = id;
		this.kniga = kniga;
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

	public Knjiga getKniga() {
		return kniga;
	}

	public void setKniga(Knjiga kniga) {
		this.kniga = kniga;
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
	
}
