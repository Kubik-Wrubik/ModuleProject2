package island.entities.herbivorous;

import island.Cell;

public class Goat extends Herbivorous {
	public Goat(Cell cell, Object lock){
		super( 60, 3, 10, 10, 2, cell, lock);
	}
}
