package Island;

import Island.Entities.Animal;
import Island.Entities.Meat;
import Island.Entities.Vegetation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Container for storage animals, meat and vegetation.
 */
public class Cell {
	private final Island island;
	public final int positionX;
	public final int positionY;
	public final int maxCapacity;
	private final Vegetation vegetation;
	private final List<Animal> animals = new ArrayList<>();
	private final List<Meat> meatList = new ArrayList<>();

	/**
	 *<code>maxCapacity</code> is taken from island settings
	 *<p><code>vegetation</code> is initializes with random values
	 * @param island is the island which contain many cells
	 * @param positionX is the cell position along the x-axis
	 * @param positionY is the cell position along the y-axis
	 */
	Cell(Island island, int positionX, int positionY){
		this.island = island;
		this.positionX = positionX;
		this.positionY = positionY;
		maxCapacity = island.getMaxCellCapacity();

		ThreadLocalRandom rand = ThreadLocalRandom.current();
		vegetation = new Vegetation(rand.nextInt(30, 200), rand.nextInt(10, 40));
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

	public List<Meat> getMeat(){
		return meatList;
	}

	public synchronized void addMeat(Meat meat){
		meatList.add(meat);
	}

	public synchronized void removeMeat(Meat meat){
		meatList.remove(meat);
	}

	public Vegetation getVegetation(){
		return vegetation;
	}

	public Island getIsland(){
		return island;
	}
}
