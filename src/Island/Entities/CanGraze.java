package Island.Entities;

import java.util.List;

public interface CanGraze {
	default boolean graze(Vegetation vegetation){
		if(vegetation.currentPlants.get() > 0){
			vegetation.currentPlants.decrementAndGet();
			return true;
		}
		return false;
	}
	default void eating(Animal animal, Vegetation vegetation){
		double wantToEat = animal.maxSatiety - animal.currentSatiety;
		int gotSatiety = 0;
		while(graze(vegetation)){
			gotSatiety++;
			if(gotSatiety>wantToEat) break;
		}
		System.out.println(animal.name + " eaten " + gotSatiety + " plants!");
		animal.currentSatiety += gotSatiety;
	}
}
