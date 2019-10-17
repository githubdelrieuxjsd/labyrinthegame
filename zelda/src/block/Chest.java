package block;
import damage.Damage;
import item.Key;
import item.Rubi;
import mobInterface.DropItem;
import model.Case;
import model.Coordonnee;
import model.Direction;
import model.Plateau;

public class Chest extends  Block implements DropItem {

	
	public Chest( ) {
		super();
		this.setSpawnable(false);

		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection( new Direction ("down" ) );		
		this.setNom("Chest"); 
		
	}
	
	public void ouvrir( Plateau plateau) {
			dropItem(plateau.getListCase().get(this.getNumeroCase()));
			this.detruir();
	}
	
//#################### PERDRE VIE ######################

	@Override
	public void perdreVie(Damage damage, Plateau plateau) {
	}
	
	// ######################### DROP ITEM #########################################

		@Override
		public void dropItem(Case c) {
			if (c.getItem().getNom().equals("Rien")) {
				c.setItem(new Key());
			}
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
		return 3*c.getTailleCasePixel();
	}

	@Override
	public int trouverlongeur(Case c) {
		// TODO Auto-generated method stub
		return 3*c.getTailleCasePixel();
	}


	

	
	

	
	
}
