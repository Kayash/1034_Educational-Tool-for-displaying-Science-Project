package com.example.categoryview;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class ExperimentStepsView extends Activity {

	TabHost th;
	
	// This Variable will have no of steps to be added.
	static int a;
	//It will have current clicked tag index
	int currentTab;
	VideoView videoView;
	// Creating Step Database Object
	StepDbAdapter stepdb = new StepDbAdapter(this);
	
	// It will all step collection list.
	List<Step> stepData=new ArrayList<Step>();
	
	//Variable which Will have Video and Pdf URL.
	String videoPath,pdfPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_experiment_steps_view);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.header);

		// TaskBar text button setting the text,color
		TextView txt = (TextView) findViewById(R.id.textScreenName);
		txt.setText("Experiment             Details");
		txt.setTextSize(17);
		txt.setTextColor(Color.parseColor("#DC143C"));
		txt.setBackgroundResource(R.drawable.tab_button_blue);
		
		//Geeting all data from Step Table
		
		stepdb.open();
		stepData=(List<Step>) stepdb.readStep(ExperimentView.expClickedId);
		stepdb.close();

		// Setting up TabHost View
		th = (TabHost) findViewById(R.id.tabhost);
		th.setup();
		
		//Adding Experiment Name in List View
		TextView stepExpName=(TextView)findViewById(R.id.stepExpName);
		stepExpName.setText("Title : "+ExperimentView.expClickedName);

		// Adding step button to tabhost
		TabSpec ts = th.newTabSpec("tag1");
		ts.setContent(R.id.steps);
		ts.setIndicator("");
		th.addTab(ts);

		// Making First tag invisible
		th.getTabWidget().getChildAt(0).setVisibility(View.GONE);
		
		// Adding Button to TabHost According to need of Button
		for (int i = 1; i <= a; i++) {
			TabSpec our = th.newTabSpec("tag1");
			our.setContent(new TabHost.TabContentFactory() {

				@Override
				// On Create of Tab
				public View createTabContent(String tag) {
					return (null);
				}
			});

			our.setIndicator("  STEP " + i + "  ");
			th.addTab(our);
		}// End of Loop

		// Adding onClick Listener to the tab buttons
		for (int i = 1; i <= a; i++) {
			
			final int myTabIndex = i;
			th.getTabWidget().getChildAt(myTabIndex)
					.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							currentTab=myTabIndex;
							changeData();
							//if(videoView.isShown())
							//{
							//videoView.stopPlayback();
							//videoView.setVisibility(videoView.INVISIBLE);
							//}
							
							//th.setCurrentTab(currentTab);
							//th.getTabWidget().focusCurrentTab(currentTab);
							
						}
					});
			
			
			//Seeting the tab text size and color
			TextView x = (TextView) th.getTabWidget().getChildAt(i)
					.findViewById(android.R.id.title);
			x.setTextSize(20);
			x.setTextColor(Color.parseColor("#990000"));
			
		}// end loop
		
		
		// Adding References tab button in Tab Host also adding onClick listener
		/*TabSpec our = th.newTabSpec("tag1");
		our.setContent(new TabHost.TabContentFactory() {
			@Override
			// On Create of Tab
			public View createTabContent(String tag) {
				return (null);
			}
		});
		our.setIndicator("   REFERENCES   ");
		th.addTab(our);
           
		TextView txtReferences = (TextView) th.getTabWidget().getChildAt(a + 1)
				.findViewById(android.R.id.title);
		txtReferences.setTextSize(23);
		txtReferences.setTextColor(Color.parseColor("#FFF000"));
		
		th.getTabWidget().getChildAt(a+1)
		.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			 //Do code For references button
			}
		});
		
		//for(int i=1;i<=a+1;i++)
		//for(int k=1;k<5;k++)
			//th.getTabWidget().getChildAt(11).setFocusableInTouchMode(true);
*/
		//DO First Tag clicked
				currentTab=1;
				changeData();
				
				
				
		
		// Backward button onclick event
		Button backButton = (Button) findViewById(R.id.bForward);
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(currentTab<a)
				++currentTab;
				else
				Toast.makeText(ExperimentStepsView.this,"YOU ARE ON LAST STEP",Toast.LENGTH_LONG).show();
				changeData();
			}
		});

		// Forward button onclick event
		Button forwardButton = (Button) findViewById(R.id.bBackward);
		forwardButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(currentTab>1)
				--currentTab;
				else
				Toast.makeText(ExperimentStepsView.this,"YOU ARE ON FIRST STEP",Toast.LENGTH_LONG).show();
				changeData();	
			}
		});

		// Code for Home Button
		Button homeButton = (Button) findViewById(R.id.homeButton);
		homeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(ExperimentStepsView.this,
						MainActivity.class);
				startActivity(i);
				finish();

			}
		});

		// Code for Close Button..
		Button closeButton = (Button) findViewById(R.id.closeButton);
		closeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		// Code for Setting Button...
		Button addExpButton = (Button) findViewById(R.id.settingButton);
		addExpButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(ExperimentStepsView.this,
						ExperimentStepsView.class);
				startActivity(i);
				finish();
			}
		});
		
		//Code for View Animation Button
		
		Button viewAnimation=(Button)findViewById(R.id.bViewAnimation);
		viewAnimation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				videoView=(VideoView) findViewById(R.id.videoAnimation);
				
				videoView.setVisibility(videoView.VISIBLE);
				
				
				MediaController mc=new MediaController(ExperimentStepsView.this);
				mc.setAnchorView(videoView);
				mc.setMediaPlayer(videoView);
				
				Uri uri=Uri.parse(videoPath);
				
				videoView.setMediaController(mc);
				videoView.setVideoURI(uri);
				
				videoView.start();
			}
		});
		
		//code for view pdf button 
		
		Button bViewPdf=(Button) findViewById(R.id.bViewPdf);
		bViewPdf.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				File file = new File(pdfPath);

                if (file.exists()) {
                    Uri path = Uri.fromFile(file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    try {
                        startActivity(intent);
                    } 
                    catch (ActivityNotFoundException e) {

                    }
                }
			}
		});
			
	}	
	
	// Method which change data of Step View on Current TAb

	public void changeData() {
		
		//Change Data into the StepDesc,Image View,Video Button,Pdf Button.
		TextView sExpDesc = (TextView) findViewById(R.id.stepExpDesc);
		//Toast.makeText(ExperimentStepsView.this,stepData.get(currentTab-1).getStepDesc(), Toast.LENGTH_LONG).show();
		sExpDesc.setText(stepData.get(currentTab-1).getStepDesc());
		ImageView sImage = (ImageView) findViewById(R.id.stepImageView);
		//Toast.makeText(ExperimentStepsView.this,stepData.get(currentTab-1).getImageLink(), Toast.LENGTH_LONG).show();
		sImage.setImageURI(Uri.fromFile(new File(stepData.get(currentTab-1).getImageLink())));
		
		videoPath=stepData.get(currentTab-1).getAnimationLink();
		pdfPath=stepData.get(currentTab-1).getPdfLink();
		
		//Setting up the Color 
		for (int k = 1; k <= a; k++) {
			if (currentTab == k) {
				TextView txt = (TextView) th.getTabWidget().getChildAt(k)
						.findViewById(android.R.id.title);
				txt.setTextColor(Color.parseColor("#33FFFF"));
				th.getTabWidget().getChildAt(k)
						.setBackgroundColor(Color.parseColor("#87CEEB"));
			} else
				th.getTabWidget().getChildAt(k)
						.setBackgroundColor(Color.parseColor("#6495ED"));

		}
	}
	
	// ***** Tried Zoom in Zoom out in Image View (NOT WORKING) *****
	
			/*ImageView sImage = (ImageView) findViewById(R.id.stepImageView);
			sImage.setOnTouchListener((OnTouchListener) ExperimentStepsView.this);
			
			 /*
		     * --------------------------------------------------------------------------
		     * Method: spacing Parameters: MotionEvent Returns: float Description:
		     * checks the spacing between the two fingers on touch
		     * ----------------------------------------------------
		     */

		    /*float spacing(MotionEvent event) 
		    {
		        float x = event.getX(0) - event.getX(1);
		        float y = event.getY(0) - event.getY(1);
		        return FloatMath.sqrt(x * x + y * y);
		    }

		    /*
		     * --------------------------------------------------------------------------
		     * Method: midPoint Parameters: PointF object, MotionEvent Returns: void
		     * Description: calculates the midpoint between the two fingers
		     * ------------------------------------------------------------
		     */

		    /*void midPoint(PointF point, MotionEvent event) 
		    {
		        float x = event.getX(0) + event.getX(1);
		        float y = event.getY(0) + event.getY(1);
		        point.set(x / 2, y / 2);
		    }

		    /** Show an event in the LogCat view, for debugging */
		    /*void dumpEvent(MotionEvent event) 
		    {
		        String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE","POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
		        StringBuilder sb = new StringBuilder();
		        int action = event.getAction();
		        int actionCode = action & MotionEvent.ACTION_MASK;
		        sb.append("event ACTION_").append(names[actionCode]);

		        if (actionCode == MotionEvent.ACTION_POINTER_DOWN || actionCode == MotionEvent.ACTION_POINTER_UP) 
		        {
		            sb.append("(pid ").append(action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
		            sb.append(")");
		        }

		        sb.append("[");
		        for (int i = 0; i < event.getPointerCount(); i++) 
		        {
		            sb.append("#").append(i);
		            sb.append("(pid ").append(event.getPointerId(i));
		            sb.append(")=").append((int) event.getX(i));
		            sb.append(",").append((int) event.getY(i));
		            if (i + 1 < event.getPointerCount())
		                sb.append(";");
		        }

		        sb.append("]");
		        Log.d("Touch Events ---------", sb.toString());
		    }

			
			boolean onTouch(View v, MotionEvent event) 
		    {
		        ImageView view = (ImageView) v;
		        view.setScaleType(ImageView.ScaleType.MATRIX);
		        float scale;

		        dumpEvent(event);
		        // Handle touch events here...

		        switch (event.getAction() & MotionEvent.ACTION_MASK) 
		        {
		            case MotionEvent.ACTION_DOWN:   // first finger down only
		                                                savedMatrix.set(matrix);
		                                                start.set(event.getX(), event.getY());
		                                                Log.d(TAG, "mode=DRAG"); // write to LogCat
		                                                mode = DRAG;
		                                                break;

		            case MotionEvent.ACTION_UP: // first finger lifted

		            case MotionEvent.ACTION_POINTER_UP: // second finger lifted

		                                                mode = NONE;
		                                                Log.d(TAG, "mode=NONE");
		                                                break;

		            case MotionEvent.ACTION_POINTER_DOWN: // first and second finger down

		                                                oldDist = spacing(event);
		                                                Log.d(TAG, "oldDist=" + oldDist);
		                                                if (oldDist > 5f) {
		                                                    savedMatrix.set(matrix);
		                                                    midPoint(mid, event);
		                                                    mode = ZOOM;
		                                                    Log.d(TAG, "mode=ZOOM");
		                                                }
		                                                break;

		            case MotionEvent.ACTION_MOVE:

		                                                if (mode == DRAG) 
		                                                { 
		                                                    matrix.set(savedMatrix);
		                                                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y); // create the transformation in the matrix  of points
		                                                } 
		                                                else if (mode == ZOOM) 
		                                                { 
		                                                    // pinch zooming
		                                                    float newDist = spacing(event);
		                                                    Log.d(TAG, "newDist=" + newDist);
		                                                    if (newDist > 5f) 
		                                                    {
		                                                        matrix.set(savedMatrix);
		                                                        scale = newDist / oldDist; // setting the scaling of the
		                                                                                    // matrix...if scale > 1 means
		                                                                                    // zoom in...if scale < 1 means
		                                                                                    // zoom out
		                                                        matrix.postScale(scale, scale, mid.x, mid.y);
		                                                    }
		                                                }
		                                                break;
		        }

		        view.setImageMatrix(matrix); // display the transformation on screen

		        return true; // indicate event was handled
		    }*/
		  // ***** End of  Zoom in Zoom out Code.. *****
	      
			// ***** I tried another code for Zoom in Zoom Out (NOT WORKING) *****
			
			//ImageView iv = (ImageView) findViewById(R.id.stepImageView);  
		    //iv.setOnTouchListener(new Touch());
	// ***** End of Second Code of Zoom in Zoom out in Image View *****	
}
