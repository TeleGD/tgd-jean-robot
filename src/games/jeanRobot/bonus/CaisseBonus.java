package games.jeanRobot.bonus;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import games.jeanRobot.util.*;

public class CaisseBonus extends Entity{

	private Bonus[] bonuses;
	private Boolean protect;

	public CaisseBonus(Bonus[] bonuses,boolean protect){
		this.bonuses = bonuses;
		this.protect=protect;
	}

	public Bonus chooseBonus(){
		int nb=(int) Math.abs(Math.random()*bonuses.length);
		return bonuses[nb];
	}

	public Boolean getProtect(){
		return protect;
	}

	public void setProtect(boolean bool){
		this.protect=bool;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		// TODO Auto-generated method stub

	}


}
