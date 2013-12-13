package com.example.categoryview;

import java.io.File;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

public class ImportModule extends Activity {

	public String XmlPath;
	private static final int SELECT_PICTURE = 1;
	private String selectedImagePath;
    private String filemanagerstring;
    private Context ctx;
    // XML declaration
    Category cat=new Category();
    CategoryDbAdapter catAd=new CategoryDbAdapter(ctx);
    
    Experiment exp=new Experiment();
    ExperimentDbAdapter expAd=new ExperimentDbAdapter(ctx);
    
    Step step=new Step();
    StepDbAdapter stepAd=new StepDbAdapter(ctx);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_import_module);
		
		
		
	}
	
	public void addNewCategory(View v)
	{	
		Intent i = new Intent(ImportModule.this,AddCategory.class);
		startActivity(i);	 
	}
	public void addNewExperiment(View v)
	{
		Intent i = new Intent(ImportModule.this,AddExperiment.class);
		startActivity(i);
	}
	public void changeEnglish(View v)
	{
		if(MainActivity.languageToLoad.equals("en"))
		{
		Toast.makeText(ImportModule.this,"Language Already Selected",Toast.LENGTH_LONG).show();	
		}
		else
		{
		Locale locale = new Locale(MainActivity.languageToLoad);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config, null);
		MainActivity.languageToLoad="en";
		Intent i = new Intent(ImportModule.this,MainActivity.class);
		startActivity(i);
		finish();
		}
	}
	public void changeHindi(View v)
	{
		if(MainActivity.languageToLoad.equals("hi"))
		{
			Toast.makeText(ImportModule.this,"Language Already Selected",Toast.LENGTH_LONG).show();
		}
		else
		{
		Locale locale = new Locale(MainActivity.languageToLoad);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config, null);
		MainActivity.languageToLoad="hi";
		Intent i = new Intent(ImportModule.this,MainActivity.class);
		startActivity(i);
		finish();
		}
	}
	
	//XML code BY Parag
	
	public void selectXML(View view)
	{
		Intent intent=new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select XML"),SELECT_PICTURE);
		
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
                	Toast.makeText(ImportModule.this,"IF NOW",Toast.LENGTH_LONG).show();
                //	changePath(selectedImagePath);
                	Toast.makeText(ImportModule.this,selectedImagePath + " RIGHT one for you",Toast.LENGTH_LONG).show();
                	XmlPath=selectedImagePath.toString();
                	
                	//changePath(selectedImagePath);
                }
                	//System.out.println("selectedImagePath is the right one for you!");
                else
                {	
                	Toast.makeText(ImportModule.this,"Else NOW",Toast.LENGTH_LONG).show();
                //	changePath(filemanagerstring);
                	Toast.makeText(ImportModule.this,filemanagerstring + " RIGHT one for you",Toast.LENGTH_LONG).show();
                	XmlPath=filemanagerstring.toString();
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
	           
	            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	            cursor.moveToFirst();
	            return cursor.getString(column_index);
	        }
	        else return null;
	}
	//XML Code begins
	public void PassXmlFile(View v)
	{
		Toast.makeText(ImportModule.this,"On my count",Toast.LENGTH_LONG).show();
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file = new File(XmlPath);
            Toast.makeText(ImportModule.this,"On my count2",Toast.LENGTH_LONG).show();
            if (file.exists())
            {
            	Toast.makeText(ImportModule.this,"On my count3",Toast.LENGTH_LONG).show();
            	
            	Document doc = db.parse(file);
                //change 1
            	org.w3c.dom.Element docEle = doc.getDocumentElement();

                // Print root element of the document
              //change 2
                
                Toast.makeText(ImportModule.this,"Root element of the document: "+ ((Node) docEle).getNodeName(),Toast.LENGTH_LONG).show();
                //System.out.println("Root element of the document: "+ ((Node) docEle).getNodeName());
                	
              //change 3
                NodeList categoryList = docEle.getElementsByTagName("category");

                // Print total student elements in document
                
                Toast.makeText(ImportModule.this,"Total Category: " + categoryList.getLength(),Toast.LENGTH_LONG).show();
            //    System.out.println("Total Category: " + categoryList.getLength());

                //Category List
                
                if (categoryList != null && categoryList.getLength() > 0)
                {
                	
                	for (int i = 0; i < categoryList.getLength(); i++)
                    {			
                		
                			function3(categoryList,i,docEle);
					
					}
				}//Category List
	
            Toast.makeText(ImportModule.this,"Out of program",Toast.LENGTH_LONG).show();    

			}
			else
			{
			     	//System.out.println("In else part");
				 	Toast.makeText(ImportModule.this,"NO MATCH",Toast.LENGTH_LONG).show();
			      	System.exit(1);
            } 
			
		}
		catch(Exception e)
		{
			
		} 
	}
	public void function3(NodeList categoryList,int i,org.w3c.dom.Element docEle )
	{

		Node experiment = categoryList.item(i);
		if (experiment.getNodeType() == Node.ELEMENT_NODE)
		{
			org.w3c.dom.Element e =  (org.w3c.dom.Element) experiment;
			NodeList nodeList = e.getElementsByTagName("categoryName");
			String catName=nodeList.item(0).getChildNodes().item(0).getNodeValue();
	//		System.out.println("Category Name: "+ catName);

			nodeList = e.getElementsByTagName("categoryDesc");
			String catDesc=nodeList.item(0).getChildNodes().item(0).getNodeValue();
	//		System.out.println("Category Desc: "+ catDesc );

			//Secod In to experiment details

			NodeList expList = e.getElementsByTagName("experiment");
			//System.out.println("Total Experiment: " + expList.getLength());
			Toast.makeText(ImportModule.this,"Total Experiment: " + expList.getLength(),Toast.LENGTH_LONG).show();
			//Exp List
			if(expList!=null && expList.getLength()>0)
			{
				int cnt1=1;
				for(int j=0;j<expList.getLength();j++)
				{
				
					function2(expList,j,docEle);

					setCategory(catName,catDesc);
					cnt1++;

				}
			}//expList end  */
		}
}
public void function2(NodeList expList,int j,org.w3c.dom.Element docEle)
{
	Node step=expList.item(j);
	if(step.getNodeType()==Node.ELEMENT_NODE)
	{
		//System.out.println("***************************");
		org.w3c.dom.Element e1=(org.w3c.dom.Element)step;

	//	NodeList nodeList1 = e1.getElementsByTagName("expId");
//		System.out.println("Experiment ID: "+ nodeList1.item(0).getChildNodes().item(0).getNodeValue());

		NodeList nodeList1 = e1.getElementsByTagName("expName");
		String expName=nodeList1.item(0).getChildNodes().item(0).getNodeValue();
	//	System.out.println("Experiment Name: "+expName );

		nodeList1 = e1.getElementsByTagName("categoryId");
		int catId=Integer.parseInt(nodeList1.item(0).getChildNodes().item(0).getNodeValue());
	//	System.out.println("Category ID: "+catId );

		//Third in to step details
		NodeList stepList = e1.getElementsByTagName("step");
		int stepNo=stepList.getLength();
		System.out.println("Total Steps: " + stepNo);

		if(stepList!=null && stepList.getLength()>0)
		{
			int cnt2=1;
			for(int k=0;k<stepList.getLength();k++)
			{
				
				function1(stepList,k,docEle);			
				setExperiment(expName,catId,stepNo);
				cnt2++;
			}
		}//step list end
	}
}
public void function1(NodeList stepList,int k,org.w3c.dom.Element docEle)
	{
		Node stepDetails=stepList.item(k);

		if(stepDetails.getNodeType()==Node.ELEMENT_NODE)
		{
			k++;
			
			org.w3c.dom.Element e2=(org.w3c.dom.Element)stepDetails;

			NodeList nodeList3 = e2.getElementsByTagName("stepDesc");
		    String stepDesc=nodeList3.item(0).getChildNodes().item(0).getNodeValue();
		//	System.out.println("Step Desc: "+stepDesc);

			nodeList3 = e2.getElementsByTagName("expId");
			int expId=Integer.parseInt(nodeList3.item(0).getChildNodes().item(0).getNodeValue());
		//	System.out.println("Experiment ID: "+expId );

			nodeList3 = e2.getElementsByTagName("imgLink");
			String imgLink=nodeList3.item(0).getChildNodes().item(0).getNodeValue();
		//	System.out.println("Image Name: "+ imgLink);

			nodeList3 = e2.getElementsByTagName("pdfLink");
			String pdfLink=nodeList3.item(0).getChildNodes().item(0).getNodeValue();
			
			nodeList3 = e2.getElementsByTagName("animationLink");
			String animationLink=nodeList3.item(0).getChildNodes().item(0).getNodeValue();
		//	System.out.println("PDF Name: "+ pdfLink);

			Toast.makeText(ImportModule.this,"Step No: "+k,Toast.LENGTH_LONG).show();	

		
			setStep(stepDesc,expId,imgLink,pdfLink,animationLink);
		}


}





//Here onwards your integration is there. Remove the comments of Adapters. Ok? Say Boom.!
public void setCategory(String name, String desc)
{
	cat.setCatName(name);
	cat.setCatDesc(desc);
	//catAd.addCategory(cat);
	
}	
public void setExperiment(String name,int id,int no)
{
	exp.setExpName(name);
	exp.setCatId(id);
	exp.setNoOfSteps(no);
	//expAd.addExperitemt(exp);
}
public void setStep(String desc,int expId,String imgLink,String pdfLink,String animationLink)
{
	step.setStepDesc(desc);
	step.setExpId(expId);
	step.setImageLink(imgLink);
	step.setPdfLink(pdfLink);
	step.setAnimationLink(animationLink);
	//Vikas or Amit you need to manage only your adapters. I have reach to your adapter level through parameters. Ok? Say Boom.!
//	stepAd.AddStep(step);
}

}
