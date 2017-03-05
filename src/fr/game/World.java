package fr.game;


import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.bonus.Bonus;
import fr.characters.BasicPlayer;
import fr.characters.Bat;
import fr.characters.Player;
import fr.characters.enemies.Ennemy;
import fr.decor.Decor;
import fr.jerome.Level;
import fr.projectiles.Projectile;
import fr.util.LevelUtils;


/**
 *  INFOS IMPORTANTES
 *  
 * Le init(GameContainer ...) est appelé au debut du programme
 * Le enter(GameContainer ...) est appelé quand on va entrer dans cette vue
 * 
 * Afin d'eviter un démarrage très long, j'ai déplacé tous le contenu de la methode init dans enter
 * Eviter donc de surcharger la methode init pour rien
 */

/**
 * 	NOUVEAUTES GENERALES
 * 
 * - Les plateformes sont maintenant dans Decor car C'est plus logique
 * Decor.getPlateforms(), Decor.getPlateform(indexDeLaPlateforme),
 * Decor.addPlateform(), Decor.removePlateform(indexDelaPlateforme sont des methodes accessibles n'importe ou
 *
 * - La caméra est un peu moins oppressante
 *  (Plusieurs Parametres sont reglables en statique, pour definir precisement a quel moment recadrer le joueur un peu plus au centre de la fenêtre
 * 
 * - Objet de Type Level Defini: contient les plateformes, les ennemis, les bonus
 * - Charger un niveau avec LevelsUtils.loadLevel("nomDuNiveau"); cela renvoit un objet de type level ou sont contenues, les plateformes, les ennemis et les bonus
 * 
 */


public class World extends BasicGameState {

	//VARIABLE PROPRE A SLICK2D
	public static int ID = 0;
	public static StateBasedGame game;

	//TYPE COMPLEXE ICI
	private static Player player;
	private static Decor decor;

	private static ArrayList<Ennemy> enemies = null;
	private static ArrayList<Projectile> projectiles = null;
	private static ArrayList<Bonus> bonuss = null;
	
	//TYPE PRIMITIF ICI
	private static int score; //entier corespondant au score
	
	
	@Override
	public void init(GameContainer arg0, StateBasedGame game) throws SlickException {
		World.game=game;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
	     super.enter(container, game);
		 reset();
	 }

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		decor.render(arg0,arg1,arg2);
		
		for(Bonus b : bonuss)
		{
			b.render(arg0, arg1, arg2);
		}
		
		for (int i=0; i<enemies.size();i++){
			enemies.get(i).render(arg0, arg1, arg2);
		}
		
		for(Projectile p : projectiles){
			p.render(arg0, arg1, arg2);
		}
		
		player.render(arg0, arg1, arg2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		player.update(arg0, arg1, arg2);
		decor.update(arg0,arg1,arg2);
		
		for(int i=0;i<enemies.size();i++){
			enemies.get(i).update(arg0, arg1, arg2);
			
			if (enemies.get(i).isDestructed()){
				player.addScore(enemies.get(i).getScore());
				enemies.remove(i);
				i--;
			}
		}
		for(int i=0;i<projectiles.size();i++){
			projectiles.get(i).update(arg0, arg1, arg2);
			
			if (projectiles.get(i).isDestructed()){
				projectiles.remove(i);
				i--;
			}
		}
		
		
		for(Bonus b : bonuss)
		{
			b.update(arg0, arg1, arg2);
		}
	}

	@Override
	public int getID() {
		return ID;
	}

	public void keyReleased(int key, char c) {
		player.keyReleased(key, c);
	}

	public void keyPressed(int key, char c) {
		player.keyPressed(key, c);
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}
	}

	public static void reset() throws SlickException{
		player = new Bat(new BasicPlayer());
		projectiles = new ArrayList<Projectile>();
		decor = new Decor("img/background.png");
		
		score = 0;

		Level level=LevelUtils.loadLevel("niveau");
		
		if(level!=null){
			enemies=level.getEnemys();
			bonuss =level.getBonus();
			
			Decor.setPlateforms(level.getPlateforms());
		}else{
			System.out.println("ERREUR LORS DU CHARGEMENT DU NIVEAU");
		}
		
		//enemies.add(new Enemy1(new EnnemyShooter(new BasicEnnemy(plateforms.get(3)))));
		//bonuss.add(new BatBonus(50.0,0.0,10,10,player));
		//bonuss.add(new GunBonus(0.0,0.0,10,10,player));
	}
	
	
	//Getters*******************************************************************************
	public static Player getPlayer(){
		return player;
	}
	public static ArrayList<Ennemy> getEnemies(){
		return enemies;
	}
	
	public static ArrayList<Projectile> getProjectiles(){
		return projectiles;
	}
	
	public static ArrayList<Bonus> getBonus(){
		return bonuss;
	}

	public static int getScore() {
		// C'est pour le menu de fin de partie surtout
		return score;
	}
	
	//Setters*******************************************************************************
	
	public void setScore(int i) {
		score = i;
	}
	
	public static void addProjectile(Projectile p){
		if(p!=null) projectiles.add(p);
	}
	
	//Modified*******************************************************************************
	public void addScore(int scoreLoc)
	{
		score += scoreLoc;
	}



	public static void setPlayer(Player player) {
		World.player=player;
	}
	
	
	

}
