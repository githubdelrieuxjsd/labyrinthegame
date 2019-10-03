package model;

import java.util.ArrayList;
import java.util.List;

import baseDonnee.Niveau;
import block.Arbre;
import block.Block;
import block.Bush;
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
	
	private List<Case> listCase ;
	private int nombreCaseX ;
	private int nombreCaseY;

	public Plateau( Hero hero, List<Block> listBlock, List<Monstre> listMonstre ) {
		
		this.nombreCaseX = 30;
		this.nombreCaseY = 17 ;
		
		this.listCase = new ArrayList<Case>() ;
		
		placerVide();
		
		//placerBlockRandom(listBlock);
		this.placerBlockBaseDonne(Niveau.jeu3());
		
		placerMonstreRandom(listMonstre);
		
		if (hero.getNumeroCase() == -1 ) {
			this.placerElement(hero, new Coordonnee (9,1));
		}else {
			this.placerElement(hero, this.getListCase().get(hero.getNumeroCase()).getCoordonnee());
		}
		
		//contourArbre();
		//this.afficher();
		}
	
	
	private void placerBlockBaseDonne(int [][] tabBlock) {
		for (int i = 0 ;i< tabBlock.length; i++) {
			Element block = new Vide();
			switch (tabBlock[i][0]) {
			case 1: block = new Arbre();
				break;
			case 2: block = new Rock();
			break;
			case 3: block = new Bush();
			break;
			default : 
				break;
			}
			this.placerElement(block,new Coordonnee( tabBlock [i][1] , tabBlock [i][2] ));
			}
	}


	/**
	 * ajout des arbre tout autour de la carte pour bloquer les unites 
	 * 
	 */
	private void contourArbre() {
		for (int i = 0 ; i<nombreCaseY ;i++) {
			this.placerElement(new Arbre() , new Coordonnee (nombreCaseX -1,i)) ;
			this.placerElement(new Arbre() , new Coordonnee (i,0)) ;
			this.placerElement(new Arbre() , new Coordonnee (i,nombreCaseY -1 )) ;
			this.placerElement(new Arbre() , new Coordonnee (0,i)) ;	
		}
		for (int i = nombreCaseY; i < nombreCaseX; i++) {
			this.placerElement(new Arbre() , new Coordonnee (i,0)) ;
			this.placerElement(new Arbre() , new Coordonnee (i,nombreCaseY -1)) ;
		}
	}
	

	private void placerMonstreRandom(List<Monstre> listMonstre) {
		for (Monstre m : listMonstre) {
			int x = (int) (Math.random() * (nombreCaseX-2 + 1 - 1)) + 1;
			int y = (int) (Math.random() * (nombreCaseY-2 + 1 - 1)) + 1;

				this.placerElement(m,new Coordonnee(x,y));
			}
	}

	private void placerBlockRandom(List<Block> listBlock) {
		for (Block b : listBlock) {
			int x = (int) (Math.random() * (nombreCaseX-1 + 1 - 0)) + 0;
			int y = (int) (Math.random() * (nombreCaseY-1 + 1 - 0)) + 0;

			this.placerElement(b,new Coordonnee(x,y));		}
	}

	private void placerVide() {
		// TODO Auto-generated method stub
		for (int i =0 ;i < nombreCaseY ; i++) {
			for (int j = 0 ; j<nombreCaseX ;j++) {
					Case vide = new Case( new Coordonnee (j,i)  );
					vide.setElement(new Vide () );
					vide.setItem(new Rien () );
					//vide.setItem(new Rubi () );

					vide.setProjectil(new Aire() );
					listCase.add( vide ) ;
			}
		}
	}
	
	
	public void placerElement (Element element , Coordonnee coordonnee) {
		int num = Tool.CoordinateToNum(coordonnee);
		this.listCase.get(num).setElement(element);
		element.setNumeroCase(num);
	}
	
	public void placerElement (Element element , int num) {
		if (num> -1 && num < nombreCaseY*nombreCaseX) {
			this.listCase.get(num).setElement(element);
		}
	}
	public void placerItem(Item item , Coordonnee coordonnee) {
		int num = Tool.CoordinateToNum( coordonnee);
		this.listCase.get(num).setItem(item);
	}
	
	public void placerItem(Item item , int num) {
		if (num> -1 && num < nombreCaseY*nombreCaseX) {
			this.listCase.get(num).setItem(item);
		}
	}
	public void placerProjectil(Projectil projectil , Coordonnee coordonnee) {
		int num = Tool.CoordinateToNum( coordonnee);
		this.listCase.get(num).setProjectil(projectil);
		projectil.setNumeroCase(num);

	}
	
	public void placerProjectil (Projectil projectil , int num) {
		if (num> -1 && num < nombreCaseY*nombreCaseX) {
			this.listCase.get(num).setProjectil(projectil);
		}
	}	
	
	//######################## GETTER SETTER ##########################################

	public List<Case> getListCase() {
		return listCase;
	}

	public void setListCase(List<Case> listCase) {
		this.listCase = listCase;
	}
	
	public Case getCaseDevant (Case c,Direction direction) {
		Case res = this.getCaseUp(c.getCoordonnee());
		
		switch (direction.getDirection()) {			
		case "down":
			res =  this.getCaseDown(c.getCoordonnee());
			break;
			
		case "right":
			res =  this.getCaseRight(c.getCoordonnee());
			break;
			
		case "left":
			res =  this.getCaseLeft(c.getCoordonnee());
			break;
		default:
			break;
		}
		return res;
	}
	
	public Case getCaseUp (Coordonnee coordonnee) {
		
		if (this.getCase(coordonnee).getCoordonnee().getY()==0) {
			return this.getCase(coordonnee);
		}
		return this.listCase.get(Tool.CoordinateToNum(coordonnee.getX() , coordonnee.getY()-1 ));
	}
	public Case getCaseDown (Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getY()==this.nombreCaseY-1) {
			return this.getCase(coordonnee);
		}
		return this.listCase.get(Tool.CoordinateToNum(coordonnee.getX() , coordonnee.getY()+1 )) ;

	}
	public Case getCaseLeft (Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getX()==0) {
			return this.getCase(coordonnee);
		}
		return this.listCase.get(Tool.CoordinateToNum(coordonnee.getX()-1 , coordonnee.getY() )) ;
	}
	public Case getCaseRight (Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getX()== this.nombreCaseX-1) {
			return this.getCase(coordonnee);
		}
		return this.listCase.get(Tool.CoordinateToNum(coordonnee.getX()+1 , coordonnee.getY() )) ;
	}
	public Case getCaseUpLeft(Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getY()==0 ||
				this.getCase(coordonnee).getCoordonnee().getX()==0) {
			return this.getCase(coordonnee);
		}
		return this.listCase.get(Tool.CoordinateToNum(coordonnee.getX()-1 , coordonnee.getY()-1 )) ;
	}
	public Case getCaseDownLeft(Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getY()== this.nombreCaseY-1 ||
				this.getCase(coordonnee).getCoordonnee().getX()==0) {
			return this.getCase(coordonnee);
		}
		return  this.listCase.get(Tool.CoordinateToNum(coordonnee.getX()-1 , coordonnee.getY()+1 )) ;

	}
	public Case getCaseUpRight(Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getY()== 0 ||
				this.getCase(coordonnee).getCoordonnee().getX()== this.nombreCaseX-1) {
			return this.getCase(coordonnee);
		}
		return this.listCase.get(Tool.CoordinateToNum(coordonnee.getX()+1 , coordonnee.getY()-1 )) ;
	}
	public Case getCaseDownRight(Coordonnee coordonnee) {
		if (this.getCase(coordonnee).getCoordonnee().getY()== this.nombreCaseY-1 ||
				this.getCase(coordonnee).getCoordonnee().getX()== this.nombreCaseX-1) {
			return this.getCase(coordonnee);
		}
		return this.listCase.get(Tool.CoordinateToNum(coordonnee.getX()+1 , coordonnee.getY()+1 )) ;
	}

	public Case getCase(Coordonnee coordonnee){
		return this.listCase.get(Tool.CoordinateToNum(coordonnee.getX(), coordonnee.getY() )) ;
	}
	
	
	
	
	
	public int getNombreCaseX() {
		return nombreCaseX;
	}





	public int getNombreCaseY() {
		return nombreCaseY;
	}





	public void afficher(){
		for (Case c : listCase) {
			c.afficher();
		}
	}


	public void end() {
		for (Case c : listCase) {
			c.setElement(new Vide ());
			c.setItem(new Key () );
		}		
	}


	
}
