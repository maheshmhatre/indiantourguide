package e14.net.indiantourguide;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	private static final String READ_URL  = "http://problemsolvingconcepts.com/good-deeds.in/indiantourguide.php?";
	// Progress Dialog
	private ProgressDialog pDialog;
	//tags
	private static final String TAG_LOO = "loo";
	private static final String TAG_DEST = "destination";

	//json array
	JSONArray dest = null;
	String[] placesArray;
	//private ArrayList<HashMap<String, String>>trainList;
	final ArrayList<HashMap<String,String>> listDest = new ArrayList<HashMap<String,String>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//WebView	webView = (WebView) findViewById(R.id.webViewDest);
		//webView.getSettings().setJavaScriptEnabled(true);
		//webView.loadUrl("http://wikitravel.org/en/File:HarmandirSahib_Gateway.JPG");
		
		/*ImageView iv = (ImageView)findViewById(R.id.imageView1);
try
{
		URL url = new URL("http://wikitravel.org/en/File:HarmandirSahib_Gateway.JPG");
		InputStream content = (InputStream)url.getContent();
		Drawable d = Drawable.createFromStream(content , "src"); 
		iv.setImageDrawable(d);
}catch (Exception e) {
	e.printStackTrace();
}*/
		
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
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
		
	protected void onListItemClick(ListView l, View v, int position, long id) {

	    super.onListItemClick(l, v, position, id);
	    Object o = this.getListAdapter().getItem(position);
	    String pen = o.toString();
	    //Toast.makeText(this, "clicked chosen the : " + " " + pen, Toast.LENGTH_LONG).show();
	    Intent mIntent = new Intent(this, InfoActivity.class);
	    mIntent.putExtra("name",pen);
	    startActivity(mIntent);
	}
	
	public class LoadData extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
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
			String[] from = { "destination"};
            int[] to = { R.id.txtDestination};
            SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), listDest, R.layout.itemdestination, from, to);         
            setListAdapter(adapter);
		}
	}
	
	public void updateJSONdata() {

		JSONParser jParser = new JSONParser();
		JSONObject json = jParser.getJSONFromUrl(READ_URL);
		
		try {

			// available
			dest = json.getJSONArray(TAG_LOO);			
			placesArray = new String[dest.length()];

			// looping through all posts according to the json object returned
			for (int i = 0; i<(dest.length()); i++) 
			{
				JSONObject c = dest.getJSONObject(i);

				// gets the content of each tag
				String type = c.getString(TAG_DEST);
				
				Log.d("abcd","Value: >>>>>>>>>>>>>>" + type);
				
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();

				map.put(TAG_DEST, type);

				// adding HashList to ArrayList
				listDest.add(map);
				//counter++;
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
