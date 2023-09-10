package island;

import island.entities.Animal;
import island.entities.herbivorous.*;
import island.entities.predators.*;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Island {
	private final int length;
	private final int width;
	private final int maxCellCapacity;
	public Cell[][] cells;
	private final Object myLock = new Object();
	private ScheduledExecutorService executor;

	/**
	 * creates an island with a shape of a rectangle
	 * @param length - length
	 * @param width - width
	 * @param capacity - max amount of animals on one cell
	 */
	private Island(int length, int width, int capacity){
		this.length = length;
		this.width = width;
		maxCellCapacity = capacity;
		cells = new Cell[length][width];
		for(int i = 0; i < length; i++){
			for(int j = 0; j < width; j++){
				cells[i][j] = new Cell(this, j, i);
			}
		}
	}

	/**
	 * length, width and capacity will be written in the console
	 * animals will be added in the code
	 * <p>sleepBetweenAnimalMethodCycles field means the time between running {@link Animal#searchFood()}, {@link Animal#breed()} and {@link Animal#move()}
	 * in a run method {@link Animal#run()}
	 * <p>sleepBetweenLifeCycles field means time between all run methods {@link Animal#run()}
	 */
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.print("set island length(~5): ");
		 int length = scanner.nextInt();

		System.out.print("set island width(~5): ");
		 int width = scanner.nextInt();

		System.out.print("set max animals at one cell(~5): ");
		 int capacity = scanner.nextInt();

		Island battleRoyalIsland = new Island(length, width, capacity);

		ThreadLocalRandom rand = ThreadLocalRandom.current();

		new Bear( battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
		new Bear( battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);

		new Eagle( battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
		new Eagle( battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);

		new Fox(battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
		new Fox(battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);

		new Python(battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
		new Python(battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
		new Horse( battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
		new Horse( battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);

		new Worm( battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
		new Worm(battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);

		new Duck( battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
		new Duck( battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);

		int sleepBetweenAnimalMethodCycles = 500;
		int sleepBetweenLifeCycles = 3;

		battleRoyalIsland.executor = Executors.newScheduledThreadPool(1);
		LifeCycleThread lifeCycleThread = new LifeCycleThread("life",sleepBetweenAnimalMethodCycles, battleRoyalIsland, battleRoyalIsland.myLock);
		battleRoyalIsland.executor.scheduleAtFixedRate(lifeCycleThread, 0, sleepBetweenLifeCycles, TimeUnit.SECONDS);

	}

	/**
	 * stop life cycle when all animals are died. This method are called from {@link LifeCycleThread}
	 */
	 static void stop(Island island){
		island.executor.shutdownNow();
		System.out.println("STOPPED");
	}

	public int getLength(){
		return length;
	}
	public int getWidth(){
		return width;
	}
	public int getMaxCellCapacity(){
		return maxCellCapacity;
	}
}
