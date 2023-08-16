package Island.Entities.Predators;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Herbivorous.*;
import Island.Island;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Bear extends Predator{

	public Bear(String name, Cell cell, Object lock){
		super(name, 500, 2, 80,25, 4, cell, lock);
	}

	public int getPrey(Animal animal){
		if(animal instanceof Python) return 80;
		else if(animal instanceof Horse) return 40;
		else if(animal instanceof Deer) return 80;
		else if(animal instanceof Rabbit) return 80;
		else if(animal instanceof Mouse) return 90;
		else if(animal instanceof Goat) return 70;
		else if(animal instanceof Sheep) return 70;
		else if(animal instanceof Boar) return 50;
		else if(animal instanceof Buffalo) return 20;
		else if(animal instanceof Duck) return 10;
		return 0;
	}
}
