package Island.Entities.Predators;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Herbivorous.*;
import Island.Island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Predator {


	public Wolf(String name, Cell cell, Object lock){
		super(name, cell ,lock);
		weight = 50;
		speed = 3;
		maxSatiety = 8;
	}

	public boolean eat(){
		int successPercent = 0;
		List<Animal> animals = currentLocation.getAnimals();
		for(Animal animal : animals){
			if(animal instanceof Horse) successPercent = 10;
			else if(animal instanceof Deer) successPercent = 15;
			else if(animal instanceof Rabbit) successPercent = 60;
			else if(animal instanceof Mouse) successPercent = 80;
			else if(animal instanceof Goat) successPercent = 60;
			else if(animal instanceof Sheep) successPercent = 70;
			else if(animal instanceof Boar) successPercent = 15;
			else if(animal instanceof Buffalo) successPercent = 10;
			else if(animal instanceof Duck) successPercent = 40;

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
