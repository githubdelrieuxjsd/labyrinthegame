package baseDonnee;

public class Niveau {

	// 1 tree
	// 2 rock
	// 3 bush
	//
	// 101 knight
	// 102 chicken
	// 103 Tomato
	// 104 Goblin 
	
	// 201 Key
	// 202 Heart
	// 203 Rubi
	// 204 Chess
	public static void generationMapRandom() {
		System.out.print("{");
		for (int i = 0 ; i <400 ;i++) {
			int block = (int) (Math.random() * (3 + 1 - 0)) + 0;
			int x = (int) (Math.random() * (30-1 + 1 - 0)) + 0;
			int y = (int) (Math.random() * (17-1 + 1 - 0)) + 0;

			System.out.print(" , {"+block+","+x+","+y+"}");
		}
		System.out.println("} ;");
	}
	
	
	
	public static int [][] jeu1() {
		int [][] tab = { {1,0,0} , {1,2,2} } ; 
		return tab;
	}
	
	public static int [][] jeu2() {
		int [][] tab = {{2,28,3} , {2,20,7} , {3,21,5} , {2,5,2} , {3,8,6} , {0,17,12} , {2,11,14} , {2,18,6} , {2,3,6} , {0,14,2} , {3,15,10} , {1,24,8} , {1,14,12} , {0,8,9} , {1,26,13} , {1,20,2} , {2,8,9} , {0,23,2} , {1,13,4} , {2,2,14} , {0,6,6} , {2,28,11} , {0,19,6} , {3,20,12} , {1,11,5} , {2,11,15} , {2,26,2} , {3,22,5} , {2,8,15} , {1,9,16} , {1,28,7} , {2,11,2} , {1,16,6} , {1,19,10} , {0,1,8} , {0,18,14} , {1,28,9} , {0,14,9} , {3,17,9} , {3,12,6} , {2,8,11} , {0,3,10} , {0,13,4} , {2,9,12} , {3,18,7} , {1,12,8} , {1,14,1} , {0,27,9} , {3,27,3} , {1,11,13} , {2,21,7} , {1,24,14} , {2,8,11} , {2,5,2} , {1,1,2} , {1,2,7} , {3,10,8} , {2,2,14} , {2,20,16} , {3,29,8} , {1,2,2} , {2,3,7} , {3,27,6} , {2,24,8} , {3,25,8} , {0,13,3} , {3,10,1} , {2,22,15} , {1,7,16} , {1,19,9} , {3,3,4} , {0,19,6} , {2,12,12} , {3,7,5} , {3,14,13} , {0,8,3} , {2,4,6} , {0,5,8} , {3,21,7} , {2,15,4} , {1,1,3} , {3,9,15} , {0,27,13} , {3,3,12} , {2,2,1} , {2,20,2} , {1,23,9} , {0,27,3} , {3,14,10} , {0,15,5} , {2,21,10} , {2,8,16} , {3,8,16} , {2,13,7} , {1,21,15} , {2,28,5} , {0,10,3} , {0,16,8} , {2,9,9} , {1,20,15} , {3,6,8} , {2,15,10} , {1,13,3} , {0,25,5} , {3,26,11} , {0,18,3} , {0,2,9} , {2,1,4} , {2,29,3} , {2,23,13} , {0,8,16} , {0,3,5} , {3,2,2} , {1,13,2} , {2,23,9} , {3,27,3} , {1,15,7} , {0,1,12} , {2,5,7} , {0,17,5} , {1,16,4} , {2,25,8} , {1,11,6} , {0,3,2} , {0,4,9} , {1,6,15} , {3,8,14} , {3,23,13} , {0,11,2} , {2,21,1} , {3,6,7} , {0,20,3} , {1,28,9} , {3,26,6} , {0,10,16} , {3,18,14} , {2,17,9} , {2,14,14} , {1,5,7} , {0,14,3} , {3,28,2} , {3,21,6} , {1,18,14} , {2,22,15} , {3,10,9} , {1,5,9} , {2,25,5} , {2,27,13} , {0,18,5} , {0,26,15} , {1,5,7} , {2,28,15} , {0,25,8} , {3,15,3} , {0,24,3} , {2,23,4} , {3,17,3} , {3,16,5} , {3,28,11} , {3,28,1} , {3,17,6} , {2,4,2} , {3,17,13} , {3,18,1} , {3,24,1} , {1,2,9} , {3,25,13} , {2,6,10} , {3,4,16} , {3,17,11} , {0,23,8} , {3,3,10} , {2,5,6} , {1,6,16} , {1,12,8} , {2,2,4} , {0,3,7} , {3,28,12} , {0,6,5} , {0,22,8} , {0,28,12} , {3,10,6} , {3,18,5} , {0,12,15} , {0,25,13} , {2,4,13} , {3,19,6} , {1,18,3} , {0,17,3} , {1,19,13} , {2,28,6} , {3,5,6} , {2,19,13} , {1,8,16} , {1,23,13} , {0,11,12} , {1,4,11} , {3,7,6} , {2,15,14} , {2,13,14}}
 ; 
		return tab;
	}
	
	public static int [][] jeu3() {
		int [][] tab ={ {1,6,15} , {3,7,1} , {0,9,7} , {0,10,0} , {2,23,1} , {1,22,7} , {0,25,2} , {2,14,0} , {3,1,7} , {2,0,13} , {2,17,6} , {0,25,9} , {3,8,3} , {1,11,2} , {3,8,3} , {1,2,11} , {0,20,12} , {1,19,9} , {2,11,4} , {2,27,6} , {3,21,4} , {3,23,6} , {1,0,16} , {3,24,10} , {2,28,14} , {1,29,5} , {3,5,0} , {2,2,15} , {2,1,13} , {1,25,5} , {0,22,13} , {3,12,16} , {2,27,0} , {0,25,4} , {3,12,2} , {2,25,5} , {2,19,12} , {3,11,14} , {3,15,16} , {1,2,16} , {2,20,8} , {2,8,1} , {2,27,0} , {1,27,6} , {1,7,9} , {3,11,0} , {2,24,12} , {1,19,8} , {0,25,7} , {1,8,5} , {0,24,13} , {0,1,1} , {2,0,1} , {0,23,10} , {3,22,12} , {3,26,8} , {1,3,16} , {2,7,7} , {3,26,1} , {0,29,3} , {0,18,12} , {1,1,2} , {3,1,9} , {2,16,12} , {0,1,2} , {2,12,15} , {1,26,0} , {1,5,9} , {2,22,13} , {0,5,10} , {1,9,9} , {0,7,14} , {0,22,0} , {3,23,11} , {0,16,0} , {2,17,4} , {0,24,4} , {3,20,14} , {2,7,16} , {3,23,14} , {1,27,3} , {3,13,16} , {0,24,15} , {1,1,10} , {2,25,6} , {1,7,13} , {2,16,7} , {0,12,0} , {3,26,11} , {1,1,0} , {1,19,4} , {0,4,1} , {3,15,6} , {0,17,15} , {3,13,13} , {0,25,9} , {0,8,8} , {2,4,13} , {3,18,15} , {2,7,14} , {0,23,12} , {1,25,16} , {1,4,3} , {1,12,0} , {2,7,7} , {3,6,9} , {2,3,16} , {3,17,0} , {3,4,13} , {2,19,8} , {3,11,12} , {1,15,7} , {1,0,9} , {3,7,13} , {2,22,4} , {1,28,15} , {0,26,2} , {1,13,11} , {1,24,15} , {3,0,7} , {2,20,9} , {0,23,2} , {3,4,2} , {3,22,10} , {0,20,3} , {2,10,13} , {1,2,4} , {3,19,10} , {2,19,16} , {0,10,10} , {1,15,7} , {1,27,12} , {2,17,12} , {2,16,3} , {3,4,11} , {3,13,13} , {0,6,14} , {2,21,1} , {1,28,14} , {3,22,9} , {2,19,2} , {0,3,4} , {3,1,15} , {3,11,8} , {0,29,5} , {0,22,6} , {1,6,15} , {2,25,12} , {1,26,12} , {2,0,11} , {3,21,10} , {0,15,7} , {0,16,4} , {0,16,16} , {1,22,14} , {3,5,16} , {1,3,4} , {3,25,7} , {1,20,6} , {0,15,5} , {1,0,14} , {2,28,13} , {2,24,16} , {0,26,16} , {3,10,10} , {3,5,2} , {3,3,6} , {3,21,10} , {1,23,5} , {0,29,5} , {2,28,11} , {2,17,5} , {0,11,10} , {2,12,6} , {1,9,16} , {2,12,10} , {3,18,13} , {3,24,4} , {2,27,13} , {1,7,12} , {0,13,3} , {1,7,13} , {1,5,1} , {2,11,13} , {1,16,0} , {3,16,8} , {0,20,8} , {0,18,13} , {2,6,1} , {0,13,1} , {1,6,13} , {0,6,2} , {0,18,6} , {1,5,3} , {0,27,6} , {0,7,12} , {3,12,9} , {1,0,2} , {1,11,6} , {3,29,0} , {1,5,12} , {0,22,11} , {1,3,8} , {0,8,13} , {0,26,13} , {3,12,16} , {2,15,14} , {3,8,2} , {1,15,15} , {3,29,5} , {0,8,3} , {2,12,6} , {0,17,12} , {3,0,11} , {3,13,1} , {2,16,0} , {1,20,8} , {0,12,16} , {3,7,13} , {2,5,4} , {2,23,13} , {2,16,9} , {2,27,3} , {2,5,5} , {0,26,12} , {1,26,8} , {0,8,7} , {2,28,13} , {3,2,4} , {2,13,11} , {3,24,3} , {2,3,1} , {3,13,9} , {3,9,7} , {0,28,10} , {1,20,12} , {1,27,11} , {1,4,11} , {3,10,1} , {2,23,3} , {0,2,10} , {1,18,10} , {0,25,10} , {0,5,12} , {3,25,11} , {0,21,2} , {3,14,0} , {0,11,8} , {1,24,15} , {1,2,15} , {1,23,1} , {1,4,12} , {2,28,8} , {0,1,12} , {2,14,12} , {3,29,15} , {0,20,1} , {2,18,10} , {0,25,12} , {1,19,5} , {3,18,11} , {1,9,3} , {2,29,0} , {2,8,16} , {0,17,4} , {2,14,14} , {1,6,0} , {3,27,16} , {2,25,7} , {2,6,14} , {0,1,10} , {0,14,13} , {1,17,0} , {2,12,9} , {3,8,11} , {2,26,16} , {0,14,7} , {2,11,9} , {2,6,0} , {2,26,10} , {0,0,5} , {0,23,0} , {2,10,14} , {3,15,5} , {3,17,16} , {0,10,4} , {3,11,2} , {2,4,0} , {0,12,1} , {0,11,5} , {1,6,16} , {0,26,9} , {1,25,5} , {1,3,6} , {3,24,1} , {0,6,15} , {2,1,12} , {2,19,8} , {3,4,2} , {2,24,0} , {1,0,14} , {2,26,8} , {2,5,12} , {2,19,4} , {1,23,1} , {0,3,2} , {3,12,9} , {3,14,5} , {3,28,15} , {2,9,14} , {1,7,14} , {3,8,3} , {0,1,4} , {2,6,16} , {2,3,0} , {0,24,1} , {2,26,6} , {3,3,5} , {3,25,6} , {1,7,7} , {2,24,6} , {1,10,5} , {3,24,7} , {2,7,15} , {0,18,9} , {2,17,16} , {0,8,9} , {2,1,0} , {1,14,15} , {2,9,10} , {1,18,15} , {2,29,9} , {2,9,9} , {0,7,4} , {2,7,13} , {3,12,1} , {3,18,0} , {2,11,5} , {0,18,5} , {3,6,9} , {1,22,15} , {3,20,8} , {2,19,4} , {1,21,5} , {0,7,8} , {0,6,8} , {2,16,10} , {1,0,4} , {2,2,9} , {1,27,13} , {0,16,13} , {3,5,7} , {0,24,10} , {1,0,3} , {0,9,6} , {1,5,4} , {1,24,5} , {1,18,13} , {0,14,3} , {2,27,3} , {1,9,11} , {2,15,0} , {1,20,14} , {2,27,8} , {3,10,2} , {2,16,15} , {1,27,16} , {0,9,11} , {0,4,15} , {2,1,6} , {2,12,0} , {0,2,15} , {2,10,1} , {0,18,14} , {3,8,11} , {2,4,6} , {0,13,6} , {3,11,11} , {3,10,4} , {1,26,13} , {2,16,4} , {1,11,16} , {0,0,13} , {3,20,15} , {3,11,10} , {2,12,1} , {1,17,4} , {0,18,12} , {0,8,3} , {1,9,6} , {0,23,15} , {0,7,16} , {2,10,7} , {3,28,15} , {1,16,11} , {0,9,15} , {2,12,10} , {2,27,6} , {3,11,12} , {2,25,8}} ;

		return tab;
	}

	
}
