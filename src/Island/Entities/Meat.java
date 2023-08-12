package Island.Entities;

import Island.Cell;

public class Meat implements Runnable{
	public String deadAnimal;
	public double weight;
	public int years = 0;
	public int rotingYears = 3;
	public Cell currentLocation;

	public Meat(Animal animal){
		deadAnimal = animal.name;
		weight = animal.weight;
		currentLocation = animal.currentLocation;
		currentLocation.addMeat(this);
	}

	@Override
	public void run(){
		if(rotingYears == years){
			currentLocation.removeMeat(this);
		}
		years++;
	}
}
