package Island.Entities.Herbivorous;

import Island.Cell;

public class Deer extends Herbivorous {
	public Deer(String name, Cell cell, Object lock){
		super(name, 300, 4, 50, 20, 3, cell, lock);
	}
}
