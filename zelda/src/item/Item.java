package item;

import mobInterface.Dessin;
import model.Case;
import model.Plateau;

public abstract class Item implements Dessin{

	private String nom;
	private int frame ;
	private String curentAction;
	private int numeroCase ;
	
	





	public abstract void etreRamasser(Case c) ;
	
	
	
	
	
	
	@Override
	public String toString() {
		return  nom ;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	

	public String getCurentAction() {
		return curentAction;
	}

	public void setCurentAction(String curentAction) {
		this.curentAction = curentAction;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setNumeroCase(int numeroCase) {
		this.numeroCase = numeroCase;
	}


	
	
	
	
}
