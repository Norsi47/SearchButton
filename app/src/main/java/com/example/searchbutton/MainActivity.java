package com.example.searchbutton;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button;

    //for the list in ui side of app
    ListView listView;
    //adding name into string array
    String[] randomNames = {"Christopher", "Jenny", "Maria", "Steve", "Chirs", "ivana", "Michael",
            "Craig", "Kelly", "Joseph", "Crhistene"};


    // Define array adapter for ListView
    ArrayAdapter<String> arrayAdapter;

    // Define array List for List View data
    ArrayList<String> mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //not sure where this came from
        setContentView(R.layout.activity_main);

        // initialise ListView with id
        //this is the list view in the ui side ID
        listView = (ListView) findViewById(R.id.listView);

        /*//an already defined layout
         * this will show the randomNames array list we created*/
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, randomNames);
        listView.setAdapter(arrayAdapter);

//        //adding this fixed the error
//        textView = (TextView) findViewById(R.id.textView);
//
//        //manually setting text
//        textView.setText(" NOT Getting Error");
        //this fixed the error for button
        button = (Button) findViewById(R.id.buttonTest);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //what will show when clicked
                Toast.makeText(getApplicationContext(), "Clicked Button", Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //use searchmenu xml file name
        getMenuInflater().inflate(R.menu.searchmenu, menu);

        //this refers to the id used for search in searchmenu.xml
        MenuItem menuItem = menu.findItem(R.id.search_button);

        //have this import " androidx.appcompat.widget.SearchView"
        //this needs to match Appcompant what it extends to
        //this fixed the crashes
        SearchView searchView = (SearchView) menuItem.getActionView();
        //this is hint for whenever the user clicks on the icon

        //query listener to show what user presses
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            //what will show when user types in a text
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            //if user types in something else
            public boolean onQueryTextChange(String newText) {
                //whenever user changes any text
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}