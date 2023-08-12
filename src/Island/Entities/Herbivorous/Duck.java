package Island.Entities.Herbivorous;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.CanHunt;
import Island.Entities.Plant;
import Island.Island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Herbivorous implements CanHunt {


	public Duck(String name, Cell cell, Object lock){
		super(name, cell,lock);
		weight = 1;
		speed = 4;
		maxSatiety = 0.15;
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
