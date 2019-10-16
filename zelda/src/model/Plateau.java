package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import baseDonnee.Niveau;
import block.Arbre;
import block.Block;
import block.Bush;
import block.Chest;
import block.PanneauAffichage;
import block.Rock;
import block.Vide;
import item.Bomb;
import item.Heart;
import item.Item;
import item.Key;
import item.Rien;
import item.Rubi;
import monstre.Monstre;
import projectil.Aire;
import projectil.Projectil;
import tool.Tool;

public class Plateau {

	private List<Case> listCase;
	private int nombreCaseX;
	private int nombreCaseY;
	private int nombreCaseZ;

	public Plateau(Hero hero, List<Case> listCase, List<Monstre> listMonstre, List<Block> listBlock, int nombreCaseX,
			int nombreCaseY) {
		this.nombreCaseX = nombreCaseX;
		this.nombreCaseY = nombreCaseY;
		this.nombreCaseZ = 1;
		this.listCase = listCase;
		System.out.println("nombre case sur le plateau : " + listCase.size());

		List<Case> listCaseSpawnable = new ArrayList<Case>();
		for (Case c : listCase) {
			if (c.getElement().isSpawnable()) {
				Case cSpawnable = new Case(c.getCoordonnee());
				listCaseSpawnable.add(cSpawnable);
			}
		}
		System.out.println("nombre case spawnable " + listCaseSpawnable.size());

		placerMonstre(listMonstre);
		placerBlock(listBlock, listCaseSpawnable);

		
		int num = (int) (Math.random() * ((listCaseSpawnable.size() - 1) - 0 + 1)) + 0;
		this.placerElement(hero, listCaseSpawnable.get(num).getCoordonnee());
		listCaseSpawnable.remove(num);

		num = (int) (Math.random() * ((listCaseSpawnable.size() - 1) - 0 + 1)) + 0;
		this.placerTrap(new Stairs(), listCaseSpawnable.get(num).getCoordonnee());
		listCaseSpawnable.remove(num);

	}

	private void placerBlock(List<Block> listBlock, List<Case> listCaseSpawnable) {
		// TODO Auto-generated method stub
		for (Element m : listBlock) {
			int num = (int) (Math.random() * ((listCaseSpawnable.size() - 1) - 0 + 1)) + 0;
			this.placerElement(m, listCaseSpawnable.get(num).getCoordonnee());
			listCaseSpawnable.remove(num);
		}
	}

	private void placerMonstre(List<Monstre> listMonstre) {
		for (Element m : listMonstre) {
			boolean heroplacer = false;
			while (!heroplacer) {
				int x = (int) (Math.random() * ((nombreCaseX - 6) - 6 + 1)) + 6;
				int y = (int) (Math.random() * ((nombreCaseY - 6) - 6 + 1)) + 6;
				if (this.getListCase().get(this.coordinateToNum(x, y, 0)).getElement().getNom().equals("Vide")) {
					this.placerElement(m, new Coordonnee(x, y, 0));
					heroplacer = true;
				}
			}
		}
	}

	public Plateau(Hero hero, List<Block> listBlock, List<Monstre> listMonstre) {

		this.nombreCaseX = 60;
		this.nombreCaseY = 60;
		this.nombreCaseZ = 1;

		this.listCase = new ArrayList<Case>();

		placerVide();

		placerBlockRandom(listBlock);
		// this.placerBlockBaseDonne(Niveau.jeu3());

		placerMonstreRandom(listMonstre);

		if (hero.getNumeroCase() == -1) {
			this.placerElement(hero, new Coordonnee(30, 10, 0));
		} else {
			this.placerElement(hero, this.getListCase().get(hero.getNumeroCase()).getCoordonnee());
		}
		// this.placerTrap(new Stairs(new Coordonnee(15,5,0)), new Coordonnee(10,10,0));
		// this.placerTrap(new Stairs(new Coordonnee(10,10,0)), new Coordonnee(15,5,0));

		// contourTrap();
		contourArbre();
		// this.afficher();
	}

	public Plateau(Hero hero, int numJeu) {

		this.nombreCaseX = 22;
		this.nombreCaseY = 22;
		this.nombreCaseZ = 2;

		this.listCase = new ArrayList<Case>();

		placerVide();

		this.placerBlockBaseDonne(Niveau.mapLabyrinthe(numJeu));

		if (hero.getNumeroCase() == -1) {
			this.placerElement(hero, new Coordonnee(1, 6, 0));
		} else {
			this.placerElement(hero, this.getListCase().get(hero.getNumeroCase()).getCoordonnee());
		}
		contourTrap();
	}

	public Plateau(int nombreCaseX, int nombreCaseY) {
		this.nombreCaseX = nombreCaseX;
		this.nombreCaseY = nombreCaseY;
		this.listCase = new ArrayList<Case>();
		}

	private void placerBlockBaseDonne(int[][] tabBlock) {
		for (int i = 0; i < tabBlock.length; i++) {
			Element block = new Vide();
			switch (tabBlock[i][0]) {
			case 1:
				block = new Arbre();
				break;
			case 2:
				block = new Rock();
				break;
			case 3:
				block = new Bush();
				break;
			case 4:
				block = new Chest();
				break;
			default:
				break;
			}

			this.placerElement(block, new Coordonnee(tabBlock[i][1], tabBlock[i][2], tabBlock[i][3]));
		}
	}

	/**
	 * ajout des arbre tout autour de la carte pour bloquer les unites
	 * 
	 */
	private void contourArbre() {
		for (int i = 0; i < nombreCaseY; i++) {
			this.placerElement(new Arbre(), new Coordonnee(nombreCaseX - 1, i, 0));
			this.placerElement(new Arbre(), new Coordonnee(i, 0, 0));
			this.placerElement(new Arbre(), new Coordonnee(i, nombreCaseY - 1, 0));
			this.placerElement(new Arbre(), new Coordonnee(0, i, 0));
		}
		for (int i = nombreCaseY; i < nombreCaseX; i++) {
			this.placerElement(new Arbre(), new Coordonnee(i, 0, 0));
			this.placerElement(new Arbre(), new Coordonnee(i, nombreCaseY - 1, 0));
		}
	}

	private void contourTrap() {
		for (int i = 0; i < nombreCaseY; i++) {
			this.placerTrap(new ChangementPlateau(), new Coordonnee(nombreCaseX - 1, i, 0));
			this.placerTrap(new ChangementPlateau(), new Coordonnee(i, 0, 0));
			this.placerTrap(new ChangementPlateau(), new Coordonnee(i, nombreCaseY - 1, 0));
			this.placerTrap(new ChangementPlateau(), new Coordonnee(0, i, 0));
		}
		for (int i = nombreCaseY; i < nombreCaseX; i++) {
			this.placerTrap(new ChangementPlateau(), new Coordonnee(i, 0, 0));
			this.placerTrap(new ChangementPlateau(), new Coordonnee(i, nombreCaseY - 1, 0));
		}
	}

	private void placerMonstreRandom(List<Monstre> listMonstre) {
		for (Monstre m : listMonstre) {
			int x = (int) (Math.random() * (nombreCaseX - 2 + 1 - 1)) + 1;
			int y = (int) (Math.random() * (nombreCaseY - 2 + 1 - 1)) + 1;

			this.placerElement(m, new Coordonnee(x, y, 0));
		}
	}

	private void placerBlockRandom(List<Block> listBlock) {
		for (Block b : listBlock) {
			int x = (int) (Math.random() * (nombreCaseX - 1 + 1 - 0)) + 0;
			int y = (int) (Math.random() * (nombreCaseY - 1 + 1 - 0)) + 0;
			this.placerElement(b, new Coordonnee(x, y, 0));
		}
	}

	private void placerVide() {
		for (int z = 0; z < nombreCaseZ; z++) {
			for (int i = 0; i < nombreCaseY; i++) {
				for (int j = 0; j < nombreCaseX; j++) {
					Case caseVide = new Case(new Coordonnee(j, i, z));
					caseVide.setElement(new Vide());
					// caseVide.setElement(new Bush());
					caseVide.setItem(new Rien());
					// caseVide.setItem(new Rubi());
					caseVide.setProjectil(new Aire());
					caseVide.setTrap(new TrapVide());
					listCase.add(caseVide);
				}
			}
		}
	}

	public void placerElement(Element element, Coordonnee coordonnee) {
		int num = this.coordinateToNum(coordonnee);
		Case c = this.listCase.get(num);
		c.setElement(element);
		element.setNumeroCase(num);
	}

	public void placerElement(Element element, int num) {
		if (num > -1 && num < nombreCaseY * nombreCaseX) {
			this.listCase.get(num).setElement(element);
		}
	}

	public void placerItem(Item item, Coordonnee coordonnee) {
		int num = this.coordinateToNum(coordonnee);
		this.listCase.get(num).setItem(item);
	}

	public void placerItem(Item item, int num) {
		if (num > -1 && num < nombreCaseY * nombreCaseX) {
			this.listCase.get(num).setItem(item);
		}
	}

	public void placerTrap(Trap trap, Coordonnee coordonnee) {
		int num = this.coordinateToNum(coordonnee);
		this.listCase.get(num).setTrap(trap);
	}

	public void placerTrap(Trap trap, int num) {
		if (num > -1 && num < nombreCaseY * nombreCaseX) {
			this.listCase.get(num).setTrap(trap);
		}
	}

	public void placerProjectil(Projectil projectil, Coordonnee coordonnee) {
		int num = this.coordinateToNum(coordonnee);
		this.listCase.get(num).setProjectil(projectil);
		projectil.setNumeroCase(num);

	}

	public void placerProjectil(Projectil projectil, int num) {
		if (num > -1 && num < nombreCaseY * nombreCaseX) {
			this.listCase.get(num).setProjectil(projectil);
		}
	}

	// ######################## GETTER SETTER
	// ##########################################

	public List<Case> getListCase() {
		return listCase;
	}

	public void setListCase(List<Case> listCase) {
		this.listCase = listCase;
	}

	public Case getCaseDevantMemeZ(Case c, Direction direction) {
		Case res = this.getCaseUpMemeZ(c.getCoordonnee());

		switch (direction.getDirection()) {
		case "down":
			res = this.getCaseDownMemeZ(c.getCoordonnee());
			break;

		case "right":
			res = this.getCaseRightMemeZ(c.getCoordonnee());
			break;

		case "left":
			res = this.getCaseLeftMemeZ(c.getCoordonnee());
			break;
		default:
			break;
		}
		return res;
	}

	public Case getCaseUpMemeZ(Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getY() == 0) {
			return this.getCase(coordonnee);
		}
		Case res = this.listCase.get(this.coordinateToNum(coordonnee.getX(), coordonnee.getY() - 1, coordonnee.getZ()));
		return res;
	}

	public Case getCaseDownMemeZ(Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getY() == this.nombreCaseY - 1) {
			return this.getCase(coordonnee);
		}
		Case res = this.listCase.get(this.coordinateToNum(coordonnee.getX(), coordonnee.getY() + 1, coordonnee.getZ()));
		return res;
	}

	public Case getCaseLeftMemeZ(Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getX() == 0) {
			return this.getCase(coordonnee);
		}
		Case res = this.listCase.get(this.coordinateToNum(coordonnee.getX() - 1, coordonnee.getY(), coordonnee.getZ()));
		return res;
	}

	public Case getCaseRightMemeZ(Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getX() == this.nombreCaseX - 1) {
			return this.getCase(coordonnee);
		}
		Case res = this.listCase.get(this.coordinateToNum(coordonnee.getX() + 1, coordonnee.getY(), coordonnee.getZ()));
		return res;
	}

	public Case getCaseDevant(Case c, Direction direction) {
		Case res = this.getCaseUp(c.getCoordonnee());

		switch (direction.getDirection()) {
		case "down":
			res = this.getCaseDown(c.getCoordonnee());
			break;

		case "right":
			res = this.getCaseRight(c.getCoordonnee());
			break;

		case "left":
			res = this.getCaseLeft(c.getCoordonnee());
			break;
		default:
			break;
		}
		return res;
	}

	public Case getCaseUp(Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getY() == 0) {
			return this.getCase(coordonnee);
		}
		Case res = this.listCase.get(this.coordinateToNum(coordonnee.getX(), coordonnee.getY() - 1));
		int z = 0;
		while (z < coordonnee.getZ() + 1 && z < this.nombreCaseZ - 1) {
			Case caseAuDessus = this.listCase
					.get(this.coordinateToNum(coordonnee.getX(), coordonnee.getY() - 1, z + 1));
			Case caseAuDessous = this.listCase.get(this.coordinateToNum(coordonnee.getX(), coordonnee.getY() - 1, z));

			if (caseAuDessus.getElement().getNom().equals("Vide")
					&& caseAuDessous.getElement().getNom().equals("Rock")) {
				return caseAuDessus;
			}
			z++;
		}

		return res;
	}

	public Case getCaseDown(Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getY() == this.nombreCaseY - 1) {
			return this.getCase(coordonnee);
		}
		Case res = this.listCase.get(this.coordinateToNum(coordonnee.getX(), coordonnee.getY() + 1));
		int z = 0;
		while (z < coordonnee.getZ() + 1 && z < this.nombreCaseZ - 1) {
			Case caseAuDessus = this.listCase
					.get(this.coordinateToNum(coordonnee.getX(), coordonnee.getY() + 1, z + 1));
			Case caseAuDessous = this.listCase.get(this.coordinateToNum(coordonnee.getX(), coordonnee.getY() + 1, z));

			if (caseAuDessus.getElement().getNom().equals("Vide")
					&& caseAuDessous.getElement().getNom().equals("Rock")) {
				return caseAuDessus;
			}
			z++;
		}
		return res;
	}

	public Case getCaseLeft(Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getX() == 0) {
			return this.getCase(coordonnee);
		}
		Case res = this.listCase.get(this.coordinateToNum(coordonnee.getX() - 1, coordonnee.getY()));
		int z = 0;
		while (z < coordonnee.getZ() + 1 && z < this.nombreCaseZ - 1) {
			Case caseAuDessus = this.listCase
					.get(this.coordinateToNum(coordonnee.getX() - 1, coordonnee.getY(), z + 1));
			Case caseAuDessous = this.listCase.get(this.coordinateToNum(coordonnee.getX() - 1, coordonnee.getY(), z));

			if (caseAuDessus.getElement().getNom().equals("Vide")
					&& caseAuDessous.getElement().getNom().equals("Rock")) {
				return caseAuDessus;
			}
			z++;
		}

		return res;
	}

	public Case getCaseRight(Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getX() == this.nombreCaseX - 1) {
			return this.getCase(coordonnee);
		}
		Case res = this.listCase.get(this.coordinateToNum(coordonnee.getX() + 1, coordonnee.getY()));
		int z = 0;

		while (z < coordonnee.getZ() + 1 && z < this.nombreCaseZ - 1) {
			Case caseAuDessus = this.listCase
					.get(this.coordinateToNum(coordonnee.getX() + 1, coordonnee.getY(), z + 1));
			Case caseAuDessous = this.listCase.get(this.coordinateToNum(coordonnee.getX() + 1, coordonnee.getY(), z));
			if (caseAuDessus.getElement().getNom().equals("Vide")
					&& caseAuDessous.getElement().getNom().equals("Rock")) {
				return caseAuDessus;
			}
			z++;
		}

		return res;
	}

	public Case getCaseUpLeftMemeZ(Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getY() == 0
				|| this.getCase(coordonnee).getCoordonnee().getX() == 0) {
			return this.getCase(coordonnee);
		}
		return this.listCase.get(this.coordinateToNum(coordonnee.getX() - 1, coordonnee.getY() - 1, coordonnee.getZ()));
	}

	public Case getCaseDownLeftMemeZ(Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getY() == this.nombreCaseY - 1
				|| this.getCase(coordonnee).getCoordonnee().getX() == 0) {
			return this.getCase(coordonnee);
		}
		return this.listCase.get(this.coordinateToNum(coordonnee.getX() - 1, coordonnee.getY() + 1, coordonnee.getZ()));

	}

	public Case getCaseUpRightMemeZ(Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getY() == 0
				|| this.getCase(coordonnee).getCoordonnee().getX() == this.nombreCaseX - 1) {
			return this.getCase(coordonnee);
		}
		return this.listCase.get(this.coordinateToNum(coordonnee.getX() + 1, coordonnee.getY() - 1, coordonnee.getZ()));
	}

	public Case getCaseDownRightMemeZ(Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getY() == this.nombreCaseY - 1
				|| this.getCase(coordonnee).getCoordonnee().getX() == this.nombreCaseX - 1) {
			return this.getCase(coordonnee);
		}
		return this.listCase.get(this.coordinateToNum(coordonnee.getX() + 1, coordonnee.getY() + 1, coordonnee.getZ()));
	}

	public Case getCase(Coordonnee coordonnee) {
		return this.listCase.get(this.coordinateToNum(coordonnee.getX(), coordonnee.getY(), coordonnee.getZ()));
	}

	public int getNombreCaseX() {
		return nombreCaseX;
	}

	public int getNombreCaseY() {
		return nombreCaseY;
	}

	public int getNombreCaseZ() {
		return nombreCaseZ;
	}

	public void afficher() {
		for (Case c : listCase) {
			c.afficher();
		}
	}

	public List<Case> getListCaseAfficher(Hero hero) {
		Coordonnee heroCoordonnee = this.getListCase().get(hero.getNumeroCase()).getCoordonnee();
		// Coordonnee heroCoordonnee2 = hero.getCoordonneeVisuel(this);

		List<Case> res = new ArrayList<Case>();

		for (int y = heroCoordonnee.getY() - 4; y < heroCoordonnee.getY() + 6; y++) {
			for (int x = heroCoordonnee.getX() - 9; x < heroCoordonnee.getX() + 11; x++) {
				Case c = this.listCase.get(this.coordinateToNum(x, y, 0));
				res.add(c);
			}
		}
		return res;
	}

	public int coordinateToNum(Coordonnee c) {
		return c.getX() + this.nombreCaseX * c.getY() + c.getZ() * this.nombreCaseX * this.nombreCaseY;
	}

	public int coordinateToNum(int x, int y) {
		return x + this.nombreCaseX * y;
	}

	public int coordinateToNum(int x, int y, int z) {
		return this.nombreCaseX * this.nombreCaseY * z + x + this.nombreCaseX * y;
	}

	public void end() {
		for (Case c : listCase) {
			c.setElement(new Vide());
			c.setItem(new Key());
		}
	}

}
