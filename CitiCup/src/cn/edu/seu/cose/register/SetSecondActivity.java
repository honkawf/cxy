package cn.edu.seu.cose.register;

import java.io.File;
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
import cn.edu.seu.cose.encrypt.LocalInfo;
import cn.edu.seu.cose.encrypt.LocalInfoIO;
import cn.edu.seu.cose.register.LockPatternView.Cell;
import cn.edu.seu.cose.register.LockPatternView.DisplayMode;
import cn.edu.seu.cose.register.LockPatternView.OnPatternListener;

public class SetSecondActivity extends Activity implements OnClickListener {

	// private OnPatternListener onPatternListener;

	private LockPatternView lockPatternView;

	private LockPatternUtils lockPatternUtils;
	private String first_pattern,userName,password;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_second);
		lockPatternView = (LockPatternView) findViewById(R.id.lpv_lock_second);

		Intent intent = getIntent();
		first_pattern = intent.getStringExtra("firstPattern");
		userName = intent.getStringExtra("userName");
		password = intent.getStringExtra("password");

		lockPatternUtils = new LockPatternUtils(this);
		lockPatternView.setOnPatternListener(new OnPatternListener() {

			public void onPatternStart() {

			}

			public void onPatternDetected(List<Cell> pattern) {

				if (first_pattern.equals(LockPatternUtils
						.patternToString(pattern))) {
					LocalInfoIO lio = new LocalInfoIO("sdcard/data",
							"local.dat");
					LocalInfo li = new LocalInfo();
					li.setAvailableBalance("0");
					li.setBalance("0");
					li.setGesturePwd(first_pattern);
					li.setPassword(password);
					li.setPrivateKey("0");
					li.setPublicKeyn("0");
					li.setUserName(userName);
					li.setCardnum("0");
					lio.writefile(li);

					// lockPatternUtils.saveLockPattern(pattern);
					lockPatternView.clearPattern();

					Intent intentToCheck = new Intent();
					intentToCheck.setClass(SetSecondActivity.this,
							LinkBankCardActivity.class);
					intentToCheck.putExtra("firstPattern", first_pattern);
					intentToCheck.putExtra("userName", userName);
					Log.i("second", userName);
					intentToCheck.putExtra("password", password);
					Log.i("second", password);
					startActivity(intentToCheck);
					SetSecondActivity.this.finish();

				} else {
					Toast.makeText(SetSecondActivity.this, "两次密码不一致",
							Toast.LENGTH_LONG).show();
					Intent intentToFirst = new Intent();
					intentToFirst.setClass(SetSecondActivity.this,
							SetFirstActivity.class);
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
