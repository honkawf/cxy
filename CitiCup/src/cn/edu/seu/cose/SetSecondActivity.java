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

public class SetSecondActivity extends Activity implements OnClickListener {

	// private OnPatternListener onPatternListener;

	private LockPatternView lockPatternView;

	private LockPatternUtils lockPatternUtils;
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_second);
		lockPatternView = (LockPatternView) findViewById(R.id.lpv_lock_second);

		Intent intent = getIntent();
        final String first_pattern = intent.getStringExtra("firstPattern");
        
		lockPatternUtils = new LockPatternUtils(this);
		lockPatternView.setOnPatternListener(new OnPatternListener() {

			public void onPatternStart() {

			}

			public void onPatternDetected(List<Cell> pattern) {
				
				if( first_pattern.equals(LockPatternUtils.patternToString(pattern))){
					lockPatternUtils.saveLockPattern(pattern);
					Toast.makeText(SetSecondActivity.this, "密码已经设置", Toast.LENGTH_LONG)
					.show();
					lockPatternView.clearPattern();
					Intent intentToCheck = new Intent();
					intentToCheck.setClass(SetSecondActivity.this, MainActivity.class);
		            startActivity(intentToCheck);
		            SetSecondActivity.this.finish();
				} else{
					Toast.makeText(SetSecondActivity.this, "输入不一致，请重新输入", Toast.LENGTH_LONG)
					.show();
					Intent intentToFirst = new Intent();
					intentToFirst.setClass(SetSecondActivity.this, SetFirstActivity.class);
		            startActivity(intentToFirst);
		            SetSecondActivity.this.finish();
				}
				
				
			
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
