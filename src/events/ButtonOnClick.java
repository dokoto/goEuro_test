package events;

import com.mansierra.goeuro.R;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ButtonOnClick implements OnClickListener
{
	private Activity view;
	private EditText et_departure_day;
	private AutoCompleteTextView actv_city_from;
	private AutoCompleteTextView actv_city_to;	
	private Button bt_search;
	
	public ButtonOnClick(Activity view)
	{
		this.view = view;
		this.et_departure_day = (EditText) view.findViewById(R.id.layo_main_et_departure_day);
		this.actv_city_from = (AutoCompleteTextView) view.findViewById(R.id.layo_main_actv_city_from);
		this.actv_city_to = (AutoCompleteTextView) view.findViewById(R.id.layo_main_actv_city_to);	
		this.et_departure_day = (EditText)view.findViewById(R.id.layo_main_et_departure_day);
		this.bt_search = (Button) view.findViewById(R.id.layo_main_bt_search);
	}
	@Override
	public void onClick(View arg0)
	{		
		Toast.makeText(view.getApplicationContext(), "Search is not yet implemented, Sorry.", Toast.LENGTH_LONG).show();
		et_departure_day.setText("");
		actv_city_from.setText("");
		actv_city_to.setText("");
		bt_search.setEnabled(false);
		actv_city_from.requestFocus();
	}

}
