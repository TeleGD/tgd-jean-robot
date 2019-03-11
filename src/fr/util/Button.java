package fr.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;


public class Button extends Entity{

	private boolean isPressed,isEntered;
	private String titre;

	private Color couleurNormal=Color.white;
	private Color couleurSelectionne=Color.red;
	private Color couleurText=couleurNormal;
	private TrueTypeFont font;
	private OnClickListener onClickListener;

	public Button(String titre, int x, int y) {
		this.x=x;
		this.y=y;
		this.titre=titre;
		Font titre1Font = new Font("Arial", Font.BOLD, 14);
		font = new TrueTypeFont(titre1Font, false);

		this.width=font.getWidth(titre)+20;
		this.height=font.getHeight(titre)+10;

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		if(!isPressed)g.setColor(Color.white);
		else g.setColor(Color.red);

		if(isEntered)g.drawRect((float)x, (float)y, (float)width, (float)height);

		g.setFont(font);
		g.setColor(couleurText);
		g.drawString(titre, (float)(x+width/2-font.getWidth(titre)/2), (float) (y+height/2-font.getHeight(titre)/2));
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub

	}

	public void moussePressed(int button, int x, int y) {
		if(containsPoint(x,y)){
			isPressed=true;
			couleurText=couleurSelectionne;
		}else{
			isPressed=false;
			couleurText=couleurNormal;
		}


	}

	public void mouseReleased(int button, int x, int y) {
		if(isPressed=true)onClickListener.onClicked(this);
		isPressed=false;
		couleurText=couleurNormal;
	}

	public void mouseMoved(int newx, int newy) {
		isEntered=containsPoint(newx,newy);

	}

	public void mouseDragged(int newx, int newy) {
		// TODO Auto-generated method stub

	}

	public void setOnClickListener(OnClickListener onClickListener) {
			this.onClickListener=onClickListener;
	}

	public interface OnClickListener{
		public void onClicked(Button b);
	}

}
