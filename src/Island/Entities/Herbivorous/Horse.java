package Island.Entities.Herbivorous;

import Island.Cell;

public class Horse extends Herbivorous {
	public Horse(String name, Cell cell, Object lock){
		super(name, 400, 4, 60, 25, 3, cell, lock);
	}
}
