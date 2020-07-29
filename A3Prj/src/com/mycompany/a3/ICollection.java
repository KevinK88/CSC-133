package com.mycompany.a3;

public interface ICollection {
	  
	  // Adding a new object into a List
	  public void add(GameObjects object);
	  
	  // Return iterator than can go through the collection
	  IIterator getIterator();
}
