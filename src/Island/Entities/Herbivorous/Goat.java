package Island.Entities.Herbivorous;

import Island.Cell;
import Island.Entities.Vegetation;

import java.util.List;

public class Goat extends Herbivorous{


	public Goat(String name, Cell cell, Object lock){
		super(name, 60, 3, 10,10, 2, cell, lock);
	}


}
