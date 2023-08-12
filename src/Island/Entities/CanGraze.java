package Island.Entities;

import java.util.List;

public interface CanGraze {
	default boolean eatPlant(List<Plant> plants){
		if(plants.size()>0){
			plants.remove(0);
			return true;
		}
		return false;
	}
}
