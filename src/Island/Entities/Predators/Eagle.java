package Island.Entities.Predators;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Herbivorous.Duck;
import Island.Entities.Herbivorous.Mouse;
import Island.Entities.Herbivorous.Rabbit;
import Island.Island;

import java.util.List;

public class Eagle extends Predator {


	public Eagle(String name, Cell cell, Object lock){
		super(name, cell, lock);
		weight = 6;
		speed = 3;
		maxSatiety = 1;
	}

	@Override
	public boolean eat(){
		int successPercent = 0;
		List<Animal> animals = currentLocation.getAnimals();
		for(Animal animal : animals){
			if(animal instanceof Fox) successPercent = 10;
			else if(animal instanceof Rabbit) successPercent = 90;
			else if(animal instanceof Mouse) successPercent = 90;
			else if(animal instanceof Duck) successPercent = 80;

			if(successPercent != 0){
				return catchPrey(successPercent, animals, animal);
			}
		}
		return false;
	}


	@Override
	public void run(){

	}
}
