package com.example.fruitworld.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fruitworld.Adapters.MyAdapter;
import com.example.fruitworld.Models.Fruit;
import com.example.fruitworld.R;

import java.util.ArrayList;

public class ListFruits extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView list;
    GridView grid;
    ArrayList<Fruit> fruits;
    int count = 0;
    MyAdapter adapter, adapterGrid;
    private final int LENGTH = 5;
    MenuItem itemList, itemGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        list = (ListView) findViewById(R.id.idListFruit);
        grid = (GridView) findViewById(R.id.idChangeGrid);

        fruits = new ArrayList<Fruit>(LENGTH);
        fruits.add(new Fruit("Apple", "Swiss", R.mipmap.apple_round));
        fruits.add(new Fruit("banana", "Italy", R.mipmap.banana_round));
        fruits.add(new Fruit("Grenade", "America", R.mipmap.grenade_round));
        fruits.add(new Fruit("plum", "Europe", R.mipmap.plum_round));
        fruits.add(new Fruit("Start Fruit", "Europe", R.mipmap.start_round));

        adapter = new MyAdapter(this, fruits, R.layout.item);
        list.setAdapter(adapter);

        adapterGrid = new MyAdapter(this, fruits, R.layout.item_grid);
        grid.setAdapter(adapterGrid);

        list.setOnItemClickListener(this);
        grid.setOnItemClickListener(this);

        registerForContextMenu(list);
        registerForContextMenu(grid);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position < LENGTH)
            Toast.makeText(this, "My name is " + fruits.get(position).getName() + " and I am a fruit preloaded", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "My name is " + fruits.get(position).getName() + " and I am a new fruit", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        itemList = menu.findItem(R.id.idChangeList);
        itemGrid = menu.findItem(R.id.idChangeGrid);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.idAdd:
                //Añadir un nuevo elemento
                fruits.add(new Fruit("Fruit " + (--count), "Unknow", R.mipmap.ic_launcher_round));
                //Notificar cambios en los datos
                adapter.notifyDataSetChanged();
                adapterGrid.notifyDataSetChanged();
                return true;
            case R.id.idChangeList:{
                list.setVisibility(View.VISIBLE);
                grid.setVisibility(View.INVISIBLE);
                itemGrid.setVisible(true);
                itemList.setVisible(false);
                return true;}
            case R.id.idChangeGrid:{
                grid.setVisibility(View.VISIBLE);
                list.setVisibility(View.INVISIBLE);
                itemList.setVisible(true);
                itemGrid.setVisible(false);
                return true;}
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(fruits.get(info.position).getName());
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()){
            case R.id.idDelete:
                //Borrar elemento
                fruits.remove(info.position);
                //Notificar
                adapterGrid.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}