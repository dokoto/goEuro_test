package com.mansierra.goeuro;

import com.mansierra.utils.LocationImp;
import events.AutoCompleteOnClickItem;
import events.ButtonOnClick;
import events.DelayDaysOnClick;
import events.EditTextOnChange;
import AutoCompletions.AutoCompleteAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Main extends FragmentActivity
{

	public LocationImp loc;
	private AutoCompleteTextView actv_city_from;
	private AutoCompleteTextView actv_city_to;
	private ImageButton ib_departure_day;
	private Button bt_search;
	private EditText et_departure_day;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layo_main);
		InitVars();
		InitEvents();
	}

	private void InitVars()
	{
		loc = new LocationImp(this);
		loc.beginToListenUpdates();
		actv_city_from = (AutoCompleteTextView) findViewById(R.id.layo_main_actv_city_from);
		actv_city_to = (AutoCompleteTextView) findViewById(R.id.layo_main_actv_city_to);
		ib_departure_day = (ImageButton) findViewById(R.id.layo_main_ib_departure_day);
		bt_search = (Button) findViewById(R.id.layo_main_bt_search);
		et_departure_day = (EditText) findViewById(R.id.layo_main_et_departure_day);
		bt_search.setEnabled(false);
	}

	private void InitEvents()
	{
		AutoCompleteAdapter cityFrom = new AutoCompleteAdapter(this, R.layout.auto_complete_list_items);
		cityFrom.SetLocation(loc);
		actv_city_from.setAdapter(cityFrom);
		actv_city_from.setOnFocusChangeListener(new EditTextOnChange((Activity)this));
		actv_city_from.setOnItemClickListener(new AutoCompleteOnClickItem((Activity)this));
		
		AutoCompleteAdapter cityTo = new AutoCompleteAdapter(this, R.layout.auto_complete_list_items);
		cityTo.SetLocation(loc);
		actv_city_to.setAdapter(cityTo);
		actv_city_to.setOnFocusChangeListener(new EditTextOnChange((Activity)this));
		actv_city_to.setOnItemClickListener(new AutoCompleteOnClickItem((Activity)this));

		ib_departure_day.setOnClickListener(new DelayDaysOnClick(getSupportFragmentManager(), (Activity)this));
		et_departure_day.setOnFocusChangeListener(new EditTextOnChange((Activity)this));
		
		bt_search.setOnClickListener(new ButtonOnClick((Activity)this));

	}

}
