package model;

public class Bomb extends Item {

	private Damage damage ;
	
	public Bomb() {
		super();
		this.damage = new Damage (0,0,2,0)	;
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection("down");
		this.setNom("Bomb");
		
	}

	private void exploser(Plateau plateau, Case c){
		Coordonnee coord = c.getElement().getCoordonnee() ;
		Case c1 = plateau.getCase(coord);
		Case c2 = plateau.getCaseUp(coord);
		Case c3 = plateau.getCaseUpRight(coord);
		Case c4 = plateau.getCaseUpLeft(coord);
		Case c5 = plateau.getCaseDown(coord);
		Case c6 = plateau.getCaseDownRight(coord);
		Case c7 = plateau.getCaseDownLeft(coord);
		Case c8= plateau.getCaseLeft(coord);
		Case c9= plateau.getCaseRight(coord);
		
		c1.getElement().perdreVie(this.damage, plateau);
		c2.getElement().perdreVie(this.damage, plateau);
		c3.getElement().perdreVie(this.damage, plateau);
		c4.getElement().perdreVie(this.damage, plateau);
		c5.getElement().perdreVie(this.damage, plateau);
		c6.getElement().perdreVie(this.damage, plateau);
		c7.getElement().perdreVie(this.damage, plateau);
		c8.getElement().perdreVie(this.damage, plateau);
		c9.getElement().perdreVie(this.damage, plateau);


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
			if (this.getFrame() == 1) {
				this.exploser(plateau, c);
			}
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
