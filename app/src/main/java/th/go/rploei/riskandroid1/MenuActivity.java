package th.go.rploei.riskandroid1;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    TextView tm1;
    Button bm1,bm2,bm3;
    String user="";
    String [] userdetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //------------------------
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //--------------------------
        bm1 = (Button) findViewById(R.id.buttonM1);
        bm2 = (Button) findViewById(R.id.buttonM2);
        bm3 = (Button) findViewById(R.id.buttonM3);
        tm1 = (TextView) findViewById(R.id.textViewM1);
        Intent it = getIntent();//get ค่าที่ถูกส่งมา
        user = it.getStringExtra("user");
        userdetail = user.split(",");//รูปแบบการใช้ \r \n ในการตัด
        tm1.setText(userdetail[1]);

        bm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,WriteActivity.class);
                intent.putExtra("userID", userdetail[0]);//  การส่งค่าผ่าหน้า activity
                intent.putExtra("userName", userdetail[1]);
                intent.putExtra("userDep", userdetail[2]);
                intent.putExtra("userMDep", userdetail[3]);
                intent.putExtra("userStatus", userdetail[4]);
                startActivity(intent);
            }
        });
        bm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,DepRiskActivity.class);
                intent.putExtra("userID", userdetail[0]);//  การส่งค่าผ่าหน้า activity
                intent.putExtra("userName", userdetail[1]);
                intent.putExtra("userDep", userdetail[2]);
                intent.putExtra("userMDep", userdetail[3]);
                intent.putExtra("userStatus", userdetail[4]);
                startActivity(intent);
            }
        });
        bm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuActivity.this,"Logout สำเร็จแล้ว",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MenuActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
