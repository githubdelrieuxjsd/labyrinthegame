package model;

public class Heart extends Item{

	
	public Heart() {
		super();
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setNom("Heart");
		
	}

	@Override
	public String getImage(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return "hyrule/heart/rouge.png";
	}

	@Override
	public void etreRamasser( Case c) {
		c.setItem();
	}

	@Override
	protected int trouverX(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return c.getElement().getCoordonnee().getX() *40 + 10;
	}

	@Override
	protected int trouverY(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return c.getElement().getCoordonnee().getY() *40 ;
	}

	@Override
	protected int trouverlargeur() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	protected int trouverlongeur() {
		// TODO Auto-generated method stub
		return 20;
	} 
	
	
}
