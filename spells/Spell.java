package spells;

import game.Map;

/**
 * clasa spell
 * 
 * @author Luciana Viziru
 *
 */
public abstract class Spell {

	protected int baseDamage; // damage de baza
	protected int level; // level atacator
	protected Map type; // tip teren

	/**
	 * constructor fara parametrii
	 */
	public Spell() {
		baseDamage = 0;
		level = 0;
	}

	/**
	 * setter tip teren
	 * 
	 * @param type
	 */
	public void setType(Map type) {
		this.type = type;
	}

	/**
	 * setter level atacator
	 * 
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

}
