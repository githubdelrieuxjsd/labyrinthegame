package block;

import javax.swing.ImageIcon;

import damage.Damage;
import mobInterface.Deplacement;
import model.Case;
import model.Coordonnee;
import model.Direction;
import model.Plateau;
import tool.Tool;

public class Rock extends Block implements Deplacement {

	private int life;

	public Rock() {
		super();
		this.setSpawnable(false);

		this.setFrame(0);
		this.setCurentAction("nothing");
		this.setDirection(new Direction("down"));
		this.setNom("Rock");
		this.setFrame(0);
		this.life = 1;
	}
	
	// ####################A FAIRE ######################

	
	//######################### DEPLACEMENT  #########################################	


	@Override
	public void deplacer(Plateau plateau, Case caseAvant, Case caseApres) {
		this.setCurentAction("moving");
		this.setFrame(0);

		Coordonnee cordApres = new Coordonnee(caseApres.getCoordonnee());

		int num = plateau.coordinateToNum(caseAvant.getCoordonnee());
		Vide v = new Vide( );
		plateau.getListCase().get(num).setElement(v);

		num = plateau.coordinateToNum(caseApres.getCoordonnee());
		
		plateau.getListCase().get(num).setElement(this);
		this.setNumeroCase(num);
		
	}

	@Override
	public void interactionDeplacement(Plateau plateau, Case caseAvant, Direction direction) {
		this.setDirection(direction);
		Case caseApres = plateau.getCaseDevant(caseAvant, this.getDirection());
		
		if (caseApres.getElement().getNom().equals("Vide")) {
			
			//this.deplacer(plateau,caseAvant, caseApres);
		}
		
	}



	//######################### PERDRE VIE  #########################################	
	
	@Override
	public void perdreVie(Damage damage, Plateau plateau) {
		if (damage.doDamage(this)) {
			this.life = life - damage.getExplosion();
		}
		if (life < 0) {
			this.detruir();
			
		}
	}



//######################### IMAGE  #########################################

	@Override
	public String trouverImage(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		String icon = "hyrule/block/rock.png";

		switch (this.getCurentAction()) {
		case "nothing":
			break;
		case "moving":
			this.setFrame((getFrame() + 1) % 3);
			if (this.getFrame() == 0) {
				this.setCurentAction("nothing");
			}
			break;
		case "dead":
			icon = "";
			break;
		default:
			break;

		}

		return icon;
	}

	@Override
	public int trouverX(Case c) {
		int res = c.getCoordonnee().getX()*c.getTailleCasePixel()+15-2*c.getTailleCasePixel();
		if (this.getDirection().equals("right") && this.getCurentAction().equals("moving")) {
			res = res + this.getFrame() * c.getTailleCasePixel()/3 - c.getTailleCasePixel() ;
		} else if (this.getDirection().equals("left") && this.getCurentAction().equals("moving")) {
			res = res - this.getFrame() * c.getTailleCasePixel()/3 + c.getTailleCasePixel();
		}
		return res;
	}

	@Override
	public int trouverY(Case c) {
		int res = c.getCoordonnee().getY()*c.getTailleCasePixel() -15 -c.getTailleCasePixel()
				- c.getCoordonnee().getZ()*c.getTailleCasePixel()/2;
		if (this.getDirection().equals("up") && this.getCurentAction().equals("moving")) {
			res = res - this.getFrame() * c.getTailleCasePixel()/3 +c.getTailleCasePixel() ;
		} else if (this.getDirection().equals("down") && this.getCurentAction().equals("moving")) {
			res = res + this.getFrame() * c.getTailleCasePixel()/3 -c.getTailleCasePixel();
		}
		return res;
	}

	@Override
	public int trouverlongeur(Case c) {
		// TODO Auto-generated method stub
		return 3*c.getTailleCasePixel();
	}

	@Override
	public int trouverlargeur(Case c) {
		// TODO Auto-generated method stub
		return 3*c.getTailleCasePixel();
	}

	

}
