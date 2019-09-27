package model;

import javax.swing.ImageIcon;

import tool.Tool;

public class Rock extends Block{

	public Rock(Coordonnee position) {
		super(position);
		// TODO Auto-generated constructor stub
		this.setNom("Rock");
		this.setFrame(0);
	}
	
	
	public Rock(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.setNom("Rock");
		this.setFrame(0);

	}


	@Override
	public void deplacer(String direction, Plateau plateau, boolean pousserRock) {
		// TODO Auto-generated method stub
		this.setDirection(direction);
					switch (direction) {

					case "up":
						Case caseUp = plateau.getCaseUp(this.getCoordonnee());
						interactionDeplacement(plateau, caseUp ,direction ,pousserRock);
						break;
					case "down":
						Case caseDown = plateau.getCaseDown(this.getCoordonnee());
						interactionDeplacement(plateau, caseDown ,direction ,pousserRock);
						break;
					case "left":
						Case caseLeft = plateau.getCaseLeft(this.getCoordonnee());
						interactionDeplacement(plateau, caseLeft ,direction ,pousserRock);
						break;
					case "right":
						Case caseRight = plateau.getCaseRight(this.getCoordonnee());
						interactionDeplacement(plateau, caseRight ,direction ,pousserRock);
						break;
					default:
						break;

					}

				}


			private void interactionDeplacement(Plateau plateau, Case c, String direction, boolean pousserRock) {
				// TODO Auto-generated method stub
				switch (c.getElement().getNom()) {

				case "Vide":
					interactionVide(plateau, c);
					break;
				case "Arbre":
					interactionArbre();
					break;
				case "Rock":
					if (pousserRock) {
						interactionRock(plateau , c,direction);
					}
					
					break;
				case "Minotaure": interactionMinautore(plateau, c);

					break;
				case "HeroProjectil": interactionVide(plateau, c);


				break;
				default:
					//System.out.println("hero est rentrer dans " + c.getElement().getNom());
					break;
				}
			}

			private void interactionRock(Plateau plateau, Case c, String direction) {
				// TODO Auto-generated method stub
				((Rock) c.getElement()).deplacer(direction, plateau);
				
				if (c.getElement().getNom().equals("Vide")) {
					this.deplacer(direction, plateau , false);
					this.setCurentAction("moving");
				}
			}


			private void interactionMinautore(Plateau plateau, Case c) {
			

			}

			private void interactionArbre() {
			
			}

			private void interactionVide(Plateau plateau, Case caseApres) {
				// TODO Auto-generated method stub
				Case caseAvant = plateau.getCase(this.getCoordonnee());
				Coordonnee cordApres = new Coordonnee(caseApres.getElement().getCoordonnee());

				int num = Tool.CoordinateToNum(caseAvant.getElement().getCoordonnee());
				Vide v = new Vide(this.getCoordonnee());
				plateau.getListCase().get(num).setElement(v);

				num = Tool.CoordinateToNum(caseApres.getElement().getCoordonnee());
				plateau.getListCase().get(num).setElement(this);
				this.setCoordonnee( cordApres );

				
				ObjetCacher objCacher = caseApres.getObjetCacher();
				switch (objCacher.getNom()) {
				
				case "Spike": caseApres.setObjetCacher(new Rien() );
					break;
				default :;
				}
				this.setCurentAction("moving");

				
			}


			@Override
			public void deplacer(String direction, Plateau plateau) {
				// TODO Auto-generated method stub
				deplacer (direction ,plateau, false);
			}


			@Override
			public String getImage(Case c) {
				// TODO Auto-generated method stub
				ObjetCacher objCacher = c.getObjetCacher();
				String icon = "hyrule/block/rock.png";

				switch (this.getCurentAction()) {
				case "nothing":
					break;
				case "moving":
					this.setFrame((getFrame() + 1) % 4);
					if (this.getFrame() == 0) {
						this.setCurentAction("nothing");
					}
					break;
				default:
					break;

				}
				
				
				return icon;
			}
				
			
			@Override
			protected int trouverX() {
				int res =  this.getCoordonnee().getX()*40 -40 ;
				if (this.getDirection().equals("right") && this.getCurentAction().equals("moving")) {
					res = res + this.getFrame()*10 -40 ;
				}
				else if (this.getDirection().equals("left") && this.getCurentAction().equals("moving")) {
					res = res - this.getFrame()*10 +40;
				}
				return res;
			}

			@Override
			protected int trouverY() {
				int res =  this.getCoordonnee().getY()*40 -40;
				if (this.getDirection().equals("up") && this.getCurentAction().equals("moving")) {
					res = res - this.getFrame()*10 +40;
				}
				else if (this.getDirection().equals("down") && this.getCurentAction().equals("moving")) {
					res = res + this.getFrame()*10 -40;
				}
				return res;
			}

			@Override
			protected int trouverlargeur() {
				// TODO Auto-generated method stub
				return 120;
			}

			@Override
			protected int trouverlongeur() {
				// TODO Auto-generated method stub
				return 120;
			}



			@Override
			protected void perdreVie(int damage, Plateau plateau) {
				// TODO Auto-generated method stub
				
			}



	
	
}
