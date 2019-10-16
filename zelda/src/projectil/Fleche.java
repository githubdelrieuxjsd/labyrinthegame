package projectil;

import javax.swing.ImageIcon;

import block.Vide;
import damage.Damage;
import mobInterface.Attaque;
import mobInterface.Deplacement;
import mobInterface.Dessin;
import model.Case;
import model.Coordonnee;
import model.Direction;
import model.Plateau;
import tool.Tool;

public class Fleche extends Projectil {

	
	public Fleche(Direction direction ) {
		super();
		this.setExist(true);
		this.setCurentAction("moving") ;
		this.setDirection(direction);
		this.setNom("Fleche");
		this.setDamageHero(0,0,0,1);
		this.setFrame(0);
		
	}

	@Override
	public String trouverImage(Plateau plateau , Case c) {
		String icon = "img/vide.png";
		//"hyrule/link/arrow/arrowUp.png"

		if (getExist() ) {
			if (getDirection().equals("up")) {
				icon = "hyrule/arrow/U.png";
			} else if (getDirection().equals("down")) {
				icon = "hyrule/arrow/D.png";
			} else if (getDirection().equals("left")) {
				icon = "hyrule/arrow/L.png";
			} else if (getDirection().equals("right")) {
				icon = "hyrule/arrow/R.png";
			}
		}
		//System.out.println("frame:"+ getFrame());

		return icon;
	}
	
	
	@Override
	public int trouverX(Case c) {
		int res =  c.getCoordonnee().getX()*c.getTailleCasePixel()+15 -c.getTailleCasePixel();
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
		int res = c.getCoordonnee().getY()*c.getTailleCasePixel() -17
				- c.getCoordonnee().getZ()*c.getTailleCasePixel()/2;
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
