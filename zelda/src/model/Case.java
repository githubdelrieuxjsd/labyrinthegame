package model;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Case {

	private Element element ;
	private ObjetCacher objetCacher;
	
	// 0 -> vide 
	// 1 -> mur 
	// 100 -> hero 
	// 101 -> monstre 
	// 102 -> Minotaure
	// 2 -> secret 
	// 102 -> fantome
	// 8 -> tresort

	public Case(Element element) {
		super();
		this.element = element;
		this.objetCacher = new Rien () ;
	}


	public Case(Element element, ObjetCacher objetCacher) {
		super();
		this.element = element;
		if (this.estVide()) {
			this.objetCacher = objetCacher;
		}
		else {
			this.objetCacher = new Rien();
		}
	}

	public Element getElement() {
		return element;
	}

	
	public void setElement(Element element) {
		this.element = element;
	}

	

	public ObjetCacher getObjetCacher() {
		return objetCacher;
	}

	public void setObjetCacher(ObjetCacher objetCacher) {
		this.objetCacher = objetCacher;
	}
	
	public boolean estVide () {
		return this.getElement().getNom().equals("Vide");
		
	}
	
	private Image trouverImage(Plateau plateau ) {
		Element element = this.getElement();
		ObjetCacher objCacher = this.getObjetCacher();
		ImageIcon icon = new ImageIcon(element.getImage( plateau ,this));
		Image img = icon.getImage();
		return img;
	}
	private int trouverX() {
		
		return this.getElement().trouverX() ;
	}
	private int trouverY() {
		int res = this.getElement().trouverY() ;
		return res ;
	}
	private int trouverlongeur() {
		int res = this.getElement().trouverlargeur() ;
		return res ;
	}
	private int trouverlargeur() {
		int res = this.getElement().trouverlongeur() ;
		return res ;
	}
	
	public void dessin(Plateau plateau , Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(this.trouverImage(plateau), this.trouverX(),
				this.trouverY() , this.trouverlongeur(), this.trouverlargeur(), null);
	}
	
	
	
	public void afficher() {
		if (this.getElement().getNom().equals("Hero")) {
			System.out.println( this.getElement().getNom() +"," + this.getElement().getCoordonnee() +","
		+ ((Hero)this.getElement()).getLife());

		}
		
	}


	@Override
	public String toString() {
		String res = this.getElement().getNom() ; 
		return res;
	}
	
		
}
