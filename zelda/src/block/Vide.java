package block;

import java.awt.Image;

import javax.swing.ImageIcon;

import damage.Damage;
import model.Case;
import model.Coordonnee;
import model.Direction;
import model.Element;
import model.Plateau;

public class Vide extends Element {

	
	public Vide( ) {
		super();
		this.setSpawnable(false);
		this.setNom("Vide") ;
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection(new Direction ("down" ));
	}
	
	public Vide(boolean spawnable ) {
		super();
		this.setSpawnable(spawnable);
		this.setNom("Vide") ;
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection(new Direction ("down" ));
	}
	
	@Override
	public void perdreVie(Damage damage, Plateau plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String trouverImage(Plateau plateau ,Case c ) {
		Element element = c.getElement() ;
		String icon = "img/vide.png";
		if ( ! (c.getCoordonnee().getZ() == 0) ) {
			//icon = "hyrule/block/sol.png";
		}
		return icon;
	}
	
	@Override
	public int trouverX(Case c ) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getX()*c.getTailleCasePixel() - 2*c.getTailleCasePixel();
	}

	@Override
	public int trouverY(Case c ) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getY()*c.getTailleCasePixel() - 2*c.getTailleCasePixel();
	}

	@Override
	public int trouverlargeur(Case c) {
		// TODO Auto-generated method stub
		return c.getTailleCasePixel()*3;
	}

	@Override
	public int trouverlongeur(Case c) {
		// TODO Auto-generated method stub
		return c.getTailleCasePixel()*3;
	}


	
	
	

	
	
	
	
}
