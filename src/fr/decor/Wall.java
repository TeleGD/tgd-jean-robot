package fr.decor;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import fr.characters.BasicPlayer;
import fr.characters.Player;
import fr.game.Game;
import fr.util.Collisions;

public class Wall extends Plateform  {
	
	public Wall(int indexX, int indexY, int sizeX, int sizeY) {
		super(indexX, indexY, sizeX, sizeY);
	}
	
	//test
	//construteur appele pour charger de niveau.
	public Wall(String ligne) 
	{
		super(ligne);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.red);
		g.fillRect((float) x, (float) y, (float) width, (float) height);
	}
	
	@Override
	public void collPlayer(Player player){
		
	}
	
	@Override
	public String parseString() {
		return "DeathBloc "+getX()+ ";"+ getY()+";"+getWidth()+";"+getHeight();
	}
	
	@Override
	public Plateform copy() {
		Wall p=new Wall((int)x/Game.DENSITE_X,(int)y/Game.DENSITE_Y,(int)x/Game.DENSITE_X,(int) (y/Game.DENSITE_Y));
		p.height=this.height;
		p.width=this.width;
		return p;
	}
}
