package com.mycompany.a3;

public interface ICollider {
		public boolean collidesWith(GameObjects otherObject);
		public void handleCollision(GameObjects otherObject);

	}
