package spells;

import game.Map;
import heroes.Hero;

/**
 * clasa spell tip Execute
 * 
 * @author Luciana Viziru
 *
 */
public class Execute extends Spell {

	private int damage;// damage ce va fi aplicat

	/**
	 * constructor fara parametrii, seteaza damage-ul de baza
	 */
	public Execute() {
		baseDamage = 200;
		damage = 0;
	}

	/**
	 * metoda verifica daca oponentul poate fi ucis instantaneu
	 * 
	 * @param h
	 *            oponent
	 * @return true sau false
	 */
	public boolean HPLowerThanLimit(Hero h) {
		return h.getHP() < Math.round(Math.min(0.2 + 0.01 * 
								h.getLevel(), 0.4) * h.updateHP());
	}

	/**
	 * metoda modifica damage-ul curent in functie de level si teren
	 * 
	 * @return damage
	 */
	public int levelFieldDamage() {
		damage = baseDamage + 30 * level;
		if (type == Map.Land) {
			damage = Math.round(damage * 1.15f);
		}
		return damage;
	}

	/**
	 * metoda aplica damage-ul curent si verifica limita pt instant kill
	 * 
	 * @param h
	 *            oponent
	 * @return damage
	 */
	public int apply(Hero h) {
		if (HPLowerThanLimit(h)) {
			h.setAlive(false);
		}
		this.levelFieldDamage();
		return damage;
	}
}
