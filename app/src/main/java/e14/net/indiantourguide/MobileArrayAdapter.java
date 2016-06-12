package e14.net.indiantourguide;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MobileArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] typevalues;


	public MobileArrayAdapter(Context context, String[] typevalues) {
		super(context, R.layout.itemdestination, typevalues);
		this.context = context;
		this.typevalues = typevalues;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		String []ar=typevalues[position].split("[,]");
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.itemdestination, parent, false);
		TextView textdest = (TextView) rowView.findViewById(R.id.txtDestination);
		//TextView textrate = (TextView) rowView.findViewById(R.id.rating);
		//TextView textrev = (TextView) rowView.findViewById(R.id.review);
		//ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView1);
		textdest.setText(ar[0]);
		//textrev.setText(ar[1]);
		//texttype.setText(ar[2]);

		// Change icon based on name
	/*	String s = typevalues[position];
		Log.e("str>>>>>>>>>>>>>>>",ar[0]+"-"+ar[1]+"-"+ar[2]);
		System.out.println(s);

		if (ar[2].equalsIgnoreCase("l"))
		{
			imageView.setImageResource(R.drawable.l);
		}
		else if (ar[2].equalsIgnoreCase("b")) 
		{
			imageView.setImageResource(R.drawable.b);
		}
		else if (ar[2].equalsIgnoreCase("r"))
		{
			imageView.setImageResource(R.drawable.r);
		}
		else
		{
			imageView.setImageResource(R.drawable.u);
		}
*/
		return rowView;
	}
}
