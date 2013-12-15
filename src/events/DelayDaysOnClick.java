package events;

import java.util.Calendar;

import com.mansierra.goeuro.R;
import com.mansierra.utils.DatePickerFragment;

import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.support.v4.app.FragmentManager;

public class DelayDaysOnClick implements OnClickListener
{

	private FragmentManager manager;
	private EditText et_departure_day;
	private AutoCompleteTextView actv_city_from;
	private AutoCompleteTextView actv_city_to;	
	private Button bt_search;
	public DelayDaysOnClick(FragmentManager manager, Activity view)
	{
		this.manager = manager;
		this.et_departure_day = (EditText) view.findViewById(R.id.layo_main_et_departure_day);
		this.actv_city_from = (AutoCompleteTextView) view.findViewById(R.id.layo_main_actv_city_from);
		this.actv_city_to = (AutoCompleteTextView) view.findViewById(R.id.layo_main_actv_city_to);	
		this.et_departure_day = (EditText)view.findViewById(R.id.layo_main_et_departure_day);
		this.bt_search = (Button) view.findViewById(R.id.layo_main_bt_search);
	}
	
	@Override
	public void onClick(View view)
	{		
		DatePickerFragment date = new DatePickerFragment();
		Calendar calender = Calendar.getInstance();		
		Bundle args = new Bundle();
		args.putInt("year", calender.get(Calendar.YEAR));
		args.putInt("month", calender.get(Calendar.MONTH));
		args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
		date.setArguments(args);
		date.setCallBack(new OnDateSetListener()
		{
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
			{
				et_departure_day.setText(((dayOfMonth < 10) ? "0" : "") + String.valueOf(dayOfMonth) + "/" + (((monthOfYear+1) < 10) ? "0" : "")
						+ String.valueOf(monthOfYear+1) + "/" + String.valueOf(year));
				if (!actv_city_from.getText().toString().isEmpty() && !actv_city_to.getText().toString().isEmpty() &&
						!et_departure_day.getText().toString().isEmpty())
					bt_search.setEnabled(true);
			}
		});
		date.show(manager, "Date Picker");	
	}

}
