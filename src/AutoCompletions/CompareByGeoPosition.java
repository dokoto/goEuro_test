package AutoCompletions;

import java.util.Comparator;

public class CompareByGeoPosition implements Comparator<CityPositions>
{

	@Override
	public int compare(CityPositions city1, CityPositions city2)
	{
		if (city1.DistanceFromCurrentLocation > city2.DistanceFromCurrentLocation)
			return 1;
		else if (city1.DistanceFromCurrentLocation < city2.DistanceFromCurrentLocation)
			return -1;
		else
			return 0;
	}

}
