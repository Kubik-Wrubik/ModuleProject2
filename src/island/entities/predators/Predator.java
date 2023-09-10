package island.entities.predators;

import island.Cell;
import island.entities.Animal;
import island.entities.CanHunt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Predator extends Animal implements CanHunt {
	protected final Map<Class<? extends Animal>, Integer> preys = new HashMap<>();

	public Predator(double weight, int speed, double maxSatiety, int maxAge, int breedAbleAge, Cell cell, Object lock){
		super(weight, speed, maxSatiety, maxAge, breedAbleAge, cell, lock);
	}

	public synchronized boolean searchFood(){
		if(eatRandomMeat(this)) return true;

		List<Animal> animals = getCurrentLocation().getAnimals();
		for(Animal animal : animals){
			int successPercent = getPrey(animal);
			if(successPercent != 0){
				return catchPrey(this, successPercent, animal);
			}
		}
		return false;
	}

	public int getPrey(Animal animal){
		Class<? extends Animal> preyClass = animal.getClass();
		if(preys.containsKey(preyClass))
			return preys.get(preyClass);
		return 0;
	}
}
