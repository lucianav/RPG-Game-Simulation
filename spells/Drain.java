package spells;

import game.Map;
import heroes.Hero;

/**
 * clasa spell tip Drain
 * 
 * @author Luciana Viziru
 *
 */
public class Drain extends Spell {

	private float damagePercent; // procentul de drain

	/**
	 * constructor fara parametrii, seteaza procentul de baza
	 */
	public Drain() {
		baseDamage = 20;
		damagePercent = 0;
	}

	/**
	 * metoda modifica procentul de baza in functie de level si teren
	 * 
	 * @return procent damage modificat
	 */
	public float levelFieldDamage() {
		damagePercent = (float) (baseDamage * 0.01 + 0.05 * level);
		if (type == Map.Desert) {
			damagePercent = damagePercent * 1.10f;
		}
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
		return this.levelFieldDamage();
	}
}
