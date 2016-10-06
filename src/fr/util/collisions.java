package fr.util;

//import fr.character.Ennemy1;
import fr.character.Player;
import fr.decor.Platform;

public class collisions {

	private static int margev = 0;
	private static double valint1, valint2, valint3;

	public static boolean isCollisionplayer1plateform(Player player1, Platform plat, int delta) {

		if (player1.getspeedY() < 0) {
			return false;
		}
		if (player1.getY() + player1.getHeight() < plat.getY() - margev) {
			return false;
		}
		if (player1.getY() + player1.getHeight() > plat.getY() + margev) {
			return false;
		}
		if (player1.getX() + player1.getWidth() / 2 < plat.getX()) {
			return false;
		}
		if (player1.getX() + player1.getWidth() / 2 > plat.getX() + plat.getWidth()) {
			return false;
		}
		return true;
	}

	// Detection de collisions entre deux rectangle,le premier etant au-dessus
	// du deuxieme:
	/*
	 * public static boolean col2RecTopBottom(Rectangle top,Rectangle bottom){
	 * 
	 * if((top.getY()+top.getHeight()<bottom.getY())&&(top.getnewY()+top.
	 * getHeight()<bottom.getnewY())){return false;} else
	 * if((top.getX()+top.getWidth()<bottom.getX())&&(top.getnewX()+top.getWidth
	 * ()<bottom.getX()))
	 * 
	 * ATTENTION A LA FEUILLE DE MATH
	 * 
	 * 
	 * return true; }
	 */

	public static boolean col2RecLeftRight(Rectangle left, Rectangle right) {

		if (left.getX() + left.getWidth() < right.getX()) {
			return false;
		}
		if (left.getY() + left.getHeight() < right.getY()) {
			return false;
		}
		if (left.getY() > right.getY() + right.getHeight()) {
			return false;
		}
		return true;
	}

	/*
	 * collision ou non de deux rectangle l'un au-dessus de l'autre on consid鑽e
	 * que les rectanggle n'ont pas de rotation selon l'axe z (qui sort de
	 * l'馗ran) on a donc la trajectoirede tous les points qui est la m麥e pour
	 * un rectangle donn� c'est pourquoi on s'interesse a certains coins et non
	 * a chaque coin on s'occupe du coin inferieur droit du rectange du haut et
	 * au coin superieur gauche du rectangle d'en bas
	 */

	public static boolean col2RecTopBottom(Rectangle top, Rectangle bottom) {
		if ((top.getnewY() + top.getHeight() > bottom.getnewY()) && (top.getY() + top.getHeight() < bottom.getY())) {
			
			if ((top.getnewX() == top.getX()) && (bottom.getnewX() == bottom.getnewX())) {
				return true;
			}
			// voir si il faut mettre && bottom.getX()!=bottom.getnewX()
			// on verifie si la trajectoire des pts correspond
			else if (top.getX() == top.getnewX()) {
				if ((bottom.getY() + ((bottom.getnewY() - bottom.getY()) / (bottom.getnewX() - bottom.getY()))
						* (top.getX() - bottom.getX()) < top.getY() + top.getHeight())
						&& ((bottom.getY() + ((bottom.getnewY() - bottom.getY()) / (bottom.getnewX() - bottom.getY()))
								* (top.getX() + top.getWidth() - bottom.getX()) < top.getnewY() + top.getHeight()))) {
					return false;
				} else {
					return true;
				}
			}

			else if (bottom.getX() == bottom.getnewX()) {
				if ((top.getY() + top.getHeight()
						+ (bottom.getX() - top.getX() - top.getWidth()) * (top.getnewY() - top.getY())
								/ (top.getnewX() - top.getX()) > bottom.getY())
						&& (top.getY() + top.getHeight() + (bottom.getX() - top.getX() - top.getWidth())
								* (top.getnewY() - top.getY()) / (top.getnewX() - top.getX()) > bottom.getnewY())) {
					return false;
				} else {
					return true;
				}
			}

			else if ((bottom.getX() != bottom.getnewX()) && (top.getX() != top.getnewX())) {
				valint1 = (top.getnewY() - top.getY()) / (top.getnewX() - top.getX());
				valint2 = (bottom.getnewY() - bottom.getY()) / (bottom.getnewX() - bottom.getX());
				valint3 = (bottom.getY() - top.getY() - top.getHeight() + top.getX() * valint1
						+ (bottom.getX() + bottom.getWidth()) * valint2) / (valint1 - valint2);
				if (valint3 > Math.max(top.getnewX(), top.getX())) {
					return false;
				} else if (valint3 < Math.min(top.getX(), top.getnewX())) {
					return false;
				} else if (valint3 > Math.max(bottom.getX(), bottom.getnewX()) + bottom.getWidth()) {
					return false;
				} else if (valint3 < Math.min(bottom.getX(), bottom.getnewX()) + bottom.getWidth()) {
					return false;
				} else {
					return true;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}

	}

	// Fonction de Quentin
	/*
	 * public static boolean isCollisionRectRect(Rectangle rect1, Rectangle
	 * rect2) { int marge = 0; if (rect1.getX() + rect1.getWidth() - marge >=
	 * rect2.getX() && rect1.getX() + marge <= rect2.getX() + rect2.getWidth())
	 * { if (rect1.getY() + rect1.getHeight() - marge >= rect2.getY()&&
	 * rect1.getY() + marge <= rect2.getY() + rect2.getHeight()) { return true;
	 * } else return false; } else return false; }
	 */

}