package e14.net.indiantourguide;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import e14.net.indiantourguide.MainActivity.LoadData;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Hotels extends ListActivity {
	
	String READ_URL;
	private static final String TAG_LOO = "loo";
	private static final String TAG_NAME = "name";
	private static final String TAG_ADD = "address";
	private static final String TAG_PHONE = "phoneno";
	private static final String TAG_FAX = "fax";
	private static final String TAG_EMAIL = "emailid";
	private static final String TAG_WEBSITE = "website";
	private static final String TAG_TYPE = "type";
	// Progress Dialog
	private ProgressDialog pDialog;
	
	//json array
	JSONArray dest = null;
	String[] placesArray;
	//private ArrayList<HashMap<String, String>>trainList;
	final ArrayList<HashMap<String,String>> listHotelNames = new ArrayList<HashMap<String,String>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotels);
		
		Bundle extras = getIntent().getExtras();
		String destination = extras.getString("dest");
		String substr=destination.substring(13,destination.length()-1);
		//Toast.makeText(this, "You have chosen the : " + " " + substr, Toast.LENGTH_LONG).show();
		substr = substr.replaceAll(" ","%20");
		READ_URL  = "http://problemsolvingconcepts.com/good-deeds.in/findhotels.php?destination="+substr;
		
		// start the action
		//connection detector
		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		 //Load Data
		 if(cd.isConnectingToInternet())
			{
			new LoadData().execute();
			}
			else
			{
			Toast.makeText(getApplicationContext(), "Please Check For Internet Connection...", Toast.LENGTH_SHORT).show();
			}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hotels, menu);
		return true;
	}
	
	public class LoadData extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Hotels.this);
			pDialog.setMessage("Loading Data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {
			updateJSONdata();
			return null;

		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			pDialog.dismiss();
			
			String[] from = { "name","address","phoneno","fax","email","website","type"};
            int[] to = { R.id.textHotelName,R.id.txtAddress,R.id.txtPhone,R.id.txtFax,R.id.txtEmail,R.id.txtWebsite,R.id.txtType};
            SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), listHotelNames, R.layout.itemhotelname, from, to);         
            setListAdapter(adapter);
		}
	}
	
	public void updateJSONdata() {


		
		try {
			// available
			//Log.d("just reached","Value: >>>>>>>>>>>>>>");
			JSONParser jParser = new JSONParser();
			JSONObject json = jParser.getJSONFromUrl(READ_URL);
			dest = json.getJSONArray(TAG_LOO);			
			placesArray = new String[dest.length()];

			// looping through all posts according to the json object returned
			for (int i = 0; i<(dest.length()); i++) 
			{
				JSONObject c = dest.getJSONObject(i);
				
				// gets the content of each tag
				String name = c.getString(TAG_NAME);
				String address = c.getString(TAG_ADD);
				String phone = c.getString(TAG_PHONE);
				String fax = c.getString(TAG_FAX);
				String email = c.getString(TAG_EMAIL);
				String website = c.getString(TAG_WEBSITE);
				String type = c.getString(TAG_TYPE);
				
				Log.d("fdvs","Value: >>>>>>>>>>>>>>" + type);
				
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();

				map.put(TAG_NAME, name);
				map.put(TAG_ADD, address);
				map.put(TAG_PHONE, phone);
				map.put(TAG_FAX, fax);
				map.put(TAG_EMAIL, email);
				map.put(TAG_WEBSITE, website);
				map.put(TAG_TYPE, type);

				// adding HashList to ArrayList
				listHotelNames.add(map);
				//counter++;
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
