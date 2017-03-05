package fr.jerome;
import fr.bonus.*;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Path;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import fr.characters.enemies.*;
import fr.decor.DeathBloc;
import fr.decor.ElevatorTrap;
import fr.decor.Plateform;
import fr.game.Game;
import fr.menus.Mainmenu;
import fr.menus.MenuEditor;
import fr.util.Button;
import fr.util.Button.OnClickListener;
import fr.util.FontUtils;
import fr.util.LevelUtils;

public class Editor extends BasicGameState{

	private static final int vitesseMenu=10;
	
	public static final int ID = 1;

	private ArrayList<Bonus> bonus=new ArrayList<Bonus>();
	private static ArrayList<Plateform> plateforms=new ArrayList<Plateform>();
	private ArrayList<Ennemy> enemys=new ArrayList<Ennemy>();
	
	//private Player player=new Player((int)(Game.longueur*0.15f),(int)(Game.hauteur*0.7f));
	
	private static boolean ouvrirMenu;
	private static boolean fermerMenu;
	private boolean menuRentre;

	private static boolean menuLocked=true;
	private static int tailleMenu=(int) (Game.hauteur*0.2f);
	private static int yMenu=Game.hauteur-tailleMenu;
	
	//Faudra voir pour le constructeur des plateformes (Nico)
	private Plateform[] plateformsMenu={new Plateform(5,1,3,1),new DeathBloc(5,2,3,1),new ElevatorTrap(5,3,3,1)};
	
	private Ennemy[] enemysMenu={new EnnemyShooter(new BasicEnnemy(12*Game.DENSITE_X,1*Game.DENSITE_Y)),new EnemyVolant(new BasicEnnemy(12*Game.DENSITE_X,1*Game.DENSITE_Y))};

	//private Enemy enemyMenu=new Enemy();
	
	private static StateBasedGame game;
	private static long time;

	private static boolean gridEnabled=true;
	
	private Plateform plateformEnCours;
	private Ennemy enemyEnCours;
	
	//pour le titre de l'editeur
	private static boolean showTitle=true;
	private TrueTypeFont fontTitle;
	

	private String title="LEVEL EDITOR";

	private Button enregistrer;



	private boolean flecheBackVisible=true;
	private boolean flecheForwardVisible=true;
	private boolean flecheUpVisible=true;
	private boolean flecheDownVisible=true;

	private boolean decalXEnCours=false;
	private boolean decalYEnCours=false;

	private int direction=1;
	private int directionY=1;
	
	private int decalage=0;
	private int decalageY=0;

	
	public Editor(){
		
	}
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
	    super.enter(container, game);
	     
	    fontTitle = FontUtils.loadCustomFont("PressStart2P.ttf", Font.PLAIN, 50);
	
		for(int i=0;i<plateformsMenu.length;i++){
			plateformsMenu[i].setY(yMenu+10+40*i);
			plateformsMenu[i].setX(Game.longueur*0.2);
		}
		for(int i=0;i<enemysMenu.length;i++){
			enemysMenu[i].setY(yMenu+10+40*i);
			if(enemysMenu[i] instanceof EnnemyShooter){
				((EnnemyShooter)enemysMenu[i]).setIsShooting(false);
			}
		}
			
		enregistrer=new Button("ENREGISTRER",Game.longueur-130,5);
		enregistrer.setOnClickListener(new OnClickListener(){

			@Override
			public void onClicked(Button b) {
				Level level=new Level();
				level.setEnemys(enemys);
				level.setPlateforms(plateforms);
				LevelUtils.saveLevel(level,"niveau",true);
			}});
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		if(gridEnabled)renderGrid(container,game,g);
		
		g.translate(-decalage, -decalageY);
		renderPlateforms(container,game,g);
		g.translate(decalage, decalageY);
		renderFleches(container,game,g);
		renderMenu(container,game,g);
		
		enregistrer.render(container, game, g);
		
		if(showTitle){
			g.setFont(fontTitle);
			g.setColor(Color.white);
			g.drawString(title,(int)((Game.longueur-fontTitle.getWidth(title))/2),(int)((Game.hauteur-fontTitle.getHeight(title))/2));
		}
		
		
	}
	
	
	private void renderPlateforms(GameContainer container, StateBasedGame game2, Graphics g) throws SlickException {
		
		for(int i=0;i<plateforms.size();i++){
			plateforms.get(i).render(container, game, g);
		}		
		for(int i=0;i<enemys.size();i++){
			enemys.get(i).render(container, game, g);
		}		
		if(plateformEnCours!=null){
			plateformEnCours.render(container, game, g);

			g.setColor(Color.red);
			g.drawRect((float)plateformEnCours.getX(),(float)plateformEnCours.getY(),(float)plateformEnCours.getWidth(),(float)plateformEnCours.getHeight());
		}else if(enemyEnCours!=null){
			enemyEnCours.render(container, game, g);
		}
	}
	private void renderFleches(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.white);

		g.setAntiAlias(true);
		g.setLineWidth(10);
		
		if(flecheForwardVisible){
			Path path = new Path(Game.longueur-35,2*Game.hauteur/5);
			path.lineTo(Game.longueur-10,Game.hauteur/2+1);
			path.lineTo(Game.longueur-10,Game.hauteur/2-1);
			path.lineTo(Game.longueur-35,3*Game.hauteur/5);

			g.draw(path);
		}
		

		if(flecheBackVisible){
			Path path2 = new Path(35,2*Game.hauteur/5);
			path2.lineTo(10,Game.hauteur/2+1);
			path2.lineTo(10,Game.hauteur/2-1);
			path2.lineTo(35,3*Game.hauteur/5);
			
			g.draw(path2);

		}
		
		if(flecheUpVisible){
			Path path2 = new Path(6*Game.longueur/11,35);
			path2.lineTo(Game.longueur/2+1,10);
			path2.lineTo(Game.longueur/2-1,10);
			path2.lineTo(5*Game.longueur/11,35);

			g.draw(path2);

		}
		g.setLineWidth(1);
		
	}

	public void renderGrid(GameContainer container, StateBasedGame game, Graphics g) {
		int nbCaseHori=Game.longueur/32;
		int nbCaseVert=Game.hauteur/32;
		g.setColor(Color.white);
		for(int i=0;i<nbCaseHori;i++){
			for(int j=0;j<nbCaseVert;j++){
				for(int k=0;k<3;k++){
					g.fillRect(i*Game.DENSITE_X-decalage%Game.DENSITE_X+k*32/3, j*32-decalageY%Game.DENSITE_Y, 32/5, 1);
					g.fillRect(i*Game.DENSITE_X-decalage%Game.DENSITE_X, j*32+k*32/3-decalageY%Game.DENSITE_Y, 1, 32/5);
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

		for(int i=0;i<plateformsMenu.length;i++){
			plateformsMenu[i].render(container, game, g);
		}
		for(int i=0;i<enemysMenu.length;i++){
			enemysMenu[i].render(container, game, g);
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
		

		g.setColor(Color.white);
		g.drawString("Press Z to undo",15,yMenu+60);
		g.drawString("Press U to Height++",15,yMenu+75);
		g.drawString("Press J to Height--",15,yMenu+90);
		g.drawString("Press H to Width--",15,yMenu+105);
		g.drawString("Press K to Width++",15,yMenu+120);
		
		g.drawString("("+decalage+"px ,"+decalageY+"px )",Game.longueur-150,yMenu+60);
		g.drawString("("+decalage/Game.DENSITE_X+"bloc ,"+decalageY/Game.DENSITE_Y+"bloc )",Game.longueur-150,yMenu+75);

		
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
			game.enterState(MenuEditor.ID, new FadeOutTransition(),
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
		}else if(key==Input.KEY_W){
			if(plateforms.size()>0){
				plateforms.remove(plateforms.size()-1);
			}
			
		}
		
	    
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		time+=delta;
		if(time>4000 && time<4500 ){
			if(!menuRentre)fermerMenu=true;
			showTitle=false;
			flecheForwardVisible=false;
			flecheBackVisible=false;
			flecheUpVisible=false;

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
		for(int i=0;i<plateformsMenu.length;i++){
			plateformsMenu[i].setY(yMenu+10+40*i);
			plateformsMenu[i].update(container, game, delta);
		}
		for(int i=0;i<enemysMenu.length;i++){
			enemysMenu[i].setY(yMenu+10+40*i);
			enemysMenu[i].update(container, game, delta);
		}
		if(decalXEnCours){
			decalage+=direction*1;
		}
		if(decalYEnCours){
			decalageY+=directionY*1;
		}
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		game=arg1;		
	}

	public void mouseDragged(int oldx,int  oldy, int newx,int  newy){
		enregistrer.mouseDragged(newx,newy);
		if(newy<Game.hauteur-tailleMenu && !menuRentre){
			fermerMenu=true;
		}
		
		mouseMoved(oldx,oldy,newx,newy);
		mouseReleased(0,newx,newy);
		
		
	}
	public void mouseMoved(int oldx,int  oldy, int newx,int  newy){
		enregistrer.mouseMoved(newx,newy);
		
		
		decalXEnCours=false;
		decalYEnCours=false;

		if(newy<Game.hauteur-tailleMenu && !menuRentre){
			fermerMenu=true;
		}
			
		if(newy>Game.hauteur-50 && menuRentre){
			ouvrirMenu=true;
		}
		if(!isTherePlatform(newx,newy)){
			if(plateformEnCours!=null){
				plateformEnCours.setPosition((int)((newx-plateformEnCours.getWidth()/2+decalage)/Game.DENSITE_X),(int) ((newy-plateformEnCours.getHeight()/2+decalageY)/Game.DENSITE_Y));
			}else if(enemyEnCours!=null){
				enemyEnCours.setX((int)((newx-enemyEnCours.getWidth()/2+decalage)/Game.DENSITE_X)*Game.DENSITE_X);
				enemyEnCours.setY((int) ((newy-enemyEnCours.getHeight()/2+decalageY)/Game.DENSITE_Y)*Game.DENSITE_Y);
			}
		}
		
		
		if(newx>Game.longueur-50){
			flecheForwardVisible=true;
			time=0;
			
			decalXEnCours=true;
			direction=1*(newx-(Game.longueur-50))/2;
		}
		if(newx<50){
			flecheBackVisible=true;
			time=0;
			
			decalXEnCours=true;
			direction=-1*(50-newx)/2;
		}
		if(decalage<0)decalage=0;

		if(newy<50){
			flecheUpVisible=true;
			time=0;
			decalYEnCours=true;
			directionY=-1*(50-newy)/2;
		}
		
		if(newy>Game.hauteur-50){
			flecheDownVisible=true;
			time=0;
			
			decalYEnCours=true;
			directionY=1*(newy-(Game.hauteur-50))/2;
		}
		
	}

	private boolean isTherePlatform(int newx, int newy) {
		
		for(int i=0;i<plateforms.size();i++){
			if(plateforms.get(i).containsPoint(newx+decalage, newy+decalageY)){
				return true;
			}
			
		}
		return false;
	}
/*
 * private boolean isTherePlatform(int newx, int newy) {	
		newx=(int)((newx-plateformEnCours.getWidth()/2)/Game.DENSITE_X)*Game.DENSITE_X;
		newy=(int)((newy-plateformEnCours.getHeight()/2)/Game.DENSITE_Y)*Game.DENSITE_Y;
		
		int width=(int) plateformEnCours.getWidth();
		int height=(int) plateformEnCours.getHeight();
		
		for(int i=0;i<plateforms.size();i++){
			for(int k=0;k<width/Game.DENSITE_X;k++){
				for(int l=0;l<height/Game.DENSITE_Y;l++){
					if(newx+k*Game.DENSITE_X>=plateforms.get(i).getX()-decalage
					&& newx+k*Game.DENSITE_X<plateforms.get(i).getX()-decalage+plateforms.get(i).getWidth()
					&& newy+l*Game.DENSITE_Y>=plateforms.get(i).getY()-decalageY
				    && newy+l*Game.DENSITE_Y<plateforms.get(i).getY()-decalageY+plateforms.get(i).getHeight())
							{
								return true;
							}
				}
			}
			
		}
		return false;
	}

 */
	public void mouseReleased(int button, int x,int y){
		if(plateformEnCours!=null && enregistrer.containsPoint(x, y)){
			enregistrer.mouseReleased(button,x,y);
			return;
		}
		
		if(button==1){
			//clic droit
			plateformEnCours=null;
			return;
		}
		
		boolean plateformMenuClicked=false;
		for(Plateform p:plateformsMenu){
			if(p.containsPoint(x, y)){
				plateformEnCours=p.copy();
				plateformMenuClicked=true;
			}
		}
		
		boolean ennemyMenuClicked=false;
		for(Ennemy enemy:enemysMenu){
			if(enemy.containsPoint(x, y)){
				ennemyMenuClicked=true;
				enemyEnCours=enemy.copy();
			}
		}
		
		if(!plateformMenuClicked && plateformEnCours!=null){
			createPlateforme();
		}else if(!ennemyMenuClicked && enemyEnCours!=null){
			createEnemy();
		}
		
		
		
	}
	
	private void createEnemy() {
		double oldX=enemyEnCours.getX();
		enemyEnCours.setX((int)((enemyEnCours.getX())/Game.DENSITE_X)*Game.DENSITE_X);
		enemyEnCours.setY((int) ((enemyEnCours.getY())/Game.DENSITE_Y)*Game.DENSITE_Y);
		enemys.add(enemyEnCours);
		
		enemyEnCours=enemyEnCours.copy();
		enemyEnCours.setX(oldX);			
	}
	private void createPlateforme() {
		double oldX=plateformEnCours.getX();
		plateformEnCours.setPosition((int)((plateformEnCours.getX())/Game.DENSITE_X),(int) ((plateformEnCours.getY())/Game.DENSITE_Y));
		plateforms.add(plateformEnCours);
		
		plateformEnCours=plateformEnCours.copy();
		plateformEnCours.setX(oldX);		
	}

	@Override
	public void mousePressed(int button, int x,int y){
		enregistrer.moussePressed(button,x,y);
		showTitle=false;
	}

	

	public static void reset(){
		time=0;
		showTitle=true;
		menuLocked=true;
		gridEnabled=true;
		ouvrirMenu=false;
		fermerMenu=false;
		yMenu=Game.hauteur-tailleMenu;
		
	}
	@Override
	public int getID() {
		return ID;
	}
	
	public static void setLevel(Level loadLevel) {
		plateforms=loadLevel.getPlateforms();
	}

	
}
