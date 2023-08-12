package Island.Entities.Herbivorous;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Plant;
import Island.Island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Worm extends Herbivorous{


	public Worm(String name, Cell cell, Object lock){
		super(name, cell,lock);
		weight = 0.01;
		speed = 0;
		maxSatiety = 0;
	}

	@Override
	public boolean eat(){
		List<Plant> plants = currentLocation.getPlants();
		return eatPlant(plants);
	}
}
