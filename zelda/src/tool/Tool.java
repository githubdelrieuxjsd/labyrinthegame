package tool;

import model.Coordonnee;

public abstract class Tool {

	private static int nbCaseX = 200;
	private static int nbCaseY = 100;
	
	public static int CoordinateToNum(Coordonnee c) {
		return c.getX()+nbCaseX*c.getY()+c.getZ()*nbCaseX*nbCaseY  ;
	}
	
	public static int CoordinateToNum(int x , int y) {
		return x+nbCaseX*y;
	}
	
	
	public static int CoordinateToNum(int x , int y,int z) {
		return nbCaseX*nbCaseY*z+x+nbCaseX*y;
	}
	
	
	/*
	public static Coordonnee NumToCoordinate(int num) {
		System.out.println(num/nbCaseY +","+num%nbCaseX);
		return new Coordonnee(num/nbCaseY , num%nbCaseX);
	}
	
	public static int[] NumToCoordinateTab(int num) {
		int [] res =  {num/nbCaseY , num%nbCaseX} ;
		return res  ;
	}
	*/
}
