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
		return icon;
	}
	
	@Override
	public int trouverX(Case c ) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getX()*40;
	}

	@Override
	public int trouverY(Case c ) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getY()*40;
	}

	@Override
	public int trouverlargeur(Case c) {
		// TODO Auto-generated method stub
		return 40;
	}

	@Override
	public int trouverlongeur(Case c) {
		// TODO Auto-generated method stub
		return 40;
	}

	

	
	
	
	
}
