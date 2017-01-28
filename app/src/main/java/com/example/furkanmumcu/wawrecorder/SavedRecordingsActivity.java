package com.example.furkanmumcu.wawrecorder;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;

public class SavedRecordingsActivity extends AppCompatActivity {

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_recordings);

        /* Path handling */
        String path = Environment.getExternalStorageDirectory().toString()+"/AudioRecorder";
        AppLog.logString(path); // check
        File f = new File(path);
        File file[] = f.listFiles(); // gets path of all recordings
        String[] parts = file[0].toString().split("/"); // gets the name of file
        System.out.println(parts[parts.length-1]); // TODO: create a method which returns each name


        /* list view */

        String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
                "WebOS","Ubuntu","Windows7","Max OS X"}; // example

        adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) findViewById(R.id.recordinglist);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.recordinglist) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;

            menu.add(Menu.NONE,1,1,"one");
            menu.add(Menu.NONE,2,2,"two");

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                AppLog.logString("one basildi");
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                //AppLog.logString("removing item pos=" + info.position);
                //adapter.remove(info.position);
                return true;
            case 2:
                AppLog.logString("two basildi");
            default:
                return super.onContextItemSelected(item);
        }
    }
}
