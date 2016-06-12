package e14.net.indiantourguide;



import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class InfoActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		
		
		Bundle extras = getIntent().getExtras();
		String ndest = extras.getString("name");
		
		//Toast.makeText(this, "in info act we got : " + " " + ndest, Toast.LENGTH_LONG).show();
		
		
        TabHost tabHost = getTabHost();
        
        // Tab for Photos
        TabSpec hotelspec = tabHost.newTabSpec("Hotels");
        hotelspec.setIndicator("Find Hotels", getResources().getDrawable(R.drawable.travelagentlogo));
        Intent photosIntent = new Intent(this, Hotels.class);
        photosIntent.putExtra("dest", ndest);
        hotelspec.setContent(photosIntent);
        
        // Tab for Songs
        TabSpec travelAgentspec = tabHost.newTabSpec("Travel Agents");
        // setting Title and Icon for the Tab
        travelAgentspec.setIndicator("Find Service", getResources().getDrawable(R.drawable.travelagentlogo));
        Intent songsIntent = new Intent(this, TravelAgents.class);
        songsIntent.putExtra("dest", ndest);
        travelAgentspec.setContent(songsIntent);
        
        // Tab for Videos
        TabSpec funspec = tabHost.newTabSpec("Fun");
        funspec.setIndicator("Info", getResources().getDrawable(R.drawable.travelagentlogo));
        Intent videosIntent = new Intent(this, FunZone.class);
        videosIntent.putExtra("dest", ndest);
        funspec.setContent(videosIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(hotelspec); // Adding photos tab
        tabHost.addTab(travelAgentspec); // Adding songs tab
        tabHost.addTab(funspec); // Adding videos tab
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info, menu);
		return true;
	}

}
