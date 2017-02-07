package fr.util;

import fr.characters.Character;
import fr.characters.Player;

//J'ai adapte les collisions au Character, on a pour l'instant des col de type Character/Entity.
//La cause fut une modification de la structure du Character (application decorator)
//Je modifierais pour avoir les collisions avec les ennemis des que j'aurais vu comment les implementer de maniere plus simple
//N.B.

public class Collisions {

	private static double delta=0; //delta par defaut, valeur de penetration dans l entity.
	
	//TODO
	// ET OPTION SELON LE TYPE DE COLLISION SOUHAITE (tranverse dessous collision totale, ...)
	//Getter, setter pour le delta par defaut??
	
	/**
	 * verifie qu'il existe une collision entre la Entity 1 et Entity2 sur l'axe Y
	 * @author PA
	 * @param h1 entity1
	 * @param h2 entity2
	 * @param delta valeur de penetration
	 * @return -1 -> par le haut / 0 -> aucune  / 1 -> par le bas 
	 */
	public static int isCollisionY(Character h1,Entity h2, double delta){
		int h = 0;
		//si la Entity est au meme niveau que l autre entity sur l autre axe
		if (h1.getX()+h1.getWidth()/2>h2.getX() && h1.getX()+h1.getWidth()/2<h2.getX()+h2.getWidth()){
			//si la Entity tombe et va plus bas que le haut de l'autre Entity
			if ((h1.getSpeedY()>0) && ((h1.getY()+h1.getSpeedY()+h1.getHeight()>=h2.getY()+h2.getSpeedY()) && (h1.getY()+h1.getSpeedY()<=h2.getY()+h2.getSpeedY()+h2.getHeight()))){
				h= -1;
			}
			//si la Entity saute et se cogne sur la Entity du dessus.
			if ((h1.getSpeedY()<0) && ((h1.getY()+h1.getSpeedY()<=h2.getY()+h2.getHeight()) && (h1.getY()+h1.getSpeedY()+h1.getHeight()>= h2.getY()+ h2.getHeight()))){
				h = 1;
			}
		}
		//sinon, aucune collision
		return h;
	}
	
	/**
	 * meme chose, mais avec le delta par defaut
	 * @author PA
	 * @param h1 entity1
	 * @param h2 entity2
	 * @return -1 -> par le haut / 0 -> aucune  / 1 -> par le bas 
	 */
	public static int isCollisionY(Character h1,Entity h2){
		//return Collisions.isCollisionY(h1, h2, delta);
		if(colCharacterTopEntity(h1,h2)){return -1;}
		if(colCharacterBotEntity(h1,h2)){return 1;}
		return 0;
	}
	
	
	/**
	 * verifie qu'il existe une collision entre la Entity 1 et Entity2 sur l'axe X
	 * @author PA
	 * @param h1 entity1
	 * @param h2 entity2
	 * @param delta valeur de penetration
	 * @return -1 -> par la gauche / 0 -> aucune  / 1 -> par la droite 
	 */
	public static int isCollisionX(Character h1,Entity h2, double delta){
		int h = 0;
		//si la Entity est au meme niveau que l autre entity sur l autre axe
		if (h1.getY()+h1.getSpeedY()+h1.getHeight()>h2.getY()+h2.getSpeedY() && h1.getY()+h1.getSpeedY()<h2.getY()+h2.getSpeedY()+h2.getHeight()){
			
			//si la Entity va vers la droite et heurte l'autre
			if ( ((h1.getX()+h1.getSpeedX()+h1.getWidth()>=h2.getX()) && (h1.getX()+h1.getSpeedX()<h2.getX()+h2.getWidth() ))){
				h = -1;
			}
			//si la Entity va vers la gauche et heurte l'autre
			if ( ((h1.getX()+h1.getSpeedX()<=h2.getX()+h2.getSpeedX()+h2.getWidth()) && (h1.getX()+h1.getSpeedX()>=h2.getX()))){
				h = 1;
			}	
		}
		//sinon, aucune collision
		return h;
	}
	
	/**
	 * meme chose, mais avec le delta par defaut
	 * @author PA
	 * @param h1 entity1
	 * @param h2 entity2
	 * @return -1 -> par la gauche / 0 -> aucune  / 1 -> par la droite 
	 */
	public static int isCollisionX(Character h1,Entity h2){
		return Collisions.isCollisionX(h1,h2,delta);
	}
	
	/**
	 * retourne le vecteur inverse du mouvement, du coup suffit de l appliquer pour avoir la reaction de h1 par rapport à h2
	 * @author PA
	 * @param h1 entity1
	 * @param h2 entity2
	 * @param delta valeur de penetration
	 * @return le vecteur directeur de la reaction au mouvement, un exemple : si h1 vient dans la direction (1,1), ca renvoie (-1,-1) 
	 * du coup on peut appliquer directement le resultat si l on veut rebondir
	 */
	public static int[] isCollision(Character h1, Entity h2, double delta){
		int[] resultat={Collisions.isCollisionX(h1, h2, delta),Collisions.isCollisionY(h1,h2,delta)};
		return resultat;
	}
	/**
	 * meme chose avec le delta par defaut
	 * @author PA
	 * @param h1 entity1
	 * @param h2 entity2
	 * @return le vecteur directeur de la reaction au mouvement, un exemple : si h1 vient dans la direction (1,1), ca renvoie (-1,-1) 
	 * du coup on peut appliquer directement le resultat si l on veut rebondir
	 */
	public static int[] isCollision(Character h1, Entity h2){
		return Collisions.isCollision(h1, h2, delta);
	}
	
	public static boolean inCollision(Character h1, Entity h2){
		if ((h1.getY()+h1.getSpeedY()+h1.getHeight()>h2.getY()+h2.getSpeedY() && h1.getY()+h1.getSpeedY()<h2.getY()+h2.getSpeedY()+h2.getHeight()) & (h1.getX()+h1.getWidth()>h2.getX() && h1.getX()<h2.getX()+h2.getWidth())){
			return true;
		}
		return false;
	}
	
	public static boolean colCharacterTopEntity(Character c,Entity e){
		if(c.getSpeedY()<=0){return false;}
		if(c.getnewY()+c.getHeight()<e.y){return false;}
		if(c.getY()>e.y){return false;}
		if(c.getX()>e.x+e.width){return false;}
		if(c.getX()+c.getWidth()<e.x){return false;}
		return true;
	}
	
	public static boolean colCharacterBotEntity(Character c,Entity e){
		if(c.getSpeedY()>=0){return false;}
		if(c.getnewY()>e.getY()+e.getHeight()){return false;}
		if(c.getY()<e.getY()){return false;}
		if(c.getX()>e.x+e.width){return false;}
		if(c.getX()+c.getWidth()<e.x){return false;}
		return true;
	}
	
	/*
	public static int isCollisionY(Player c1, Character c2){
		return 0;
	}

	public static boolean colCharacterTopEntity(Player c2,Character c1){
		if(c.getSpeedY()<=0){return false;}
		if(c.getnewY()+c.getHeight()<e.y){return false;}
		if(c.getY()>e.y){return false;}
		if(c.getX()>e.x+e.width){return false;}
		if(c.getX()+c.getWidth()<e.x){return false;}
		return true;
	}*/
}