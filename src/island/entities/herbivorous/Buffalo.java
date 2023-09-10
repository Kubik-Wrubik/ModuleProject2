package island.entities.herbivorous;

import island.Cell;

public class Buffalo extends Herbivorous {
	public Buffalo(Cell cell, Object lock){
		super( 700, 3, 100, 40, 3, cell, lock);
	}
}
