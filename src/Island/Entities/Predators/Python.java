package Island.Entities.Predators;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Herbivorous.Duck;
import Island.Entities.Herbivorous.Mouse;
import Island.Entities.Herbivorous.Rabbit;

public class Python extends Predator {
	public Python(String name, Cell cell, Object lock){
		super(name, 15, 1, 3, 10, 2, cell, lock);
	}

	public int getPrey(Animal animal){
		if(animal instanceof Fox) return 15;
		if(animal instanceof Rabbit) return 20;
		if(animal instanceof Mouse) return 40;
		if(animal instanceof Duck) return 10;
		return 0;
	}
}
