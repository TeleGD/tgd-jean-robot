package games.jeanRobot.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import app.AppFont;
import app.AppLoader;

import games.jeanRobot.World;

/**
 *
 * Pour faire un menu c'est simple,
 * il suffit de faire une classe qui herite de celle la et de
 * reseiner via les setters, les params TitrePrincipal, titreSecondaire
 * et les items. Et C'est tout
 * Vous recevrez l'index de l'item selectionné dans la méthode
 * onOptionItemSelected.
 *
		super.setTitrePrincipal("TELE-ARCADE DESIGN");
		super.setTitreSecondaire("Menu Principal");
		super.setItems("Jouer","Editeur", "Quitter");

		super.setEnableClignote(true);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
 *
 */
public abstract class Menu extends BasicGameState {



	private String titrePrincipal="";
	private String titreSecondaire="";
	private String[] items;

	private TrueTypeFont fontTitrePrincipal;
	private TrueTypeFont fontTitreSecondaire;
	private TrueTypeFont fontItem;

	private int selection;
	protected long tempsClignote=400;
	protected Color couleurClignote=Color.red;
	private boolean enableClignote=false;

	protected GameContainer container;
	protected StateBasedGame game;
	protected long time;

	protected TrueTypeFont fontConfirmText;

	private int indexItemPlusGrand;

	public Menu(){
		setFontTitrePrincipal("/fonts/press-start-2p.ttf",AppFont.BOLD,40);
		setFontTitreSecondaire("Kalinga",AppFont.BOLD,24); // TODO: trouver une fonte équivalente
		setFontItem("Kalinga",AppFont.BOLD,14); // TODO: trouver une fonte équivalente

		fontConfirmText=AppLoader.loadFont("/fonts/press-start-2p.ttf", AppFont.PLAIN, 20);
	}
	@Override
	public void init(GameContainer container, StateBasedGame game) {
		time=System.currentTimeMillis();
		this.container = container;
		this.game = game;
		container.setShowFPS(false);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) {

		renderTitrePrincipal(arg0,arg1,g);
		renderTitreSecondaire(arg0,arg1,g);
		renderMenusItems(arg0,arg1,g);
		renderSelectionItem(arg0,arg1,g,selection);

		g.setColor(Color.white);
		g.drawRect(World.longueur/2-300, World.hauteur/2-130, 600,37);

		//g.drawRect(World.longueur/2-300, World.hauteur-200, 600,37);

		//g.setFont(fontConfirmText);
		//g.drawString(CONFIRM_TEXT, World.longueur/2-fontConfirmText.getWidth(CONFIRM_TEXT)/2, 530);

	}

	public void renderSelectionItem(GameContainer arg0, StateBasedGame arg1, Graphics g,int position) {
		if(items==null)return;
		if(enableClignote){
			if((System.currentTimeMillis()-time)%(2*tempsClignote)<=tempsClignote)g.setColor(Color.white);
			else g.setColor(couleurClignote);
		}else{
			g.setColor(couleurClignote);
		}
		g.drawString(">>", World.longueur/2-fontItem.getWidth(items[indexItemPlusGrand])/2-35, 360 + 30 * selection);
		g.drawString("<<", World.longueur/2-fontItem.getWidth(items[indexItemPlusGrand])/2+fontItem.getWidth(items[position])+10, 360 + 30 * position);

	}

	public void renderTitrePrincipal(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		g.setColor(Color.red);
		g.setFont(fontTitrePrincipal);
		g.drawString(titrePrincipal,(World.longueur-fontTitrePrincipal.getWidth(titrePrincipal))/2 , 120);
		g.setColor(Color.white);
		g.setFont(fontTitrePrincipal);
		g.drawString(titrePrincipal,(World.longueur-fontTitrePrincipal.getWidth(titrePrincipal))/2+4 , 122);

	}

	public void renderTitreSecondaire(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		g.setFont(fontTitreSecondaire);
		g.drawString(titreSecondaire, World.longueur/2-fontTitreSecondaire.getWidth(titreSecondaire)/2, 232);
	}

	public void renderMenusItems(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		if(items==null)return;

		g.setColor(Color.white);

		for (int i = 0; i < items.length; i++) {
			g.setFont(fontItem);
			g.drawString(this.items[i], World.longueur/2-fontItem.getWidth(items[indexItemPlusGrand])/2, 360 + 30 * i);
		}

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {


	}

	public abstract int getID();

	@Override
	public void keyPressed(int key, char c) {
		//time=System.currentTimeMillis();
		switch (key) {
		//case Input.KEY_NUMPAD2:
		case Input.KEY_DOWN:
			if (selection < items.length - 1)
				selection++;
			else
				selection = 0;

			onOptionItemFocusedChanged(selection);
			break;
		//case Input.KEY_NUMPAD8:
		case Input.KEY_UP:
			if (selection > 0)
				selection--;
			else
				selection = items.length - 1;

			onOptionItemFocusedChanged(selection);
			break;
		case Input.KEY_ENTER:
			onOptionItemSelected(selection);
			break;

		case Input.KEY_ESCAPE:
			//exit();
			break;
		}
	}


	public abstract void onOptionItemFocusedChanged(int position);
	public abstract void onOptionItemSelected(int position);

	public String getTitrePrincipal() {
		return titrePrincipal;
	}

	public void setTitrePrincipal(String titrePrincipal) {
		this.titrePrincipal = titrePrincipal;
	}

	public String getTitreSecondaire() {
		return titreSecondaire;
	}

	public void setTitreSecondaire(String titreSecondaire) {
		this.titreSecondaire = titreSecondaire;
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String... items) {
		this.items = items;

		indexItemPlusGrand=0;
		for(int i=0;i<items.length;i++){
			if(items[indexItemPlusGrand].length()<items[i].length()){
				indexItemPlusGrand=i;
			}
		}
	}

	public void setFontTitrePrincipal(String name, int type, int size) {
		fontTitrePrincipal=AppLoader.loadFont(name,type,size);
	}

	public void setFontTitreSecondaire(String name, int type, int size) {
		fontTitreSecondaire=AppLoader.loadFont(name,type,size);
	}
	public void setFontItem(String name, int type, int size) {
		fontItem=AppLoader.loadFont(name,type,size);

	}


	public void setEnableClignote(boolean b) {
		enableClignote=b;
	}

	public void setTempsClignote(long timeEnMillisecond){
		this.tempsClignote=timeEnMillisecond;
	}

	public void setCouleurClignote(Color coul){
		this.couleurClignote=coul;
	}



}
