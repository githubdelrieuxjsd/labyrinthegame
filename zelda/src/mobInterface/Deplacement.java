package mobInterface;

import model.Case;
import model.Direction;
import model.Plateau;

public interface Deplacement {

	void deplacer(Plateau plateau, Case caseAvant, Case caseApres);
	void interactionDeplacement(Plateau plateau, Case caseAvant, Direction direction);

	
	
	
}
