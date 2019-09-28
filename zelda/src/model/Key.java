package model;

public class Key extends Item {

	

	public Key() {
		super();
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection("down");
		this.setNom("Key");
		
	}

	@Override
	public String getImage(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return "hyrule/item/key.png";
	}

	@Override
	public void etreRamasser(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int trouverX(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return c.getElement().getCoordonnee().getX() *40 +7;
	}

	@Override
	protected int trouverY(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return c.getElement().getCoordonnee().getY() *40+10;
	}

	@Override
	protected int trouverlargeur() {
		// TODO Auto-generated method stub
		return 25;
	}

	@Override
	protected int trouverlongeur() {
		// TODO Auto-generated method stub
		return 25;
	} 
	
	
}
