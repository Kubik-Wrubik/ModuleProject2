package island.entities.herbivorous;

import island.Cell;

public class Worm extends Herbivorous {
	public Worm(Cell cell, Object lock){
		super( 0.01, 0, 0.01, 3, 0, cell, lock);
	}
}
