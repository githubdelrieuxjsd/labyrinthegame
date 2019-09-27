package model;

public class Coordonnee {

	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}

	int x ;
	int y ;
	
	public Coordonnee(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Coordonnee(Coordonnee coordonnee) {
		// TODO Auto-generated constructor stub
		this.x = coordonnee.getX() ;
		this.y = coordonnee.getY() ;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isEqual(Coordonnee coordonnee) {
		// TODO Auto-generated method stub
		return (x==x && y==y);
	}

	
	
	
}
