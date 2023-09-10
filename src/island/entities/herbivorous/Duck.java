package island.entities.herbivorous;

import island.Cell;
import island.entities.Animal;
import island.entities.CanHunt;
import island.entities.Vegetation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Duck extends Herbivorous implements CanHunt {
	private final int chanceToGetWorm = 90;
	private final Map<Class<? extends Animal>, Integer> preys = new HashMap<>(){{
		put(Worm.class, chanceToGetWorm);
	}};

	public Duck(Cell cell, Object lock){
		super( 1, 4, 0.15, 5, 1, cell, lock);
	}

	@Override
	public boolean searchFood(){
		if(eatRandomMeat(this)) return true;

		List<Animal> animals = getCurrentLocation().getAnimals();
		for(Animal animal : animals){
			int successPercent = getPrey(animal);
			if(successPercent != 0){
				return catchPrey(this, successPercent, animal);
			}
		}

		Vegetation vegetation = getCurrentLocation().getVegetation();
		if(vegetation.currentPlants > 0){
			graze(this, vegetation);
			return true;
		}

		return false;
	}

	@Override
	public int getPrey(Animal animal){
		Class<? extends Animal> preyClass = animal.getClass();
		if(preys.containsKey(preyClass))
			return preys.get(preyClass);
		return 0;
	}
}
