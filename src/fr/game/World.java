package fr.game;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;
import fr.characters.enemies.Enemy;
import fr.decor.Plateform;

public class World extends BasicGameState {

	private static final String REPERTOIRE_NIVEAU = "levels";
	public static int ID = 0;
	private static Player Nico;
	private static ArrayList<Plateform> plateforms = null;
	private static ArrayList<Enemy> enemies = null;
	public static StateBasedGame game;
	private static Plateform plateform;
	private int score; //entier corespondant au score
	
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		game = arg1;
		Nico = new Player();
		plateforms= new ArrayList<Plateform>();
		enemies=new ArrayList<Enemy>();
		this.score = 0;
		
		if(!chargerNiveau("niveau1")){
			plateforms.add(new Plateform(4,4,10,1));
		}
		enemies.add(new Enemy());
	}

	

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		Nico.render(arg0, arg1, arg2);
		for (int i=0; i<plateforms.size();i++){
			plateforms.get(i).render(arg0, arg1, arg2);
			
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Nico.update(arg0, arg1, arg2);
		for (int i=0; i<plateforms.size();i++){
			plateforms.get(i).update(arg0, arg1, arg2);
		}
		
	}

	//Souris*****************************************************************************
	public void mousePressed(int button,int x,int y){
	}


	@Override
	public int getID() {
		return ID;
	}

	public void keyReleased(int key, char c) {
		Nico.keyReleased(key, c);
	}

	public void keyPressed(int key, char c) {
		Nico.keyPressed(key, c);
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}
	}

	public static void reset(){
	}
	
	
	//Getters*******************************************************************************
	public static Player getPlayer(){
		return Nico;
	}
	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	public static ArrayList<Plateform> getPlateforms(){
		return plateforms;
	}

	public int getScore() {
		// C'est pour le menu de fin de partie surtout
		return score;
	}
	
	//Setters*******************************************************************************
	
	public void setScore(int i) {
		this.score = i;
	}
	
	//Modified*******************************************************************************
	public void plus50score()
	{
		
	}
	
	private boolean chargerNiveau(String niveau) {
		File f=new File(niveau);
		if(f.exists()){
			System.out.println("Le niveau "+niveau+" n'existe pas dans le repertoire "+REPERTOIRE_NIVEAU);
			return false;
		}
		try {
			BufferedReader br=new BufferedReader(new FileReader(REPERTOIRE_NIVEAU+File.separator+niveau));
			String ligne;
			while((ligne=br.readLine())!=null){
				if(ligne.startsWith("Plateform")){
					Plateform p=new Plateform(ligne);
					plateforms.add(p);
				}
			}
			br.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
		
	}

}
