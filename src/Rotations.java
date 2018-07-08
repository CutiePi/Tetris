
public class Rotations {
	/**
	 * @author Jasmin
	 * classe qui contient chaque rotation pour chaque piece pré calculer. 
	 * Calculer a partir dune grille de 0-199 avec ligne de 10  case 0-9
	 */
	int tabRotationS1[] = {20,11,0,-9};
	int tabLvLS1[] = {20,10,0,-10};
	int tabRotationZ1[] = {9,11,0,2};
	int tabLvLZ1[] = {10,10,0,0};
	int tabRotationI1[] = {-1,-10,-19,-28};
	int tabLvLI1[] = {0,-10,-20,-30};
	
	int tabRotationT1[] = {20,11,0,2}, tabRotationT2[] = {2,-9,0,-20}, tabRotationT3[] = {-20,-11,0,-2}, tabRotationT4[] = {-2,9,0,20};
	int tabLvLT1[] = {20,10,0,0}, tabLvLT2[] = {0,-10,0,-20}, tabLvLT3[] = {-20,-10,0,0},tabLvLT4[] = {0,10,0,20};
	
	int tabRotationLR1[] = {9,0,2,-9}, tabRotationLR2[] = {10,-1,-21,-12}, tabRotationLR3[] = {2,11,9,20}, tabRotationLR4[] = {-21,-10,10,1};
	int tabLvLLR1[] = {10,0,0,-10}, tabLvLLR2[] = {10,0,-20,-10}, tabLvLLR3[] = {0,10,10,20}, tabLvLLR4[] = {-20,-10,10,0};
	
	int tabRotationL1[] = {20,11,2,-9}, tabRotationL2[] = {1,-10,-21,-12}, tabRotationL3[] = {-9,0,9,20}, tabRotationL4[] = {-12,-1,10,1};
	int tabLvLL1[] = {20,10,0,-10}, tabLvLL2[] = {0,-10,-20,-10}, tabLvLL3[] = {-10,0,10,20}, tabLvLL4[] = {-10,0,10,0};
	
	int[] tabT[] = {tabRotationT1,tabRotationT2,tabRotationT3,tabRotationT4}; 
	int[] tabLvLT[] = {tabLvLT1,tabLvLT2,tabLvLT3,tabLvLT4}; 
	
	int[] tabLR[] = {tabRotationLR1,tabRotationLR2,tabRotationLR3,tabRotationLR4};
	int[] tabLvLLR[] = {tabLvLLR1,tabLvLLR2,tabLvLLR3,tabLvLLR4}; 
	
	int[] tabL[] = {tabRotationL1,tabRotationL2,tabRotationL3,tabRotationL4}; 
	int[] tabLvLL[] = {tabLvLL1,tabLvLL2,tabLvLL3,tabLvLL4}; 
	
	public Rotations(){
		
	}

}
