package Island.Entities.Predators;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Herbivorous.*;

public class Wolf extends Predator {
	public Wolf(String name, Cell cell, Object lock){
		super(name, 50, 3, 8, 14, 2, cell, lock);
	}

	public int getPrey(Animal animal){
		if(animal instanceof Horse) return 10;
		if(animal instanceof Deer) return 15;
		if(animal instanceof Rabbit) return 60;
		if(animal instanceof Mouse) return 80;
		if(animal instanceof Goat) return 60;
		if(animal instanceof Sheep) return 70;
		if(animal instanceof Boar) return 15;
		if(animal instanceof Buffalo) return 10;
		if(animal instanceof Duck) return 40;
		return 0;
	}
}
