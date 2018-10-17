package com.mycompany.a2;
import java.util.Iterator;

public interface ICollection {
	
	public void add(GameObject x);
	public Iterator getIterator();
	public void remove(GameObject x);

}
