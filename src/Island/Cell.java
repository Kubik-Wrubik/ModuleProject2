package Island;

import Island.Entities.Animal;
import Island.Entities.Meat;
import Island.Entities.Plant;

import java.util.ArrayList;
import java.util.List;

public class Cell{
	private final Island island;
	public final int positionX;
	public final int positionY;
	public final int maxCapacity;
	List<Animal> animals = new ArrayList<>();
	List<Plant> plants = new ArrayList<>();
	List<Meat> meatList = new ArrayList<>();

	public Cell(Island island, int positionX, int positionY){
		this.island = island;
		this.positionX = positionX;
		this.positionY = positionY;
		maxCapacity = island.maxCellCapacity;
	}

	public synchronized List<Animal> getAnimals(){
		return animals;
	}
	public synchronized void addAnimal(Animal animal){
		animals.add(animal);
	}
	public synchronized void removeAnimal(Animal animal){
		animals.remove(animal);
	}

	public List<Plant> getPlants(){
		return plants;
	}
	public synchronized void addPlants(Plant plant){
		plants.add(plant);
	}
	public synchronized void removePlant(Plant animal){
		plants.remove(animal);
	}

	public List<Meat> getMeat(){
		return meatList;
	}
	public synchronized void addMeat(Meat meat){
		meatList.add(meat);
	}
	public synchronized void removeMeat(Meat meat){
		meatList.remove(meat);
	}

	public Island getIsland(){
		return island;
	}
}
