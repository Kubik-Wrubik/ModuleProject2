package island.entities.herbivorous;

import island.Cell;

public class Sheep extends Herbivorous {
	public Sheep(Cell cell, Object lock){
		super( 70, 3, 15, 10, 1, cell, lock);
	}
}
