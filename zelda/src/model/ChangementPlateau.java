package model;

public class ChangementPlateau extends Trap{

	
	public ChangementPlateau( ) {
		super();
		this.setNom("ChangementPlateau");
	}
	
	
	// #################### IMAGE ######################

	



	@Override
	public String trouverImage(Plateau plateau ,Case c) {
		// TODO Auto-generated method stub
		return "hyrule/block/changementPlateau.png";
	}

	@Override
	public int trouverX(Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getX()*c.getTailleCasePixel()-2*c.getTailleCasePixel();
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


	
	
}
