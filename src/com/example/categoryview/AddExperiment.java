package com.example.categoryview;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddExperiment extends Activity {
	
	//Declared All Required Variables..
	EditText etExpName;
	EditText etExpDesc,etNoOfSteps;
	Category cat;
	
	//Creating Category Database Object..
	ExperimentDbAdapter expDB = new ExperimentDbAdapter(this);
	
	//creating Global Variable which will have noofsteps value So, we can access it while add Steps.
	static int noOfSteps;

	//This Method will run automatically when AddExperiment Activity Start.
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_experiment);
		
		//Creating objects for each component of AddExperiment Layout
		etExpName = (EditText)findViewById(R.id.etExpName);
		etExpDesc =(EditText)findViewById(R.id.etExpDesc);
		etNoOfSteps=(EditText)findViewById(R.id.addStepDesc);
		Button bAddExp=(Button)findViewById(R.id.bAddExp);
		 
		// Adding onclick event on AddCat Button
		bAddExp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				// Opening the Database connection
				expDB.open();
				
				// Adding the data into Experiment Object
				expDB.addExperiment(new Experiment(etExpName.getText().toString(),etExpDesc.getText().toString(),MainActivity.catClickedId,Integer.parseInt(etNoOfSteps.getText().toString())));
				//expDB.addExperiment(new Experiment("Rabbit","This is the Step Desc", 1, 7));
				
				//Clearing Text Fields
				etExpName.setText("");
				etExpDesc.setText("");
				noOfSteps=Integer.parseInt(etNoOfSteps.getText().toString()); // noOfSteps Global variable
				etNoOfSteps.setText("");
				
				Toast.makeText(AddExperiment.this,"Experiment Added SuccessFully",Toast.LENGTH_LONG).show();
				// Closing Database Collection
				expDB.close();	
				
				//Starting the Add Experiment Activity
				Intent i = new Intent(AddExperiment.this,AddExperimentSteps.class);
				startActivity(i);
				
			}
		});
	}
}
