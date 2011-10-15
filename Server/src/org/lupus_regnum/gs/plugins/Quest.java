package org.lupus_regnum.gs.plugins;

public abstract class Quest implements QuestInterface {
	public void sleep(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void sleep() {
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
