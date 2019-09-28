package model;

import java.util.ArrayList;
import java.util.List;

import tool.Tool;


public class Plateau {
	


	private List<Case> listCase ;

	public Plateau( Hero hero, List<Block> listBlock, List<Monstre> listMonstre ) {
		
		this.listCase = new ArrayList<Case>() ;

		
		if (! this.listCase.isEmpty()) {
			this.listCase.clear();
		}
		
		placerVide();
		placerBlock(listBlock);
		placerMonstre(listMonstre);
		placerHero(hero );
		
		System.out.println(this.getListCase().get(0).getElement().getNom());
		this.afficher();
	}
	

	private void placerMonstre(List<Monstre> listMonstre) {
		// TODO Auto-generated method stub
		for (Monstre m : listMonstre) {
				int num = Tool.CoordinateToNum(m.getCoordonnee());
				this.listCase.get(num).setElement(m);
				//this.listCase.get(num).setObjetCacher(new Rien () );
				
			}
	}



	private void placerBlock(List<Block> listBlock) {
		// TODO Auto-generated method stub
		for (Block b : listBlock) {
			
			int num = Tool.CoordinateToNum(b.getCoordonnee());
			this.listCase.get(num).setElement(b);
			this.listCase.get(num).setItem(new Rien () );
			
		}
		
	}


	


	private void placerHero(Hero hero) {
		// TODO Auto-generated method stub
		int num = Tool.CoordinateToNum(hero.getCoordonnee());
		this.listCase.get(num).setElement(hero);
		this.listCase.get(num).setItem(new Rien ());

	}



	private void placerVide() {
		// TODO Auto-generated method stub
		for (int i =0 ;i < 25 ; i++) {
			for (int j = 0 ; j<25 ;j++) {
				int random = (int)(Math.random()* (100+0-1)) + 0;
				if (random > 95) {
					listCase.add(new Case(new Vide( new Coordonnee (i,j)), new Bomb() ) ) ;
				}
				else {
					listCase.add(new Case(new Vide( new Coordonnee (i,j) ) ) ) ;
				}
			}
		}
	}
	
	public List<Case> getListCase() {
		return listCase;
	}

	public void setListCase(List<Case> listCase) {
		this.listCase = listCase;
	}
	

	public Case getCaseUp (Coordonnee coordonnee) {
		
		return this.listCase.get(Tool.CoordinateToNum(coordonnee.getX() , coordonnee.getY()-1 )) ;
	}
	public Case getCaseDown (Coordonnee coordonnee) {
		return this.listCase.get(Tool.CoordinateToNum(coordonnee.getX() , coordonnee.getY()+1 )) ;

	}
	public Case getCaseLeft (Coordonnee coordonnee) {
		return this.listCase.get(Tool.CoordinateToNum(coordonnee.getX()-1 , coordonnee.getY() )) ;
	}
	public Case getCaseRight (Coordonnee coordonnee) {
		return this.listCase.get(Tool.CoordinateToNum(coordonnee.getX()+1 , coordonnee.getY() )) ;
	}
	public Case getCaseUpLeft(Coordonnee coordonnee) {
		// TODO Auto-generated method stub
		return this.listCase.get(Tool.CoordinateToNum(coordonnee.getX()-1 , coordonnee.getY()-1 )) ;
	}
	public Case getCaseDownLeft(Coordonnee coordonnee) {
		// TODO Auto-generated method stub
		return  this.listCase.get(Tool.CoordinateToNum(coordonnee.getX()-1 , coordonnee.getY()+1 )) ;

	}
	public Case getCaseUpRight(Coordonnee coordonnee) {
		// TODO Auto-generated method stub
		return this.listCase.get(Tool.CoordinateToNum(coordonnee.getX()+1 , coordonnee.getY()-1 )) ;
	}
	public Case getCaseDownRight(Coordonnee coordonnee) {
		// TODO Auto-generated method stub
		return this.listCase.get(Tool.CoordinateToNum(coordonnee.getX()+1 , coordonnee.getY()+1 )) ;
	}

	public Case getCase(Coordonnee coordonnee){
		return this.listCase.get(Tool.CoordinateToNum(coordonnee.getX(), coordonnee.getY() )) ;
	}
	
	public void afficher(){
		for (Case c : listCase) {
			c.afficher();
		}
	}


	public void end() {
		for (Case c : listCase) {
			Coordonnee cord = new Coordonnee (c.getElement().getCoordonnee() );
			c.setElement(new Vide (cord));
			c.setItem(new Key () );
		}		
	}


	
}
