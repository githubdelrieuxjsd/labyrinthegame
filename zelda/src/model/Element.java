package model;

import mobInterface.Dessin;
import mobInterface.PerdreVie;

public abstract class Element implements PerdreVie, Dessin {

	private String nom;
	private Direction direction;
	private String curentAction;
	private int numeroCase;

	private int frame;

	public boolean isMonstre() {
		boolean res = false;
		switch (this.getNom()) {
		case "Knight":
			res = true;
			break;
		case "Chicken":
			res = true;
			break;
		case "Goblin":
			res = true;
			break;
		case "Tomato":
			res = true;
			break;
		default:
			break;
		}
		return res;
	}

	public boolean isHero() {
		return this.getNom().equals("Hero");
	}

	// ######################## TO STRING ##########################################

	public void afficher() {

		System.out.println("######" + nom + " ######");
		System.out.println("numero Case " + this.getNumeroCase());
		System.out.println("Curent action  " + this.getCurentAction());

		System.out.println("Frame " + this.getFrame());

		System.out.println("##########################");

	}

	@Override
	public String toString() {
		return nom;
	}

	// ######################## GETTER SETTER
	// ##########################################

	public String getCurentAction() {
		return curentAction;
	}

	public void setCurentAction(String curentAction) {
		this.curentAction = curentAction;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public int getNumeroCase() {
		return numeroCase;
	}

	public void setNumeroCase(int numeroCase) {
		this.numeroCase = numeroCase;
	}

}
