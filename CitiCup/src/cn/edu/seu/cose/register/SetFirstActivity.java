package cn.edu.seu.cose.register;

import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.edu.seu.cose.R;
import cn.edu.seu.cose.register.LockPatternView.Cell;
import cn.edu.seu.cose.register.LockPatternView.DisplayMode;
import cn.edu.seu.cose.register.LockPatternView.OnPatternListener;

public class SetFirstActivity extends Activity implements OnClickListener {

	// private OnPatternListener onPatternListener;

	private LockPatternView lockPatternView;

	private LockPatternUtils lockPatternUtils;
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_first);
		
		Intent intent = getIntent();
		final String userName = intent.getStringExtra("userName");
		final String password = intent.getStringExtra("password");
		
		lockPatternView = (LockPatternView) findViewById(R.id.lpv_lock_first);
		lockPatternUtils = new LockPatternUtils(this);
		lockPatternView.setOnPatternListener(new OnPatternListener() {

			public void onPatternStart() {

			}

			public void onPatternDetected(List<Cell> pattern) {
				String first_pattern = LockPatternUtils.patternToString(pattern);
			    lockPatternView.clearPattern();
			    
				Intent intentToSecond = new Intent();
				intentToSecond.putExtra("firstPattern", first_pattern);
				intentToSecond.putExtra("userName", userName);
				Log.i("first", userName);
				intentToSecond.putExtra("password", password);
				Log.i("first", password);
				intentToSecond.setClass(SetFirstActivity.this, SetSecondActivity.class);
		        startActivity(intentToSecond);
		        SetFirstActivity.this.finish();
									
	
							
			}

			public void onPatternCleared() {

			}

			public void onPatternCellAdded(List<Cell> pattern) {

			}
		});
	}

	public void onClick(View v) {

			
	}
	

}
