package Island.Entities.Predators;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Herbivorous.*;
import Island.Island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Bear extends Predator{

	public Bear(String name, Cell cell, Object lock){

		super(name, cell,lock);
		weight = 500;
		speed = 2;
		maxSatiety = 80;
	}

	@Override
	public synchronized boolean eat(){
		int successPercent = 0;
		List<Animal> animals = currentLocation.getAnimals();
		for(Animal animal : animals){ //TODO may get not actual data
			if(animal instanceof Python) successPercent = 80;
			else if(animal instanceof Horse) successPercent = 40;
			else if(animal instanceof Deer) successPercent = 80;
			else if(animal instanceof Rabbit) successPercent = 80;
			else if(animal instanceof Mouse) successPercent = 90;
			else if(animal instanceof Goat) successPercent = 70;
			else if(animal instanceof Sheep) successPercent = 70;
			else if(animal instanceof Boar) successPercent = 50;
			else if(animal instanceof Buffalo) successPercent = 20;
			else if(animal instanceof Duck) successPercent = 10;

			if(successPercent != 0){
				return catchPrey(successPercent,animals,animal);
			}
		}
		return false;
	}
}
