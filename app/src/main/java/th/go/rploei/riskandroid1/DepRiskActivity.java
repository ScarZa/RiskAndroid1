package th.go.rploei.riskandroid1;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class DepRiskActivity extends AppCompatActivity {
    String [] list;
    ArrayList<String> listData;
    ArrayAdapter<String> adapter;

    TextView tdr2;
    ListView lwdr1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dep_risk);
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

        tdr2 = (TextView) findViewById(R.id.textViewDr2);
        lwdr1 = (ListView) findViewById(R.id.listViewDr1);
        lwdr1.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        initList();
        tdr2.setText("ความเสี่ยงที่ได้รับจำนวน "+lwdr1.getAdapter().getCount()+" รายการ");
        lwdr1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

// Button 1, 2, 3, 4
                int position = i;
                String text = (String)lwdr1.getItemAtPosition(position);
                String [] risk = text.split(":");
                Toast.makeText(getApplicationContext(),"Position : "+position+"\n"+"Select Item : "+risk[0]+" : "+risk[1],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DepRiskActivity.this,ReviewRiskActivity.class);
                intent.putExtra("userID", userID);//  การส่งค่าผ่าหน้า activity
                intent.putExtra("userName", userName);
                intent.putExtra("userDep", userDep);
                intent.putExtra("userMDep", userMDep);
                intent.putExtra("userStatus", userStatus);
                intent.putExtra("takerisk_id", ""+risk[0]+"");
                startActivity(intent);
            }
        });
    }
    public void initList(){
        String url = "http://10.0.2.2/riskAndroid/depRisk.php";
        String resultServer  = callServer.getHttpGet(url);
        String [] listRisk = resultServer.split(",");//รูปแบบการใช้ \r \n ในการตัด

        list = new String[listRisk.length];
        for (int i=0;i<listRisk.length;i++) {
            list [i]=listRisk[i];
        }
        listData = new ArrayList<>(Arrays.asList(list));
        adapter = new ArrayAdapter<>(this, R.layout.listrisk_item, R.id.riskitem, listData);
        lwdr1.setAdapter(adapter);
    }
}
