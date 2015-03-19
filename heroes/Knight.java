package heroes;

import game.Field;
import spells.Execute;
import spells.Slam;

/**
 * clasa erou tip Knight
 * 
 * @author Luciana Viziru
 *
 */
public class Knight extends Hero {

	protected static Execute execute = new Execute(); // spell execute
	protected static Slam slam = new Slam(); // spell slam

	/**
	 * constructor fara parametrii, initializeaza HP-ul
	 */
	public Knight() {
		super();
		setHP(900);
	}

	/**
	 * constructor cu parametrii, fixeaza pozitia eroului si intializeaza HP-ul
	 * 
	 * @param line
	 * @param column
	 */
	public Knight(int line, int column) {
		super(line, column);
		setHP(900);
	}

	/**
	 * metoda de calcul a HP maxim
	 */
	public int updateHP() {
		return 900 + 80 * getLevel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int harm(Rogue r, Field f) {
		setLevelType(f);
		return Math.round(execute.apply(r) * 1.15f) + 
					Math.round(slam.apply(r) * 0.8f);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int harm(Knight k, Field f) {
		setLevelType(f);
		return execute.apply(k) + Math.round(slam.apply(k) * 1.2f);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int harm(Pyromancer p, Field f) {
		setLevelType(f);
		return Math.round(execute.apply(p) * 1.1f) + 
					Math.round(slam.apply(p) * 0.9f);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int harm(Wizard w, Field f) {
		setLevelType(f);
		return Math.round(execute.apply(w) * 0.8f) + 
					Math.round(slam.apply(w) * 1.05f);
	}

	/**
	 * {@inheritDoc}
	 */
	public void beHarmed(Hero h, Field f) {
		this.setHP(this.getHP() - h.harm(this, f));
	}

	/**
	 * metoda seteaza levelul si tipul terenului pt cele doua spelluri folosite
	 * de Knight
	 * 
	 * @param f
	 *            terenul luptei
	 */
	protected void setLevelType(Field f) {
		execute.setLevel(this.getLevel());
		execute.setType(f.getType());
		slam.setLevel(this.getLevel());
		slam.setType(f.getType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String print() {
		String s = new String("K ");
		if (isAlive()) {
			s = s + getLevel() + " " + XP + " " + 
						getHP() + " " + line + " " + column;
		} else {
			s = s + "dead";
		}
		return s;
	}
}
