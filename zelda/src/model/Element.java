package model;

import java.awt.Graphics;

public abstract class Element {

	private Coordonnee coordonnee ;
	private String nom ; 
	private int frame ;
	private Direction direction ;
	private String curentAction;

	public boolean isMonstre () {
		boolean res = false; 
			switch	(this.getNom()){
			case "Minotaure" : res = true;
				break ; 
			case "Chicken" : res = true ; 
				break ;
			default : 
				break;
		}
		return res ;		
	}
	
	
	/**
	public Element(Coordonnee coordonnee) {
		super();
		this.coordonnee = coordonnee;
		this.frame = 0;
		this.direction = "down";
		this.curentAction = ("nothing") ;
	}

	
	public Element(int x , int y) {
		super();
		this.coordonnee = new Coordonnee(x,y);
		this.frame = 0;
		this.direction = "down";
		this.curentAction = ("nothing") ;

	}
	*/
	
	
	public String getCurentAction() {
		return curentAction;
	}


	public void setCurentAction(String curentAction) {
		this.curentAction = curentAction;
	}


	public Direction getDirection() {
		return direction;
	}


	public void setDirection(Direction direction) {
		this.direction = direction ;
	}


	public Coordonnee getCoordonnee() {
		return coordonnee;
	}

	public void setCoordonnee(Coordonnee coordonnee) {
		this.coordonnee = coordonnee;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public abstract String getImage(Plateau plateau ,Case c) ;


	public int getFrame() {
		return frame;
	}


	public void setFrame(int frame) {
		this.frame = frame;
	}

	public abstract void perdreVie(Damage damage , Plateau plateau);

	protected abstract int trouverX();


	protected abstract int trouverY();


	protected abstract int trouverlargeur();


	protected abstract int trouverlongeur();
		
}
