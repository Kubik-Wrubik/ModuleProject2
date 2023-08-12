package Island.Entities.Herbivorous;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.CanHunt;
import Island.Entities.Meat;
import Island.Entities.Plant;
import Island.Island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Boar extends Herbivorous implements CanHunt {
	public Boar(String name, Cell cell, Object lock){
		super(name, cell ,lock);
		weight = 400;
		speed = 2;
		maxSatiety = 50;
	}

	@Override
	public boolean eat(){
		Meat meat;

		int successPercent = 0;
		List<Animal> animals = currentLocation.getAnimals();
		for(Animal animal: animals){
			if(animal instanceof Mouse) successPercent = 50;
			if(animal instanceof Worm) successPercent = 90;

			if(successPercent != 0){
				if (catchPrey(successPercent,animals,animal)) return true;
				break;
			}
		}

		List<Plant> plants = currentLocation.getPlants();
		return eatPlant(plants);
	}



	@Override
	public void run(){
	}
}
