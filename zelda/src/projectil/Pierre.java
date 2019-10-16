package projectil;

import block.Rock;
import block.Vide;
import damage.Damage;
import model.Case;
import model.Coordonnee;
import model.Direction;
import model.Element;
import model.Hero;
import model.Plateau;
import monstre.Chicken;
import tool.Tool;

public class Pierre  extends Projectil {


	
	public Pierre(Direction direction) {
		super();
		this.setNom("Pierre");
		this.setExist(true);
		this.setFrame(0);
		this.setCurentAction("moving") ;
		this.setDamageNeutre(0, 0, 0, 1);
		this.setDirection(direction);
	}



	


	

		
	//######################### IMAGE #########################################	


	@Override
	public String trouverImage(Plateau plateau , Case c) {
		String icon = "hyrule/tomato/tirer/pierre.png";
		return icon;
	}

	@Override
	public int trouverX(Case c) {
		int res = c.getCoordonnee().getX()*c.getTailleCasePixel()+15 -c.getTailleCasePixel() ;
		if (this.getDirection().equals("right")) {
			res = res + this.getFrame()*10 ;
		}
		else if (this.getDirection().equals("left")) {
			res = res - this.getFrame()*10 ;
		}
		return res;
	}

	@Override
	public int trouverY(Case c) {
		int res = c.getCoordonnee().getY()*c.getTailleCasePixel() -15 ;
		if (this.getDirection().equals("up")) {
			res = res - this.getFrame()*10 ;
		}
		else if (this.getDirection().equals("down")) {
			res = res + this.getFrame()*10 -20 ;
		}
		
		if (this.getDirection().equals("right")) {
			res = res - 10 ;
		}
		else if (this.getDirection().equals("left")) {
			res = res - 5 ;
		}
		
		return res;
	}

	@Override
	public int trouverlargeur(Case c) {
		// TODO Auto-generated method stub
		return c.getTailleCasePixel();
	}


	@Override
	public int trouverlongeur(Case c) {
		// TODO Auto-generated method stub
		return c.getTailleCasePixel();
	}

	
	
}
