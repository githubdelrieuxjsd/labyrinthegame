package model;

public class Rubi extends Item{

	public Rubi() {
		super();
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setNom("Rubi");
		
	}

	@Override
	public String getImage(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return "hyrule/item/rubi.png";
	}

	@Override
	public void etreRamasser( Case c) {
		c.setItem();
	}

	@Override
	protected int trouverX(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return c.getElement().getCoordonnee().getX() *40 ;
	}

	@Override
	protected int trouverY(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return c.getElement().getCoordonnee().getY() *40 -15 ;
	}

	@Override
	protected int trouverlargeur() {
		// TODO Auto-generated method stub
		return 40;
	}

	@Override
	protected int trouverlongeur() {
		// TODO Auto-generated method stub
		return 40;
	} 
	

}
