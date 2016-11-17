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
import org.newdawn.slick.geom.Path;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import fr.characters.enemies.*;
import fr.decor.Plateform;
import fr.game.Game;
import fr.menus.Mainmenu;
import fr.menus.MenuEditor;
import fr.util.Button;
import fr.util.Button.OnClickListener;

public class Editor extends BasicGameState{

	private static final int vitesseMenu=10;
	
	public static final int ID = 1;

	private static final String REPERTOIRE_LEVELS = "levels";
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

	private Button enregistrer=new Button("ENREGISTRER",Game.longueur-130,5);

	private int decalage=0;


	private boolean flecheBackVisible=true;
	private boolean flecheForwardVisible=true;
			
	public Editor(){
		Font titreFont;
		try {
			titreFont =Font.createFont(java.awt.Font.TRUETYPE_FONT,ResourceLoader.getResourceAsStream("font/PressStart2P.ttf"));
			fontTitle = new TrueTypeFont(titreFont.deriveFont(java.awt.Font.PLAIN, 50.f),false);

		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		plateformMenu.setY(yMenu+50);
		plateformMenu.setX(Game.longueur*0.2);
		
		enregistrer.setOnClickListener(new OnClickListener(){

			@Override
			public void onClicked(Button b) {
				enregistrer("niveau1");
				
			}});
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		
		
		if(gridEnabled)renderGrid(container,game,g);

		for(int i=0;i<plateforms.size();i++){
			plateforms.get(i).render(container, game, g);
		}

		renderFleches(container,game,g);
		renderMenu(container,game,g);
		enregistrer.render(container, game, g);
		
		if(showTitle){
			g.setFont(fontTitle);
			g.setColor(Color.white);
			g.drawString(title,(int)((Game.longueur-fontTitle.getWidth(title))/2),(int)((Game.hauteur-fontTitle.getHeight(title))/2));
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
		g.setLineWidth(1);
		
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
	
		if(plateformEnCours!=null){
			plateformEnCours.render(container, game, g);
			g.setColor(Color.red);
			g.drawRect((float)plateformEnCours.getX(),(float)plateformEnCours.getY(),(float)plateformEnCours.getWidth(),(float)plateformEnCours.getHeight());
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
		enregistrer.mouseDragged(newx,newy);
		if(newy<Game.hauteur-tailleMenu && !menuRentre){
			fermerMenu=true;
		}
		
		
	}
	public void mouseMoved(int oldx,int  oldy, int newx,int  newy){
		enregistrer.mouseMoved(newx,newy);
		
		if(newy<Game.hauteur-tailleMenu && !menuRentre){
			fermerMenu=true;
		}
		
		if(newy>Game.hauteur-50 && menuRentre){
			ouvrirMenu=true;
		}
		if(plateformEnCours!=null && !isTherePlatform(newx,newy)){
			plateformEnCours.setPosition((int)((newx-plateformEnCours.getWidth()/2)/Game.DENSITE_X),(int) ((newy-plateformEnCours.getHeight()/2)/Game.DENSITE_Y));
		}
		
		if(newx>Game.longueur-40){
			flecheForwardVisible=true;
			time=0;
		}
		if(newx<40 && decalage>0){
			flecheBackVisible=true;
			time=0;
		}
		
		
	}
	private boolean isTherePlatform(int newx, int newy) {	
		
		newx=(int)((newx-plateformEnCours.getWidth()/2)/Game.DENSITE_X)*Game.DENSITE_X;
		newy=(int)((newy-plateformEnCours.getHeight()/2)/Game.DENSITE_Y)*Game.DENSITE_Y;
		
		int width=(int) plateformEnCours.getWidth();
		int height=(int) plateformEnCours.getHeight();
		
		for(int i=0;i<plateforms.size();i++){
			for(int k=0;k<width/Game.DENSITE_X;k++){
				for(int l=0;l<height/Game.DENSITE_Y;l++){
					if(newx+k*Game.DENSITE_X>=plateforms.get(i).getX()
					&& newx+k*Game.DENSITE_X<plateforms.get(i).getX()+plateforms.get(i).getWidth()
					&& newy+l*Game.DENSITE_Y>=plateforms.get(i).getY()
				    && newy+l*Game.DENSITE_Y<plateforms.get(i).getY()+plateforms.get(i).getHeight())
							{
								return true;
							}
				}
			}
			
		}
		return false;
	}

	public void mouseReleased(int button, int x,int y){
		if(plateformEnCours!=null){
			enregistrer.mouseReleased(button,x,y);
		}
		if(button==1){
			plateformEnCours=null;
			return;
		}
		
		if(plateformMenu.containsPoint(x, y)){
			plateformEnCours=new Plateform(plateformMenu);
		}else if(plateformEnCours!=null){
			plateforms.add(plateformEnCours);
			plateformEnCours=new Plateform(plateformMenu);
		}
		
		
	}
	
	@Override
	public void mousePressed(int button, int x,int y){
		enregistrer.moussePressed(button,x,y);
	}
	
	@Override
	public void mouseWheelMoved(int val){
		System.out.println("decalage="+val);
		decalage=((int)(val/Game.DENSITE_X))*Game.DENSITE_X;
		
		decalerEcran();
	}
	
	private void decalerEcran() {
		for(int i=0;i<plateforms.size();i++){
			plateforms.get(i).setX(plateforms.get(i).getX()-decalage);
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
		File f=new File(REPERTOIRE_LEVELS+File.separator+nom);
		if(f.exists()){
			//enregistrer(nom+"2");
			return false;
		}
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(f));
			bw.write("// PLATEFORMES");
			bw.newLine();
			
			for(int i=0;i<plateforms.size();i++){
				bw.write(plateforms.get(i).parseString());
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
