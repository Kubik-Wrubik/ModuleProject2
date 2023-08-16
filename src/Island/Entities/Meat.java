package Island.Entities;

import Island.Cell;

public class Meat implements Entity{
	public String deadAnimal;
	public double weight;
	public int years = 0;
	public int rotingYear = 3;
	public Cell currentLocation;

	public Meat(Animal animal){
		deadAnimal = animal.name;
		weight = animal.weight;
		currentLocation = animal.currentLocation;
		currentLocation.addMeat(this);
	}

	public void gettingRot(){
		if(rotingYear <= years){
			currentLocation.removeMeat(this);
			System.out.println(deadAnimal + "'s meat has rotten!");
		}else years++;
	}
}
