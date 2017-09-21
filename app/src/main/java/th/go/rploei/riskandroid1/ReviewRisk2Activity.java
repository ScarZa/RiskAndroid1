package th.go.rploei.riskandroid1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class ReviewRisk2Activity extends AppCompatActivity {
    TextView trr21,trr22;
    EditText etrr21,etrr22,etrr23;
    CheckBox cbrr21,cbrr22,cbrr23,cbrr24,cbrr25,cbrr26,cbrr27,cbrr28,cbrr29;
    Button brr21,brr22,brr23;
    int year_dp1,month_db1,day_dp1,hour_tp1,minute_tp1;
    static final int DIALOG_ID = 0;
    String check1,check2,check3,check4,check5,check6,check7,check8,check9;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_risk2);

        //------------------------
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //--------------------------
        final Calendar cal = Calendar.getInstance();
        year_dp1 = cal.get(Calendar.YEAR);
        month_db1 = cal.get(Calendar.MONTH);
        day_dp1 = cal.get(Calendar.DAY_OF_MONTH);

        showDialogOnButtonClick();

        trr21 = (TextView) findViewById(R.id.textViewrr21);
        trr22 = (TextView) findViewById(R.id.textViewrr22);
        etrr21 = (EditText) findViewById(R.id.editTextrr21);
        etrr22 = (EditText) findViewById(R.id.editTextrr22);
        etrr23 = (EditText) findViewById(R.id.editTextrr23);
        cbrr21 = (CheckBox) findViewById(R.id.checkBoxrr21);
        cbrr22 = (CheckBox) findViewById(R.id.checkBoxrr22);
        cbrr23 = (CheckBox) findViewById(R.id.checkBoxrr23);
        cbrr24 = (CheckBox) findViewById(R.id.checkBoxrr24);
        cbrr25 = (CheckBox) findViewById(R.id.checkBoxrr25);
        cbrr26 = (CheckBox) findViewById(R.id.checkBoxrr26);
        cbrr27 = (CheckBox) findViewById(R.id.checkBoxrr27);
        cbrr28 = (CheckBox) findViewById(R.id.checkBoxrr28);
        cbrr29 = (CheckBox) findViewById(R.id.checkBoxrr29);
        brr22 = (Button) findViewById(R.id.buttonrr22);
        brr23 = (Button) findViewById(R.id.buttonrr23);

        Intent it = getIntent();//get ค่าที่ถูกส่งมา
        final String userID = it.getStringExtra("userID");
        final String userName = it.getStringExtra("userName");
        final String userDep = it.getStringExtra("userDep");
        final String userMDep = it.getStringExtra("userMDep");
        final String userStatus = it.getStringExtra("userStatus");
        final String takerisk_id = it.getStringExtra("takerisk_id");

        brr22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final  String[] item = {"7 วัน","15 วัน","1 เดือน","3 เดือน","6 เดือน","1 ปี"};

                new AlertDialog.Builder(ReviewRisk2Activity.this)
                        .setTitle("เลือกระยะเวลาุ")
                        .setItems(item, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                trr22.setText(item[which]);
                            }
                        })
                        .show();
            }
        });

        brr23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (twr21.getText().toString().trim().equals("") && etwr21.getText().toString().trim().equals("")){
//                    Toast.makeText(getApplicationContext(),
//                            "กรุณาบรรยายเหตุการณ์ความเสี่ยง และเลือกระดับความรุนแรง ให้ครบด้วยครับ", Toast.LENGTH_SHORT).show();
//                }else {
                    if(cbrr21.isChecked()) {
                         check1 = cbrr21.getText().toString();
                    }
                    if(cbrr22.isChecked()) {
                        check2 = cbrr22.getText().toString();
                    }
                    if(cbrr23.isChecked()) {
                        check3 = cbrr23.getText().toString();
                    }
                    if(cbrr24.isChecked()) {
                        check4 = cbrr24.getText().toString();
                    }
                    if(cbrr25.isChecked()) {
                        check5 = cbrr25.getText().toString();
                    }
                    if(cbrr26.isChecked()) {
                        check6 = cbrr26.getText().toString();
                    }
                    if(cbrr27.isChecked()) {
                        check7 = cbrr27.getText().toString();
                    }
                    if(cbrr28.isChecked()) {
                        check8 = cbrr28.getText().toString();
                    }
                    if(cbrr29.isChecked()) {
                        check9 = cbrr29.getText().toString();
                    }
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("userID", userID));
                    params.add(new BasicNameValuePair("userName", userName));
                    params.add(new BasicNameValuePair("userDep", userDep));
                    params.add(new BasicNameValuePair("userMDep", userMDep));
                    params.add(new BasicNameValuePair("userStatus", userStatus));
                    params.add(new BasicNameValuePair("takerisk_id", takerisk_id));
                    params.add(new BasicNameValuePair("mng_date", trr21.getText().toString().trim()));
                    params.add(new BasicNameValuePair("incident_old", etrr21.getText().toString().trim()));
                    params.add(new BasicNameValuePair("check1", check1));
                    params.add(new BasicNameValuePair("check2", check2));
                    params.add(new BasicNameValuePair("check3", check3));
                    params.add(new BasicNameValuePair("check4", check4));
                    params.add(new BasicNameValuePair("check5", check5));
                    params.add(new BasicNameValuePair("check6", check6));
                    params.add(new BasicNameValuePair("check7", check7));
                    params.add(new BasicNameValuePair("check8", check8));
                    params.add(new BasicNameValuePair("check9", check9));
                    params.add(new BasicNameValuePair("edit_summary",etrr22.getText().toString().trim()));
                    params.add(new BasicNameValuePair("edit_process", etrr23.getText().toString().trim()));
                    params.add(new BasicNameValuePair("evaluate", trr22.getText().toString().trim()));

                    String url = "http://10.0.2.2/riskAndroid/prc_reviewrisk.php";
                    String resultServer  = callServer.getHttpPost(url,params);
                    //tl1.setText(resultServer);
                    if (resultServer.equals("false")){
                        Toast.makeText(getApplicationContext(),
                                "ีการบันทึกไม่สำเร็จครับ", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"บันทึกรายการทบทวนเสร็จแล้ว",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ReviewRisk2Activity.this, MenuActivity.class);
                        intent.putExtra("user", resultServer);//  การส่งค่าผ่าหน้า activity
                        startActivity(intent);
                    }
                //            }
           }
        });
    }

    public void showDialogOnButtonClick(){
        brr21 = (Button) findViewById(R.id.buttonrr21);

        brr21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
            }
    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DIALOG_ID)
            return new DatePickerDialog(this , dpickerListner , year_dp1,month_db1,day_dp1);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_dp1 = year;
            month_db1 = month+1;
            day_dp1 = dayOfMonth;
            Toast.makeText(ReviewRisk2Activity.this,year_dp1+"-"+month_db1+"-"+day_dp1,Toast.LENGTH_LONG).show();
            trr21.setText(year_dp1+"-"+checkDigit(month_db1)+"-"+checkDigit(day_dp1));
        }
    };
    public String checkDigit(int number)
    {
        return number<=9?"0"+number:String.valueOf(number);
    }
}
