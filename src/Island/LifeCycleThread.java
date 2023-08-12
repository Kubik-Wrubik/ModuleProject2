package Island;

import Island.Entities.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class LifeCycleThread extends Thread {
	public Island island;
	public final Object lock;
	public boolean isSomeoneAlive = true;

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
		try{
			boolean flag = false;
			ExecutorService executorService = Executors.newWorkStealingPool(animalsCount);
			for(int i = 0; i < island.length; i++){
				for(int j = 0; j < island.width; j++){
					for(Animal animal : island.cells[i][j].getAnimals()){
						flag = true;
						executorService.submit(animal);
						animalsCount++;
					}
				}
			}
			if(!flag){
				isSomeoneAlive = false;
				interrupt();			//TODO isn't work
			}
				synchronized(lock){
					lock.wait(500);
					lock.notifyAll();
					lock.wait(500);
					lock.notifyAll();
				}
				Thread.sleep(500);
			Thread.sleep(500);
			setAllBreedable();
		} catch(Exception ex){
			ex.printStackTrace();
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
					if(animal.age < animal.breedableAge) builder.append("{" + PseudoGraphic.iconTransform(animal) + "}");
					else builder.append(PseudoGraphic.iconTransform(animal));

					animalCount++;
				}
				builder.append("] ");
				System.out.print(builder);
			}
			System.out.println();
		}

		return animalCount;
	}

	private void setAllBreedable(){
		for(int i = 0; i < island.length; i++){
			for(int j = 0; j < island.width; j++){
				for(Animal animal : island.cells[i][j].getAnimals()){
					if(animal.age >= animal.breedableAge)
						animal.hasBred = false;
				}
			}
		}
	}
}
