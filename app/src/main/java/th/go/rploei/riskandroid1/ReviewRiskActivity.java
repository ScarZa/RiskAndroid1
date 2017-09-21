package th.go.rploei.riskandroid1;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReviewRiskActivity extends AppCompatActivity {
    TextView trr1,trr2,trr3,trr4,trr5,trr6,trr7,trr8,trr9,trr10,trr11,trr12,trr13,trr14;
    Button brr1,brr2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_risk);
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
        final String takerisk_id = it.getStringExtra("takerisk_id");

        trr1 = (TextView) findViewById(R.id.textViewRr1);
        trr2 = (TextView) findViewById(R.id.textViewRr2);
        trr3 = (TextView) findViewById(R.id.textViewRr3);
        trr4 = (TextView) findViewById(R.id.textViewRr4);
        trr5 = (TextView) findViewById(R.id.textViewRr5);
        trr6 = (TextView) findViewById(R.id.textViewRr6);
        trr7 = (TextView) findViewById(R.id.textViewRr7);
        trr8 = (TextView) findViewById(R.id.textViewRr8);
        trr9 = (TextView) findViewById(R.id.textViewRr9);
        trr10 = (TextView) findViewById(R.id.textViewRr10);
        trr11 = (TextView) findViewById(R.id.textViewRr11);
        trr12 = (TextView) findViewById(R.id.textViewRr12);
        trr13 = (TextView) findViewById(R.id.textViewRr13);
        trr14 = (TextView) findViewById(R.id.textViewRr14);
        brr1 = (Button) findViewById(R.id.buttonRr1);
        brr2 = (Button) findViewById(R.id.buttonRr2);

        String url = "http://10.0.2.2/riskAndroid/detailRisk.php?takerisk_id="+takerisk_id;
        String resultServer  = callServer.getHttpGet(url);
        String [] detailRisk = resultServer.split(",");//รูปแบบการใช้ \r \n ในการตัด
        trr1.setText(detailRisk[0]);
        trr2.setText(detailRisk[1]);
        trr3.setText(detailRisk[2]);
        trr4.setText(dateThai(detailRisk[3]));
        trr5.setText(detailRisk[4]);
        trr6.setText(dateThai(detailRisk[5]));
        trr7.setText(detailRisk[6]);
        trr8.setText(detailRisk[7]);
        trr9.setText(detailRisk[8]);
        trr10.setText(detailRisk[9]);
        trr11.setText(detailRisk[10]);
        trr12.setText(detailRisk[11]);
        trr13.setText(detailRisk[12]);
        trr14.setText(detailRisk[13]);

        brr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userID", userID));
                params.add(new BasicNameValuePair("userName", userName));
                params.add(new BasicNameValuePair("userDep", userDep));
                params.add(new BasicNameValuePair("userMDep", userMDep));
                params.add(new BasicNameValuePair("userStatus", userStatus));
                params.add(new BasicNameValuePair("takerisk_id", takerisk_id));

                String url = "http://10.0.2.2/riskAndroid/prc_moverisk.php";
                String resultServer  = callServer.getHttpPost(url,params);
                //tl1.setText(resultServer);
                if (resultServer.equals("false")){
                    Toast.makeText(getApplicationContext(),
                            "ีการบันทึกไม่สำเร็จครับ", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"ส่งคืนรายการเสร็จแล้ว",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ReviewRiskActivity.this, MenuActivity.class);
                    intent.putExtra("user", resultServer);//  การส่งค่าผ่าหน้า activity
                    startActivity(intent);
                }
            }
        });

        brr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewRiskActivity.this,ReviewRisk2Activity.class);
                intent.putExtra("userID", userID);//  การส่งค่าผ่าหน้า activity
                intent.putExtra("userName", userName);
                intent.putExtra("userDep", userDep);
                intent.putExtra("userMDep", userMDep);
                intent.putExtra("userStatus", userStatus);
                intent.putExtra("takerisk_id", takerisk_id);
                startActivity(intent);
            }
        });
    }
    public static String dateThai(String strDate)
    {
        String Months[] = {
                "ม.ค", "ก.พ", "มี.ค", "เม.ย",
                "พ.ค", "มิ.ย", "ก.ค", "ส.ค",
                "ก.ย", "ต.ค", "พ.ย", "ธ.ค"};

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        int year=0,month=0,day=0;
        try {
            Date date = df.parse(strDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DATE);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return String.format("%s %s %s", day,Months[month],year+543);
    }
}
