package model;

public class Coordonnee {

	private int x;
	private int y;

	public Coordonnee(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Coordonnee(Coordonnee coordonnee) {
		// TODO Auto-generated constructor stub
		this.x = coordonnee.getX();
		this.y = coordonnee.getY();
	}

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
		return (x == x && y == y);
	}

	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}

}
