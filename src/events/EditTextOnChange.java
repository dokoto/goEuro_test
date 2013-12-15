package events;

import com.mansierra.goeuro.R;

import android.app.Activity;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class EditTextOnChange implements OnFocusChangeListener
{
	private AutoCompleteTextView actv_city_from;
	private AutoCompleteTextView actv_city_to;
	private EditText et_departure_day;
	private Button bt_search;
	public EditTextOnChange(Activity view)
	{	
		actv_city_from = (AutoCompleteTextView) view.findViewById(R.id.layo_main_actv_city_from);
		actv_city_to = (AutoCompleteTextView) view.findViewById(R.id.layo_main_actv_city_to);	
		et_departure_day = (EditText)view.findViewById(R.id.layo_main_et_departure_day);
		bt_search = (Button) view.findViewById(R.id.layo_main_bt_search);
	}
	@Override
	public void onFocusChange(View v, boolean hasFocus)
	{
		// TODO Auto-generated method stub
		if (!actv_city_from.getText().toString().isEmpty() && !actv_city_to.getText().toString().isEmpty() &&
				!et_departure_day.getText().toString().isEmpty())
			bt_search.setEnabled(true);
			
		
	}

}
