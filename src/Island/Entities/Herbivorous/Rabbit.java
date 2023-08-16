package Island.Entities.Herbivorous;

import Island.Cell;
import Island.Entities.Vegetation;

import java.util.List;

public class Rabbit extends Herbivorous{


	public Rabbit(String name, Cell cell, Object lock){
		super(name, 2, 2, 0.45,7, 2, cell, lock);
	}
}
