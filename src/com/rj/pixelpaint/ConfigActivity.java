package com.rj.pixelpaint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class ConfigActivity extends Activity implements View.OnClickListener {
	private Switch preview, grid, mirror_h, mirror_v;
	private Button ok, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.configctivity);

		Bundle b = getIntent().getExtras();
		preview = (Switch)findViewById(R.id.switch_preview);
		preview.setChecked(b.getBoolean("preview"));
		grid = (Switch)findViewById(R.id.switch_grid);
		grid.setChecked(b.getBoolean("grid"));
		mirror_h = (Switch)findViewById(R.id.switch_mirror_h);
		mirror_h.setChecked(b.getBoolean("mirror_h"));
		mirror_v = (Switch)findViewById(R.id.switch_mirror_v);
		mirror_v.setChecked(b.getBoolean("mirror_v"));

		ok = (Button)findViewById(R.id.config_ok);
		ok.setOnClickListener(this);
		cancel = (Button)findViewById(R.id.config_cancel);
		cancel.setOnClickListener(this);
    }

	@Override
	public void onClick(View view) {
		if (view == ok){
			Intent resultIntent = new Intent();

			resultIntent.putExtra("preview", preview.isChecked());
			resultIntent.putExtra("grid", grid.isChecked());

			resultIntent.putExtra("mirror_h", mirror_h.isChecked());
			resultIntent.putExtra("mirror_v", mirror_v.isChecked());

			setResult(Activity.RESULT_OK, resultIntent);

			finish();
		} else if (view == cancel){
			finish();
		}
	}
}
