package Island.Entities.Herbivorous;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Plant;
import Island.Island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Horse extends Herbivorous{


	public Horse(String name, Cell cell, Object lock){
		super(name, cell,lock);
		weight = 400;
		speed = 4;
		maxSatiety = 60;
	}

	@Override
	public boolean eat(){
		List<Plant> plants = currentLocation.getPlants();
		return eatPlant(plants);
	}
}
