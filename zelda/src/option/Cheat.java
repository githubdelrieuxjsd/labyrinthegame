package option;

public abstract class Cheat {

	private static boolean monstreAction = false ;
	
	private  static boolean heroCanDoActionNoMatterCurentAction = true ;
	private static boolean heroCanDoActionEvryTick = true ;
	private static boolean heroEpeeInfintDamage = true ;
	private static boolean heroEpeeDoDamageFirstFrame = false ;
	private static boolean heroAutoAttaqueWhenMoving = true ;
	private static boolean heroCanDestroyRock = true ;
	private static boolean heroLoseLife = false ;
	private static boolean heroInfinitFleche = false ;
	
	private static boolean treeCanBeDestroyed = true ;
	
	
	public static  boolean monstreCanDoAction() {
		return monstreAction;
	}


	public static boolean heroCanDoActionNoMatterCurentAction() {
		return heroCanDoActionNoMatterCurentAction;
	}
	public static boolean heroCanDestroyRock() {
		return heroCanDestroyRock;
	}
	public static boolean heroCanDoActionEvryTick() {
		return heroCanDoActionEvryTick;
	}
	public static boolean heroEpeeInfintDamage() {
		return heroEpeeInfintDamage;
	}
	public static boolean heroAutoAttaqueWhenMoving() {
		return heroAutoAttaqueWhenMoving;
	}
	public static boolean heroEpeeDoDamageFirstFrame() {
		return heroEpeeDoDamageFirstFrame;
	}
	public static boolean heroLoseLife() {
		return heroLoseLife;
	}
	public static boolean heroInfinitFleche() {
		return heroInfinitFleche;
	}
	
	public static boolean treeCanBeDestroyed() {
		return treeCanBeDestroyed;
	}
	
	

	
}
