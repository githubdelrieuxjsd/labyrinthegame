package model;

public class Coordonnee {

	private int x;
	private int y;
	private int z;

	public Coordonnee(int x, int y, int z ) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Coordonnee(Coordonnee coordonnee) {
		// TODO Auto-generated constructor stub
		this.x = coordonnee.getX();
		this.y = coordonnee.getY();
		this.z = coordonnee.getZ();
	}

	/**
	public Direction regardeVers(Coordonnee coordonnee) {
		Direction res = new Direction("up");
		if (this.getY() < coordonnee.getY()) {
			res.setDirection("down");
		}
		if (this.getX() < coordonnee.getX()) {
			res.setDirection("right");
		}
		if (this.getX() > coordonnee.getX()) {
			res.setDirection("left");
		}
		return res;
	}
*/
	
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

	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z=z;
	}

	
	public boolean isEqual(Coordonnee coordonnee) {
		// TODO Auto-generated method stub
		return (x == x && y == y && z==z);
	}

	@Override
	public String toString() {
		return "[" + x + "," + y + "," + z + "]";
	}

}
