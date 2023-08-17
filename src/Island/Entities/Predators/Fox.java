package Island.Entities.Predators;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Herbivorous.Duck;
import Island.Entities.Herbivorous.Mouse;
import Island.Entities.Herbivorous.Rabbit;
import Island.Entities.Herbivorous.Worm;

public class Fox extends Predator {
	public Fox(String name, Cell cell, Object lock){
		super(name, 8, 2, 2, 3, 2, cell, lock);
	}

	public int getPrey(Animal animal){
		if(animal instanceof Rabbit) return 70;
		if(animal instanceof Mouse) return 90;
		if(animal instanceof Duck) return 60;
		if(animal instanceof Worm) return 40;
		return 0;
	}
}
