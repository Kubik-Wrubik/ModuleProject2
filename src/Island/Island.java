package Island;

import Island.Entities.Animal;
import Island.Entities.Herbivorous.Horse;
import Island.Entities.Herbivorous.Worm;
import Island.Entities.Predators.Bear;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Island {
	public int length;
	public int width;
	public int maxCellCapacity;
	public Cell[][] cells;
	Object myLock = new Object();
	private ScheduledExecutorService executor;

	public Island(int length, int width, int capacity){
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

	public static void main(String[] args){
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("set island length: ");
//		int length = scanner.nextInt();
//
//		System.out.print("set island width: ");
//		int width = scanner.nextInt();
//
//		System.out.print("set cell animal capacity: ");
//		int capacity = scanner.nextInt();

		int width = 5;
		int capacity = 5;
		int length = 5;
		Island battleRoyalIsland = new Island(length, width, capacity);

		ThreadLocalRandom rand = ThreadLocalRandom.current();

		Cell bloodyCell = battleRoyalIsland.cells[2][2];

//		new Bear("Mishanya", battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
//		new Bear("Mishanya", battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
//		new Bear("Mishanya", battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
//		new Bear("Mishanya", battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
//		new Horse("MyLittlePony", battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
//		new Horse("MyLittlePony", battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
//		new Horse("MyLittlePony", battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
//		new Horse("MyLittlePony", battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);

//		new Bear("Mishanya", battleRoyalIsland.cells[0][0], battleRoyalIsland.myLock);
//		new Bear("Mishanya", battleRoyalIsland.cells[0][0], battleRoyalIsland.myLock);
//		new Bear("Mishanya", battleRoyalIsland.cells[1][1], battleRoyalIsland.myLock);
		new Bear("Mishanya", battleRoyalIsland.cells[1][1], battleRoyalIsland.myLock);
		new Bear("Mishanya", battleRoyalIsland.cells[2][2], battleRoyalIsland.myLock);
		new Bear("Mishanya", battleRoyalIsland.cells[2][2], battleRoyalIsland.myLock);

		new Worm("Wormix", battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
		new Worm("Wormix", battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
		new Worm("Wormix", battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
		new Worm("Wormix", battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
		new Worm("Wormix", battleRoyalIsland.cells[1][1], battleRoyalIsland.myLock);
		new Worm("Wormix", battleRoyalIsland.cells[1][1], battleRoyalIsland.myLock);
		new Horse("Horse", battleRoyalIsland.cells[rand.nextInt(length)][rand.nextInt(width)], battleRoyalIsland.myLock);
		new Horse("Horse", battleRoyalIsland.cells[1][1], battleRoyalIsland.myLock);
		new Horse("Horse", battleRoyalIsland.cells[1][1], battleRoyalIsland.myLock);

		battleRoyalIsland.executor = Executors.newScheduledThreadPool(10);
		LifeCycleThread lifeCycleThread = new LifeCycleThread("life", battleRoyalIsland, battleRoyalIsland.myLock);
		battleRoyalIsland.executor.scheduleAtFixedRate(lifeCycleThread, 0, 3, TimeUnit.SECONDS);

//			try{
//				while(RUNNING){
//					Thread.sleep(1000);
//				}
//			} catch(InterruptedException e){
//				e.printStackTrace();
//			}

		//TODO add plants(autoGrowing, maxCount)
		//TODO [DONE] add maxCount of moves
		//TODO [DONE] add death by maxAge
		//TODO add death animals (meat) +
		//TODO [DONE] add max animals on one cell
		//TODO [DONE] add javaFX (console output)
		//TODO [DONE]set random spawner
		//TODO realise cell maxCapacity

	}

	public static void stop(Island island){
		island.executor.shutdownNow();
		System.out.println("STOPPED");
	}
}
