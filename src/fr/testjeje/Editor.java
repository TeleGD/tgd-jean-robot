package fr.testjeje;
import fr.bonus.*;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import fr.character.Player;
import fr.character.enemies.*;
import fr.decor.Platform;
import fr.main.Main;
import fr.menus.Mainmenu;

public class Editor extends BasicGameState{

	private static final int vitesseMenu=10;
	
	public static final int ID = 1;
	private ArrayList<Bonus> bonus=new ArrayList<Bonus>();
	private ArrayList<Platform> platforms=new ArrayList<Platform>();
	private ArrayList<Enemy> enemys=new ArrayList<Enemy>();
	private Player player=new Player((int)(Main.longueur*0.15f),(int)(Main.hauteur*0.7f));
	
	private Platform plateformMenu=new Platform(Main.longueur/20,(int) (Main.hauteur*0.85f));
	private Enemy enemyMenu=new Enemy();
	private AddMunition addMunitionMenu=new AddMunition();
	private DecreaseAmmo decreaseAmmoMenu=new DecreaseAmmo();
	private IncreaseJump increaseJumpMenu=new IncreaseJump();
	private IncreaseScore increaseScoreMenu=new IncreaseScore();
	private IncreaseSpeed increaseSpeedMenu=new IncreaseSpeed();
	private InverseControl inverseControlMenu=new InverseControl();
	private InvisibleTrap invisibleTrapMenu=new InvisibleTrap();
	private InvisibleEnnemy invisibleEnnemyMenu=new InvisibleEnnemy();
	private PoppEnnemyArround poppEnnemyArroundMenu=new PoppEnnemyArround();
	private InvisiblePlayer invisiblePlayerMenu=new InvisiblePlayer();
	private TeleportBonusLevel teleportBonusLevelMenu=new TeleportBonusLevel();
	private Paralyse paralyseMenu=new Paralyse();
	
	private static GameContainer container;
	private static StateBasedGame game;
	private static long time;

	private static boolean ouvrirMenu;
	private static boolean fermerMenu;
	private boolean menuRentre;

	private static boolean menuLocked;
	private static int tailleMenu=(int) (Main.hauteur*0.2f);
	private static int yMenu=Main.hauteur-tailleMenu;

	private static boolean gridEnabled;
	
	
	
	//pour le titre de l'editeur
	private static boolean showTitle=true;
	private TrueTypeFont fontTitle;
	private String title="MENU EDITOR";

	public Editor(){
		Font titreFont;
		try {
			titreFont =Font.createFont(java.awt.Font.TRUETYPE_FONT,
					       ResourceLoader.getResourceAsStream("font/PressStart2P.ttf"));
			
			fontTitle = new TrueTypeFont(titreFont.deriveFont(java.awt.Font.PLAIN, 50.f),false);

		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		
		
		player.render(container, game, g);
		if(gridEnabled)renderGrid(container,game,g);
		
		renderMenu(container,game,g);
		
		if(showTitle){
			g.setFont(fontTitle);
			g.setColor(Color.white);
			g.drawString(title,(int)((Main.longueur-fontTitle.getWidth(title))/2),(int)((Main.hauteur-fontTitle.getHeight(title))/2));
		}
		
	}
	
	
	public void renderGrid(GameContainer container, StateBasedGame game, Graphics g) {
		int nbCaseHori=Main.longueur/32;
		int nbCaseVert=Main.hauteur/32;
		g.setColor(Color.white);
		for(int i=0;i<nbCaseHori;i++){
			for(int j=0;j<nbCaseVert;j++){
				for(int k=0;k<3;k++){
					g.fillRect(i*32+k*32/3, j*32, 32/5, 1);
					g.fillRect(i*32, j*32+k*32/3, 1, 32/5);
				}
			}
		}
	}


	public void renderMenu(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		g.setColor(Color.white);
		g.drawRect(0, yMenu, Main.longueur-1, 5);
		g.setColor(Color.darkGray);
		g.fillRect(0, yMenu+6, Main.longueur,(Main.hauteur-yMenu)-5);
		
		plateformMenu.render(container, game, g);
		enemyMenu.render(container, game, g);
		addMunitionMenu.render(container, game, g);
		decreaseAmmoMenu.render(container, game, g);
		
		renderOptions(container,game,g);
	}
	
	public void renderOptions(GameContainer container, StateBasedGame game, Graphics g) {
		if(menuLocked){
			g.setColor(Color.orange);
			g.drawString("Press L to unlock",15,yMenu+15);
		}
		else{
			g.setColor(Color.white);
			g.drawString("Press L to lock",15,yMenu+15);
		}	
		
		if(gridEnabled){
			g.setColor(Color.orange);
			g.drawString("Press G to disable grid",15,yMenu+30);
		}
		else{
			g.setColor(Color.white);
			g.drawString("Press G for 32x32 grid",15,yMenu+30);
		}	
	}


	public void keyReleased(int key, char c) {
	}

	public void keyPressed(int key, char c) {
		if(key==Input.KEY_L){
			menuLocked=!menuLocked;
			if(menuLocked)ouvrirMenu=true;
			else fermerMenu=false;
			
		}else if(key==Input.KEY_G){
			gridEnabled=!gridEnabled;
		}else if(key==Input.KEY_ESCAPE){
			game.enterState(Mainmenu.ID, new FadeOutTransition(),
					new FadeInTransition());
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		time+=delta;
		if(time>3000 && time<3500 ){
			if(!menuRentre)fermerMenu=true;
			showTitle=false;
		}
		
		if(fermerMenu && !menuRentre && !menuLocked){
			if(yMenu<Main.hauteur)yMenu+=vitesseMenu;
			else{
				fermerMenu=false;
				menuRentre=true;
			}
		}
		
		if(ouvrirMenu && menuRentre){
			if(yMenu>Main.hauteur-tailleMenu)yMenu-=vitesseMenu;
			else{
				ouvrirMenu=false;
				menuRentre=false;
			}
		}
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		game=arg1;
		container=arg0;
		
	}

	public void mouseDragged(int oldx,int  oldy, int newx,int  newy){
		if(newy<Main.hauteur-tailleMenu && !menuRentre){
			fermerMenu=true;
		}
	}
	public void mouseMoved(int oldx,int  oldy, int newx,int  newy){
		if(newy<Main.hauteur-tailleMenu && !menuRentre){
			fermerMenu=true;
		}
		
		if(newy>Main.hauteur-50 && menuRentre){
			ouvrirMenu=true;
		}
		
	}
	public void mouseReleased(int button, int x,int y){
		
	}
	public void mousePressed(int button, int oldx,int oldy){
		
	}
	
	public static void reset(){
		time=0;
		showTitle=true;
		menuLocked=false;
		gridEnabled=false;
		ouvrirMenu=false;
		fermerMenu=false;
		yMenu=Main.hauteur-tailleMenu;
		
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
