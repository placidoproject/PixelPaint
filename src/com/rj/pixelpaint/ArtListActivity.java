package com.rj.pixelpaint;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.rj.pixelpaint.ArtListFragment.ArtElement;
import com.rj.pixelpaint.ArtListFragment.ArtItemSelectedListener;

import processing.core.PApplet;

public class ArtListActivity extends FragmentActivity implements ArtItemSelectedListener {
	public final static String PATH = "path";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.listactivity);
        
		ArtListFragment details = new ArtListFragment();
		details.setListener(this);
		details.setArguments(getIntent().getExtras());
		getSupportFragmentManager().beginTransaction().add(R.id.content, details).commit();
    }

	@Override
	public void onArtItemSelected(ArtElement element) {
		Intent resultIntent = new Intent();

		resultIntent.putExtra(PATH, element.image.getAbsolutePath());
		
		setResult(Activity.RESULT_OK, resultIntent);

		finish();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		if (v.getId()==R.id.list) {
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;

			GridView gridview = (GridView) findViewById(R.id.list);
			ArtElement element = (ArtElement) gridview.getItemAtPosition(info.position);

			//Header Tittle
			menu.setHeaderTitle(element.name);
			//Header Icon
			Bitmap image = BitmapFactory.decodeFile(element.image.getAbsolutePath());
			int width = image.getWidth();
			int height = image.getHeight();
			if (width > height) {
				height = (height * 50)/width;
				width = 50;
			} else {
				width = (width * 50)/height;
				height = 50;
			}
			Bitmap bitmapResized = Bitmap.createScaledBitmap(image, width, height, false);
			menu.setHeaderIcon(new BitmapDrawable(getResources(), bitmapResized));

			String[] menuItems = {"Rename","Delete"};
			for (int i = 0; i < menuItems.length; i++) {
				menu.add(Menu.NONE, i, i, menuItems[i]);
			}
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

		GridView gridview = (GridView) findViewById(R.id.list);
		ArtElement element = (ArtElement) gridview.getItemAtPosition(info.position);

		switch (item.getItemId()){
			case 0:
				Dialogs.showRename(element.image.getAbsoluteFile(), this);
				break;
			case 1:
				Dialogs.showDelete(element.image.getAbsoluteFile(), this);
				break;
		}
		return true;
	}
}
