package model;

public class Rien extends Item {
	
		
	public Rien() {
		super();
		this.setNom("Rien");
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection("down");
	}

	@Override
	public String getImage(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void etreRamasser(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int trouverX(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return c.getElement().getCoordonnee().getX()*40;
	}

	@Override
	protected int trouverY(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return c.getElement().getCoordonnee().getY()*40;
	}

	@Override
	protected int trouverlargeur() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	protected int trouverlongeur() {
		// TODO Auto-generated method stub
		return 1;
	} 
}
