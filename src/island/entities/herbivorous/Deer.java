package island.entities.herbivorous;

import island.Cell;

public class Deer extends Herbivorous {
	public Deer(Cell cell, Object lock){
		super( 300, 4, 50, 20, 3, cell, lock);
	}
}
