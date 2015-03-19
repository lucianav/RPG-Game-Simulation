package heroes;

import spells.Deflect;
import spells.Drain;
import game.Field;
import game.Map;

/**
 * clasa erou tip Wizard
 * 
 * @author Luciana Viziru
 *
 */
public class Wizard extends Hero {

	private static Drain drain = new Drain(); // spell drain
	private static Deflect deflect = new Deflect();// spell deflect

	/**
	 * constructor fara parametrii, initializeaza HP-ul
	 */
	public Wizard() {
		super();
		setHP(400);
	}

	/**
	 * constructor cu parametrii, fixeaza pozitia eroului si intializeaza HP-ul
	 * 
	 * @param line
	 * @param column
	 */
	public Wizard(int line, int column) {
		super(line, column);
		setHP(400);
	}

	/**
	 * metoda de calcul a HP maxim
	 */
	public int updateHP() {
		return 400 + 30 * getLevel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int harm(Rogue r, Field f) {
		setLevelType(f);
		r.setLevelType(f);
		int damage = 0;
		damage += Math.round(drain.apply(r) * 0.8 * 
					Math.min(0.3 * r.updateHP(), r.getHP()));
		if ((r.criticalHit % 3 == 0 || r.isCritical) && f.getType() == Map.Woods) {
			damage += Math.round(deflect.apply(r) * 1.2
					* (Rogue.paralysis.apply(this) + 
								Math.round(Rogue.backstab.apply(this) * 1.5f)));	
		} else {
			damage += Math.round(deflect.apply(r) * 1.2 * 
					(Rogue.paralysis.apply(this) + Rogue.backstab.apply(this)));
		}
		return damage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int harm(Knight k, Field f) {
		setLevelType(f);
		k.setLevelType(f);
		int damage = 0;
		damage += Math.round(drain.apply(k) * 1.2 * 
					Math.min(0.3 * k.updateHP(), k.getHP()));
		damage += Math.round(deflect.apply(k) * 1.4 * 
					(Knight.execute.apply(this) + Knight.slam.apply(this)));
		return damage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int harm(Pyromancer p, Field f) {
		setLevelType(f);
		p.setLevelType(f);
		int damage = 0;
		damage += Math.round(drain.apply(p) * 0.9 * 
					Math.min(0.3 * p.updateHP(), p.getHP()));
		damage += Math.round(deflect.apply(p) * 1.3 *
					(Pyromancer.fireblast.apply(this) + 
						Pyromancer.ignite.apply(this)));
		return damage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int harm(Wizard w, Field f) {
		setLevelType(f);
		w.setLevelType(f);
		return (int) Math.round(drain.apply(w) * 1.05 * 
						Math.min(0.3 * w.updateHP(), w.getHP()));
	}

	/**
	 * {@inheritDoc}
	 */
	public void beHarmed(Hero h, Field f) {
		this.setHP(this.getHP() - h.harm(this, f));
	}

	/**
	 * metoda seteaza levelul si tipul terenului pt cele doua spelluri folosite
	 * de Wizard
	 * 
	 * @param f
	 *            terenul luptei
	 */
	private void setLevelType(Field f) {
		drain.setLevel(this.getLevel());
		drain.setType(f.getType());
		deflect.setLevel(this.getLevel());
		deflect.setType(f.getType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String print() {
		String s = new String("W ");
		if (isAlive()) {
			s = s + getLevel() + " " + XP + " " + 
					getHP() + " " + line + " " + column;
		} else {
			s = s + "dead";
		}
		return s;
	}

}
