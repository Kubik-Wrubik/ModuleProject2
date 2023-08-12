package Island.Entities.Predators;

import Island.Entities.Animal;
import Island.Cell;
import Island.Entities.CanHunt;
import Island.Island;

public abstract class Predator extends Animal implements CanHunt{
	public Predator(String name, Cell cell, Object lock){
		super(name, cell, lock);
	}
}
