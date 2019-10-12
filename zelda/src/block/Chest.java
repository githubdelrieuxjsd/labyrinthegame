package block;
import damage.Damage;
import model.Case;
import model.Coordonnee;
import model.Direction;
import model.Plateau;

public class Chest extends  Block {

	
	public Chest( ) {
		super();
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection( new Direction ("down" ) );		
		this.setNom("Chest"); 
	}

	
	// #################### IMAGE ######################

	
	@Override
	public String trouverImage(Plateau plateau ,Case c) {
		// TODO Auto-generated method stub
		return "hyrule/block/chest.png";
	}

	@Override
	public int trouverX(Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getX()*c.getTailleCasePixel()+15-2*c.getTailleCasePixel();
	}

	@Override
	public int trouverY(Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getY()*c.getTailleCasePixel() -15 -c.getTailleCasePixel();
	}

	@Override
	public int trouverlargeur(Case c) {
		// TODO Auto-generated method stub
		return 3*c.getTailleCasePixel();
	}

	@Override
	public int trouverlongeur(Case c) {
		// TODO Auto-generated method stub
		return 3*c.getTailleCasePixel();
	}


	@Override
	public void perdreVie(Damage damage, Plateau plateau) {
		// TODO Auto-generated method stub
		
	}



	
	

	
	
}
