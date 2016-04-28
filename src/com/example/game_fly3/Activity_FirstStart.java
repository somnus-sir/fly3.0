package com.example.game_fly3;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Activity_FirstStart extends Activity implements OnClickListener{
	
	private Button startButton;
	private Button goonButton;
	private LinearLayout linearLayout;
	private int boomsum=5;//炸弹数量-----在startActivity中指定初始化
	Boolean sumkey100 = true;// 当sum到达100
	public Boolean dieBoolean;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_start);
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(1000);
		linearLayout = (LinearLayout) findViewById(R.id.bg);
		linearLayout.setAnimation(alphaAnimation);
		startButton = (Button) findViewById(R.id.startgame);
		
		startButton.getBackground().setAlpha(255);//0~255透明度值
		goonButton = (Button) findViewById(R.id.goongame);
		goonButton.getBackground().setAlpha(255);//0~255透明度值
		
		
		startButton.setOnClickListener(this);
		goonButton.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.startgame:
			My_dialog();
			break;
		case R.id.goongame:
			Intent intent_goon = new Intent(Activity_FirstStart.this,MainActivity.class);
			startActivity(intent_goon);
			break;			
		default:
			break;
		}
		
	}
	
	
	
	public void My_dialog(){
		AlertDialog.Builder dialog = new AlertDialog.Builder(Activity_FirstStart.this);
		dialog.setTitle("开始游戏");
		dialog.setMessage("重新开始游戏");
		dialog.setCancelable(false);
		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
				editor.putInt("sum", 0);
				editor.putInt("boomsum", boomsum);
				editor.putBoolean("sumkey100", true);
				editor.putInt("HP", 1);
				editor.putBoolean("dieBoolean", true);
				editor.putInt("trans", 0);
				editor.commit();	
				Intent intent_start = new Intent(Activity_FirstStart.this,Activity_change.class);
				startActivity(intent_start);						
			}
		});
		//
		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(Activity_FirstStart.this, "取消", Toast.LENGTH_SHORT).show();
			}
		});
		dialog.show();
	}	
	
	
	
	
	public boolean onKeyDown(int keyCode,KeyEvent event) {

		if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){	
		return true;
		}
		return false;
		}
}
