package Island.Entities;

import Island.Cell;

import java.util.concurrent.atomic.AtomicInteger;

public class Vegetation implements Entity{
	private static final int MAX_PLANTS = 200;
	public AtomicInteger currentPlants;
	int growValue;
	public Cell currentLocation;


	public Vegetation(int initialValue, int growValue, Cell location){
		this.currentPlants = new AtomicInteger(initialValue);
		this.growValue = growValue;
		currentLocation = location;
//		currentLocation.setVegetation(this);
	}

	public void grow(){
		if(currentPlants.addAndGet(growValue) > MAX_PLANTS){
			currentPlants.set(MAX_PLANTS);
		}
	}
}
