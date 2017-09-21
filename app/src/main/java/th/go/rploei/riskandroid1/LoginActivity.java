package th.go.rploei.riskandroid1;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText el1,el2;
    Button bl1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //------------------------
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //--------------------------

        el1 = (EditText) findViewById(R.id.editTextL1);
        el2 = (EditText) findViewById(R.id.editTextL2);
        bl1 = (Button) findViewById(R.id.buttonL1);


        bl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = el1.getText().toString().trim();
                String pass = el2.getText().toString().trim();
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("user_account", user));
                params.add(new BasicNameValuePair("user_pwd", pass));

                if (user.equals("") && pass.equals("")){
                    Toast.makeText(getApplicationContext(),
                            "กรุณากรอกให้ครบด้วยครับ", Toast.LENGTH_SHORT).show();
                }else {

                    String url = "http://10.0.2.2/riskAndroid/checkLogin.php";
                    String resultServer  = callServer.getHttpPost(url,params);
                    //tl1.setText(resultServer);
                    if (resultServer.equals("false")){
                        Toast.makeText(getApplicationContext(),
                                "ีusername หรือ password ผิด กรุณาตรวจสอบด้วยครับ", Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        intent.putExtra("user", resultServer);//  การส่งค่าผ่าหน้า activity
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
