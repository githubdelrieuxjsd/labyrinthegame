package option;

public abstract class Cheat {

	private static boolean monstreAction = true ;
	
	private  static boolean heroCanDoActionNoMatterCurentAction = false ;
	private static boolean heroCanDoActionEvryTick = false ;
	private static boolean heroEpeeInfintDamage = false ;
	private static boolean heroEpeeDoDamageFirstFrame = false ;
	private static boolean heroAutoAttaqueWhenMoving = false ;
	private static boolean heroCanDestroyRock = false ;
	private static boolean heroLoseLife = true ;
	private static boolean heroInfinitFleche = false ;
	
	private static boolean treeCanBeDestroyed = false ;
	
	
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
