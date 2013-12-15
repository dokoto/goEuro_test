package AutoCompletions;

import java.util.ArrayList;
import com.mansierra.utils.LocationImp;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

public class AutoCompleteAdapter extends ArrayAdapter<String> implements Filterable
{
	private ArrayList<String> cities;
	private LocationImp loc;

	public AutoCompleteAdapter(Context context, int textViewResourceId)
	{
		super(context, textViewResourceId);		
	}
	
	public void SetLocation(LocationImp loc)
	{
		this.loc = loc;
	}

	@Override
	public int getCount()
	{
		return cities.size();
	}

	@Override
	public String getItem(int index)
	{
		return cities.get(index);
	}

	@Override
	public Filter getFilter()
	{
		Filter filter = new Filter()
		{
			@Override
			protected FilterResults performFiltering(CharSequence constraint)
			{
				FilterResults filterResults = new FilterResults();
				if (constraint != null)
				{
					cities = RequestCities.request(constraint.toString(), loc.getLocation());
					loc.stopToListenUpdates();
					filterResults.values = cities;
					filterResults.count = cities.size();
				}
				return filterResults;
			}

			@Override
			protected void publishResults(CharSequence constraint, FilterResults results)
			{
				if (results != null && results.count > 0)
				{
					notifyDataSetChanged();
				} else
				{
					notifyDataSetInvalidated();
				}
			}
		};
		return filter;
	}

}
