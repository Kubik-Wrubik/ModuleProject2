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
		super(name, cell,lock);
		weight = 15;
		speed = 1;
		maxSatiety = 3;
	}

	@Override
	public boolean eat(){
		int successPercent = 0;
		List<Animal> animals = currentLocation.getAnimals();
		for(Animal animal : animals){
			if(animal instanceof Fox) successPercent = 15;
			else if(animal instanceof Rabbit) successPercent = 20;
			else if(animal instanceof Mouse) successPercent = 40;
			else if(animal instanceof Duck) successPercent = 10;

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
