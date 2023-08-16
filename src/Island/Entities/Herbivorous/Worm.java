package Island.Entities.Herbivorous;

import Island.Cell;
import Island.Entities.Vegetation;

import java.util.List;

public class Worm extends Herbivorous{


	public Worm(String name, Cell cell, Object lock){
		super(name, 0.01, 0, 0.005,3, 0, cell, lock);
	}
}
