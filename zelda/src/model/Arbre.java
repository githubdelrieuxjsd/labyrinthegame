package model;

public class Arbre extends  Block {

	public Arbre(Coordonnee coordonnee) {
		super(coordonnee);
		// TODO Auto-generated constructor stub
		this.setNom("Arbre"); 
	}

	public Arbre(int x, int y) {
		super(x,y);
		this.setNom("Arbre"); 

		// TODO Auto-generated constructor stub
	}

	@Override
	public void deplacer(String direction, Plateau plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getImage(Plateau plateau ,Case c) {
		// TODO Auto-generated method stub
		return "hyrule/block/tree.png";
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
		
	}

	

	
	
}
