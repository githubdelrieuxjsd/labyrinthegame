package model;

public class Stairs extends Trap{
	
	private Coordonnee coordonnee;
	public Stairs(Coordonnee coordonnee ) {
		super();
		this.setNom("Stairs");
		this.coordonnee = coordonnee;
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

	public Coordonnee getCoordonnee() {
		return coordonnee;
	}
	
}
