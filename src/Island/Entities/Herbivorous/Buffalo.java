package Island.Entities.Herbivorous;

import Island.Cell;

public class Buffalo extends Herbivorous {
	public Buffalo(String name, Cell cell, Object lock){
		super(name, 700, 3, 100, 40, 3, cell, lock);
	}
}