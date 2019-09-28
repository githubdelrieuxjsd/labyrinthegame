package model;

public class Bomb extends Item {

	public Bomb() {
		super();
		
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection("down");
		this.setNom("Bomb");
		
	}

	@Override
	public String getImage(Plateau plateau, Case c) {
		String icon = "hyrule/item/bomb.png";
		
		switch (this.getCurentAction()) {
		
		case "nothing":
			icon = "hyrule/item/bomb.png";
			this.setFrame( (getFrame() + 1) % 32 );
			if (this.getFrame() == 0) {
				this.setCurentAction("exploding");
				this.setFrame(0);
			}
			break;
		case "exploding":
			icon = "hyrule/bomb/"+this.getFrame()+".png";
			this.setFrame((getFrame() + 1) % 12);

			if (this.getFrame() == 0) {
				c.setItem();
			}
			break;
		default: 
			break;

		}
		return icon;
		
	}

	@Override
	public void etreRamasser(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int trouverX(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return c.getElement().getCoordonnee().getX() *40 -40;
	}

	@Override
	protected int trouverY(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		return c.getElement().getCoordonnee().getY() *40 -40 ;
	}

	@Override
	protected int trouverlargeur() {
		// TODO Auto-generated method stub
		return 120;
	}

	@Override
	protected int trouverlongeur() {
		// TODO Auto-generated method stub
		return 120;
	} 
	

}
