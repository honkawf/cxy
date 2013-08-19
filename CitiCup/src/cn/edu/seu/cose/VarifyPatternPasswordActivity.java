package cn.edu.seu.cose;

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
import cn.edu.seu.cose.LockPatternView.Cell;
import cn.edu.seu.cose.LockPatternView.DisplayMode;
import cn.edu.seu.cose.LockPatternView.OnPatternListener;

public class VarifyPatternPasswordActivity extends Activity implements OnClickListener {

	// private OnPatternListener onPatternListener;

	private LockPatternView lockPatternView;

	private LockPatternUtils lockPatternUtils;

	private Button btn_set_pwd;
	private Button btn_link_test;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		lockPatternView = (LockPatternView) findViewById(R.id.lpv_lock);
		btn_set_pwd = (Button) findViewById(R.id.btn_set_pwd);
		btn_link_test = (Button) findViewById(R.id.btn_link_test);
		btn_set_pwd.setOnClickListener(this);
		btn_link_test.setOnClickListener(this);

		lockPatternUtils = new LockPatternUtils(this);
		lockPatternView.setOnPatternListener(new OnPatternListener() {

			public void onPatternStart() {

			}

			public void onPatternDetected(List<Cell> pattern) {
				int result = lockPatternUtils.checkPattern(pattern);
				Toast.makeText(VarifyPatternPasswordActivity.this,
						LockPatternUtils.patternToString(pattern),
						Toast.LENGTH_LONG).show();

				if (result != 1) {
					if (result == 0) {
						lockPatternView.setDisplayMode(DisplayMode.Wrong);
						Toast.makeText(VarifyPatternPasswordActivity.this, "密码错误",
								Toast.LENGTH_LONG).show();
						//wrong
					} else {
						lockPatternView.clearPattern();
						Toast.makeText(VarifyPatternPasswordActivity.this, "密码为空",
								Toast.LENGTH_LONG).show();
						//empty
					}

				} else {
					Toast.makeText(VarifyPatternPasswordActivity.this, "密码正确", Toast.LENGTH_LONG)
							.show();
					//correct
					Intent intent = new Intent();
					intent.setClass(VarifyPatternPasswordActivity.this, LinkBankCardActivity.class);
					startActivity(intent);
					VarifyPatternPasswordActivity.this.finish();
				}

			}

			public void onPatternCleared() {

			}

			public void onPatternCellAdded(List<Cell> pattern) {

			}
		});
	}

	public void onClick(View v) {
/*		if (v == btn_set_pwd) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, SetFirstActivity.class);
			startActivity(intent);
			MainActivity.this.finish();

		}
*/
		if(v == btn_set_pwd){
			Intent intent = new Intent();
			intent.setClass(VarifyPatternPasswordActivity.this, SetFirstActivity.class);
			startActivity(intent);
			VarifyPatternPasswordActivity.this.finish();
		}
		
		if(v == btn_link_test){
			Intent intent = new Intent();
			intent.setClass(VarifyPatternPasswordActivity.this, LinkBankCardActivity.class);
			startActivity(intent);
			VarifyPatternPasswordActivity.this.finish();
		}
	}

}
