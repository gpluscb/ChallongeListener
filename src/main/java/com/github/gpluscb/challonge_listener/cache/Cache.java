package com.github.gpluscb.challonge_listener.cache;

public abstract class Cache<T> {
	private boolean isValid;
	
	public Cache() {
		this.isValid = true;
	}
	
	protected void invalidate() {
		this.isValid = false;
	}
	
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
	
	protected abstract void update(T t);
}
