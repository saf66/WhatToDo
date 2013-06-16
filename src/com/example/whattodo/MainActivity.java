package com.example.whattodo;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

// http://www.softwarepassion.com/android-series-custom-listview-items-and-adapters/
// http://stackoverflow.com/questions/11265743/onitemclicklistener-with-custom-adapter-and-listview
public class MainActivity extends ListActivity implements OnItemClickListener,OnItemLongClickListener {
   
    private ProgressDialog m_ProgressDialog = null;
    private ArrayList<Order> m_orders = null;
    private OrderAdapter m_adapter;
   
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);
        
        m_orders = new ArrayList<Order>();
        this.m_adapter = new OrderAdapter(this, R.layout.row, m_orders);
        setListAdapter(this.m_adapter);

		Thread thread = new Thread(null, new Runnable() {
		    @Override
		    public void run() {
		        getOrders();
		    }
		});
        thread.start();
        m_ProgressDialog = ProgressDialog.show(MainActivity.this, "Please wait...", "Retrieving data ...", true);
    }
    
    private Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if(m_orders != null && m_orders.size() > 0){
                m_adapter.notifyDataSetChanged();
                for(int i=0;i<m_orders.size();i++)
                m_adapter.add(m_orders.get(i));
            }
            m_ProgressDialog.dismiss();
            m_adapter.notifyDataSetChanged();
        }
      };
    
    private void getOrders(){
        try{
            m_orders = new ArrayList<Order>();
            Order o1 = new Order();
            o1.setOrderName("SF services");
            o1.setOrderStatus("Pending");
            Order o2 = new Order();
            o2.setOrderName("SF Advertisement");
            o2.setOrderStatus("Completed");
            m_orders.add(o1);
            m_orders.add(o2);
               Thread.sleep(2000);
            Log.i("ARRAY", ""+ m_orders.size());
          } catch (Exception e) {
            Log.e("BACKGROUND_PROC", e.getMessage());
          }
          runOnUiThread(returnRes);
      }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(getApplicationContext(), "You long clicked on: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
		return true;
	}

	private class OrderAdapter extends ArrayAdapter<Order> {

	    private ArrayList<Order> items;

	    public OrderAdapter(Context context, int textViewResourceId, ArrayList<Order> items) {
	            super(context, textViewResourceId, items);
	            this.items = items;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	            View v = convertView;
	            if (v == null) {
	                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	                v = vi.inflate(R.layout.row, null);
	            }
	            Order o = items.get(position);
	            if (o != null) {
	                    TextView tt = (TextView) v.findViewById(R.id.toptext);
	                    TextView bt = (TextView) v.findViewById(R.id.bottomtext);
	                    if (tt != null) {
	                          tt.setText("Name: "+o.getOrderName());
	                    }
	                    if(bt != null){
	                          bt.setText("Status: "+ o.getOrderStatus());
	                    }
	            }
	            return v;
	    }
	}
}
