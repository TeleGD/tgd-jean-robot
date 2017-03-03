package fr.menus;

import java.awt.FontFormatException;
import java.io.IOException;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import fr.game.Game;
import fr.util.FontUtils;


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
		setFontTitrePrincipal("font/PressStart2P.ttf",Font.BOLD,40,false);
		setFontTitreSecondaire("Kalinga",Font.BOLD,24,true);
		setFontItem("Kalinga",Font.BOLD,14,true);
		
		fontConfirmText=FontUtils.chargerFont("font/PressStart2P.ttf",Font.PLAIN,20,false);
	}
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		time=System.currentTimeMillis();
		this.container = container;
		this.game = game;
		container.setShowFPS(false);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		
		renderTitrePrincipal(arg0,arg1,g);
		renderTitreSecondaire(arg0,arg1,g);
		renderMenusItems(arg0,arg1,g);
		renderSelectionItem(arg0,arg1,g,selection);
		
		g.setColor(Color.white);
		g.drawRect(Game.longueur/2-300, Game.hauteur/2-130, 600,37);

		//g.drawRect(Game.longueur/2-300, Game.hauteur-200, 600,37);
		
		//g.setFont(fontConfirmText);
		//g.drawString(CONFIRM_TEXT, Game.longueur/2-fontConfirmText.getWidth(CONFIRM_TEXT)/2, 530);

	}

	public void renderSelectionItem(GameContainer arg0, StateBasedGame arg1, Graphics g,int position) {
		if(items==null)return;
		if(enableClignote){
			if((System.currentTimeMillis()-time)%(2*tempsClignote)<=tempsClignote)g.setColor(Color.white);
			else g.setColor(couleurClignote);
		}else{
			g.setColor(couleurClignote);
		}
		g.drawString(">>", Game.longueur/2-fontItem.getWidth(items[indexItemPlusGrand])/2-35, 360 + 30 * selection);
		g.drawString("<<", Game.longueur/2-fontItem.getWidth(items[indexItemPlusGrand])/2+fontItem.getWidth(items[position])+10, 360 + 30 * position);
		
	}

	public void renderTitrePrincipal(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		g.setColor(Color.red);
		g.setFont(fontTitrePrincipal);
		g.drawString(titrePrincipal,(Game.longueur-fontTitrePrincipal.getWidth(titrePrincipal))/2 , 120);
		g.setColor(Color.white);
		g.setFont(fontTitrePrincipal);
		g.drawString(titrePrincipal,(Game.longueur-fontTitrePrincipal.getWidth(titrePrincipal))/2+4 , 122);
		
	}

	public void renderTitreSecondaire(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		g.setFont(fontTitreSecondaire);
		g.drawString(titreSecondaire, Game.longueur/2-fontTitreSecondaire.getWidth(titreSecondaire)/2, 232);		
	}

	public void renderMenusItems(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		if(items==null)return;

		g.setColor(Color.white);
		
		for (int i = 0; i < items.length; i++) {
			g.setFont(fontItem);
			g.drawString(this.items[i], Game.longueur/2-fontItem.getWidth(items[indexItemPlusGrand])/2, 360 + 30 * i);
		}
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {

	
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void keyPressed(int key, char c) {
		//time=System.currentTimeMillis();
		switch (key) {
		case Input.KEY_NUMPAD2:
			if (selection < items.length - 1)
				selection++;
			else
				selection = 0;
			
			onOptionItemFocusedChanged(selection);
			break;
		case Input.KEY_NUMPAD8:
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

	public void setFontTitrePrincipal(String name, int type, int size, boolean isSystemFont) {
		fontTitrePrincipal=FontUtils.chargerFont(name,type,size,isSystemFont);
	}
	
	public void setFontTitreSecondaire(String name, int type, int size, boolean isSystemFont) {
		fontTitreSecondaire=FontUtils.chargerFont(name,type,size,isSystemFont);	
	}
	public void setFontItem(String name, int type, int size, boolean isSystemFont) {
		fontItem=FontUtils.chargerFont(name,type,size,isSystemFont);
		
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
