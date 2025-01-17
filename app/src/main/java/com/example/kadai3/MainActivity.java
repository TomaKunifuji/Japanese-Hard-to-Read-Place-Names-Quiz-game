package com.example.kadai3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {


    MyGraphic myGraphic;
    private DBHelper dbHelper = new DBHelper(this,null,null,1);
    private ProgressDialog progressDialog;
    private ExecutorService exec = null;
    private  final Runnable task = new Runnable() {
        @Override
        public void run() {
            BufferedReader br = null;
            AssetManager assetManager = getResources().getAssets();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            try{
                InputStream input=
                        assetManager.open("dr_data_android.sql",AssetManager.ACCESS_STREAMING);

                br = new BufferedReader(new InputStreamReader(input,"UTF-8"));

                db.beginTransaction();
                String query = "";
                int i = 0;
                while((query = br.readLine()) != null){
                    db.execSQL(query);
                    progressDialog.setMessage("難読地図データ読込中..." +i++*100/5800 + "%");
                }
                br.close();
                db.setTransactionSuccessful();
                db.endTransaction();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                progressDialog.dismiss();
                progressDialog=null;
            }
        }
    };


    public int selectstage(float px, float py)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//-------------------------------DB初期化・読込---------------------------------------//
        String address = "DB 初期化完了";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String query = "select * from dr_data where id = 11;";
        String query = "select * from dr_data ;";

        Cursor c = db.rawQuery(query, null);

        if (c.getCount() == 0) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("しばらくお待ちください");
            progressDialog.setMessage("処理中...");
            progressDialog.show();
            try {
                exec = Executors.newSingleThreadExecutor();
                exec.submit(task);
            } catch (Exception e) {

            }
        }else{
//            if (c.moveToFirst()) {
//                do {
//                    String col1 = c.getString(c.getColumnIndexOrThrow("area"));
//                    String col2 = c.getString(c.getColumnIndexOrThrow("pref"));
//                    String col3 = c.getString(c.getColumnIndexOrThrow("d_street"));
//                    String col4 = c.getString(c.getColumnIndexOrThrow("pronunciation"));
//                    String id = c.getString(c.getColumnIndexOrThrow("id"));
//
//                    // IDがnullでない場合にデータをリストに追加
//                    if (id != null) {
//                        datalist.add(new String[]{col1, col2, col3, col4});
//                    }
//
//                } while (c.moveToNext()); // 次の行に移動
//            }
        }
        c.close();
        db.close();
//        TextView text = (TextView) findViewById(R.id.text1);
//        text.setText(address);
//-------------------------------DB初期化・読込終了---------------------------------------//

        ImageView img = (ImageView) findViewById(R.id.imageView);
        ImageView img2 = (ImageView) findViewById(R.id.imageView2);

        img2.setVisibility(View.INVISIBLE);
        LinearLayout line = (LinearLayout)findViewById(R.id.line);

        MyGraphic myGraphic = findViewById(R.id.mygraphic);
        myGraphic.setImageView(img2);

        img2.setScaleType(ImageView.ScaleType.FIT_CENTER);


        //ゲームスタート
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setVisibility(View.GONE);
                img2.setVisibility(View.VISIBLE);
                line.setVisibility(View.GONE);
                myGraphic.setVisible_flag(true);
            }
        });


        //設定
        Button btn2 = (Button) findViewById(R.id.button2);

        //難読地名データベース
        Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn3.setEnabled(false);     //連打防止用
                Intent intent = new Intent(getApplicationContext(), DataBaseActivity.class);
                startActivity(intent);      //DBアクティビティ立ち上げ

                new Handler(Looper.getMainLooper()).postDelayed(() -> btn3.setEnabled(true), 500);  //連打防止用ハンドラー（500msec）

            }
        });
    }


//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        int select = 0;
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                select = myGraphic.selectstage(event.getX(),event.getY());
//                break;
//        }
//
//        switch (select){
//            case 1:
//            case 2:
//            case 3:
//            case 4:
//            case 5:
//            case 6:
//            case 7:
//            case 8:
//            case 9:
//        }
//        return super.onTouchEvent(event);
//    }
//}