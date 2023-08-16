package Island.Entities.Predators;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Herbivorous.*;
import Island.Island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Predator {


	public Wolf(String name, Cell cell, Object lock){
		super(name, 50, 3, 8,14, 2, cell, lock);
	}

	public int getPrey(Animal animal){
		if(animal instanceof Horse) return 10;
		else if(animal instanceof Deer) return 15;
		else if(animal instanceof Rabbit) return 60;
		else if(animal instanceof Mouse) return 80;
		else if(animal instanceof Goat) return 60;
		else if(animal instanceof Sheep) return 70;
		else if(animal instanceof Boar) return 15;
		else if(animal instanceof Buffalo) return 10;
		else if(animal instanceof Duck) return 40;
		return 0;
	}
}
