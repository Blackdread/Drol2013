package base.views;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import base.engine.Game;
import base.engine.gui.ElementString;
import base.engine.gui.Elements;
import base.engine.gui.ListeDeroulante;
import base.utils.ResourceManager;
import base.utils.StatsSerializable;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public class SoloView extends View {

	private Image background;
	private MouseOverArea butJouer, butRetour;
	
	private ListeDeroulante listeScoresKey, listeScoresValue, listeStatsKey, listeStatsValue;
	private Rectangle shapeScore, shapeStats;
	
	private StatsSerializable stats, scores;
	
	/**
	 * Apres c'est reserver pour les boutons
	 */
	private int yMax;
	
	@Override
	public void initResources() {
		/*
		 * Un bouton pour lancer la partie, retour
		 * Un tableau de score en mode Solo
		 * Des stats (nb de fois morts, nb de fois gagner, perdu, ...)
		 * 
		 */
		stats = new StatsSerializable("config/statsSolo.seria");
		scores = new StatsSerializable("config/scoreSolo.seria");
		
		final int MARGIN = 30;
		int x = container.getWidth();
		int y = container.getHeight();
		Image tmp = ResourceManager.getImage("MenuJouer");
		
		int larg = tmp.getWidth();
		int haut = tmp.getHeight();
		
		int yBut = y - haut - MARGIN;	// zone a ne pas depasser pour ce qui se trouve au-dessus
		yMax = yBut;
		
		shapeScore = new Rectangle(MARGIN, 100, x/2 - MARGIN - 10, yBut - 100);
		shapeStats = new Rectangle(x/2+10, 100, x/2 - MARGIN-10, yBut - 100);
		
		butRetour = new MouseOverArea(container, ResourceManager.getImage("butRetour"), MARGIN, yBut, larg, haut);
		butRetour.setMouseOverImage(ResourceManager.getImage("butRetourOver"));
		
		butJouer = new MouseOverArea(container, tmp, tmp.getWidth() + MARGIN*2, yBut, larg, haut);
		butJouer.setMouseOverImage(ResourceManager.getImage("MenuJouerOver"));
		
		listeStatsKey = new ListeDeroulante(container, ResourceManager.getImage("transparent"), (int)shapeStats.getX() + MARGIN , (int)shapeStats.getY() + 2);
		listeStatsKey.setScrolled(true);
		
		listeStatsValue = new ListeDeroulante(container, ResourceManager.getImage("transparent"), (int)shapeStats.getX() + (int)shapeStats.getWidth() / 2 + MARGIN, (int)shapeStats.getY() + 2);
		listeStatsValue.setScrolled(true);
		
		listeScoresKey = new ListeDeroulante(container, ResourceManager.getImage("transparent"), (int)shapeScore.getX() + MARGIN , (int)shapeScore.getY() + 2);
		listeScoresKey.setScrolled(true);
		listeScoresKey.setAutoriserWheel(false);
		
		listeScoresValue = new ListeDeroulante(container, ResourceManager.getImage("transparent"), (int)shapeScore.getX() + (int)shapeScore.getWidth() / 2 + MARGIN , (int)shapeScore.getY() + 2);
		listeScoresValue.setScrolled(true);
		listeScoresValue.setAutoriserWheel(false);
		
		int hautLettre = container.getDefaultFont().getHeight("1")+2;
		int maxToDraw =  (int) (shapeStats.getHeight()/hautLettre);
		
		listeStatsKey.setMaxElementsToDraw(maxToDraw);
		listeStatsValue.setMaxElementsToDraw(maxToDraw);
		listeScoresKey.setMaxElementsToDraw(maxToDraw);
		listeScoresValue.setMaxElementsToDraw(maxToDraw);
		
		// Je ne veux pas que slick les notifie a ma place
		listeStatsKey.setAcceptingInput(false);
		listeStatsValue.setAcceptingInput(false);
		
		getStats();
		getScores();
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {	
		g.setColor(Color.white);
		g.drawString("Scores :", shapeScore.getX()+shapeScore.getWidth()/2-container.getDefaultFont().getWidth("Scores :")/2, shapeScore.getY()-container.getDefaultFont().getHeight("Scores :") - 2);
		g.drawString("Statistique :", shapeStats.getX()+shapeStats.getWidth()/2-container.getDefaultFont().getWidth("Statistique :")/2, shapeStats.getY()-container.getDefaultFont().getHeight("Statistique :") - 2);
		
		g.setDrawMode(Graphics.MODE_COLOR_MULTIPLY);
		g.setColor(Color.gray);
		g.fill(shapeScore);
		g.fill(shapeStats);
		g.setDrawMode(Graphics.MODE_NORMAL);
		
		g.setColor(Color.white);
		g.draw(shapeScore);
		g.draw(shapeStats);
		
		g.setClip(shapeStats);
		listeStatsKey.renderString(container, g);
		listeStatsValue.renderString(container, g);
		
		g.setClip(shapeScore);
		listeScoresKey.renderString(container, g);
		listeScoresValue.renderString(container, g);
		g.clearClip();
		
		butRetour.render(container, g);
		butJouer.render(container, g);
		super.render(container, sbgame, g);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		switch(key){
		case Input.KEY_ESCAPE:
			gotoPreviousView();
			break;
		}
	}
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		
		if(butJouer.isMouseOver())
			startCampaign();
		else if(butRetour.isMouseOver())
			gotoPreviousView();
		
	}
	@Override
	public void mouseWheelMoved(int change){
		listeStatsKey.mouseWheelMoved(change);
		listeStatsValue.mouseWheelMoved(change);
	}
	
	/**
	 * 
	 * TODO pas sur, tronquer les mots s'ils depassent de la zone d'affichage
	 */
	private void getStats(){
		stats.loadStats();
		
		for(Entry<String, String> v : stats.getHashStats().entrySet())
			if(v != null && v.getKey() != null && v.getValue() != null){
				Elements tmp = new ElementString(container, ResourceManager.getImage("transparent").getScaledCopy(10, container.getDefaultFont().getHeight("1")+2), 0,0,""+v.getKey());
				listeStatsKey.addElement(tmp);
				Elements tmp2 = new ElementString(container, ResourceManager.getImage("transparent").getScaledCopy(10, container.getDefaultFont().getHeight("1")+2), 0,0,""+v.getValue());
				listeStatsValue.addElement(tmp2);
			}
	}
	
	private void getScores(){
		scores.loadStats();
		
		ArrayList<Elements> arrayKey = new ArrayList<Elements>();
		ArrayList<Elements> arrayValue = new ArrayList<Elements>();
		
		for(Entry<String, String> v : scores.getHashStats().entrySet())
			if(v != null && v.getKey() != null && v.getValue() != null){
				Elements tmp = new ElementString(container, ResourceManager.getImage("transparent").getScaledCopy(10, container.getDefaultFont().getHeight("1")+2), 0,0,""+v.getKey());
				//listeScoresKey.addElement(tmp);
				arrayKey.add(tmp);
				Elements tmp2 = new ElementString(container, ResourceManager.getImage("transparent").getScaledCopy(10, container.getDefaultFont().getHeight("1")+2), 0,0,""+v.getValue());
				//listeScoresValue.addElement(tmp2);
				arrayValue.add(tmp2);
			}
		Elements tmp3;
		for(int j=0; j < arrayValue.size(); j++)
			for(int i=1; i < arrayValue.size(); i++){
				if(Integer.valueOf(arrayValue.get(i).toString()) > Integer.valueOf(arrayValue.get(i-1).toString())){
					 tmp3 = arrayValue.get(i);
					 arrayValue.set(i, arrayValue.get(i-1));
					 arrayValue.set(i-1, tmp3);
					 
					 tmp3 = arrayKey.get(i);
					 arrayKey.set(i, arrayKey.get(i-1));
					 arrayKey.set(i-1, tmp3);
				}
			}
		for(int i=0; i < arrayValue.size(); i++){
			listeScoresKey.addElement(arrayKey.get(i));
			listeScoresValue.addElement(arrayValue.get(i));
		}
		
	}
	
	private void startCampaign(){
		container.setMouseGrabbed(false);
		((View)game.getState(Game.TEST_STATE_ID)).initResources();	// TODO temporaire
		game.enterState(Game.TEST_STATE_ID, new FadeOutTransition(), new FadeInTransition());
	}
	
	@Override
	public int getID() {
		return Game.SOLO_VIEW_ID;
	}
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		engineManager.setPlayingMulti(false);
	}
}
