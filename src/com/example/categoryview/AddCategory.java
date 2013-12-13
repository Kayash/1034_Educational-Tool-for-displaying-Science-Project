package com.example.categoryview;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddCategory extends Activity {
	
	//Declared All Required Variables..
	EditText etExpName;
	EditText etCatDesc,txt2;
	Category cat;
	
	//Creating Category Database Object..
	CategoryDbAdapter catdb = new CategoryDbAdapter(this);
	
	//This Method will run automatically when AddCategory Activity Start.
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_category);
		
		// Creating the objects
		 etExpName = (EditText)findViewById(R.id.etExpName);
		 etCatDesc =(EditText)findViewById(R.id.etCatDesc);
		 Button bAddCat=(Button)findViewById(R.id.bAddExp);
		 
		// Adding onclick event on AddCat Button
		bAddCat.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				// Opening the Database connection
				catdb.open();
				
				// Adding the data into Category Object
				CategoryDbAdapter.countNoOfCat++;
				cat = new Category(CategoryDbAdapter.countNoOfCat,etExpName.getText().toString(),etCatDesc.getText().toString(),0);
				//cat=new Category(1,"Motion","This is Testing for Motion",0);
				
				// Adding the data into category table..
				catdb.addCategory(cat);
				Toast.makeText(AddCategory.this,"Category Added SuccessFully",Toast.LENGTH_LONG).show();
				etExpName.setText("");
				etCatDesc.setText("");
				
				// Closing The Connection.
				catdb.close();	
				
			}
		});
	
	}

	
}
