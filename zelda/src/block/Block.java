package block;

import controleur.Control;
import model.Element;

public abstract class Block extends Element{

	public void detruir () {
		Control.removeBlock(this);
	}
	
}
