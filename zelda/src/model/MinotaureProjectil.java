package model;

import tool.Tool;

public class MinotaureProjectil  extends Projectil{


	
	public MinotaureProjectil(Coordonnee coordonnee, String direction) {
		super();
		this.setNom("MinotaureProjectil");
		this.setExist(true);
		this.setCoordonnee(coordonnee);
		this.setFrame(0);
		this.setCurentAction("moving") ;
		this.setDirection(direction);
		this.setFrame(0);
		this.setDamage(0, 0, 0, 0);

	}


	@Override
	public void deplacer(Plateau plateau) {
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
			case "Hero": interactionHero(plateau, c);

				break;
			case "HeroProjectil": interactionHeroProjectil(plateau, c);

			break;
			case "Chicken": interactionChicken(plateau, c);

			break;
			
			default:
				//System.out.println("MinotaureProjectil est rentrer dans " + c.getElement().getNom());
				break;
			}
		}
		
		private void interactionHeroProjectil(Plateau plateau, Case c) {
			// TODO Auto-generated method stub
			int num = Tool.CoordinateToNum(getCoordonnee());
			Vide v = new Vide(getCoordonnee());
			plateau.getListCase().get(num).setElement(v);
			this.setExist(false); 
			
			HeroProjectil heroProjectil = ((HeroProjectil)c.getElement());
			num = Tool.CoordinateToNum(heroProjectil.getCoordonnee());
			v = new Vide(heroProjectil.getCoordonnee());
			plateau.getListCase().get(num).setElement(v);
			heroProjectil.setExist(false); 
		}

		private void interactionChicken(Plateau plateau, Case c) {
			// TODO Auto-generated method stub
			((Chicken)c.getElement()).perdreVie(new Damage (0,0,0,0),plateau);
			int num = Tool.CoordinateToNum(getCoordonnee());
			Vide v = new Vide(getCoordonnee());
			plateau.getListCase().get(num).setElement(v);
			this.setExist(false); 
		}

		private void interactionHero(Plateau plateau, Case c) {
			// TODO Auto-generated method stub
			((Hero)c.getElement()).perdreVie(getDamage(),plateau);
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
				this.setCoordonnee(cordApres);
			}
			

		}

	@Override
	public String getImage(Plateau plateau , Case c) {
		String icon = "img/grass2.png";
		if (getExist() ) {
			switch (this.getFrame()) {
			case 0 : icon = "img/axeg1.png";
				break;
			case 1 : icon = "img/axeg3.png";
				break ;
			case 2 : icon = "img/axeg5.png";
			break;
			case 3 : icon = "img/axeg7.png";
			break;
			default :;
			}
		}
		this.setFrame( (getFrame() + 1)%4 );
		return icon;
	}


	@Override
	protected int trouverX() {
		// TODO Auto-generated method stub
		return this.getCoordonnee().getX()*40;
	}

	@Override
	protected int trouverY() {
		// TODO Auto-generated method stub
		return this.getCoordonnee().getY()*40;
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
