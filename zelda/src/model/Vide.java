package model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Vide extends Element {

	public Vide(Coordonnee coordonnee ) {
		super(coordonnee);

		this.setNom("Vide") ;
	}
	
	public Vide(int x, int y) {
		super(x, y);
		this.setNom("Vide") ;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getImage(Case c ) {
		Element element = c.getElement() ;
		ObjetCacher objCacher = c.getObjetCacher() ;
		String icon = "img/vide.png";
		if ( objCacher != null) {
			switch (objCacher.getNom()) {
			case "Spike" : 
				if (((Spike)objCacher).isVisible()) {
					icon = "img/spike.png"; 
				}
				break;
			case "Key" : 
				icon = "img/key3.png";
				break;
			default :;
			}
		}
		
		
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
	protected void perdreVie(int damage, Plateau plateau) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
