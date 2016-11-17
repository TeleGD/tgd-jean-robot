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

	public Menu(){
		setFontTitrePrincipal("font/PressStart2P.ttf",Font.BOLD,40,false);
		setFontTitreSecondaire("Kalinga",Font.BOLD,14,true);
		setFontItem("Kalinga",Font.BOLD,14,true);
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
	}

	public void renderSelectionItem(GameContainer arg0, StateBasedGame arg1, Graphics g,int position) {
		if(items==null)return;
		if(enableClignote){
			if((System.currentTimeMillis()-time)%(2*tempsClignote)<=tempsClignote)g.setColor(Color.white);
			else g.setColor(couleurClignote);
		}else{
			g.setColor(couleurClignote);
		}
		g.drawString(">>", 535, 360 + 30 * selection);
		g.drawString("<<", 563+fontItem.getWidth(items[position]), 360 + 30 * position);
		
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
		g.drawString(titreSecondaire, 550, 320);		
	}

	public void renderMenusItems(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		if(items==null)return;
		for (int i = 0; i < items.length; i++) {
			g.setFont(fontItem);
			g.drawString(this.items[i], 560, 360 + 30 * i);
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
		case Input.KEY_DOWN:
			if (selection < items.length - 1)
				selection++;
			else
				selection = 0;
			
			onOptionItemFocusedChanged(selection);
			break;
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
	}

	public void setFontTitrePrincipal(String name, int type, int size, boolean isSystemFont) {
		fontTitrePrincipal=chargerFont(name,type,size,isSystemFont);
	}
	
	public void setFontTitreSecondaire(String name, int type, int size, boolean isSystemFont) {
		fontTitreSecondaire=chargerFont(name,type,size,isSystemFont);	
	}
	public void setFontItem(String name, int type, int size, boolean isSystemFont) {
		fontItem=chargerFont(name,type,size,isSystemFont);
		
	}

	private TrueTypeFont chargerFont(String name, int type, int size, boolean isSystemFont) {
		
		if(isSystemFont){
			Font fontTemp = new Font(name, type, size);
			return new TrueTypeFont(fontTemp, false);
		}else{
			Font fontTemp = null;
			try {
				fontTemp = Font.createFont(java.awt.Font.TRUETYPE_FONT,
					       ResourceLoader.getResourceAsStream("font/PressStart2P.ttf"));
			} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new TrueTypeFont(fontTemp.deriveFont(java.awt.Font.PLAIN, 40.f),false);
		
		}
		
		
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
