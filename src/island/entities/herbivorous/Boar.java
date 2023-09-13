package island.entities.herbivorous;

import island.Cell;
import island.entities.Animal;
import island.entities.CanHunt;
import island.entities.Vegetation;
import island.entities.predators.Python;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Boar extends Herbivorous implements CanHunt {
	public final String icon = "\uD83D\uDC17";
	private final int chanceToGetMouse = 50;
	private final int chanceToGetWorm = 90;
	private final Map<Class<? extends Animal>, Integer> preys = new HashMap<>(){{
		put(Mouse.class, chanceToGetMouse);
		put(Worm.class, chanceToGetWorm);
	}};
	public Boar(Cell cell, Object lock){
		super( 400, 2, 50, 15, 2, cell, lock);
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

	@Override
	public String getIcon(){
		return icon;
	}
}
