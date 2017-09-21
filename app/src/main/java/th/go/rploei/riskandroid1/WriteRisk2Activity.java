package th.go.rploei.riskandroid1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class WriteRisk2Activity extends AppCompatActivity {
    EditText etwr21,etwr22,etwr23;
    TextView twr21,twr23;
    Button bwr21,bwr22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_risk2);
//------------------------
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //--------------------------
        Intent it = getIntent();//get ค่าที่ถูกส่งมา
        final String userID = it.getStringExtra("userID");
        final String userName = it.getStringExtra("userName");
        final String userDep = it.getStringExtra("userDep");
        final String userMDep = it.getStringExtra("userMDep");
        final String userStatus = it.getStringExtra("userStatus");
        final String cate = it.getStringExtra("cate");
        final String subCate = it.getStringExtra("subCate");
        final String cateName = it.getStringExtra("cateName");
        final String takeDate = it.getStringExtra("takeDate");
        final String takeTime = it.getStringExtra("takeTime");
        final String takePlace = it.getStringExtra("takePlace");
        final String hn = it.getStringExtra("hn");
        final String an = it.getStringExtra("an");
        final String takeOther = it.getStringExtra("takeOther");
        final String resDep = it.getStringExtra("resDep");

        etwr21 = (EditText) findViewById(R.id.editTextwr21);
        etwr22 = (EditText) findViewById(R.id.editTextwr22);
        etwr23 = (EditText) findViewById(R.id.editTextwr23);
        twr21 = (TextView) findViewById(R.id.textViewwr22);
        twr23 = (TextView) findViewById(R.id.textViewWr23);
        bwr21 = (Button) findViewById(R.id.buttonWr21);
        bwr22 = (Button) findViewById(R.id.buttonWr22);

        twr23.setText(userName);

        bwr21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://10.0.2.2/riskAndroid/listLevel.php";
                String resultServer  = callServer.getHttpGet(url);
                String [] listRisk = resultServer.split(",");//รูปแบบการใช้ \r \n ในการตัด
                final  String[] item;
                item = new String[listRisk.length];
                for (int i=0;i<listRisk.length;i++) {
                    item [i]=listRisk[i];
                }

                new AlertDialog.Builder(WriteRisk2Activity.this)
                        .setTitle("เลือกระดับความรุนแรง")
                        .setItems(item, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                twr21.setText(item[which]);
                            }
                        })
                        .show();
            }
        });

        bwr22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (twr21.getText().toString().trim().equals("") && etwr21.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),
                            "กรุณาบรรยายเหตุการณ์ความเสี่ยง และเลือกระดับความรุนแรง ให้ครบด้วยครับ", Toast.LENGTH_SHORT).show();
                }else {

                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("userID", userID));
                    params.add(new BasicNameValuePair("userName", userName));
                    params.add(new BasicNameValuePair("userDep", userDep));
                    params.add(new BasicNameValuePair("userMDep", userMDep));
                    params.add(new BasicNameValuePair("userStatus", userStatus));
                    params.add(new BasicNameValuePair("cate", cate));
                    params.add(new BasicNameValuePair("subCate", subCate));
                    params.add(new BasicNameValuePair("cateName", cateName));
                    params.add(new BasicNameValuePair("takeDate", takeDate));
                    params.add(new BasicNameValuePair("takeTime", takeTime));
                    params.add(new BasicNameValuePair("takePlace", takePlace));
                    params.add(new BasicNameValuePair("hn", hn));
                    params.add(new BasicNameValuePair("an", an));
                    params.add(new BasicNameValuePair("takeOther", takeOther));
                    params.add(new BasicNameValuePair("resDep", resDep));
                    params.add(new BasicNameValuePair("take_detail", etwr21.getText().toString().trim()));
                    params.add(new BasicNameValuePair("take_first", etwr22.getText().toString().trim()));
                    params.add(new BasicNameValuePair("take_counsel", etwr23.getText().toString().trim()));
                    params.add(new BasicNameValuePair("level_risk", twr21.getText().toString().trim()));

                    String url = "http://10.0.2.2/riskAndroid/prc_risk.php";
                    String resultServer  = callServer.getHttpPost(url,params);
                    //tl1.setText(resultServer);
                    if (resultServer.equals("false")){
                        Toast.makeText(getApplicationContext(),
                                "ีการบันทึกไม่สำเร็จครับ", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"บันทึกรายการเสร็จแล้ว",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(WriteRisk2Activity.this, MenuActivity.class);
                        intent.putExtra("user", resultServer);//  การส่งค่าผ่าหน้า activity
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
