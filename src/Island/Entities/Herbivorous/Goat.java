package Island.Entities.Herbivorous;

import Island.Cell;
import Island.Entities.Animal;
import Island.Entities.Plant;
import Island.Island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Goat extends Herbivorous{


	public Goat(String name, Cell cell, Object lock){
		super(name, cell,lock);
		weight = 60;
		speed = 3;
		maxSatiety = 10;
	}

	@Override
	public boolean eat(){
		List<Plant> plants = currentLocation.getPlants();
		return eatPlant(plants);
	}

}
