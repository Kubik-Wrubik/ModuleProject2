package Island.Entities.Herbivorous;

import Island.Cell;

public class Sheep extends Herbivorous {
	public Sheep(String name, Cell cell, Object lock){
		super(name, 70, 3, 15, 10, 1, cell, lock);
	}
}
