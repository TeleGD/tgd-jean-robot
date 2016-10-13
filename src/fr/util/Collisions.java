package fr.util;

//import fr.character.Ennemy1;
import fr.characters.Player;
//import fr.decor.Platforms;
import fr.decor.Plateform;

public class Collisions {

	private static double delta=5; //delta par defaut, valeur de penetration.
	
	public static boolean altCollisionY(Entity h1, Entity h2){
		//Dans l'idee, on renvoie faux s'il n'y a PAS de collision, et vrai sinon.
		
		// h1 au dessus de h2 OU h2 au dessus de h1
		if( (h1.getY()+h1.getHeight()<h2.getY()) || (h2.getY()+h2.getHeight()<h1.getY()) ){
			if(h1 instanceof Player) ((Player) h1).setPosJump(true);
			if(h2 instanceof Player) ((Player) h2).setPosJump(true);
			return false;
		}
		else{
			// si h1 et h2 forment un couple joueur/plate-forme, et le joueur monte
			if((h1 instanceof Player && h2 instanceof Plateform)&& h1.getSpeedY() < 0) return false;
			if((h2 instanceof Player && h1 instanceof Plateform)&& h2.getSpeedY() < 0) return false;
		}
		return true;
	}
	
	public static boolean altCollisionX(Entity h1, Entity h2){
		//Pareil que altCollisionY, mais sur l'axe X.
		
		//h1 a gauche de h2 OU h2 a gauche de h1
		if( (h1.getX()+h1.getWidth()<h2.getX()) || (h2.getX()+h2.getWidth()<h1.getX()) ){
			return false;
		}
		return true;
	}
	
	public static int altCollisionSide(Entity h1, Entity h2){
	/*Renvoie (on suppose h2 immobile pour le moment):
	 *	0 si pas de collision; Et sinon :
	 *		
	 *				2
	 *				|
	 *				v
	 *			  ------	
	 *		3 -->|	h2	|<-- 1
	 *			  ------
	 *				*
	 *				|
	 *				4
	 * */
		
		boolean d,h,g,b;
		boolean supX,supY;//Superposition au moins partielle de h1 et h2
		
		supX = (h1.getNewX() + h1.getWidth() >= h2.getX()) && (h1.getNewX() <= h2.getX() + h2.getWidth());
		supY = (h1.getNewY() + h1.getHeight() >= h2.getY()) && (h1.getNewY() <= h2.getY() + h2.getHeight());
		d = ( h1.getSpeedX() < 0 ); // collision par la droite possible
		h = ( h1.getSpeedY() > 0 ); // Collision par le haut possible
		g = ( h1.getSpeedX() > 0 ); // Collision par la gauche possible
		b = ( h1.getSpeedY() < 0 ); // Collision par le bas possible
		
		if(supX && supY){
			if(d) return 1;
			if(h) return 2;
			if(g) return 3;
			if(b) return 4;
		}
		return 0;
	}
	
	// Coucou PA, ici Arthur. Cette fois ci je ne me trompe pas de personne. Ahah (cf Player.java).
	// C'est tres la rigolance ici, mais j'arrive pas à faire marcher tes collisions (explique moi quand tu pourras, s'il te plait.), 
	// donc j'ai implemente des collisions plus rigides, mais qui marchent facilement (elles sont juste au dessus).
	// J'ai essaye de reprendre ton idee d'identifier le cote par lequel on realise la collision, c'est effectivement pratique.
	// P.S. : Par pitie, pas d'accents dans le code ou les commentaires : ca fout le merdier chez moi 
	// (mon PC est un paysan qui encode tout en ANSI au lieu de faire de l'UTF-8, donc j'ai du japonais partout...).
	
	//verifie qu'il existe une collision entre la Entity 1 et Entity2 sur l'axe Y
	//retourne :
	//0 -> aucune collision
	//-1 -> collision par le haut
	//1 -> collision par le bas
	//note : on peut utiliser la valeur retourner pour modifier la vitesse suite a la collision ! merci bibi 
	public static int isCollisionY(Entity h1,Entity h2, double delta){
		
		//si la Entity tombe et va plus bas que le haut de l'autre Entity
		if ( (h1.getSpeedY()>0) && (h1.getNewY()+h1.getHeight()>=h2.getNewY()+delta) ){
			return -1;
		}
		
		//si la Entity saute et se cogne sur la Entity du dessus.
		if ( (h1.getSpeedY()<0) && (h1.getY()-delta<=h2.getY()+h2.getHeight())){
			return 1;
		}
		
		//sinon, aucune collision
		return 0;
	}
	public static int isCollisionY(Entity h1,Entity h2){
		
		//si la Entity tombe et va plus bas que le haut de l'autre Entity
		if ( (h1.getSpeedY()>0) && (h1.getNewY()+h1.getHeight()>=h2.getNewY()+delta) ){
			return -1;
		}
		//si la Entity saute et se cogne sur la Entity du dessus.
		if ( (h1.getSpeedY()<0) && (h1.getY()-delta<=h2.getY()+h2.getHeight())){
			return 1;
		}
		//sinon, aucune collision
		return 0;
	}
	
	//verifie qu'il existe une collision entre la Entity 1 et Entity2 sur l'axe X
		//retourne :
		//0 -> aucune collision
		//-1 -> collision par la gauche
		//1 -> collision par la droite
		//note : on peut utiliser la valeur retournee pour modifier la vitesse suite a la collision ! merci bibi
	public static int isCollisionX(Entity h1,Entity h2, double delta){
		
		//si la Entity va vers la gauche et heurte l'autre
		if ( (h1.getSpeedX()>0) && (h1.getNewX()+h1.getWidth()>=h2.getNewX()+delta) ){
			return -1;
		}
		//si la Entity va vers la droite et heurte l'autre
		if ( (h1.getSpeedX()<0) && (h1.getX()-delta<=h2.getX()+h2.getWidth())){
			return 1;
		}
		
		//sinon, aucune collision
		return 0;
	}
			
	//verifie qu'il existe une collision entre la Entity 1 et Entity2 sur l'axe X
		//retourne :
		//0 -> aucune collision
		//-1 -> collision par la gauche
		//1 -> collision par la droite
		//note : on peut utiliser la valeur retournee pour modifier la vitesse suite a la collision ! merci bibi
	
	public static int isCollisionX(Entity h1,Entity h2){
		
		//si la Entity va vers la droite et heurte l'autre
		if ( (h1.getSpeedX()>0) && (h1.getNewX()+h1.getWidth()>=h2.getNewX()-delta) ){
			return -1;
		}
		//si la Entity va vers la gauche et heurte l'autre
		if ( (h1.getSpeedX()<0) && (h1.getX()-delta<=h2.getX()+h2.getWidth())){
			return 1;
		}
		
		//sinon, aucune collision
		return 0;
	}
	
	
	
	
	
	
	/*private static int margev = 0;
	private static double valint1, valint2, valint3;

	public static boolean isCollisionplayer1plateform(Player player1, Platforms plat, int delta) {

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

	
	 * collision ou non de deux rectangle l'un au-dessus de l'autre on considﾃｩ窶伉ｽe
	 * que les rectanggle n'ont pas de rotation selon l'axe z (qui sort de
	 * l'écran) on a donc la trajectoirede tous les points qui est la mﾃｩﾂｺﾂ･e pour
	 * un rectangle donnﾃｯﾂｿﾂｽ c'est pourquoi on s'interesse a certains coins et non
	 * a chaque coin on s'occupe du coin inferieur droit du rectange du haut et
	 * au coin superieur gauche du rectangle d'en bas
	 

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
	
	 * public static boolean isCollisionRectRect(Rectangle rect1, Rectangle
	 * rect2) { int marge = 0; if (rect1.getX() + rect1.getWidth() - marge >=
	 * rect2.getX() && rect1.getX() + marge <= rect2.getX() + rect2.getWidth())
	 * { if (rect1.getY() + rect1.getHeight() - marge >= rect2.getY()&&
	 * rect1.getY() + marge <= rect2.getY() + rect2.getHeight()) { return true;
	 * } else return false; } else return false; }
	 
*/
}