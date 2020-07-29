package com.mycompany.a3;

public interface IIterator {
	// Check if there is any element in the List. If not return FALSE
	public boolean hasNext();
	
	// Return the next element in the List.
	public GameObjects getNext();
	

	// Return the current element in the List
	public GameObjects getCurrent();
	
	// Remove the current element in the List
	public void remove(GameObjects gameobjects);
	
	
}
