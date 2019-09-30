package model;

public class Tomato extends Monstre{

	
		public Tomato(Coordonnee coordonnee) {
			super();
			this.setExist(true);

			this.setCoordonnee(coordonnee);
			this.setFrame(0);
			this.setCurentAction("appear");
			this.setDirection(new Direction("up"));
			this.setNom("Tomato");
			this.setMaxLife(3);
			this.setLife(this.getMaxLife() );
			this.setDamageMonstre(0, 0, 0, 0);
		}

		public Tomato(int x, int y) {
			super();
			this.setExist(true);
			this.setNom("Tomato");
			this.setCoordonnee(new Coordonnee(x, y));
			this.setFrame(0);
			this.setCurentAction("appear");
			this.setMaxLife(3);
			this.setLife(this.getMaxLife() );
			this.setDamageMonstre(0, 0, 0, 0);
			this.setDirection(new Direction("up"));

		}

		@Override
		public void deplacer(Direction direction, Plateau plateau) {
			
		}
		
		
		@Override
		public void perdreVie(Damage damage, Plateau p) {

			if (! this.getCurentAction().equals("animationDeath")) {
				if (damage.doDamage(this)) {
					this.setLife(this.getLife() - damage.getEpee() - damage.getProjectil() - damage.getExplosion());
				}
				if (getLife() <= 0) {
					this.setCurentAction("animationDeath");
					this.setFrame(0);
				}
			}
			
		}

		@Override
		public void soigner(int soin) {
			// TODO Auto-generated method stub
			if (soin + getLife() >= getMaxLife()) {
				setLife(getMaxLife());
			} else {
				this.setLife(getLife() + soin);
			}
		}

		private void dropItem(Case c) {
			if (c.getItem().getNom().equals("Rien")) {
				int x = (int) (Math.random() * (100 + 1 - 1)) + 1;
				if (x < 0 && x > 50) {
					c.setItem(new Rubi());
				} else if (x > 50 && x < 70) {
					c.setItem(new Heart());
				}

			}

		}

		@Override
		public void attaquer(Plateau plateau) {
	
		}


		@Override
		public String getImage(Plateau p, Case c) {
			// System.out.println(this.getFrame());

			String icon = "hyrule/tomato/beat/D1.png";
			;
			switch (this.getCurentAction()) {

			case "nothing":
				icon = this.imageNothing();
				this.setFrame((getFrame() + 1) % 6);
				break;
			case "appear":
				icon = this.imageAppear();
				this.setFrame((getFrame() + 1) % 4);
				if (this.getFrame() == 0) {
					this.setCurentAction("disappear");
					this.setDirection(new Direction (  Monstre.getHero().getDirection().getDirection()  ));
				}
				break;
			case "disappear":
				icon = this.imageDisappear();
				this.setFrame((getFrame() + 1) % 1);
				if (this.getFrame() == 0) {
					this.setCurentAction("disappear");
					this.setDirection(new Direction (  Monstre.getHero().getDirection().getDirection()  ) );

				}
				break;
				
			case "animationDeath":
				int num = this.getFrame() / 2 + 1;
				// System.out.println(num);
				icon = "hyrule/death/" + num + ".png";
				this.setFrame((getFrame() + 1) % 14);

				if (this.getFrame() == 4) {
					this.dropItem(c);
				}
				if (this.getFrame() == 0) {
					this.setCurentAction("dead");
					int x = (int) (Math.random() * (23 + 1 - 1)) + 1;
					int y = (int) (Math.random() * (16 + 1 - 1)) + 1;
					this.mourir(p, x, y, false);
				}
				break;

			default:
				num = this.getFrame() / 2 + 1;
				// System.out.println(num);
				icon = "hyrule/death/" + num + ".png";
				this.setFrame((getFrame() + 1) % 14);
				break;

			}

			return icon;
		}

		

		private String imageDisappear() {
			int num = this.getFrame() + 1;
			// System.out.println("frame: "+this.getFrame());

			// System.out.println("num: "+num);

			String icon = "hyrule/tomato/disappear/Left" + num + ".png";
			switch (this.getDirection().getDirection()) {

			case "up":
				icon = "hyrule/tomato/disappear/Up/" + num + ".png";
				break;
			case "down":
				icon = "hyrule/tomato/disappear/Down/" + num + ".png";
				break;
			case "left":
				icon = "hyrule/tomato/disappear/Left/" + num + ".png";
				break;
			case "right":
				icon = "hyrule/tomato/disappear/Right/" + num + ".png";
				break;
			default:
				break;

			}

			return icon;
		}

		private String imageAppear() {
			int num = this.getFrame() + 1;
			// System.out.println("frame: "+this.getFrame());

			// System.out.println("num: "+num);

			String icon = "hyrule/tomato/appear/Left" + num + ".png";
			switch (this.getDirection().getDirection()) {

			case "up":
				icon = "hyrule/tomato/appear/Up/" + num + ".png";
				break;
			case "down":
				icon = "hyrule/tomato/appear/Down/" + num + ".png";
				break;
			case "left":
				icon = "hyrule/tomato/appear/Left/" + num + ".png";
				break;
			case "right":
				icon = "hyrule/tomato/appear/Right/" + num + ".png";
				break;
			default:
				break;

			}

			return icon;
		}

		private String imageNothing() {
			int num = this.getFrame() + 1;
			// System.out.println("frame: "+this.getFrame());

			// System.out.println("num: "+num);

			String icon = "hyrule/tomato/beat/R" + num + ".png";
			switch (this.getDirection().getDirection()) {

			case "up":
				icon = "hyrule/tomato/beat/R" + num + ".png";
				break;
			case "down":
				icon = "hyrule/tomato/beat/L" + num + ".png";
				break;
			case "left":
				icon = "hyrule/tomato/beat/L" + num + ".png";
				break;
			case "right":
				icon = "hyrule/tomato/beat/R" + num + ".png";
				break;
			default:
				break;

			}

			return icon;

		}

		@Override
		protected int trouverX() {
			int res = this.getCoordonnee().getX() * 40 - 40;
			
			return res;
		}

		@Override
		protected int trouverY() {
			int res = this.getCoordonnee().getY() * 40 - 40;
			
				// System.out.println(this.getFrame() +","+ (num) );
			
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
		public void animationAttaque() {
			// TODO Auto-generated method stub

		}

		@Override
		public int trouverBarreVieX() {
			// TODO Auto-generated method stub
			return this.trouverX() + 45;
		}

		@Override
		public int trouverBarreVieY() {
			// TODO Auto-generated method stub
			return this.trouverY() + 20;
		}

	}



