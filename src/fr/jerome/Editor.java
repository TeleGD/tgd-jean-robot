package fr.jerome;
import fr.bonus.*;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

import fr.characters.Player;
import fr.characters.enemies.*;
import fr.decor.Plateform;
import fr.game.Game;
import fr.game.World;
import fr.menus.Mainmenu;
import fr.util.Button;

public class Editor extends BasicGameState{

	private static final int vitesseMenu=10;
	
	public static final int ID = 1;

	private static final String DOSSIER_LEVEL = "levels";
	private ArrayList<Bonus> bonus=new ArrayList<Bonus>();
	private ArrayList<Plateform> plateforms=new ArrayList<Plateform>();
	private ArrayList<Enemy> enemys=new ArrayList<Enemy>();
	//private Player player=new Player((int)(Game.longueur*0.15f),(int)(Game.hauteur*0.7f));
	

	private static boolean ouvrirMenu;
	private static boolean fermerMenu;
	private boolean menuRentre;

	private static boolean menuLocked;
	private static int tailleMenu=(int) (Game.hauteur*0.2f);
	private static int yMenu=Game.hauteur-tailleMenu;
	
	//Faudra voir pour le constructeur des plateformes (Nico)
	private Plateform plateformMenu=new Plateform(5,1,3,1);
	private Enemy enemyMenu=new Enemy();
	private AddMunition addMunitionMenu=new AddMunition();
	private DecreaseAmmo decreaseAmmoMenu=new DecreaseAmmo();
	private IncreaseJump increaseJumpMenu=new IncreaseJump();
	private IncreaseScore increaseScoreMenu=new IncreaseScore();
	private IncreaseSpeed increaseSpeedMenu=new IncreaseSpeed();
	private InverseControl inverseControlMenu=new InverseControl();
	private InvisibleTrap invisibleTrapMenu=new InvisibleTrap();
	private InvisibleEnnemy invisibleEnnemyMenu=new InvisibleEnnemy();
	private InvisiblePlayer invisiblePlayerMenu=new InvisiblePlayer();
	private TeleportBonusLevel teleportBonusLevelMenu=new TeleportBonusLevel();
	private Paralyse paralyseMenu=new Paralyse();
	
	private static GameContainer container;
	private static StateBasedGame game;
	private static long time;

	private static boolean gridEnabled;
	
	private Plateform plateformEnCours;
	
	//pour le titre de l'editeur
	private static boolean showTitle=true;
	private TrueTypeFont fontTitle;
	private String title="LEVEL EDITOR";

	private Button enregistrer=new Button("Enregistrer",15,yMenu-tailleMenu-35);

	private boolean saveAbove;

	private boolean saveClicked;

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
		
		plateformMenu.setY(yMenu+50);
		plateformMenu.setX(Game.longueur*0.2);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		World.getPlayer().render(container, game, g);
		if(gridEnabled)renderGrid(container,game,g);

		for(int i=0;i<plateforms.size();i++){
			plateforms.get(i).render(container, game, g);
		}
		
		renderMenu(container,game,g);
		
		if(showTitle){
			g.setFont(fontTitle);
			g.setColor(Color.white);
			g.drawString(title,(int)((Game.longueur-fontTitle.getWidth(title))/2),(int)((Game.hauteur-fontTitle.getHeight(title))/2));
		}
		
	}
	
	
	public void renderGrid(GameContainer container, StateBasedGame game, Graphics g) {
		int nbCaseHori=Game.longueur/32;
		int nbCaseVert=Game.hauteur/32;
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
		g.drawRect(0, yMenu, Game.longueur-1, 5);
		g.setColor(Color.darkGray);
		g.fillRect(0, yMenu+6, Game.longueur,(Game.hauteur-yMenu)-5);
		
		plateformMenu.render(container, game, g);
		//enemyMenu.render(container, game, g);
		//addMunitionMenu.render(container, game, g);
		//decreaseAmmoMenu.render(container, game, g);
		
		
		if(plateformEnCours!=null){
			plateformEnCours.render(container, game, g);
			g.setColor(Color.red);
			g.drawString("SELECTED",(int)(plateformEnCours.getX()+plateformEnCours.getWidth()/2-40), (int)(plateformEnCours.getY()+plateformEnCours.getHeight()/2-10));
		}
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

		
		if(saveAbove){
			g.setColor(Color.red);
			g.drawRect(5, yMenu+tailleMenu-35, 120, 26);
		}
		
		if(saveClicked)
			g.setColor(Color.red);
		else
			g.setColor(Color.white);
		g.drawString("ENREGISTRER",15,yMenu+tailleMenu-30);
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
		}else if(key==Input.KEY_K ){
			if(plateformEnCours!=null){
				plateformEnCours.setWidth(Game.DENSITE_X*((int)(plateformEnCours.getWidth()/Game.DENSITE_X)+1));
			}
		}else if(key==Input.KEY_H ){
			if(plateformEnCours!=null){
				plateformEnCours.setWidth(Game.DENSITE_X*((int)(plateformEnCours.getWidth()/Game.DENSITE_X)-1));
			}
		}else if(key==Input.KEY_U ){
			if(plateformEnCours!=null ){
				plateformEnCours.setHeight(Game.DENSITE_Y*((int)(plateformEnCours.getHeight()/Game.DENSITE_Y)+1));
			}
		}else if(key==Input.KEY_J ){
			if(plateformEnCours!=null){
				plateformEnCours.setHeight(Game.DENSITE_Y*((int)(plateformEnCours.getHeight()/Game.DENSITE_Y)-1));
			}
		}
		
	    
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		time+=delta;
		if(time>4000 && time<4500 ){
			if(!menuRentre)fermerMenu=true;
			showTitle=false;
		}
		
		if(fermerMenu && !menuRentre && !menuLocked){
			if(yMenu<Game.hauteur)yMenu+=vitesseMenu;
			else{
				fermerMenu=false;
				menuRentre=true;
			}
		}
		
		if(ouvrirMenu && menuRentre){
			if(yMenu>Game.hauteur-tailleMenu)yMenu-=vitesseMenu;
			else{
				ouvrirMenu=false;
				menuRentre=false;
			}
		}
		
		for(int i=0;i<plateforms.size();i++){
			plateforms.get(i).update(container, game, delta);
		}
		plateformMenu.setY(yMenu+50);
		plateformMenu.update(container, game, delta);
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		game=arg1;
		container=arg0;
		
	}

	public void mouseDragged(int oldx,int  oldy, int newx,int  newy){
		if(newy<Game.hauteur-tailleMenu && !menuRentre){
			fermerMenu=true;
		}
		
		
	}
	public void mouseMoved(int oldx,int  oldy, int newx,int  newy){
		if(newy<Game.hauteur-tailleMenu && !menuRentre){
			fermerMenu=true;
		}
		
		if(newy>Game.hauteur-50 && menuRentre){
			ouvrirMenu=true;
		}
		if(plateformEnCours!=null){
			plateformEnCours.setPosition((int)((newx-plateformEnCours.getWidth()/2)/Game.DENSITE_X),(int) ((newy-plateformEnCours.getHeight()/2)/Game.DENSITE_Y));
		}
		if(newx>5 && newy>yMenu+tailleMenu-35 && newx<130 && newy<yMenu+tailleMenu+45){
			saveAbove=true;
		}else saveAbove=false;
		
		
	}
	public void mouseReleased(int button, int x,int y){
		//c'est quoi
		if(plateformMenu.containsPoint(x, y)){
			plateformEnCours=new Plateform(plateformMenu);
		}else if(plateformEnCours!=null){
			plateforms.add(plateformEnCours);
			plateformEnCours=new Plateform(plateformMenu);
		}
		
		if(x>5 && y>yMenu+tailleMenu-35 && x<130 && y<yMenu+tailleMenu+45){
			enregistrer("niveau1");
			saveClicked=false;
		}
	}
	public void mousePressed(int button, int x,int y){
		if(x>5 && y>yMenu+tailleMenu-35 && x<130 && y<yMenu+tailleMenu+45){
			saveClicked=true;
		}
	}
	
	public static void reset(){
		time=0;
		showTitle=true;
		menuLocked=false;
		gridEnabled=false;
		ouvrirMenu=false;
		fermerMenu=false;
		yMenu=Game.hauteur-tailleMenu;
		
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
	public boolean enregistrer(String nom){
		File f=new File(DOSSIER_LEVEL+File.separator+nom);
		if(f.exists())return false;
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(f));
			String ligne;
			bw.write("// PLATEFORMES");
			bw.newLine();
			
			for(int i=0;i<plateforms.size();i++){
				bw.write(plateforms.get(i).toString());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
