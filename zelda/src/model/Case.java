package model;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import block.Vide;
import item.Item;
import item.Rien;
import monstre.Monstre;
import projectil.Aire;
import projectil.Projectil;

public class Case {

	private int tailleCasePixel ;

	
	private Coordonnee coordonnee ;
	
	private Element element ;
	private Item item;
	private Projectil projectil ;
	private Trap trap ;
	
	private static int compt = 0 ;
	

	private int num ;
	

	public Case(Coordonnee coordonnee ) {
		this.tailleCasePixel = 14;
		
		this.coordonnee = coordonnee;
		this.num = compt;
		
		this.element = new Vide ();
		this.item = new Rien();
		this.projectil = new Aire();
		this.trap = new TrapVide();
		compt ++;

	}
	
	

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.getElement().getNom().equals("Vide")&&this.getItem().getNom().equals("Rien")
				&&this.getProjectil().getNom().equals("Aire")
				&&this.getTrap().getNom().equals("TrapVide");
	}
	
	
	public void setHauteur(int z) {
		this.getCoordonnee().setZ(z);		

	}

	
	public Image trouverElementImage(Plateau plateau ) {
		ImageIcon icon = new ImageIcon(this.getElement().trouverImage( plateau ,this));
		Image img = icon.getImage();
		return img;
	}
	
	public Image trouverItemImage(Plateau plateau ) {
		ImageIcon icon = new ImageIcon(this.getItem().trouverImage( plateau ,this));
		Image img = icon.getImage();
		return img;
	}
	
	public Image trouverProjectilImage(Plateau plateau ) {
		ImageIcon icon = new ImageIcon(this.getProjectil().trouverImage( plateau ,this));
		Image img = icon.getImage();
		return img;
	}
	
	public Image trouverTrapImage(Plateau plateau ) {
		ImageIcon icon = new ImageIcon(this.getTrap().trouverImage( plateau ,this));
		Image img = icon.getImage();
		return img;
	}
	
	
	private int trouverElementX() {
		return this.getElement().trouverX(this) ;
	}
	private int trouverElementY() {
		int res = this.getElement().trouverY(this) ;
		return res ;
	}
	private int trouverElementLongeur() {
		int res = this.getElement().trouverlargeur(this) ;
		return res ;
	}
	private int trouverElementLargeur() {
		int res = this.getElement().trouverlongeur(this) ;
		return res ;
	}
	private int trouverProjectilX() {
		return this.getProjectil().trouverX(this) ;
	}
	private int trouverProjectilY() {
		int res = this.getProjectil().trouverY(this) ;
		return res ;
	}
	private int trouverProjectilLongeur() {
		int res = this.getProjectil().trouverlargeur(this) ;
		return res ;
	}
	private int trouverProjectilLargeur() {
		int res = this.getProjectil().trouverlongeur(this) ;
		return res ;
	}
	private int trouverItemX() {
		return this.getItem().trouverX(this) ;
	}
	private int trouverItemY() {
		int res = this.getItem().trouverY(this) ;
		return res ;
	}
	private int trouverItemLongeur() {
		int res = this.getItem().trouverlargeur(this) ;
		return res ;
	}
	private int trouverItemLargeur() {
		int res = this.getItem().trouverlongeur(this) ;
		return res ;
	}
	private int trouverTrapX() {
		return this.getTrap().trouverX(this) ;
	}
	private int trouverTrapY() {
		int res = this.getTrap().trouverY(this) ;
		return res ;
	}
	private int trouverTrapLongeur() {
		int res = this.getTrap().trouverlargeur(this) ;
		return res ;
	}
	private int trouverTrapLargeur() {
		int res = this.getTrap().trouverlongeur(this) ;
		return res ;
	}
	
	public void dessinItemBlockProjectil(Plateau plateau , Graphics g) {
		// TODO Auto-generated method stub
		if ( ! (this.isEmpty() ) ) {
			
		g.drawImage(this.trouverTrapImage(plateau), this.trouverTrapX(),
				this.trouverTrapY() , this.trouverTrapLongeur(), this.trouverTrapLargeur(), null);

			
		g.drawImage(this.trouverItemImage(plateau), this.trouverItemX(),
			this.trouverItemY() , this.trouverItemLongeur(), this.trouverItemLargeur(), null);
		
		
		//this.afficherNum(102);
		if ( ! (this.getElement().isHero() ||
				this.getElement().isMonstre()  )) {
			g.drawImage(this.trouverElementImage(plateau), this.trouverElementX(),
					this.trouverElementY() , this.trouverElementLongeur(), this.trouverElementLargeur(), null);
		}
		
		g.drawImage(this.trouverProjectilImage(plateau), this.trouverProjectilX(),
				this.trouverProjectilY() , this.trouverProjectilLongeur(), this.trouverProjectilLargeur(), null);
		}
	}
	
	public void dessinUnite(Plateau plateau , Graphics g) {
		// TODO Auto-generated method stub

		//this.afficherNum(102);
		if (this.getElement().getNom().equals("Hero") ) {
			g.drawImage(this.trouverElementImage(plateau), this.trouverElementX(),
					this.trouverElementY() , this.trouverElementLongeur(), this.trouverElementLargeur(), null);
			
			if ( ((Hero)this.getElement()).hold() ) {
				ImageIcon icon = new ImageIcon("hyrule/block/rock.png");
				Image img = icon.getImage();		

					g.drawImage(img, ((Hero) this.getElement()).trouverX(this),
							((Hero) this.getElement()).trouverY(this) - this.getTailleCasePixel() + this.getTailleCasePixel()/6  , 3*this.getTailleCasePixel(), 3*this.getTailleCasePixel(), null);
				
			
			
		}
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

						g.drawImage(img, ((Monstre) this.getElement()).trouverBarreVieX(this)+15*i ,
								((Monstre) this.getElement()).trouverBarreVieY(this)	 , 15, 15, null);
					}
			}
			}
		}
		
		g.drawImage(this.trouverProjectilImage(plateau), this.trouverProjectilX(),
				this.trouverProjectilY() , this.trouverProjectilLongeur(), this.trouverProjectilLargeur(), null);
		
	}
	
	
	
	
	//######################## TO STRING ##########################################

	

	public void afficher() {	
		if ( this.getElement().getNom().equals("Hero")|| true) {
			System.out.println("###### Case "+num+" ######");
			System.out.println("Coordonnee "+this.getCoordonnee());
			System.out.println("Element "+this.getElement());

			System.out.println("Item "+this.getItem());
			System.out.println("Projectil "+this.getProjectil());
			System.out.println("Trap "+this.getTrap());

			System.out.println("##########################");
		}
		


	}

	public void afficherNum(int num) {
		if (this.num == num ) {
			
			System.out.println("###### Case "+num+" ######");
			System.out.println("Coordonnee "+this.getCoordonnee());
			System.out.println("Element "+this.getElement());

			System.out.println("Item "+this.getItem());
			System.out.println("Projectil "+this.getProjectil());
			System.out.println("Trap "+this.getTrap());


			System.out.println("##########################");


			System.out.println(this.getElement().getNom() +",  "+this.getItem().getNom());
		}
	}

	@Override
	public String toString() {
		String res = "case comport :"+this.getElement().getNom() ; 
		return res;
	}


	
	//######################## GETTER SETTER ##########################################

	
	
	public Element getElement() {
		return element;
	}
	
	public Coordonnee getCoordonnee() {
		return coordonnee;
	}

	public Projectil getProjectil() {
		return projectil;
	}

	public void setProjectil(Projectil projectil) {
		this.projectil = projectil;
		this.projectil.setNumeroCase(num);

	}

	public void setElement(Element element) {
		this.element = element;
		this.element.setNumeroCase(num);
	}

	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
		this.item.setNumeroCase(num);

	}
	public void setItem() {
		this.setItem(new Rien() );	
	}

	public Trap getTrap() {
		return trap;
	}

	public void setTrap(Trap trap) {
		this.trap = trap;
		this.trap.setNumeroCase(num);

	}
	public void setTrap() {
		this.setTrap(new TrapVide() );	
	}
	
	public int getTailleCasePixel() {
		return tailleCasePixel;
	}


	public static void setCompt(int compt) {
		Case.compt = compt;
	}

	public int getNum() {
		return num;
	}
	

		
	
	
	}
	

	
	
		

