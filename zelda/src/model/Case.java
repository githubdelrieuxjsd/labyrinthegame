package model;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Case {

	private Element element ;
	private Item item;
	
	private static int compt = 0 ;
	private int num ;
	

	public Case(Element element) {
		super();
		compt++;
		this.num = compt;
		this.element = element;
		this.item = new Rien () ;
	}


	public Case(Element element, Item item) {
		super();
		compt++;
		this.num = compt;
		this.element = element;
		if (this.estVide()) {
			this.item = item;
		}
		else {
			this.item = new Rien();
		}
	}

	public Element getElement() {
		return element;
	}

	
	public void setElement(Element element) {
		this.element = element;
	}

	

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	public void setItem() {
		this.setItem(new Rien() );	
	}
	public boolean estVide () {
		return this.getElement().getNom().equals("Vide");
		
	}
	
	private Image trouverElementImage(Plateau plateau ) {
		Element element = this.getElement();
		ImageIcon icon = new ImageIcon(element.getImage( plateau ,this));
		Image img = icon.getImage();
		return img;
	}
	
	private Image trouverItemImage(Plateau plateau ) {
		Item item = this.getItem();
		ImageIcon icon = new ImageIcon(item.getImage( plateau ,this));
		Image img = icon.getImage();
		return img;
	}
	
	private int trouverElementX() {
		return this.getElement().trouverX() ;
	}
	private int trouverElementY() {
		int res = this.getElement().trouverY() ;
		return res ;
	}
	private int trouverElementLongeur() {
		int res = this.getElement().trouverlargeur() ;
		return res ;
	}
	private int trouverElementLargeur() {
		int res = this.getElement().trouverlongeur() ;
		return res ;
	}
	private int trouverItemX(Plateau plateau, Case c) {
		return this.getItem().trouverX( plateau,  c) ;
	}
	private int trouverItemY(Plateau plateau, Case c) {
		int res = this.getItem().trouverY(plateau, c) ;
		return res ;
	}
	private int trouverItemLongeur() {
		int res = this.getItem().trouverlargeur() ;
		return res ;
	}
	private int trouverItemLargeur() {
		int res = this.getItem().trouverlongeur() ;
		return res ;
	}
	
	public void dessinBlock(Plateau plateau , Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(this.trouverItemImage(plateau), this.trouverItemX(plateau , this),
			this.trouverItemY(plateau, this) , this.trouverItemLongeur(), this.trouverItemLargeur(), null);

		//this.afficherNum(102);
		
		if ( ! (this.getElement().getNom().equals("Hero") ||
				this.getElement().isMonstre()  )) {
			g.drawImage(this.trouverElementImage(plateau), this.trouverElementX(),
					this.trouverElementY() , this.trouverElementLongeur(), this.trouverElementLargeur(), null);
		}
		
	}
	public void dessinUnite(Plateau plateau , Graphics g) {
		// TODO Auto-generated method stub

		//this.afficherNum(102);
		if (this.getElement().getNom().equals("Hero") ) {
			g.drawImage(this.trouverElementImage(plateau), this.trouverElementX(),
					this.trouverElementY() , this.trouverElementLongeur(), this.trouverElementLargeur(), null);
		}
		
		else if ( this.getElement().isMonstre() ) {
			
			g.drawImage(this.trouverElementImage(plateau), this.trouverElementX(),
					this.trouverElementY() , this.trouverElementLongeur(), this.trouverElementLargeur(), null);
			if ( this.getElement().isMonstre() ) {
			
				if ( ((Monstre)this.getElement()).getLife() <  ((Monstre)this.getElement()).getMaxLife() 
						&& ((Monstre)this.getElement()).getLife()>0 ) {
					for (int i = 0 ; i < ((Monstre)this.getElement()).getMaxLife() ;i ++) {
						ImageIcon icon = new ImageIcon("hyrule/heart/noir.png");
						
						if (i < ((Monstre)this.getElement()).getLife()) {
							icon = new ImageIcon("hyrule/heart/rouge.png");	
						}
						Image img = icon.getImage();		

						g.drawImage(img, ((Monstre) this.getElement()).trouverBarreVieX()+10*i ,
								((Monstre) this.getElement()).trouverBarreVieY()	 , 10, 10, null);
					}
			}
			}
		}
		
		
		
	}
	
	
	
	
	

	public void afficher() {
		if (this.getElement().getNom().equals("Hero")) {
			System.out.println( this.getElement().getNom() +"," + this.getElement().getCoordonnee() +","
		+ ((Hero)this.getElement()).getLife()  +",   "+this.num);

		}

		if (this.getElement().getNom().equals("Minotaure")) {
			System.out.println( this.getElement().getNom() +"," + this.getElement().getCoordonnee()  +",   "+this.num );

		}
	}

	public void afficherNum(int num) {
		if (this.num == num ) {
			System.out.println(this.getElement().getNom() +",  "+this.getItem().getNom());
		}
	}

	@Override
	public String toString() {
		String res = this.getElement().getNom() ; 
		return res;
	}


	


	
	
		
}
