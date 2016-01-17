package org.wolf_kingdom.gs.model;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


/**
 * Player cache
 * @author Peeter
 *
 * This system enables a programmer to store key/value pairs in the database with ease. 
 */
public class Cache {

	public Map<String, Object>  getCacheMap() {
		return storage;
	}
	/**
	 * Determines if the selected key already exists in the cache
	 * @param key
	 * @return
	 */
	public boolean hasKey(String key) {
		return storage.containsKey(key);
	}
	
	/**
	 * Where we store the data.
	 */
	private Map<String, Object> storage = new HashMap<String,Object>();
	
	/**
	 * Store an integer in the cache
	 * @param key
	 * @param i
	 * @throws
	 * 			IllegalArgumentException when the key is already used in the database
	 */
	public void store(String key, Integer i) {
		if(storage.containsKey(key))
			throw new IllegalArgumentException("Key already exists, please use update() to update an item");
		storage.put(key, i);
	}
	/**
	 * Store a String in the cache
	 * @param key
	 * @param s
	 * @throws
	 * 			IllegalArgumentException when the key is already used in the database
	 */
	public void store(String key, String s) {
		if(storage.containsKey(key))
			throw new IllegalArgumentException("Key already exists, please use update() to update an item");
		storage.put(key, s);
	}
	/**
	 * Store a Boolean in the cache
	 * @param key
	 * @param b
	 * @throws
	 * 			IllegalArgumentException when the key is already used in the database
	 */
	public void store(String key, Boolean b) {
		if(storage.containsKey(key))
			throw new IllegalArgumentException("Key already exists, please use update() to update an item");
		storage.put(key, b);
	}
	/**
	 * Store a long in the cache
	 * @param key
	 * @param b
	 * @throws
	 * 			IllegalArgumentException when the key is already used in the database
	 */
	public void store(String key, Long l) {
		if(storage.containsKey(key))
			throw new IllegalArgumentException("Key already exists, please use update() to update an item");
		storage.put(key, l);
	}

	
	
	
	
	
	/**
	 * Update an Integer in the cache
	 * @param key
	 * @param i
	 * @throws
	 * 			IllegalArgumentException when the key is not in use
	 */
	public void update(String key, Integer i) {
		if(!storage.containsKey(key))
			throw new IllegalArgumentException("Key doesn't exists, please use store() to store an item");
		storage.put(key, i);
	}
	/**
	 * Update a String in the cache
	 * @param key
	 * @param i
	 * @throws
	 * 			IllegalArgumentException when the key is not in use
	 */
	public void update(String key, String i) {
		if(!storage.containsKey(key))
			throw new IllegalArgumentException("Key doesn't exists, please use store() to store an item");
		storage.put(key, i);
	}
	/**
	 * Update a Boolean in the cache
	 * @param key
	 * @param i
	 * @throws
	 * 			IllegalArgumentException when the key is not in use
	 */
	public void update(String key, Boolean i) {
		if(!storage.containsKey(key))
			throw new IllegalArgumentException("Key doesn't exists, please use store() to store an item");
		storage.put(key, i);
	}
	/**
	 * Update a Long in the cache
	 * @param key
	 * @param i
	 * @throws
	 * 			IllegalArgumentException when the key is not in use
	 */
	public void update(String key, Long i) {
		if(!storage.containsKey(key))
			throw new IllegalArgumentException("Key doesn't exists, please use store() to store an item");
		storage.put(key, i);
	}
	
	
	
	
	
	
	
	/**
	 * Get an Integer	 from the cache
	 * @param key
	 * @return 
	 * @throws 
	 * 		NoSuchElementException - When no object is found for the key
	 * 		IllegalArgumentException - When object is found, but is not an Integer
	 */
	public Integer getInt(String key) {
		if(!storage.containsKey(key))
			throw new NoSuchElementException("No object found for that key");
		if(!(storage.get(key) instanceof Integer)) {
			throw new IllegalArgumentException("Object found, but not an Integer");
		}
		return (Integer)(storage.get(key));
	}
	/**
	 * Get a String from the cache
	 * @param key
	 * @return 
	 * @throws 
	 * 		NoSuchElementException When no object is found for the key
	 * @throws
	 * 		IllegalArgumentException When object is found, but is not a string 
	 */
	public String getString(String key) {
		if(!storage.containsKey(key))
			throw new NoSuchElementException("No object found for that key");
		if(!(storage.get(key) instanceof String)) {
			throw new IllegalArgumentException("Object found, but not an String");
		}
		return (String)(storage.get(key));
	}
	/**
	 * Get a Boolean from the cache
	 * @param key
	 * @return 
	 * @throws 
	 * 		NoSuchElementException When no object is found for the key
	 * @throws
	 * 		IllegalArgumentException When object is found, but is not a Boolean 
	 */
	public Boolean getBoolean(String key) {
		if(!storage.containsKey(key))
			throw new NoSuchElementException("No object found for that key");
		if(!(storage.get(key) instanceof Boolean)) {
			throw new IllegalArgumentException("Object found, but not a Boolean");
		}
		return (Boolean)(storage.get(key));
	}
	/**
	 * Get a Long from the cache
	 * @param key
	 * @return 
	 * @throws 
	 * 		NoSuchElementException When no object is found for the key
	 * @throws
	 * 		IllegalArgumentException When object is found, but is not a Long 
	 */
	public Long getLong(String key) {
		if(!storage.containsKey(key))
			throw new NoSuchElementException("No object found for that key");
		if(!(storage.get(key) instanceof Long)) {
			throw new IllegalArgumentException("Object found, but not a Long");
		}
		return (Long)(storage.get(key));
	}
	
	/**
	 * Remove a value from the cache.
	 * @param key
	 */
	public void remove(String key) {
		storage.remove(key);
	}
	
}
