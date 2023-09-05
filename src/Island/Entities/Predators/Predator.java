package Island.Entities.Predators;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.CanHunt;

import java.util.List;

public abstract class Predator extends Animal implements CanHunt {
	public Predator(String name, double weight, int speed, double maxSatiety, int maxAge, int breedAbleAge, Cell cell, Object lock){
		super(name, weight, speed, maxSatiety, maxAge, breedAbleAge, cell, lock);
	}

	public synchronized boolean searchFood(){
		if(eatRandomMeat(this)) return true;

		List<Animal> animals = currentLocation.getAnimals();
		for(Animal animal : animals){
			int successPercent = getPrey(animal);
			if(successPercent != 0){
				return catchPrey(this, successPercent, animal);
			}
		}
		return false;
	}
}
