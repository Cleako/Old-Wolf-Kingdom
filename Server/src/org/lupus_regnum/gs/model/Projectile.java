package org.lupus_regnum.gs.model;

public class Projectile {
    /**
     * Who fired the projectile
     */
    private Mob caster;
    /**
	 * Who the projectile is being fired at
	 */
	private Mob victim;
	private Mob cannonVictim;
	/**
	 * The type: 1 = magic, 2 = ranged
	 */
	private int type;
	
	private GameObject cannon;

	public Projectile(Mob caster, Mob victim, int type) {
		this.caster = caster;
		this.victim = victim;
		this.type = type;
	}
	
	public Projectile(GameObject cannon, Mob cannonVictim, int type) {
		this.cannon = cannon;
		this.cannonVictim = cannonVictim;
		this.type = type;
	}

	public Mob getCaster() {
		return caster;
	}
	
	public Mob getVictim() {
		return victim;
	}
	
	public Mob getCannonVictim() {
		return cannonVictim;
	}
	
	public int getType() {
		return type;
	}
	public GameObject getCannon() {
		return cannon;
	}

}
