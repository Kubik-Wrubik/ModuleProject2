package Island;

import Island.Entities.Animal;
import Island.Entities.Meat;
import Island.Entities.Vegetation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Cell{
	private final Island island;
	public final int positionX;
	public final int positionY;
	public final int maxCapacity;
	private final Vegetation vegetation;
	List<Animal> animals = new ArrayList<>();
	final List<Meat> meatList = new ArrayList<>();

	public Cell(Island island, int positionX, int positionY){
		this.island = island;
		this.positionX = positionX;
		this.positionY = positionY;
		maxCapacity = island.maxCellCapacity;

		ThreadLocalRandom rand = ThreadLocalRandom.current();
		vegetation = new Vegetation(rand.nextInt(0,200), rand.nextInt(10,70), this);
	}

	public List<Animal> getAnimals(){
		return animals;
	}
	public synchronized void addAnimal(Animal animal){
		animals.add(animal);
	}
	public synchronized void removeAnimal(Animal animal){
		animals.remove(animal);
	}

	public Vegetation getVegetation(){
		return vegetation;
	}
//	public void setVegetation(Vegetation vegetation){
//		this.vegetation = vegetation;
//	}

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
