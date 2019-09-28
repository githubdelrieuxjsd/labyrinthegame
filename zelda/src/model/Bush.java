package model;

public class Bush extends Block {

	private int life ; 
	
	public Bush(Coordonnee coordonnee) {
		super(coordonnee);
		this.setNom("Bush");
		this.life = 1;
		// TODO Auto-generated constructor stub
	}
	
	public Bush(int x, int y) {
		super(x, y);
		this.setNom("Bush"); 
		this.life = 1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deplacer(String direction, Plateau plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getImage(Plateau plateau , Case c) {
		// TODO Auto-generated method stub
		//String icon = "hyrule/block/bush.png" ;
		String icon = "hyrule/objet/bomb.png" ;

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

	@Override
	protected void perdreVie(int damage, Plateau plateau) {
		// TODO Auto-generated method stub
		this.life = life - damage ;
		if (life == 0 ) {
			this.setCurentAction("dead")  ;
			this.setNom("Vide");
		}
	}


	

}
