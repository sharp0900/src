package com.mycompany.a2;

public interface ICollider {
	public boolean collideWith(ICollider gameObject);
	public void handleCollision(ICollider gameObject);
}
