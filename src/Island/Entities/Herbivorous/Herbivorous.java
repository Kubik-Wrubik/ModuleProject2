package Island.Entities.Herbivorous;

import Island.Entities.Animal;
import Island.Entities.CanGraze;
import Island.Cell;

public abstract class Herbivorous extends Animal implements CanGraze {
	public Herbivorous(String name, Cell cell, Object lock){
		super(name, cell, lock);
	}
}
