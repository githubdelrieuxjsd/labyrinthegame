package model;

public abstract class Block extends Element{


	public Block(Coordonnee coordonnee) {
		super(coordonnee);
	}
	
	public Block(int x, int y) {
		super(x,y);
	}

	public abstract void deplacer(String direction  , Plateau plateau) ;

	
	public void deplacer(String direction, Plateau plateau, boolean pousserRock) {
		// TODO Auto-generated method stub
		
	}
}
