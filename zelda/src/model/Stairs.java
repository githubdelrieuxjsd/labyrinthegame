package model;

public class Stairs extends Trap{
	
	public Stairs() {
		super();
		this.setNom("Stairs");
	}
	
	
	// #################### IMAGE ######################




	@Override
	public String trouverImage(Plateau plateau ,Case c) {
		// TODO Auto-generated method stub
		return "hyrule/trap/stairs.png";
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
