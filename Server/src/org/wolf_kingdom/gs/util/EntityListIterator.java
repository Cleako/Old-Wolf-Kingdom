package org.wolf_kingdom.gs.util;

import java.util.Iterator;
import java.util.Set;

import org.wolf_kingdom.gs.model.Entity;


public class EntityListIterator<E extends Entity> implements Iterator<E> {
    private int curIndex = 0;
    private Object[] entities;
    private EntityList<E> entityList;
    private Integer[] indicies;

    public EntityListIterator(Object[] entities, Set<Integer> indicies, EntityList<E> entityList) {
	this.entities = entities;
	synchronized (indicies) {
		this.indicies = new Integer[indicies.size()];
		int i = 0;
		for(Integer integ : indicies) {
			this.indicies[i++] = integ;
		}
	}
	this.entityList = entityList;
    }

    public boolean hasNext() {
	return indicies.length != curIndex;
    }

    public E next() {
		synchronized (entities) {
			synchronized (indicies) {
				Object temp = entities[indicies[curIndex]];
				curIndex++;
				return (E) temp;
			}
		}
    }

    public void remove() {
		synchronized (entities) {
			synchronized (indicies) {
				if (curIndex >= 1) {
					entityList.remove(indicies[curIndex - 1]);
				}
			}
		}
    }

}
