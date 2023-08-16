package Island.Entities.Predators;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Herbivorous.*;
import Island.Island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Fox extends Predator{


	public Fox(String name, Cell cell, Object lock){
		super(name, 8, 2, 2,3, 2, cell, lock);
	}


	public int getPrey(Animal animal){
		if(animal instanceof Rabbit) return 70;
		else if(animal instanceof Mouse) return 90;
		else if(animal instanceof Duck) return 60;
		else if(animal instanceof Worm) return 40;
		return 0;
	}
}
