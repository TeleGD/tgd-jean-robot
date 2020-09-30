package games.jeanRobot;
import games.jeanRobot.bonus.*;

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
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Path;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppFont;
import app.AppLoader;

import games.jeanRobot.characters.enemies.*;
import games.jeanRobot.decor.DeathBloc;
import games.jeanRobot.decor.ElevatorTrap;
import games.jeanRobot.decor.Plateform;
import games.jeanRobot.World;
import games.jeanRobot.util.Button;
import games.jeanRobot.util.Button.OnClickListener;

public class Editor extends BasicGameState{

	private int ID;

	private static final int vitesseMenu=10;

	private static final String REPERTOIRE_LEVELS = "levels";
	private ArrayList<Bonus> bonus=new ArrayList<Bonus>();
	private static ArrayList<Plateform> plateforms=new ArrayList<Plateform>();
	private ArrayList<BasicEnnemy> enemys=new ArrayList<BasicEnnemy>();
	//private Player player=new Player((int)(World.longueur*0.15f),(int)(World.hauteur*0.7f));
	private static boolean ouvrirMenu;
	private static boolean fermerMenu;
	private boolean menuRentre;

	private static boolean menuLocked=true;
	private static int tailleMenu=(int) (World.hauteur*0.2f);
	private static int yMenu=World.hauteur-tailleMenu;

	//Faudra voir pour le constructeur des plateformes (Nico)
	private Plateform[] plateformsMenu={new Plateform(5,1,3,1),new DeathBloc(5,2,3,1),new ElevatorTrap(5,3,3,1)};

	//private Enemy enemyMenu=new Enemy();
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

	private static boolean gridEnabled=true;

	private Plateform plateformEnCours;

	//pour le titre de l'editeur
	private static boolean showTitle=true;
	private TrueTypeFont fontTitle;
	private String title="LEVEL EDITOR";

	private Button enregistrer=new Button("ENREGISTRER",World.longueur-130,5);



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



	public Editor(int ID) {
		this.ID = ID;
		fontTitle = AppLoader.loadFont("/fonts/press-start-2p.ttf", AppFont.PLAIN, 50);

		for(int i=0;i<plateformsMenu.length;i++){

			plateformsMenu[i].setY(yMenu+10+40*i);
			plateformsMenu[i].setX(World.longueur*0.2);
		}


		enregistrer.setOnClickListener(new OnClickListener(){

			@Override
			public void onClicked(Button b) {
				enregistrer("niveau",0);

			}});
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {

		if(gridEnabled)renderGrid(container,game,g);

		for(int i=0;i<plateforms.size();i++){

			double oldX=plateforms.get(i).getX();
			double oldY=plateforms.get(i).getY();

			plateforms.get(i).setX(oldX-decalage);
			plateforms.get(i).setY(oldY-decalageY);

			plateforms.get(i).render(container, game, g);
			plateforms.get(i).setX(oldX);
			plateforms.get(i).setY(oldY);


		}

		renderFleches(container,game,g);
		renderMenu(container,game,g);
		enregistrer.render(container, game, g);

		if(showTitle){
			g.setFont(fontTitle);
			g.setColor(Color.white);
			g.drawString(title,(int)((World.longueur-fontTitle.getWidth(title))/2),(int)((World.hauteur-fontTitle.getHeight(title))/2));
		}

	}


	private void renderFleches(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.white);

		g.setAntiAlias(true);
		g.setLineWidth(10);

		if(flecheForwardVisible){
			Path path = new Path(World.longueur-35,2*World.hauteur/5);
			path.lineTo(World.longueur-10,World.hauteur/2+1);
			path.lineTo(World.longueur-10,World.hauteur/2-1);
			path.lineTo(World.longueur-35,3*World.hauteur/5);

			g.draw(path);
		}


		if(flecheBackVisible){
			Path path2 = new Path(35,2*World.hauteur/5);
			path2.lineTo(10,World.hauteur/2+1);
			path2.lineTo(10,World.hauteur/2-1);
			path2.lineTo(35,3*World.hauteur/5);

			g.draw(path2);

		}

		if(flecheUpVisible){
			Path path2 = new Path(6*World.longueur/11,35);
			path2.lineTo(World.longueur/2+1,10);
			path2.lineTo(World.longueur/2-1,10);
			path2.lineTo(5*World.longueur/11,35);

			g.draw(path2);

		}
		g.setLineWidth(1);

	}

	public void renderGrid(GameContainer container, StateBasedGame game, Graphics g) {
		int nbCaseHori=World.longueur/32;
		int nbCaseVert=World.hauteur/32;
		g.setColor(Color.white);
		for(int i=0;i<nbCaseHori;i++){
			for(int j=0;j<nbCaseVert;j++){
				for(int k=0;k<3;k++){
					g.fillRect(i*World.DENSITE_X-decalage%World.DENSITE_X+k*32/3, j*32-decalageY%World.DENSITE_Y, 32/5, 1);
					g.fillRect(i*World.DENSITE_X-decalage%World.DENSITE_X, j*32+k*32/3-decalageY%World.DENSITE_Y, 1, 32/5);
				}
			}
		}
	}


	public void renderMenu(GameContainer container, StateBasedGame game, Graphics g) {

		g.setColor(Color.white);
		g.drawRect(0, yMenu, World.longueur-1, 5);
		g.setColor(Color.darkGray);
		g.fillRect(0, yMenu+6, World.longueur,(World.hauteur-yMenu)-5);

		for(int i=0;i<plateformsMenu.length;i++){
			plateformsMenu[i].render(container, game, g);
		}
		if(plateformEnCours!=null){

			double oldX=plateformEnCours.getX();
			double oldY=plateformEnCours.getY();

			plateformEnCours.setX(oldX-decalage%World.DENSITE_X);
			plateformEnCours.setY(oldY-decalageY%World.DENSITE_Y);

			plateformEnCours.render(container, game, g);
			plateformEnCours.setX(oldX);
			plateformEnCours.setY(oldY);


			g.setColor(Color.red);
			g.drawRect((float)plateformEnCours.getX()-decalage%World.DENSITE_Y,(float)plateformEnCours.getY()-decalageY%World.DENSITE_Y,(float)plateformEnCours.getWidth(),(float)plateformEnCours.getHeight());
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

		g.drawString("("+decalage+"px ,"+decalageY+"px )",World.longueur-150,yMenu+60);
		g.drawString("("+decalage/World.DENSITE_X+"bloc ,"+decalageY/World.DENSITE_Y+"bloc )",World.longueur-150,yMenu+75);


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
			game.enterState(5 /* MenuEditor */, new FadeOutTransition(),
					new FadeInTransition());
		}else if(key==Input.KEY_K ){
			if(plateformEnCours!=null){
				plateformEnCours.setWidth(World.DENSITE_X*((int)(plateformEnCours.getWidth()/World.DENSITE_X)+1));
			}
		}else if(key==Input.KEY_H ){
			if(plateformEnCours!=null){
				plateformEnCours.setWidth(World.DENSITE_X*((int)(plateformEnCours.getWidth()/World.DENSITE_X)-1));
			}
		}else if(key==Input.KEY_U ){
			if(plateformEnCours!=null ){
				plateformEnCours.setHeight(World.DENSITE_Y*((int)(plateformEnCours.getHeight()/World.DENSITE_Y)+1));
			}
		}else if(key==Input.KEY_J ){
			if(plateformEnCours!=null){
				plateformEnCours.setHeight(World.DENSITE_Y*((int)(plateformEnCours.getHeight()/World.DENSITE_Y)-1));
			}
		}else if(key==Input.KEY_W){
			if(plateforms.size()>0){
				plateforms.remove(plateforms.size()-1);
			}

		}


	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		time+=delta;
		if(time>4000 && time<4500 ){
			if(!menuRentre)fermerMenu=true;
			showTitle=false;
			flecheForwardVisible=false;
			flecheBackVisible=false;
			flecheUpVisible=false;

		}

		if(fermerMenu && !menuRentre && !menuLocked){
			if(yMenu<World.hauteur)yMenu+=vitesseMenu;
			else{
				fermerMenu=false;
				menuRentre=true;
			}
		}

		if(ouvrirMenu && menuRentre){
			if(yMenu>World.hauteur-tailleMenu)yMenu-=vitesseMenu;
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
		if(decalXEnCours){
			decalage+=direction*1;
		}
		if(decalYEnCours){
			decalageY+=directionY*1;
		}
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) {
		game=arg1;
		container=arg0;

	}

	public void mouseDragged(int oldx,int  oldy, int newx,int  newy){
		enregistrer.mouseDragged(newx,newy);
		if(newy<World.hauteur-tailleMenu && !menuRentre){
			fermerMenu=true;
		}

		System.out.println("oldX="+oldx+"  newx="+newx);

		mouseMoved(oldx,oldy,newx,newy);
		mouseReleased(0,newx,newy);


	}
	public void mouseMoved(int oldx,int  oldy, int newx,int  newy){
		enregistrer.mouseMoved(newx,newy);


		decalXEnCours=false;
		decalYEnCours=false;

		if(newy<World.hauteur-tailleMenu && !menuRentre){
			fermerMenu=true;
		}

		if(newy>World.hauteur-50 && menuRentre){
			ouvrirMenu=true;
		}
		if(plateformEnCours!=null && !isTherePlatform(newx,newy)){
			plateformEnCours.setPosition((int)((newx-plateformEnCours.getWidth()/2)/World.DENSITE_X),(int) ((newy-plateformEnCours.getHeight()/2)/World.DENSITE_Y));
		}

		if(newx>World.longueur-50){
			flecheForwardVisible=true;
			time=0;

			decalXEnCours=true;
			direction=1*(newx-(World.longueur-50))/2;
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

		if(newy>World.hauteur-50){
			flecheDownVisible=true;
			time=0;

			decalYEnCours=true;
			directionY=1*(newy-(World.hauteur-50))/2;
		}

	}
	private boolean isTherePlatform(int newx, int newy) {

		newx=(int)((newx-plateformEnCours.getWidth()/2)/World.DENSITE_X)*World.DENSITE_X;
		newy=(int)((newy-plateformEnCours.getHeight()/2)/World.DENSITE_Y)*World.DENSITE_Y;

		int width=(int) plateformEnCours.getWidth();
		int height=(int) plateformEnCours.getHeight();

		for(int i=0;i<plateforms.size();i++){
			for(int k=0;k<width/World.DENSITE_X;k++){
				for(int l=0;l<height/World.DENSITE_Y;l++){
					if(newx+k*World.DENSITE_X>=plateforms.get(i).getX()-decalage
					&& newx+k*World.DENSITE_X<plateforms.get(i).getX()-decalage+plateforms.get(i).getWidth()
					&& newy+l*World.DENSITE_Y>=plateforms.get(i).getY()-decalageY
				    && newy+l*World.DENSITE_Y<plateforms.get(i).getY()-decalageY+plateforms.get(i).getHeight())
							{
								return true;
							}
				}
			}

		}
		return false;
	}

	public void mouseReleased(int button, int x,int y){
		if(plateformEnCours!=null && enregistrer.containsPoint(x, y)){
			enregistrer.mouseReleased(button,x,y);
			return;
		}
		if(button==1){
			plateformEnCours=null;
			return;
		}

		boolean changePlateforme=false;
		for(Plateform p:plateformsMenu){
			if(p.containsPoint(x, y)){
				plateformEnCours=p.copy();
				changePlateforme=true;
			}
		}

		if(!changePlateforme && plateformEnCours!=null){
			createPlateforme();
		}



	}

	private void createPlateforme() {
		double oldX=plateformEnCours.getX();
		plateformEnCours.setPosition((int)((plateformEnCours.getX()+decalage)/World.DENSITE_X),(int) ((plateformEnCours.getY()+decalageY)/World.DENSITE_Y));
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
		yMenu=World.hauteur-tailleMenu;

	}

	public boolean enregistrer(String nom,int version){

		File f=new File(REPERTOIRE_LEVELS+File.separator+nom);

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

	public static void loadLevel(String niveau) {
		File f=new File(niveau);
		try {
			BufferedReader br=new BufferedReader(new FileReader("res"+File.separator+"data"+File.separator+"jeanRobot"+File.separator+"levels"+File.separator+niveau));
			String ligne;
			while((ligne=br.readLine())!=null){
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
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
