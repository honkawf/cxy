package cn.edu.seu.cose;

import com.wgs.jiesuo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity{
	
	private EditText account, pwd1, pwd2, realName;
	private TextView pwd1_label, pwd2_label, account_label, realName_label;
	private Button button;
	String pwd1_content = "", pwd2_content = "", account_content = "";
	boolean account_correct = false;
	boolean pwd_correct = false;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		account = (EditText) findViewById(R.id.account);
		pwd1 = (EditText) findViewById(R.id.password1);
		pwd2 = (EditText) findViewById(R.id.password2);
		realName = (EditText) findViewById(R.id.realName);
		button = (Button) findViewById(R.id.submit);
		
		pwd1_label = (TextView) findViewById(R.id.pwd1_label);
		pwd2_label = (TextView) findViewById(R.id.pwd2_label);
		account_label = (TextView) findViewById(R.id.account_label);
		realName_label = (TextView) findViewById(R.id.realName_label);
		

		
		
		
		//按键事件
		account.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event){
				pwd1.setText("");
//				Toast.makeText(getApplicationContext(), "account1111", 1000).show();
				return false;
			}
		});
		
		pwd1.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event){
				pwd1.setText("");
//				Toast.makeText(getApplicationContext(), "pw11111", 1000).show();
				return false;
			}
		});
		
		pwd2.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event){
				pwd1.setText("");
//				Toast.makeText(getApplicationContext(), "pwd21111", 1000).show();
				return false;
			}
		});
		
		realName.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event){
				pwd1.setText("");
//				Toast.makeText(getApplicationContext(), "pwd21111", 1000).show();
				return false;
			}
		});
		
		account.setOnFocusChangeListener(new OnFocusChangeListener(){
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					account_label.setText("");
				}else{
					if(account.getText().toString().equals("")){
						account_label.setText("用户名不能为空");
					}else{
						account_correct  =true;
					}
				}
			}
			
		});
		
		realName.setOnFocusChangeListener(new OnFocusChangeListener(){
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					realName_label.setText("");
				}else{
					if(realName.getText().toString().equals("")){
						realName_label.setText("真实姓名不能为空");
					}
				}
			}
			
		});
		
		pwd1.setOnFocusChangeListener(new OnFocusChangeListener(){
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					pwd1_label.setText("");
				}else{
					if(pwd1.getText().toString().equals("")){
						pwd1_label.setText("密码不能为空");
					}else{
						pwd1_content = pwd1.getText().toString();
						if(pwd1_content.equals(pwd2_content)){
							pwd_correct = true;
							pwd1_label.setText("");
							pwd2_label.setText("");
						}
							
					}
				}
			}
			
		});
		
		pwd2.setOnFocusChangeListener(new OnFocusChangeListener(){
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					pwd2_label.setText("");
				}else{
					if(pwd2.getText().toString().equals("")){
						pwd2_label.setText("密码不能为空");		
					}else{
						pwd2_content = pwd2.getText().toString();
						if(!pwd1_content.equals(pwd2_content)){
							pwd2_label.setText("两次密码不一致，请重新输入");
						}else{
							pwd_correct = true;
//							button.setEnabled(account_correct && pwd_correct);
						}
					}
				}
			}
			
		});
		
		
		//button
		button.setOnClickListener(new OnClickListener(){
			
			public void onClick(View arg0){
	
					
				}
				
			
			
		});
	}
	

}
