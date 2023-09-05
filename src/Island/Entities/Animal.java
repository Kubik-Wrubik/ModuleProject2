package Island.Entities;

import Island.Cell;
import Island.Directions;
import Island.Island;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Animal implements Runnable, Entity{
	private static final AtomicInteger ID = new AtomicInteger(1);
	private final Object lock;

	public final String name;
	public final double weight;
	public final int speed;
	public double currentSatiety;
	public final double maxSatiety;
	public int age = 0;
	public final int maxAge;
	public final int breedAbleAge;
	public Cell currentLocation;
	public volatile boolean hasBred = true;
	public volatile boolean hasEaten = false;

	/**
	 * abstact animal
	 * @param name is name of animal
	 * @param weight is weight of enimal
	 * @param speed is maximum number of {@link #move()} calls
	 * @param maxSatiety is the maximum amount of food that the animal can eat
	 * @param maxAge is the maximum age an animal can reach
	 * @param breedAbleAge is the minimum age from which an animal can breed
	 * @param cell is animal current location
	 * @param lock is necessary for waiting for other animals
	 */
	public Animal(String name, double weight, int speed, double maxSatiety,int maxAge, int breedAbleAge, Cell cell, Object lock){
		this.name = name + "#" + ID.getAndIncrement();
		this.weight = weight;
		this.speed = speed;
		this.maxSatiety = maxSatiety;
		this.currentSatiety = maxSatiety / 2;
		this.maxAge = maxAge;
		this.breedAbleAge = breedAbleAge;
		this.currentLocation = cell;
		this.lock = lock;

		currentLocation.addAnimal(this);

	}

	/**
	 * main animal method.
	 * <p>firstly, checks animal for old age.
	 * <p>secondly, eats and waits for other animals to eat.
	 * <p>thirdly, breed and waits for other animals to breed.
	 * <p>then, moves and getting old.
	 */
	@Override
	public void run(){
		if(age >= maxAge){
			die("died from old age");
			return;
		}
		synchronized(lock){
			searchFood();
			try{
				lock.wait();
				if(this.hasEaten) return;
				breed();
				lock.wait();
				if(this.speed > 0){
					int moves = ThreadLocalRandom.current().nextInt(this.speed);
					for(int i = 0; i < moves; i++)
						move();
				}

			} catch(Exception e){
				e.printStackTrace();
			}
			age++;
		}

	}

	/**
	 * the method determines how the animal can eat. animal eat meat OR hunt and eat meat OR eat vegetation
	 * @return true if the animal ate
	 */
	public abstract boolean searchFood();

	/**
	 * method selects two animals and creates a new animal
	 */
	public void breed(){
		List<Animal> animals = currentLocation.getAnimals();
		for(Animal animal : animals){
			if(animals.size() < currentLocation.maxCapacity
					&& canBread(this, animal)){
				if(!this.hasBred && !animal.hasBred){
					this.hasBred = true;
					animal.hasBred = true;
					ThreadLocalRandom randomNameNumber = ThreadLocalRandom.current();
					Names name = Names.values()[randomNameNumber.nextInt(Names.values().length)];
					Animal newAnimal;
					try{
						Constructor<?> constructor = this.getClass().getConstructor(String.class, Cell.class, Object.class);
						newAnimal = (Animal) constructor.newInstance(name + this.getClass().getSimpleName(), currentLocation, lock);
						System.out.printf("oh my.. %s + %s = %s\n", this.name, animal.name, newAnimal.name);
					} catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e){
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}

	/**
	 * the method selects a valid direction of animal movement(up, down, left, right)
	 */
	public void move(){
		final Island island = currentLocation.getIsland();

		int directionsAmount = 4;
		List<Directions> directionsList = new ArrayList<>(Arrays.asList(Directions.UP, Directions.RIGHT, Directions.DOWN, Directions.LEFT));

		if(currentLocation.positionX == 0){
			directionsList.remove(Directions.LEFT);
			directionsAmount--;
		}
		if(currentLocation.positionX == island.getWidth() - 1){
			directionsList.remove(Directions.RIGHT);
			directionsAmount--;
		}
		if(currentLocation.positionY == 0){
			directionsList.remove(Directions.UP);
			directionsAmount--;
		}
		if(currentLocation.positionY == island.getLength() - 1){
			directionsList.remove(Directions.DOWN);
			directionsAmount--;
		}

		currentLocation.removeAnimal(this);

		ThreadLocalRandom rand = ThreadLocalRandom.current();
		Directions direction = directionsList.get(rand.nextInt(directionsAmount));
		if(direction == Directions.UP){
			limitMoving(island.cells[currentLocation.positionY - 1][currentLocation.positionX], island.getMaxCellCapacity());
		} else if(direction == Directions.DOWN){
			limitMoving(island.cells[currentLocation.positionY + 1][currentLocation.positionX], island.getMaxCellCapacity());
		} else if(direction == Directions.LEFT){
			limitMoving(island.cells[currentLocation.positionY][currentLocation.positionX - 1], island.getMaxCellCapacity());
		} else if(direction == Directions.RIGHT){
			limitMoving(island.cells[currentLocation.positionY][currentLocation.positionX + 1], island.getMaxCellCapacity());
		}

		currentLocation.addAnimal(this);
	}

	/**
	 * @param animal1 first animal
	 * @param animal2 second animal
	 * @return true if the animals belong to the same class and are not the same animal
	 */
	private boolean canBread(Animal animal1, Animal animal2){
		return animal1.getClass() == animal2.getClass()
				&& !animal1.equals(animal2);
	}

	/**
	 * limit moving animal by maxCapacity
	 * @param cell animal's current cell
	 * @param capacity max animal capacity at one cell
	 */
	private void limitMoving(Cell cell, int capacity){
		if(cell.getAnimals().size() < capacity) currentLocation = cell;
	}

	/**
	 * if the animal is old enough to allow it to have offspring
	 */
	public void setBreedAble(){
		if(age >= breedAbleAge)
			hasBred = false;
	}

	/**
	 * reduce <code>satiety</code> by 10% of <code>maxSatiety</code>
	 */
	public void starve(){
		double epsilon = Math.pow(10,3);
		currentSatiety -= Math.round(maxSatiety/10 * epsilon) / epsilon;
		if(currentSatiety <= 0) die("dead by starve");
	}

	/**
	 * @param lastWord show cause of death
	 * @return meat of died animal
	 */
	public Meat die(String lastWord){
		this.hasEaten = true;
		currentLocation.removeAnimal(this);
		System.out.println(this.name + " " + lastWord);
		return new Meat(this);
	}
}
