package osobe;

public enum TipClanarine {
	OSNOVNA(250),
	DECA(150),
	PENZIONERI(100);

	public int cena;

	TipClanarine(int cena){
		this.cena = cena;
	}

	public int getCena(){
		return this.cena;
	}

	public void setCena(){
		this.cena = cena;
	}
}
