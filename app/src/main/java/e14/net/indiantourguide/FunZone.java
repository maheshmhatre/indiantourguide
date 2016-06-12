package e14.net.indiantourguide;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.Toast;

public class FunZone extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fun_zone);
		
		Bundle extras = getIntent().getExtras();
		String destination = extras.getString("dest");
		//Toast.makeText(this, "You have chosen the : " + " " + destination, Toast.LENGTH_LONG).show();
		
		String substr=destination.substring(13,destination.length()-1);
		//Toast.makeText(this, "You have chosen the : " + " " + substr, Toast.LENGTH_LONG).show();
		substr = "http://m.wikitravel.org/en/" + substr;
		
		WebView	webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(substr);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fun_zone, menu);
		return true;
	}

}
