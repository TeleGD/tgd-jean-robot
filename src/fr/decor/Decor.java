package fr.decor;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.game.Game;
import fr.game.World;
import fr.util.Movable;

public class Decor extends Movable {
	// pourcentage en X de l'ecran a partir du moment ou la camera bouge pour
	// recadrer le personnage
	private static final double X_BORNE_SUP = 67 / 100.0; // MAX_X
	private static final double X_BORNE_INF = 33 / 100.0; // MIN_X
	private static final double X_BORNE_SUP_IDEALE = 60 / 100.0; // MAX_X
																	// lorsque
																	// personnage
																	// immobile
	private static final double X_BORNE_INF_IDEALE = 40 / 100.0; // MIN Y
																	// lorsque
																	// personnage
																	// immobile

	// pourcentage en X de l'ecran a partir du moment ou la camera bouge pour
	// recadrer le personnage
	private static final double Y_BORNE_SUP = 67 / 100.0; // MAX_Y
	private static final double Y_BORNE_INF = 33 / 100.0; // MIN_Y
	private static final double Y_BORNE_SUP_IDEALE = 60 / 100.0; // MAX_Y
																	// lorsque
																	// personnage
																	// immobile
	private static final double Y_BORNE_INF_IDEALE = 40 / 100.0; // MIN_Y
																	// lorsque
																	// personnage
																	// immobile

	private Background background;
	private static ArrayList<Plateform> plateforms;

	public Decor(String backgroundTexturePath) throws SlickException {
		super(0, 0, Game.longueur, Game.hauteur);

		background = new Background(new Image(backgroundTexturePath));
		plateforms = new ArrayList<Plateform>();

		this.speedX = 0;
		this.speedY = 0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// translation de la caméra (fixé sur le personnage)
		g.translate((float) x, (float) y);

		// rendu du fond d'écran
		background.render(container, game, g);

		renderPlateforms(container, game, g);
	}

	// rendu des plateformes
	private void renderPlateforms(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		for (int i = 0; i < plateforms.size(); i++) {
			plateforms.get(i).render(container, game, g);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		if (World.getPlayer().getSpeedX() > 0) {
			// si le player avance vers la droite
			if (World.getPlayer().getX() > -x + width * X_BORNE_SUP) {
				// si le player depasse les deux tiers de l'écran
				speedX = -World.getPlayer().getSpeedX();
			} else
				speedX = 0;

		} else if (World.getPlayer().getSpeedX() < 0) {
			// si le player avance vers la gauche
			if (World.getPlayer().getX() < -x + width * X_BORNE_INF) {
				// si le player avant le tier de l'écran et qu'il recule
				speedX = -World.getPlayer().getSpeedX();
			} else
				speedX = 0;
		} else {
			if (World.getPlayer().getX() > -x + width * X_BORNE_SUP_IDEALE)
				speedX = -0.2;
			else if (World.getPlayer().getX() < -x + width * X_BORNE_INF_IDEALE)
				speedX = 0.2;
			else
				speedX = 0;
		}

		if (World.getPlayer().getSpeedY() > 0) {
			// si le player va vers le bas
			if (World.getPlayer().getY() > -y + height * Y_BORNE_SUP) {
				// si le player depasse les deux tiers de l'écran
				speedY = -World.getPlayer().getSpeedY();
			} else
				speedY = 0;

		} else if (World.getPlayer().getSpeedY() < 0) {
			// si le player avance vers la gauche
			if (World.getPlayer().getY() < -y + height * Y_BORNE_INF) {
				// si le player avant le tier de l'écran et qu'il recule
				speedY = -World.getPlayer().getSpeedY();
			} else
				speedY = 0;
		} else {
			if (World.getPlayer().getY() > -y + height * Y_BORNE_SUP_IDEALE)
				speedY = -0.2;
			else if (World.getPlayer().getY() < -y + height * Y_BORNE_INF_IDEALE)
				speedY = 0.2;
			else
				speedY = 0;
		}

		moveX(delta);
		moveY(delta);

		if (x >= 0) {
			if (World.getPlayer().getX() < 0)
				World.getPlayer().setX(0);
			x = 0;
		}

		updatePlateforms(container, game, delta);
		background.setY(-y);
		background.update(container, game, delta);
	}

	private void updatePlateforms(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		for (int i = 0; i < plateforms.size(); i++) {
			plateforms.get(i).update(container, game, delta);
		}
	}

	public static void addPlateform(Plateform p) {
		plateforms.add(p);
	}

	public static void removePlateform(Plateform p) {
		plateforms.remove(p);
	}

	public static Plateform getPlateform(int index) {
		return plateforms.get(index);
	}

	public static ArrayList<Plateform> getPlateforms() {
		return plateforms;
	}

	public static void setPlateforms(ArrayList<Plateform> plateforms) {
		Decor.plateforms=plateforms;
	}

}
