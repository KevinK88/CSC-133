package com.mycompany.a2;
import java.util.ArrayList;
public class GameWorldCollection implements ICollection {
	private ArrayList<GameObjects> gameObjects;
	
	public GameWorldCollection() {
		gameObjects = new ArrayList<GameObjects>();
	}
	
	@Override
	public void add(GameObjects newObject) {
		gameObjects.add(newObject);
	}
	
	@Override
	public IIterator getIterator() {
	
		return new GameWorldIterator();
	}
	private class GameWorldIterator implements IIterator{
		private int index;
		public GameWorldIterator() {
			index = -1;
		}
		@Override
		public boolean hasNext() {
			if(gameObjects.size() <= 0)
				return false;
			if(index == gameObjects.size() - 1)
			{
				return false;
			}
			return true;
		}
		@Override
		public GameObjects getNext() {
			index++;
			return gameObjects.get(index);
		}
	
		@Override
		public GameObjects getCurrent() {
			// TODO Auto-generated method stub
			return gameObjects.get(index);
		}

		@Override
		public void remove(GameObjects gameObj) {
			gameObjects.remove(gameObj);
			
		}
	}
}
	
