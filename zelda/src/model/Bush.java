package model;

public class Bush extends Block {

	private int life ; 
	
	public Bush(Coordonnee coordonnee) {
		super();
		this.setCoordonnee(coordonnee);
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection("down");
		this.setNom("Bush");
		this.life = 1;
	}
	
	public Bush(int x, int y) {
		super();
		this.setCoordonnee(new Coordonnee(x,y));
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setDirection("down");
		this.setNom("Bush"); 
		this.life = 1;
	}

	@Override
	public void perdreVie(Damage damage, Plateau plateau) {
		// TODO Auto-generated method stub
		this.life = life - damage.getEpee() ;
		if (life < 0 ) {
			this.setCurentAction("dead")  ;
			this.setNom("Vide");
		}
	}
	
	
	@Override
	public void deplacer(String direction, Plateau plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getImage(Plateau plateau , Case c) {
		// TODO Auto-generated method stub
		String icon = "hyrule/block/bush.png" ;

		if (this.getCurentAction().equals("dead")) {
			icon = "";
		}
		return icon ;
	}

	@Override
	protected int trouverX() {
		// TODO Auto-generated method stub
		return this.getCoordonnee().getX()*40-40;
	}

	@Override
	protected int trouverY() {
		// TODO Auto-generated method stub
		return this.getCoordonnee().getY()*40-40;
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
