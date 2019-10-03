package item;

import model.Case;
import model.Plateau;

public class Rien extends Item {
	
		
	public Rien() {
		super();
		this.setNom("Rien");
		this.setFrame(0);
		this.setCurentAction("nothing") ;
	}

	@Override
	public String trouverImage(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void etreRamasser( Case c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int trouverX( Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getX()*60;
	}

	@Override
	public int trouverY( Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getY()*60;
	}

	@Override
	public int trouverlargeur(Case c) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int trouverlongeur(Case c) {
		// TODO Auto-generated method stub
		return 1;
	} 
}
