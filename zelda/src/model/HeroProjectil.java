package model;

import javax.swing.ImageIcon;

import tool.Tool;

public class HeroProjectil extends Projectil {

	
	public HeroProjectil(Coordonnee coordonnee, String direction ) {
		super();
		this.setExist(true);
		this.setCoordonnee(coordonnee);
		this.setCurentAction("moving") ;
		this.setDirection(direction);
		this.setNom("HeroProjectil");
		this.setDamage(0,0,0,1);
		this.setFrame(-2);
		
	}

	@Override
	public void deplacer(Plateau plateau) {
		// TODO Auto-generated method stub
		if (this.getExist()) {

			switch (getDirection()) {

			case "up":
				Case caseUp = plateau.getCaseUp(this.getCoordonnee());
				interactionDeplacement(plateau, caseUp);
				break;
			case "down":
				Case caseDown = plateau.getCaseDown(this.getCoordonnee());
				interactionDeplacement(plateau, caseDown);
				break;
			case "left":
				Case caseLeft = plateau.getCaseLeft(this.getCoordonnee());
				interactionDeplacement(plateau, caseLeft);
				break;
			case "right":
				Case caseRight = plateau.getCaseRight(this.getCoordonnee());
				interactionDeplacement(plateau, caseRight);
				break;
			default:
				break;

			}

		}

	}

	private void interactionDeplacement(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		switch (c.getElement().getNom()) {

		case "Vide":
			interactionVide(plateau, c);
			break;
		case "Arbre":
			interactionArbre(plateau);
			break;
		case "Rock":
			interactionArbre(plateau);
			break;
		case "Minotaure": interactionMinautore(plateau, c);

			break;
		case "MinotaureProjectil": interactionMinotaureProjectil(plateau, c);

		break;
		case "Chicken": interactionChicken(plateau, c);

		break;
		
		default:interactionArbre(plateau);

		//	System.out.println("heroprojectil est rentrer dans " + c.getElement().getNom());
			break;
		}
	}

	private void interactionMinotaureProjectil(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		int num = Tool.CoordinateToNum(getCoordonnee());
		Vide v = new Vide(getCoordonnee());
		plateau.getListCase().get(num).setElement(v);
		this.setExist(false);
		
		MinotaureProjectil mp = ((MinotaureProjectil)c.getElement());
		num = Tool.CoordinateToNum(mp.getCoordonnee());
		v = new Vide(mp.getCoordonnee());
		plateau.getListCase().get(num).setElement(v);
		mp.setExist(false);
		}

	private void interactionChicken(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		c.getElement().perdreVie(getDamage(),plateau);
		int num = Tool.CoordinateToNum(getCoordonnee());
		Vide v = new Vide(getCoordonnee());
		plateau.getListCase().get(num).setElement(v);
		this.setExist(false); 
	}

	private void interactionMinautore(Plateau plateau, Case c) {
		// TODO Auto-generated method stub
		((Minotaure)c.getElement()).perdreVie(getDamage(),plateau);
		int num = Tool.CoordinateToNum(getCoordonnee());
		Vide v = new Vide(getCoordonnee());
		plateau.getListCase().get(num).setElement(v);
		this.setExist(false); 

	}

	private void interactionArbre(Plateau plateau) {
		// TODO Auto-generated method stub
		int num = Tool.CoordinateToNum(getCoordonnee());
		Vide v = new Vide(getCoordonnee());
		plateau.getListCase().get(num).setElement(v);
		this.setExist(false); 
	}

	private void interactionVide(Plateau plateau, Case caseApres) {
		// TODO Auto-generated method stub
		
		if (this.getExist()) {
			Case caseAvant = plateau.getCase(this.getCoordonnee());
			Coordonnee cordApres = new Coordonnee(caseApres.getElement().getCoordonnee());

			int num = Tool.CoordinateToNum(caseAvant.getElement().getCoordonnee());
			Vide v = new Vide(this.getCoordonnee());
			plateau.getListCase().get(num).setElement(v);

			num = Tool.CoordinateToNum(caseApres.getElement().getCoordonnee());
			plateau.getListCase().get(num).setElement(this);
			this.setCoordonnee( cordApres );
		}
		

	}

	@Override
	public String getImage(Plateau plateau , Case c) {
		String icon = "img/vide.png";
		//"hyrule/link/arrow/arrowUp.png"
		if (getExist() ) {
			if (getDirection().equals("up")) {
				icon = "image/link/arrowUp24x24.png";
			} else if (getDirection().equals("down")) {
				icon = "image/link/arrowDown24x24.png";
			} else if (getDirection().equals("left")) {
				icon = "image/link/arrowLeft24x24.png";
			} else if (getDirection().equals("right")) {
				icon = "image/link/arrowRight24x24.png";
			}
		}
		this.setFrame( (getFrame() + 1)%4 );
	//	System.out.println("frame:"+ getFrame());
		return icon;
	}
	
	
	@Override
	protected int trouverX() {
		int res =  this.getCoordonnee().getX()*40 ;
		if (this.getDirection().equals("right")) {
			res = res + this.getFrame()*10 ;
		}
		else if (this.getDirection().equals("left")) {
			res = res - this.getFrame()*10 ;
		}
		return res;
	}

	@Override
	protected int trouverY() {
		int res =  this.getCoordonnee().getY()*40 ;
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
	protected int trouverlargeur() {
		// TODO Auto-generated method stub
		return 40;
	}

	@Override
	protected int trouverlongeur() {
		// TODO Auto-generated method stub
		return 40;
	}

	@Override
	public void perdreVie(Damage damage, Plateau plateau) {
		// TODO Auto-generated method stub
		
	}


}
