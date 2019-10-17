package block;

import damage.Damage;
import model.Case;
import model.Coordonnee;
import model.Direction;
import model.Plateau;

public class PanneauAffichage extends Block{
	
	public PanneauAffichage ( ) {
		super();
		this.setSpawnable(false);

		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection( new Direction ("down" ) );		
		this.setNom("PanneauAffichage"); 
	}
	
	@Override
	public String trouverImage(Plateau plateau ,Case c) {
		// TODO Auto-generated method stub
		return "hyrule/block/panneauAffichage.png";
	}

	@Override
	public int trouverX(Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getX()*c.getTailleCasePixel()+3 - c.getTailleCasePixel();
	}

	@Override
	public int trouverY(Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getY()*c.getTailleCasePixel()+3 -c.getTailleCasePixel();
	}

	@Override
	public int trouverlargeur(Case c) {
		// TODO Auto-generated method stub
		return c.getTailleCasePixel();
	}

	@Override
	public int trouverlongeur(Case c) {
		// TODO Auto-generated method stub
		return c.getTailleCasePixel();
	}


	@Override
	public void perdreVie(Damage damage, Plateau plateau) {
		// TODO Auto-generated method stub
		
	}

}
