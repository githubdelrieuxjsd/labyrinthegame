package model;

public abstract class Item {

	private String nom;
	private int frame ;
	private String curentAction;
	
	
	
	public abstract String getImage(Plateau plateau ,Case c) ;
	
	public abstract void etreRamasser(Plateau plateau ,Case c) ;
	
	protected abstract int trouverX(Plateau plateau ,Case c);

	protected abstract int trouverY(Plateau plateau ,Case c);

	protected abstract int trouverlargeur();

	protected abstract int trouverlongeur();
		
	
	
	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	

	public String getCurentAction() {
		return curentAction;
	}

	public void setCurentAction(String curentAction) {
		this.curentAction = curentAction;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	
	
	
}
