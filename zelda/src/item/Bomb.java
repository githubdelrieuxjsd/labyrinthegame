package item;

import damage.Damage;
import damage.DamageNeutre;
import model.Case;
import model.Coordonnee;
import model.Plateau;

public class Bomb extends Item {

	private Damage damage ;
	
	public Bomb() {
		super();
		this.damage = new DamageNeutre(0,0,3,0);
		this.setFrame(0);
		this.setCurentAction("nothing") ;
		this.setNom("Bomb");
		
	}

	private void exploser(Plateau plateau, Case c){
		Coordonnee coord = c.getCoordonnee() ;
		Case c1 = plateau.getCase(coord);
		Case c2 = plateau.getCaseUpMemeZ(coord);
		Case c3 = plateau.getCaseUpRightMemeZ(coord);
		Case c4 = plateau.getCaseUpLeftMemeZ(coord);
		Case c5 = plateau.getCaseDownMemeZ(coord);
		Case c6 = plateau.getCaseDownRightMemeZ(coord);
		Case c7 = plateau.getCaseDownLeftMemeZ(coord);
		Case c8= plateau.getCaseLeftMemeZ(coord);
		Case c9= plateau.getCaseRightMemeZ(coord);
		
		c1.getElement().perdreVie(this.damage, plateau);
		c2.getElement().perdreVie(this.damage, plateau);
		c3.getElement().perdreVie(this.damage, plateau);
		c4.getElement().perdreVie(this.damage, plateau);
		c5.getElement().perdreVie(this.damage, plateau);
		c6.getElement().perdreVie(this.damage, plateau);
		c7.getElement().perdreVie(this.damage, plateau);
		c8.getElement().perdreVie(this.damage, plateau);
		c9.getElement().perdreVie(this.damage, plateau);


	}
	
	
	@Override
	public String trouverImage(Plateau plateau, Case c) {
		String icon = "hyrule/item/bomb.png";
		
		switch (this.getCurentAction()) {
		
		case "nothing":
			icon = "hyrule/item/bomb.png";
			this.setFrame( (getFrame() + 1) % 32 );
			if (this.getFrame() == 0) {
				this.setCurentAction("exploding");
				this.setFrame(0);
			}
			break;
		case "exploding":
			if (this.getFrame() == 1) {
				this.exploser(plateau, c);
			}
			icon = "hyrule/bomb/"+this.getFrame()+".png";
			this.setFrame((getFrame() + 1) % 12);

			if (this.getFrame() == 0) {
				c.setItem();
			}
			break;
		default: 
			break;

		}
		return icon;
		
	}

	@Override
	public void etreRamasser( Case c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int trouverX(Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getX() *c.getTailleCasePixel() -c.getTailleCasePixel();
	}

	@Override
	public int trouverY(Case c) {
		// TODO Auto-generated method stub
		return c.getCoordonnee().getY() *c.getTailleCasePixel() -c.getTailleCasePixel() ;
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
