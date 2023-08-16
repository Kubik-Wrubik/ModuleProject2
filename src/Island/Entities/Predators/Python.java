package Island.Entities.Predators;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Herbivorous.Duck;
import Island.Entities.Herbivorous.Horse;
import Island.Entities.Herbivorous.Mouse;
import Island.Entities.Herbivorous.Rabbit;
import Island.Island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Python extends Predator{


	public Python(String name, Cell cell, Object lock){
		super(name, 15, 1, 3,10, 2, cell, lock);
	}

	public int getPrey(Animal animal){
		if(animal instanceof Fox) return 15;
		else if(animal instanceof Rabbit) return 20;
		else if(animal instanceof Mouse) return 40;
		else if(animal instanceof Duck) return 10;
		return 0;
	}
}
