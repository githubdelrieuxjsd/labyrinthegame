package tool;

import model.Coordonnee;

public abstract class Tool {

	
	public static int CoordinateToNum(Coordonnee c) {
		return 25*c.getX()+c.getY();
	}
	
	public static int CoordinateToNum(int x , int y) {
		return 25*x+y;
	}
	
	public static Coordonnee NumToCoordinate(int num) {
		return new Coordonnee(num/25 , num%25);
	}
	
	public static int[] NumToCoordinateTab(int num) {
		int [] res =  {num/25 , num%25} ;
		return res  ;
	}
	
}
