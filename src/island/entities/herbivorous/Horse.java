package island.entities.herbivorous;

import island.Cell;

public class Horse extends Herbivorous {
	public Horse(Cell cell, Object lock){
		super( 400, 4, 60, 25, 3, cell, lock);
	}
}
