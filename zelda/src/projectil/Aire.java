package projectil;

import model.Case;
import model.Plateau;

public class Aire extends Projectil {
	
	public Aire() {
		this.setNom("Aire");
		
	}

	@Override
	public String trouverImage(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int trouverX(Case c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int trouverY(Case c) {
		// TODO Auto-generated method stub
		return 0;
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
