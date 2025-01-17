package com.example.kadai3;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataBaseActivity extends AppCompatActivity {

    private DBHelper dbHelper = new DBHelper(this,null,null,1);
    private ProgressDialog progressDialog;
    private ExecutorService exec = null;


//    public void inputdata(){
//
//        EditText edit1 = (EditText) findViewById(R.id.editText);
//        EditText edit2 = (EditText) findViewById(R.id.editText2);
//        EditText edit3 = (EditText) findViewById(R.id.editText3);
//        EditText edit4 = (EditText) findViewById(R.id.editText4);
//        EditText edit5 = (EditText) findViewById(R.id.editText5);
//        EditText edit6 = (EditText) findViewById(R.id.editText6);
//
//        String name = edit1.getText().toString();
//        String zcode = edit2.getText().toString();
//        String address = edit3.getText().toString();
//        String tel1 = edit4.getText().toString();
//        String tel2 = edit5.getText().toString();
//        String tel3 = edit6.getText().toString();
//
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String query = "insert into customer(name,zcode,address,tel1,tel2,tel3) values('"+name+"','"+zcode+"','"+address+"','"+tel1+"','"+tel2+"','"+tel3+"');";
//        try {
//            db.execSQL(query);
//            Toast.makeText(getApplicationContext(),"登録しました",Toast.LENGTH_LONG).show();
//        }catch (SQLException e){
//            Toast.makeText(getApplicationContext(),"登録に失敗しました",Toast.LENGTH_LONG).show();
//        }
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dblist);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dblist), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        String address = "DB 初期化完了";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String query = "select * from dr_data where id = 11;";
        String query = "select * from dr_data ;";

        Cursor c = db.rawQuery(query, null);

        ListView listView = findViewById(R.id.list1);


        List<String[]> datalist = new ArrayList<>();


        ListAdapter adapter = new ListAdapter(this, datalist);
        listView.setAdapter(adapter);

        if (c.getCount() != 0) {

            if (c.moveToFirst()) {
                do {
                    String col1 = c.getString(c.getColumnIndexOrThrow("area"));
                    String col2 = c.getString(c.getColumnIndexOrThrow("pref"));
                    String col3 = c.getString(c.getColumnIndexOrThrow("d_street"));
                    String col4 = c.getString(c.getColumnIndexOrThrow("pronunciation"));
                    String id = c.getString(c.getColumnIndexOrThrow("id"));

                    // IDがnullでない場合にデータをリストに追加
                    if (id != null) {
                        datalist.add(new String[]{col1, col2, col3, col4});
                    }

                } while (c.moveToNext()); // 次の行に移動
            }

        }
        c.close();
        db.close();

        Button list_btn = (Button) findViewById(R.id.list_button);
        list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_btn.setEnabled(false);
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
                finish();
                new Handler(Looper.getMainLooper()).postDelayed(() -> list_btn.setEnabled(true), 300);

            }
        });
//        TextView text = (TextView) findViewById(R.id.text1);
//        text.setText(address);
    }
}