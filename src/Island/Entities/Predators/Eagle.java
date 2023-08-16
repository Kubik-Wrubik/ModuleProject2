package Island.Entities.Predators;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Herbivorous.*;
import Island.Island;

import java.util.List;

public class Eagle extends Predator {

	public Eagle(String name, Cell cell, Object lock){
		super(name, 6, 3, 1,20, 4, cell, lock);
	}

	public int getPrey(Animal animal){
		if(animal instanceof Fox) return 10;
		else if(animal instanceof Rabbit) return 90;
		else if(animal instanceof Mouse) return 90;
		else if(animal instanceof Duck) return 80;
		return 0;
	}
}
