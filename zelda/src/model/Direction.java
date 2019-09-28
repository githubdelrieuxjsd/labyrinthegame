package model;

public class Direction {

	
	String direction ;

	
	
	public Direction(String direction) {
		super();
		switch (direction) {
		case "up":
			this.direction = "up";
			break;
		case "down":
			this.direction = "down";

			break;
		case "left":
			this.direction = "left";

			break;
		case "right":
			this.direction = "right";

			break;
		default:
			System.out.println( "!!! ERROR DIRECTION CONSTRUCTEUR CAN T ASSIGNE :"+ direction);
			this.direction = "down";
			break;
		}
	}

	public boolean oppose(Direction direction) {
		boolean res = false ;
		if (this.direction.equals("right") && direction.equals("left")) {
			res = true;
		}
		if (this.direction.equals("left") && direction.equals("right")) {
			res = true;
		}
		if (this.direction.equals("up") && direction.equals("down")) {
			res = true;
		}
		if (this.direction.equals("down") && direction.equals("up")) {
			res = true;	
		}
	return res;
	}
	
	public boolean equals (String s) {
		return this.getDirection().equals(s);
	}
	
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	} 
	
	
	
}
