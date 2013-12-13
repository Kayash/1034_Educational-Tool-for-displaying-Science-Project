package com.example.categoryview;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

//import com.example.database.AddStep;

//import com.example.database.AddStep;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddExperimentSteps extends Activity {
	
	EditText stepDesc,stepImage,stepPdf,stepVideo;
	TextView stepNo;
	public int i;
	
	
	public File destFilePath1,destFilePath;
	
	//Variable for to get all Path...
	private String selectedImagePath;
    private String filemanagerstring;
    public File sourceFilePath;
    private static final int SELECT_PICTURE = 1;
	private static final String EXTRA_MESSAGE = "Call from Main Activity";
	//my declaration [Parag]
	File sdCard = Environment.getExternalStorageDirectory();
	//WorkingFold is first parameter [Category name]
	//File directory = new File (sdCard.getAbsolutePath() +"/WorkingFold");
	
	File directory = new File (sdCard.getAbsolutePath() +"/Chhote Scientist");
//	File directory = new File (sdCard.getAbsolutePath() +"/CategoryFolder");
	boolean success=directory.mkdirs();
	    
	    
	//   File destFilePath = new File(Environment.getExternalStorageDirectory().toString() + "/New Folder");
	//NewFolder is second parameter [Experiment Name]
	File FilePath = new File (directory,"/Category"+ MainActivity.catClickedId);
	boolean success1=FilePath.mkdirs();
	public File DestFile=new File(FilePath,"/Experiment"+ExperimentDbAdapter.newExpId); 
	boolean success2=DestFile.mkdir();
	
	
	
	
	
	// Here you can go deeper in directory structure.
	//File FilePath = new File (sdCard.getAbsolutePath() +"/ChhoteScientist"+"/Category"+ MainActivity.catClickedId+"/Experiment"+ExperimentView.expClickedId);
	//
	//File destFilePath = new File(FilePath,"/"+strName);
	//**
	

	StepDbAdapter stepdb = new StepDbAdapter(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_experiment_steps);
		
		stepDesc = (EditText) findViewById(R.id.addStepDesc);
		stepImage =(EditText) findViewById(R.id.etXmlPath);
		stepPdf=(EditText) findViewById(R.id.addStepPdf);
		stepVideo=(EditText) findViewById(R.id.addStepVideo);
		stepNo=(TextView) findViewById(R.id.tvStepNo);
		Button bAddStep=(Button) findViewById(R.id.bAddStep);
		i=1;
		stepNo.setText("Step No : "+i);
		
		bAddStep.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				stepdb.open();
				Step s=new Step(stepDesc.getText().toString(),stepImage.getText().toString(),stepPdf.getText().toString(),stepVideo.getText().toString());
				stepdb.addStep(s);
				stepdb.close();
				
				Toast.makeText(AddExperimentSteps.this,"Step No " + i+ " Added Successfully",Toast.LENGTH_LONG).show();
				if(i==AddExperiment.noOfSteps)
				{
					Toast.makeText(AddExperimentSteps.this,"All Step Added",Toast.LENGTH_LONG).show();
					/*try {
					   wait(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					Intent intent=new Intent(AddExperimentSteps.this,ExperimentView.class);
					startActivity(intent);
					finish();
				}	
				else
				{
				i++;
				stepNo.setText("Step No : "+i);
				}
				
				stepDesc.setText("");
				stepImage.setText("");
				stepPdf.setText("");
				stepVideo.setText("");
				
				
		      
			}
		});

		
// add data Three Button ..... Parag...............		
		//on click of BrowseImage button...
		
		/* Button bBrowseImage=(Button) findViewById(R.id.bBrowseImage);
		bBrowseImage.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				
				stepImage.setText("");
			}
		}); */
	}
	public void selectImage(View view) throws IOException
	{
		/* destFilePath1 = new File(DestFile,"/Step"+i);
		destFilePath=new File(destFilePath1+"/step_image"+i+".jpg");
		stepImage.setText(destFilePath.toString()); */
		
		//destFilePath.createNewFile();
		//stepImage.setText(destFilePath.toString());
		//Toast.makeText(AddExperimentSteps.this,"Destination Created: "+destFilePath.toString(),Toast.LENGTH_LONG).show();
		Intent intent=new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		//Toast.makeText(AddExperimentSteps.this,"U want to image select?",Toast.LENGTH_LONG).show();
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_PICTURE);
		destFilePath1 = new File(DestFile,"/Step"+i);
		boolean s3=destFilePath1.mkdir();
		destFilePath=new File(destFilePath1+"/step_image.jpg");
		//boolean s4=destFilePath.mkdir();
		stepImage.setText(destFilePath.toString());
		
		
		//Toast.makeText(AddExperimentSteps.this,"Destination Created: "+destFilePath.toString(),Toast.LENGTH_LONG).show();
		
	}
	public void selectPdf(View view)
	{
		//destFilePath = new File(DestFile,"/Step"+i+"/step_pdf"+i+".pdf");
		//stepPdf.setText(destFilePath.toString());
		Intent intent=new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select PDF"),SELECT_PICTURE);
		
		destFilePath1 = new File(DestFile,"/Step"+i);
		boolean s3=destFilePath1.mkdir();
		destFilePath=new File(destFilePath1+"/step_pdf.pdf");
		
	//	destFilePath = new File(DestFile,"/Step"+i+"/step_pdf"+i+".pdf");
		stepPdf.setText(destFilePath.toString());
	}
	public void selectAnimation(View view)
	{
		destFilePath = new File(DestFile,"/Step"+i+"/step_video"+i+".mp4");
		stepVideo.setText(destFilePath.toString());
		Intent intent=new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Video/Animation"),SELECT_PICTURE);
		
		destFilePath1 = new File(DestFile,"/Step"+i);
		boolean s3=destFilePath1.mkdir();
		destFilePath=new File(destFilePath1+"/step_video.mp4");
		//destFilePath = new File(DestFile,"/Step"+i+"/step_video"+i+".mp4");
	    stepVideo.setText(destFilePath.toString());
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == RESULT_OK) 
		{
            if (requestCode == SELECT_PICTURE)
            {
                Uri selectedImageUri = data.getData();

                //OI FILE Manager
                filemanagerstring = selectedImageUri.getPath();
                	
                //MEDIA GALLERY
                selectedImagePath = getPath(selectedImageUri);
                
            	

               try
               {
            	   
               
                if(selectedImagePath!=null)
                {	
                //	Toast.makeText(AddExperimentSteps.this,"IF NOW",Toast.LENGTH_LONG).show();
                //	changePath(selectedImagePath);
                	Toast.makeText(AddExperimentSteps.this,"Image Path: "+selectedImagePath,Toast.LENGTH_LONG).show();
                	sourceFilePath=new File(selectedImagePath);
            //    	Toast.makeText(AddExperimentSteps.this,"Now Function Call from Image",Toast.LENGTH_LONG).show();
                	this.changePath(sourceFilePath, destFilePath1);
                	//changePath(selectedImagePath);
                }
                	//System.out.println("selectedImagePath is the right one for you!");
                else
                {	
                //	Toast.makeText(AddExperimentSteps.this,"Else NOW",Toast.LENGTH_LONG).show();
                //	changePath(filemanagerstring);
          //      	Toast.makeText(AddExperimentSteps.this,"File Path: "+filemanagerstring,Toast.LENGTH_LONG).show();
                	sourceFilePath=new File(filemanagerstring);
                	//Toast.makeText(AddExperimentSteps.this,"Now Function Call form File",Toast.LENGTH_LONG).show();
                	//Toast.makeText(AddExperimentSteps.this,"SourceFile: "+sourceFilePath+"Dest File: "+DestFile,Toast.LENGTH_LONG).show();
                	this.changePath(sourceFilePath, destFilePath);
                    //System.out.println("filemanagerstring is the right one for you!");
                }
                //Toast.makeText(AddStep.this,"Going for next function..! Boom.!",Toast.LENGTH_LONG).show();
                
               	}
               catch(Exception e)
               {
            	   
               }
                
     		    
            }
        }
	}
	
	public String getPath(Uri uri)
	{
		 String[] projection = { MediaStore.Images.Media.DATA };
	        Cursor cursor = managedQuery(uri, projection, null, null, null);
	        if(cursor!=null)
	        {
	            //HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
	            //THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
	            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	            cursor.moveToFirst();
	            return cursor.getString(column_index);
	        }
	        else return null;
	}
	public void changePath(File sourceFile,File destFile1) throws IOException
	{
		//File sourceFile,File destFile
		//Toast.makeText(AddExperimentSteps.this,"Change Path",Toast.LENGTH_LONG).show();
		
		
		//File selectedImagePath,File destFilePath
		if (!sourceFile.exists()) 
		{
			//Toast.makeText(AddExperimentSteps.this,"Return from source..! Boom.!",Toast.LENGTH_LONG).show();
			return;
		}
		else
			//Toast.makeText(AddExperimentSteps.this,"Source alredy Available",Toast.LENGTH_LONG).show();
		
		if (!destFile1.exists()) 
		{
			//Toast.makeText(AddExperimentSteps.this,"//Creating new Dest Boom.!",Toast.LENGTH_LONG).show();
			destFile1.createNewFile();
		}
			//try
		//	{
			
			//Toast.makeText(AddExperimentSteps.this,"Creating new Dest Boom.!",Toast.LENGTH_LONG).show();
		//	} 
		//	catch(Exception e)
		//	{
		//		Toast.makeText(AddExperimentSteps.this,""+e,Toast.LENGTH_LONG).show();
		//	}
			//Toast.makeText(AddExperimentSteps.this,"Creating new Dest Boom.!",Toast.LENGTH_LONG).show();
		
		/*else
			Toast.makeText(AddExperimentSteps.this,"Destination alredy Available",Toast.LENGTH_LONG).show();*/
		
		FileChannel source = null;
		FileChannel destination = null;
		//Toast.makeText(AddExperimentSteps.this,"File Channel..! ",Toast.LENGTH_LONG).show();
		source = new FileInputStream(sourceFile).getChannel();
		//Toast.makeText(AddExperimentSteps.this,"Source channel available! Say Boom! ",Toast.LENGTH_LONG).show();
		destination = new FileOutputStream(destFile1).getChannel();
		Toast.makeText(AddExperimentSteps.this,"Added File Succesfully",Toast.LENGTH_LONG).show();
		if (destination != null && source != null) 
		{
			destination.transferFrom(source, 0, source.size());
		}
		if (source != null) 
		{
			source.close();
		}
		if (destination != null) 
		{
			destination.close();
		} 
	}

	
	
}
