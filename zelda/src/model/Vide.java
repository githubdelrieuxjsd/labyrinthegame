package model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Vide extends Element {

	public Vide(Coordonnee coordonnee ) {
		super();

		this.setNom("Vide") ;
		this.setCoordonnee(coordonnee);
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection(new Direction ("down" ));
	}
	
	public Vide(int x, int y) {
		super();
		this.setNom("Vide") ;
		this.setCoordonnee(new Coordonnee(x,y));
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection(new Direction ("down" ));	}

	@Override
	public String getImage(Plateau plateau ,Case c ) {
		Element element = c.getElement() ;
		String icon = "img/vide.png";
		return icon;
	}
	
	@Override
	protected int trouverX() {
		// TODO Auto-generated method stub
		return this.getCoordonnee().getX()*40;
	}

	@Override
	protected int trouverY() {
		// TODO Auto-generated method stub
		return this.getCoordonnee().getY()*40;
	}

	@Override
	protected int trouverlargeur() {
		// TODO Auto-generated method stub
		return 40;
	}

	@Override
	protected int trouverlongeur() {
		// TODO Auto-generated method stub
		return 40;
	}

	@Override
	public void perdreVie(Damage damage, Plateau plateau) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
