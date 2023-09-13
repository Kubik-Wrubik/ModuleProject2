package island;

import island.entities.Animal;
import island.entities.Meat;
import island.entities.Vegetation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * this Thread start {@link Animal#run()} methods
 * <p>final field <Code>lock</Code> is given all animals to control animals methods {@link Animal#searchFood()}, {@link Animal#breed()}
 * and {@link Animal#move()} in {@link #run()}
 */
public class LifeCycleThread extends Thread {
	private final Island island;
	private final Object lock;
	private final int sleepingTime;

	/**
	 * cycle init
	 * @param name is the Thread name
	 * @param sleep is the time between methods {@link Animal#searchFood()}, {@link Animal#breed()} and {@link Animal#move()}
	 * @param island is the island which is linked to the cycle
	 * @param lock is the lock for threading animals
	 */
	LifeCycleThread(String name, int sleep, Island island, Object lock){
		super(name);
		sleepingTime = sleep;
		this.island = island;
		this.lock = lock;
	}

	/**
	 * main cycle method.
	 * <p>firstly, draw animals and check if there is someone alive on island.
	 * <p>secondly, refresh animal satiety, breed capability, clean rotten meat and add plants on every cell.
	 * <p>thirdly, control animals methods with a <Code>lock</Code>
	 */
	@Override
	public void run(){
		int animalsCount = drawAndGetAnimals();
		if(animalsCount == 0){
			Island.stop(island);
			return;
		}
		setAnimalsStarve();
		setAnimalsBreedable();
		rottingMeat();
		growingVegetation();

		try{
			runAnimals(animalsCount);
			synchronized(lock){
				lock.wait(sleepingTime);
				lock.notifyAll();
				lock.wait(sleepingTime);
				lock.notifyAll();
			}
			Thread.sleep(sleepingTime);
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * start {@link Animal#run()} for every animal.
	 * @param animals is an amount of animals. this is necessary for accurate thread initialization
	 */
	private void runAnimals(int animals){
		ExecutorService executorService = Executors.newWorkStealingPool(animals);
		for(int i = 0; i < island.getLength(); i++){
			for(int j = 0; j < island.getWidth(); j++){
				for(Animal animal : island.cells[i][j].getAnimals()){
					executorService.submit(animal);
				}
			}
		}
	}

	/**
	 * method gets animals from every cell and show it like an icon
	 * @return animals amount
	 */
	private int drawAndGetAnimals(){
		int animalCount = 0;

		System.out.println();
		for(int i = 0; i < island.getLength(); i++){
			for(int j = 0; j < island.getWidth(); j++){
				StringBuilder builder = new StringBuilder("[");
				for(Animal animal : island.cells[i][j].getAnimals()){
					if(animal.getAge() < animal.getBreedAbleAge())
						builder.append(String.format("{%s}", animal.getIcon()));
					else builder.append(animal.getIcon());
					animalCount++;
				}

				for(Meat meat : island.cells[i][j].getMeat()){
					builder.append(String.format("%s(%.2f)", "\uD83E\uDD69", meat.weight));
				}

				Vegetation vegetation = island.cells[i][j].getVegetation();
				builder.append(String.format("%s(%.2f)", "\uD83C\uDF31", vegetation.currentPlants));

				builder.append("] ");
				System.out.print(builder);
			}
			System.out.println();
		}

		return animalCount;
	}

	/**
	 * calls method {@link Animal#setBreedAble()} for every animal
	 */
	private void setAnimalsBreedable(){
		for(int i = 0; i < island.getLength(); i++){
			for(int j = 0; j < island.getWidth(); j++){
				for(Animal animal : island.cells[i][j].getAnimals()){
					animal.setBreedAble();
				}
			}
		}
	}

	/**
	 * calls method {@link Animal#starve()} for every animal
	 */
	private void setAnimalsStarve(){
		for(int i = 0; i < island.getLength(); i++){
			for(int j = 0; j < island.getWidth(); j++){
				List<Animal> animalsCopy = new ArrayList<>(island.cells[i][j].getAnimals());
				for(Animal animal : animalsCopy){
					animal.starve();
				}
			}
		}
	}

	/**
	 * calls method {@link Meat#gettingRot()} for every meat
	 */
	private void rottingMeat(){
		for(int i = 0; i < island.getLength(); i++){
			for(int j = 0; j < island.getWidth(); j++){
				List<Meat> meatListCopy = new ArrayList<>(island.cells[i][j].getMeat());
				for(Meat meat : meatListCopy){
					meat.gettingRot();
				}
			}
		}
	}

	/**
	 * calls method {@link Vegetation#grow()} for every vegetation
	 */
	private void growingVegetation(){
		for(int i = 0; i < island.getLength(); i++){
			for(int j = 0; j < island.getLength(); j++){
				island.cells[i][j].getVegetation().grow();
			}
		}
	}
}
