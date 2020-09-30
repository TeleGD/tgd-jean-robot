package games.jeanRobot;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppLoader;

import games.jeanRobot.characters.BasicPlayer;
import games.jeanRobot.characters.Bat;
import games.jeanRobot.characters.Player;
import games.jeanRobot.characters.enemies.BasicEnnemy;
import games.jeanRobot.characters.enemies.Enemy1;
import games.jeanRobot.characters.enemies.Ennemy;
import games.jeanRobot.characters.enemies.EnnemyShooter;
import games.jeanRobot.decor.*;
import games.jeanRobot.projectiles.Projectile;
import games.jeanRobot.bonus.Bonus;
import games.jeanRobot.bonus.GunBonus;
import games.jeanRobot.bonus.LevelEnd;
import games.jeanRobot.bonus.BatBonus;

public class World extends BasicGameState {

	private int ID;
	private int state;

	public static final int longueur=1280;
	public static final int hauteur=720;
	public static final int DENSITE_X = 32;
	public static final int DENSITE_Y = 32;
	private static final String REPERTOIRE_NIVEAU = "levels";
	private static Player Nico;
	private static ArrayList<Plateform> plateforms = null;
	private static ArrayList<Ennemy> enemies = null;
	private static ArrayList<Projectile> projectiles = null;
	private static ArrayList<Bonus> bonuss = null;
	private static int score; //entier corespondant au score
	private static Decor decor;
	public static Audio Mbackground;

	public World(int ID) {
		this.ID = ID;
		this.state = 0;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) {
		/* Méthode exécutée une unique fois au chargement du programme */
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à l'apparition de la page */
		if (this.state == 0) {
			this.play(container, game);
		} else if (this.state == 2) {
			this.resume(container, game);
		}
		reset();
		Mbackground = AppLoader.loadAudio("/musics/jeanRobot/1.ogg");
		Mbackground.playAsMusic(1, .3f, true);
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à la disparition de la page */
		if (this.state == 1) {
			this.pause(container, game);
		} else if (this.state == 3) {
			this.stop(container, game);
			this.state = 0; // TODO: remove
		}
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) {
		/* Méthode exécutée environ 60 fois par seconde */
		decor.render(arg0,arg1,arg2);

		for(Bonus b : bonuss)
		{
			b.render(arg0, arg1, arg2);
		}
		for (int i=0; i<plateforms.size();i++){
			plateforms.get(i).render(arg0, arg1, arg2);

		}
		for (int i=0; i<enemies.size();i++){
			enemies.get(i).render(arg0, arg1, arg2);
		}
		for(Projectile p : projectiles){
			p.render(arg0, arg1, arg2);
		}
		Nico.render(arg0, arg1, arg2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
		/* Méthode exécutée environ 60 fois par seconde */
		// Mbackground.getPosition();
		Input input = arg0.getInput();
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			this.setState(1);
			arg1.enterState(7, new FadeOutTransition(), new FadeInTransition());
		}
		Nico.update(arg0, arg1, arg2);
		decor.update(arg0,arg1,arg2);
		for (int i=0; i<plateforms.size();i++){
			plateforms.get(i).update(arg0, arg1, arg2);
		}
		int i = 0;
		while (i<enemies.size()){
			enemies.get(i).update(arg0, arg1, arg2);
			if (enemies.get(i).isDestructed()){
				Nico.addScore(enemies.get(i).getScore());
				enemies.remove(i);
			}
			else{
				i++;
			}
		}

		i = 0;
		while (i < projectiles.size()){
			projectiles.get(i).update(arg0, arg1, arg2);
			if (projectiles.get(i).isDestructed()){
				projectiles.remove(i);
			}
			else{
				i++;
			}
		}

		for(Bonus b : bonuss)
		{
			b.update(arg0, arg1, arg2);
		}
	}

	public void play(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
	}

	public void pause(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
	}

	public void resume(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
	}

	public void stop(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

	//Souris*****************************************************************************
	public void mousePressed(int button,int x,int y){
	}

	public void keyReleased(int key, char c) {
		Nico.keyReleased(key, c);
	}

	public void keyPressed(int key, char c) {
		Nico.keyPressed(key, c);
	}

	public static void reset(){
		Nico=new Bat(new BasicPlayer());
		plateforms= new ArrayList<Plateform>();
		bonuss=new ArrayList<Bonus>();
		enemies=new ArrayList<Ennemy>();
		projectiles = new ArrayList<Projectile>();
		decor = new Decor("/images/jeanRobot/brick.png","/images/jeanRobot/background.png");
		chargerNiveau("niveau");

		score = 0;
		boolean chargerOk=chargerNiveau("niveau");
		if(!chargerOk){
			System.out.println("niveau 1 non charge");
			plateforms.add(new Plateform(4,4,10,1));
		}
		enemies.add(new Enemy1(new EnnemyShooter(new BasicEnnemy(plateforms.get(5)))));
		enemies.add(new Enemy1(new BasicEnnemy(plateforms.get(3))));


	}


	//Getters*******************************************************************************
	public static Player getPlayer(){
		return Nico;
	}
	public static ArrayList<Ennemy> getEnemies(){
		return enemies;
	}
	public static ArrayList<Plateform> getPlateforms(){
		return plateforms;
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
	public void plus50score()
	{
		score += 50;
	}

	private static boolean chargerNiveau(String niveau) {
		File f=new File(niveau);
		if(f.exists()){
			System.out.println("Le niveau "+niveau+" n'existe pas dans le repertoire "+REPERTOIRE_NIVEAU);
			return false;
		}
		try {
			BufferedReader br=new BufferedReader(new FileReader("res"+File.separator+"data"+File.separator+"jeanRobot"+File.separator+REPERTOIRE_NIVEAU+File.separator+niveau));
			String ligne;
			while((ligne=br.readLine())!=null){
				//Def des plateformes
				if(ligne.startsWith("Plateform")){
					Plateform p=new Plateform(ligne);
					plateforms.add(p);
				}
				else if(ligne.startsWith("DeathBloc"))
				{
					DeathBloc p =new DeathBloc(ligne);
					plateforms.add(p);
				}
				else if(ligne.startsWith("ElevatorTrap")){
					ElevatorTrap p= new ElevatorTrap(ligne);
					plateforms.add(p);
				}
				//Def des ennemies
				else if(ligne.startsWith("EnnemyShooter")){
					String[] ligne2 = ligne.split(" ");
					int i = Integer.parseInt(ligne2[1]);
					enemies.add(new Enemy1(new EnnemyShooter(new BasicEnnemy(plateforms.get(i-1)))));
				}
				//Def des bonus
				else if(ligne.startsWith("LevelEnd")){
					String[] ligne2 = ligne.split(" ");
					bonuss.add(new LevelEnd(
							Double.parseDouble(ligne2[1]),
							Double.parseDouble(ligne2[2]),
							Double.parseDouble(ligne2[3]),
							Double.parseDouble(ligne2[4]),
							Nico));
				}
				else if (ligne.startsWith("BatBonus")){
					String[] ligne2 = ligne.split(" ");
					bonuss.add(new BatBonus(
							Double.parseDouble(ligne2[1]),
							Double.parseDouble(ligne2[2]),
							Double.parseDouble(ligne2[3]),
							Double.parseDouble(ligne2[4]),
							Nico));
				}
				else if (ligne.startsWith("GunBonus")){
					String[] ligne2 = ligne.split(" ");
					bonuss.add(new GunBonus(
							Double.parseDouble(ligne2[1]),
							Double.parseDouble(ligne2[2]),
							Double.parseDouble(ligne2[3]),
							Double.parseDouble(ligne2[4]),
							Nico));
				}

			}
			br.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;


	}

	public static void setPlayer(Player p){
		Nico = p;
	}


}
