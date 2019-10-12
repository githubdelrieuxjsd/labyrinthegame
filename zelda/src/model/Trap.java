package model;

import mobInterface.Dessin;

public abstract class Trap implements Dessin{
	
	String nom ;
	int numeroCase ;

	
	public int getNumeroCase() {
		return numeroCase;
	}

	public void setNumeroCase(int numeroCase) {
		this.numeroCase = numeroCase;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	} 
	

}
