package com.github.gpluscb.challonge_listener.cache;

/**
 * Represents a cache of a given class.
 * 
 * @param <T>
 *            The class of the cache.
 */
public abstract class Cache<T> {
	private boolean isValid;
	
	/**
	 * Creates a new valid cache.
	 */
	public Cache() {
		this.isValid = true;
	}
	
	/**
	 * Invalidates the cache. The cache will be unusable afterwards.
	 */
	protected void invalidate() {
		this.isValid = false;
	}
	
	/**
	 * Throws if the cache is invalid.
	 * 
	 * @throws IllegalStateException
	 *             If the cache is invalid
	 */
	protected void checkValidity() throws IllegalStateException {
		throw new IllegalStateException("Cache is invalid");
	}
	
	/**
	 * Checks whether this cache is valid or if it has been deleted.
	 * 
	 * @return Whether this cache is valid
	 */
	public boolean isValid() {
		return this.isValid;
	}
	
	/**
	 * Used to update the cache with a new instance of the given class.
	 * 
	 * @param t
	 *            The new instance
	 */
	public abstract void update(T t);
}
