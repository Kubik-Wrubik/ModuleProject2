package island.entities;

import island.Cell;
import island.Directions;
import island.Island;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Animal implements Runnable{
	private static final AtomicInteger animalID = new AtomicInteger(1);
	protected final Object lock;
	private final String name;
	private final double weight;
	private final int speed;
	private final double maxSatiety;
	private final int maxAge;
	private final int breedAbleAge;
	private int age = 0;
	private double currentSatiety;
	private Cell currentLocation;
	private volatile boolean hasBred = true;
	private volatile boolean hasEaten = false;

	/**
	 * abstact animal
	 * @param weight is weight of enimal
	 * @param speed is maximum number of {@link #move()} calls
	 * @param maxSatiety is the maximum amount of food that the animal can eat
	 * @param maxAge is the maximum age an animal can reach
	 * @param breedAbleAge is the minimum age from which an animal can breed
	 * @param cell is animal current location
	 * @param lock is necessary for waiting for other animals
	 */
	public Animal(double weight, int speed, double maxSatiety,int maxAge, int breedAbleAge, Cell cell, Object lock){
		this.name = this.getClass().getSimpleName() + "#" + animalID.getAndIncrement();
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
	 * the method determines how the animal can eat. animal eat meat OR hunt and eat meat OR eat vegetation
	 * @return true if the animal ate
	 */
	public abstract boolean searchFood();

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

	public double getMaxSatiety(){
		return maxSatiety;
	}
	public double getWeight(){
		return weight;
	}
	public String getName(){
		return name;
	}
	public double getCurrentSatiety(){
		return currentSatiety;
	}
	public void setCurrentSatiety(double currentSatiety){
		this.currentSatiety = currentSatiety;
	}
	public int getAge(){
		return age;
	}
	public int getBreedAbleAge(){
		return breedAbleAge;
	}
	public Cell getCurrentLocation(){
		return currentLocation;
	}

	/**
	 * method selects two animals and creates a new animal
	 */
	public void breed(){
		List<Animal> animals = currentLocation.getAnimals();
		for(Animal animal : animals){
			if(animals.size() < currentLocation.getMaxCapacity() && canBread(this, animal)){
				if(!this.hasBred && !animal.hasBred){
					this.hasBred = true;
					animal.hasBred = true;
					Animal newAnimal;
					try{
						Constructor<?> constructor = this.getClass().getConstructor(String.class, Cell.class, Object.class);
						newAnimal = (Animal) constructor.newInstance( this.getClass().getSimpleName(), currentLocation, lock);
						System.out.printf("%s + %s = %s\n", this.name, animal.name, newAnimal.name);
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

		int directionsAmount = Directions.values().length;
		List<Directions> directionsList = new ArrayList<>(Arrays.asList(Directions.UP, Directions.RIGHT, Directions.DOWN, Directions.LEFT));

		if(currentLocation.getPositionX() == 0){
			directionsList.remove(Directions.LEFT);
			directionsAmount--;
		}
		if(currentLocation.getPositionX() == island.getWidth() - 1){
			directionsList.remove(Directions.RIGHT);
			directionsAmount--;
		}
		if(currentLocation.getPositionY() == 0){
			directionsList.remove(Directions.UP);
			directionsAmount--;
		}
		if(currentLocation.getPositionY() == island.getLength() - 1){
			directionsList.remove(Directions.DOWN);
			directionsAmount--;
		}

		currentLocation.removeAnimal(this);

		ThreadLocalRandom rand = ThreadLocalRandom.current();
		Directions direction = directionsList.get(rand.nextInt(directionsAmount));
		if(direction == Directions.UP){
			limitMoving(island.cells[currentLocation.getPositionY() - 1][currentLocation.getPositionX()], island.getMaxCellCapacity());
		} else if(direction == Directions.DOWN){
			limitMoving(island.cells[currentLocation.getPositionY() + 1][currentLocation.getPositionX()], island.getMaxCellCapacity());
		} else if(direction == Directions.LEFT){
			limitMoving(island.cells[currentLocation.getPositionY()][currentLocation.getPositionX() - 1], island.getMaxCellCapacity());
		} else if(direction == Directions.RIGHT){
			limitMoving(island.cells[currentLocation.getPositionY()][currentLocation.getPositionX() + 1], island.getMaxCellCapacity());
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
