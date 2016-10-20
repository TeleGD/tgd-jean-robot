package fr.util;

public class Collisions {

	private static double delta=5; //delta par defaut, valeur de penetration dans l entity.
	
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
	public static int isCollisionY(Entity h1,Entity h2, double delta){
		//si la Entity est au meme niveau que l autre entity sur l autre axe
		if (h1.getNewX()+h1.getWidth()>h2.getNewX() && h1.getNewX()<h2.getNewX()+h2.getWidth()){
			//si la Entity tombe et va plus bas que le haut de l'autre Entity
			if ( (h1.getSpeedY()>0) && (h1.getNewY()+h1.getHeight()>=h2.getNewY()+delta)){
				return -1;
			}
			//si la Entity saute et se cogne sur la Entity du dessus.
			if ( (h1.getSpeedY()<0) && (h1.getY()-delta<=h2.getY()+h2.getHeight())){
				return 1;
			}
		}
		//sinon, aucune collision
		return 0;
	}
	
	/**
	 * meme chose, mais avec le delta par defaut
	 * @author PA
	 * @param h1 entity1
	 * @param h2 entity2
	 * @return -1 -> par le haut / 0 -> aucune  / 1 -> par le bas 
	 */
	public static int isCollisionY(Entity h1,Entity h2){
		return Collisions.isCollisionY(h1, h2, delta);
	}
	
	
	/**
	 * verifie qu'il existe une collision entre la Entity 1 et Entity2 sur l'axe X
	 * @author PA
	 * @param h1 entity1
	 * @param h2 entity2
	 * @param delta valeur de penetration
	 * @return -1 -> par la gauche / 0 -> aucune  / 1 -> par la droite 
	 */
	public static int isCollisionX(Entity h1,Entity h2, double delta){
		//si la Entity est au meme niveau que l autre entity sur l autre axe
		if (h1.getNewY()+h1.getHeight()>h2.getNewY() && h1.getNewY()<h2.getNewY()+h2.getHeight()){
			//si la Entity va vers la gauche et heurte l'autre
			if ( (h1.getSpeedX()>0) && (h1.getNewX()+h1.getWidth()>=h2.getNewX()+delta) ){
				return -1;
			}
			//si la Entity va vers la droite et heurte l'autre
			if ( (h1.getSpeedX()<0) && (h1.getX()-delta<=h2.getX()+h2.getWidth())){
				return 1;
			}	
		}
		//sinon, aucune collision
		return 0;
	}
	
	/**
	 * meme chose, mais avec le delta par defaut
	 * @author PA
	 * @param h1 entity1
	 * @param h2 entity2
	 * @return -1 -> par la gauche / 0 -> aucune  / 1 -> par la droite 
	 */
	public static int isCollisionX(Entity h1,Entity h2){
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
	public static int[] isCollision(Entity h1, Entity h2, double delta){
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
	public static int[] isCollision(Entity h1, Entity h2){
		return Collisions.isCollision(h1, h2, delta);
	}

}