package Island;

import Island.Entities.Animal;
import Island.Entities.Meat;
import Island.Entities.Vegetation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class LifeCycleThread extends Thread {
	public Island island;
	public final Object lock;

	public LifeCycleThread(String name, Island island, Object lock){
		super(name);
		this.island = island;
		this.lock = lock;
	}

	@Override
	public void run(){
		int animalsCount = graphicOutput();
		if (animalsCount == 0){
			Island.stop(island);
			return;
		}

		setAnimalsBreedable();
		setAnimalsStarve();
		rottingMeat();
		growingVegetation();

		try{
			runAnimals(animalsCount);
			synchronized(lock){
				lock.wait(500);
				lock.notifyAll();
				lock.wait(500);
				lock.notifyAll();
			}
			Thread.sleep(500);
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private void runAnimals(int animals){
		ExecutorService executorService = Executors.newWorkStealingPool(animals);
		for(int i = 0; i < island.length; i++){
			for(int j = 0; j < island.width; j++){
				for(Animal animal : island.cells[i][j].getAnimals()){
					executorService.submit(animal);
				}
			}
		}
	}
	private int consoleOutput(){
		int animalCount = 0;

		for(int i = 0; i < island.length; i++){
			for(int j = 0; j < island.width; j++){
				for(Animal animal : island.cells[i][j].getAnimals()){
					System.out.printf("%s[%d,%d] ", animal.name, animal.currentLocation.positionY, animal.currentLocation.positionX);
					animalCount++;
				}
			}
		}
		System.out.println();
		return animalCount;
	}
	private int graphicOutput(){
		int animalCount = 0;

		System.out.println();
		for(int i = 0; i < island.length; i++){
			for(int j = 0; j < island.width; j++){
				StringBuilder builder = new StringBuilder("[");
				for(Animal animal : island.cells[i][j].getAnimals()){
					if(animal.age < animal.breedAbleAge) builder.append("{" + PseudoGraphic.iconTransform(animal) + "}");
					else builder.append(PseudoGraphic.iconTransform(animal));
					animalCount++;
				}

				for(Meat meat : island.cells[i][j].getMeat()){
					builder.append(PseudoGraphic.iconTransform(meat) + "(" + meat.weight + ")");
				}

				Vegetation vegetation = island.cells[i][j].getVegetation();
				builder.append(PseudoGraphic.iconTransform(vegetation) + "(" + vegetation.currentPlants + ")");

				builder.append("] ");
				System.out.print(builder);
			}
			System.out.println();
		}

		return animalCount;
	}
	private void setAnimalsBreedable(){
		for(int i = 0; i < island.length; i++){
			for(int j = 0; j < island.width; j++){
				for(Animal animal : island.cells[i][j].getAnimals()){
					animal.setBreedAble();
				}
			}
		}
	}
	private void setAnimalsStarve(){
		for(int i = 0; i < island.length; i++){
			for(int j = 0; j < island.width; j++){
				List<Animal> animalsCopy = new ArrayList<>(island.cells[i][j].getAnimals());
				for(Animal animal : animalsCopy){
					animal.starve();
				}
			}
		}
	}
	private void rottingMeat(){
		for(int i = 0; i < island.length; i++){
			for(int j = 0; j < island.width; j++){
				List<Meat> meatListCopy = new ArrayList<>(island.cells[i][j].getMeat());
				for(Meat meat : meatListCopy){
					meat.gettingRot();
				}
			}
		}
	}

	private void growingVegetation(){
		for(int i = 0; i < island.length; i++){
			for(int j = 0; j < island.width; j++){
				island.cells[i][j].getVegetation().grow();
			}
		}
	}
}
