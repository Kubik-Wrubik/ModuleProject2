package Island.Entities.Predators;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Herbivorous.*;
import Island.Island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Fox extends Predator{


	public Fox(String name, Cell cell, Object lock){
		super(name, cell,lock);
		weight = 8;
		speed = 2;
		maxSatiety = 2;
	}

	@Override
	public boolean eat(){
		int successPercent = 0;
		List<Animal> animals = currentLocation.getAnimals();
		for(Animal animal : animals){
			if(animal instanceof Rabbit) successPercent = 70;
			else if(animal instanceof Mouse) successPercent = 90;
			else if(animal instanceof Duck) successPercent = 60;
			else if(animal instanceof Worm) successPercent = 40;

			if(successPercent != 0){
				return catchPrey(successPercent,animals,animal);
			}
		}
		return false;
	}

	@Override
	public void run(){

	}
}
