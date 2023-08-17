package Island.Entities.Predators;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Herbivorous.*;

public class Bear extends Predator {
	public Bear(String name, Cell cell, Object lock){
		super(name, 500, 2, 80, 25, 4, cell, lock);
	}

	public int getPrey(Animal animal){
		if(animal instanceof Python) return 80;
		if(animal instanceof Horse) return 40;
		if(animal instanceof Deer) return 80;
		if(animal instanceof Rabbit) return 80;
		if(animal instanceof Mouse) return 90;
		if(animal instanceof Goat) return 70;
		if(animal instanceof Sheep) return 70;
		if(animal instanceof Boar) return 50;
		if(animal instanceof Buffalo) return 20;
		if(animal instanceof Duck) return 10;
		return 0;
	}
}
