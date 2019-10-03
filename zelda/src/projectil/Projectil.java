package projectil;

import controleur.Control;
import damage.Damage;
import damage.DamageHero;
import damage.DamageMonstre;
import damage.DamageNeutre;
import mobInterface.Deplacement;
import mobInterface.Dessin;
import model.Case;
import model.Coordonnee;
import model.Direction;
import model.Element;
import model.Plateau;
import tool.Tool;

public abstract class Projectil implements Dessin, Deplacement {

	private Boolean exist;
	private Damage damage;

	private String nom;

	private Direction direction;
	private String curentAction;
	private int frame;
	private int numeroCase ;
	
	
	private void detruir () {
		this.setCurentAction("detruir");
	}

	@Override
	public void deplacer(Plateau plateau, Case caseAvant, Case caseApres) {

		Coordonnee cordApres = new Coordonnee(caseApres.getCoordonnee());
		int num = Tool.CoordinateToNum(caseAvant.getCoordonnee());
		Aire aire = new Aire();
		plateau.getListCase().get(num).setProjectil(aire);

		num = Tool.CoordinateToNum(caseApres.getCoordonnee());

		plateau.getListCase().get(num).setProjectil(this);
		
		this.setNumeroCase(num);
	}

	@Override
	public void interactionDeplacement(Plateau plateau, Case caseAvant, Direction direction) {
		this.setDirection(direction);
		Case caseApres = plateau.getCaseDevant(caseAvant, this.getDirection());
		Element element = caseApres.getElement();

		if (!caseApres.getProjectil().getNom().equals("Aire")) {
			this.detruir();
		} else {
			
			if (element.isMonstre() || element.isHero()) {
				element.perdreVie(this.getDamage(), plateau);
				this.detruir();
			} else {

				switch (caseApres.getElement().getNom()) {

				case "Vide":
					this.deplacer(plateau, caseAvant, caseApres);
					break;
				default:
					this.detruir();
					break;
				}
			}
		}
	}

	// ######################## TO STRING ##########################################

	@Override
	public String toString() {
		return nom;
	}

	// ######################## GETTER SETTER
	// ##########################################

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public String getCurentAction() {
		return curentAction;
	}

	public void setCurentAction(String curentAction) {
		this.curentAction = curentAction;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public void setDamage(Damage damage) {
		this.damage = damage;
	}

	public Boolean getExist() {
		return exist;
	}

	public void setExist(Boolean exist) {
		this.exist = exist;
	}
	
	public int getNumeroCase() {
		return numeroCase;
	}

	public void setNumeroCase(int numeroCase) {
		this.numeroCase = numeroCase;
	}

	
	public Damage getDamage() {
		return damage;
	}

	public void setDamageHero(int epee, int magie, int bomb, int fleche) {
		this.damage = new DamageHero(epee, magie, bomb, fleche);
	}

	public void setDamageMonstre(int epee, int magie, int bomb, int fleche) {
		this.damage = new DamageMonstre(epee, magie, bomb, fleche);
	}

	public void setDamageNeutre(int epee, int magie, int bomb, int fleche) {
		this.damage = new DamageNeutre(epee, magie, bomb, fleche);
	}

}
