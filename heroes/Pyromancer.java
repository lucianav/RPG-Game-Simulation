package heroes;

import game.Field;
import spells.Fireblast;
import spells.Ignite;

/**
 * clasa erou tip Pyromancer
 * 
 * @author Luciana Viziru
 *
 */
public class Pyromancer extends Hero {

	protected static Fireblast fireblast = new Fireblast();// spell fireblast
	protected static Ignite ignite = new Ignite();// spell ignite

	/**
	 * constructor fara parametrii, initializeaza HP-ul
	 */
	public Pyromancer() {
		super();
		setHP(500);
	}

	/**
	 * constructor cu parametrii, fixeaza pozitia eroului si intializeaza HP-ul
	 * 
	 * @param line
	 * @param column
	 */
	public Pyromancer(int line, int column) {
		super(line, column);
		setHP(500);
	}

	/**
	 * metoda de calcul a HP maxim
	 */
	public int updateHP() {
		return 500 + 50 * getLevel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int harm(Rogue r, Field f) {
		setLevelType(f);
		int damage = Math.round(fireblast.apply(r) * 0.8f) + 
						Math.round(ignite.apply(r) * 0.8f);
		r.setPastDamage(Math.round(r.getPastDamage() * 0.8f));
		return damage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int harm(Knight k, Field f) {
		setLevelType(f);
		int damage = Math.round(fireblast.apply(k) * 1.2f) + 
						Math.round(ignite.apply(k) * 1.2f);
		k.setPastDamage(Math.round(k.getPastDamage() * 1.2f));
		return damage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int harm(Pyromancer p, Field f) {
		setLevelType(f);
		int damage = Math.round(fireblast.apply(p) * 0.9f) + 
						Math.round(ignite.apply(p) * 0.9f);
		p.setPastDamage(Math.round(p.getPastDamage() * 0.9f));
		return damage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int harm(Wizard w, Field f) {
		setLevelType(f);
		int damage = Math.round(fireblast.apply(w) * 1.05f) + 
						Math.round(ignite.apply(w) * 1.05f);
		w.setPastDamage(Math.round(w.getPastDamage() * 1.05f));
		return damage;
	}

	/**
	 * {@inheritDoc}
	 */
	public void beHarmed(Hero h, Field f) {
		this.setHP(this.getHP() - h.harm(this, f));
	}

	/**
	 * metoda seteaza levelul si tipul terenului pt cele doua spelluri folosite
	 * de Pyromancer
	 * 
	 * @param f
	 *            terenul luptei
	 */
	protected void setLevelType(Field f) {
		fireblast.setLevel(this.getLevel());
		fireblast.setType(f.getType());
		ignite.setLevel(this.getLevel());
		ignite.setType(f.getType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String print() {
		String s = new String("P ");
		if (isAlive()) {
			s = s + getLevel() + " " + XP + " " + 
						getHP() + " " + line + " " + column;
		} else {
			s = s + "dead";
		}
		return s;
	}
}
