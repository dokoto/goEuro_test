package events;

import com.mansierra.goeuro.R;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AdapterView.OnItemClickListener;

public class AutoCompleteOnClickItem implements OnItemClickListener
{
	private EditText et_departure_day;
	private AutoCompleteTextView actv_city_from;
	private AutoCompleteTextView actv_city_to;	
	private Button bt_search;
	
	public AutoCompleteOnClickItem(Activity view)
	{
		this.et_departure_day = (EditText) view.findViewById(R.id.layo_main_et_departure_day);
		this.actv_city_from = (AutoCompleteTextView) view.findViewById(R.id.layo_main_actv_city_from);
		this.actv_city_to = (AutoCompleteTextView) view.findViewById(R.id.layo_main_actv_city_to);	
		this.et_departure_day = (EditText)view.findViewById(R.id.layo_main_et_departure_day);
		this.bt_search = (Button) view.findViewById(R.id.layo_main_bt_search);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	{
		if (!actv_city_from.getText().toString().isEmpty() && !actv_city_to.getText().toString().isEmpty() &&
				!et_departure_day.getText().toString().isEmpty())
			bt_search.setEnabled(true);

	}

}
