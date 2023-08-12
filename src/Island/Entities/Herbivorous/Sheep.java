package Island.Entities.Herbivorous;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Plant;
import Island.Island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Sheep extends Herbivorous{


	public Sheep(String name, Cell cell, Object lock){
		super(name, cell,lock);
		weight = 70;
		speed = 3;
		maxSatiety = 15;
	}

	@Override
	public boolean eat(){
		List<Plant> plants = currentLocation.getPlants();
		return eatPlant(plants);
	}
}
