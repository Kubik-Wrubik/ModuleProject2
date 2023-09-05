package Island.Entities.Herbivorous;

import Island.Cell;

public class Worm extends Herbivorous {
	public Worm(String name, Cell cell, Object lock){
		super(name, 0.01, 0, 0.01, 3, 0, cell, lock);
	}
}
