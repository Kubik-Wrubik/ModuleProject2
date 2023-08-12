package Island.Entities.Herbivorous;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.CanHunt;
import Island.Entities.Plant;
import Island.Island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Mouse extends Herbivorous implements CanHunt {


	public Mouse(String name, Cell cell, Object lock){
		super(name, cell,lock);
		weight = 0.05;
		speed = 1;
		maxSatiety = 0.01;
	}

	@Override
	public boolean eat(){
		int successPercent = 0;
		List<Animal> animals = currentLocation.getAnimals();
		for(Animal animal: animals){
			if(animal instanceof Worm) successPercent = 90;
			if(successPercent != 0){
				if (catchPrey(successPercent,animals,animal)) return true;
				break;
			}
		}

		List<Plant> plants = currentLocation.getPlants();
		return eatPlant(plants);
	}
}
