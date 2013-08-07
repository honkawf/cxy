package cn.edu.seu.cose;

import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.edu.seu.cose.LockPatternView.Cell;
import cn.edu.seu.cose.LockPatternView.DisplayMode;
import cn.edu.seu.cose.LockPatternView.OnPatternListener;

import com.wgs.jiesuo.R;

public class SetFirstActivity extends Activity implements OnClickListener {

	// private OnPatternListener onPatternListener;

	private LockPatternView lockPatternView;

	private LockPatternUtils lockPatternUtils;
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_first);
		lockPatternView = (LockPatternView) findViewById(R.id.lpv_lock_first);


		lockPatternUtils = new LockPatternUtils(this);
		lockPatternView.setOnPatternListener(new OnPatternListener() {

			public void onPatternStart() {

			}

			public void onPatternDetected(List<Cell> pattern) {
				String first_pattern = LockPatternUtils.patternToString(pattern);
			    lockPatternView.clearPattern();
			    
				Intent intent = new Intent();
				intent.putExtra("firstPattern", first_pattern);
		        intent.setClass(SetFirstActivity.this, SetSecondActivity.class);
		        startActivity(intent);
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
