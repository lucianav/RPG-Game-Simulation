package spells;

import game.Map;
import heroes.Hero;

/**
 * clasa spell tip Deflect
 * 
 * @author Luciana Viziru
 *
 */
public class Deflect extends Spell {

	private float damagePercent;// procentul de deflect

	/**
	 * constructor fara parametrii, seteaza procentul de baza
	 */
	public Deflect() {
		baseDamage = 35;
		damagePercent = 0;
	}

	/**
	 * metoda modifica procentul de baza in functie de level si teren
	 * 
	 * @return procent damage modificat
	 */
	public float levelFieldDamage() {
		damagePercent = (float) (baseDamage * 0.01 + 0.02 * level);
		if (type == Map.Desert) {
			damagePercent = damagePercent * 1.1f;
		}
		damagePercent = Math.min(damagePercent, 0.7f);
		return damagePercent;
	}

	/**
	 * aplica procentul de damage
	 * 
	 * @param h
	 *            oponent
	 * @return procent damage
	 */
	public float apply(Hero h) {
		this.levelFieldDamage();
		return damagePercent;
	}
}
