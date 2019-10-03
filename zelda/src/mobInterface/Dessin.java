package mobInterface;

import model.Case;
import model.Plateau;

public interface Dessin {

	
	
	String trouverImage( Plateau plateau ,Case c );

	int trouverX(Case c);

	int trouverY(Case c);

	int trouverlongeur(Case c);

	int trouverlargeur(Case c);


	
}
