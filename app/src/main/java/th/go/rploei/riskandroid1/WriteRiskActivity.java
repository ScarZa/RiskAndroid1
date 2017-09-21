package th.go.rploei.riskandroid1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class WriteRiskActivity extends AppCompatActivity {
    TextView twr1,twr2,twr3,twr4,twr5,twr6;
    EditText etwr4,etwr5,etwr6;
    Button bwr1,bwr2,bwr3,bwr4,bwr5;
    int year_dp1,month_db1,day_dp1,hour_tp1,minute_tp1;
    static final int DIALOG_ID = 0;
    static final int TDIALOG_ID = 1;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_risk);

        final Calendar cal = Calendar.getInstance();
        year_dp1 = cal.get(Calendar.YEAR);
        month_db1 = cal.get(Calendar.MONTH);
        day_dp1 = cal.get(Calendar.DAY_OF_MONTH);
        hour_tp1 = cal.get(Calendar.HOUR_OF_DAY);
        minute_tp1 = cal.get(Calendar.MINUTE);

        showDialogOnButtonClick();

        twr1 = (TextView) findViewById(R.id.textViewWR1);
        twr2 = (TextView) findViewById(R.id.textViewWR2);
        twr3 = (TextView) findViewById(R.id.textViewWR3);
        twr4 = (TextView) findViewById(R.id.textViewWR4);
        etwr4 = (EditText) findViewById(R.id.editTextwr4);
        etwr5 = (EditText) findViewById(R.id.editTextwr5);
        etwr6 = (EditText) findViewById(R.id.editTextwr6);
        twr5 = (TextView) findViewById(R.id.textViewWR5);
        bwr3 = (Button) findViewById(R.id.buttonWr3);
        bwr4 = (Button) findViewById(R.id.buttonWR4);
        bwr5 = (Button) findViewById(R.id.buttonWr5);
        twr6 = (TextView) findViewById(R.id.textViewWr6);

        Intent it = getIntent();//get ค่าที่ถูกส่งมา
        final String userID = it.getStringExtra("userID");
        final String userName = it.getStringExtra("userName");
        final String userDep = it.getStringExtra("userDep");
        final String userMDep = it.getStringExtra("userMDep");
        final String userStatus = it.getStringExtra("userStatus");
        final String cate = it.getStringExtra("cate");
        final String subCate = it.getStringExtra("subCate");
        final String cateName = it.getStringExtra("cateName");
        twr6.setText(userName);
        twr1.setText(cateName);

        bwr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://10.0.2.2/riskAndroid/listPlace.php";
                String resultServer  = callServer.getHttpGet(url);
                String [] listRisk = resultServer.split(",");//รูปแบบการใช้ \r \n ในการตัด
                final  String[] item;
                item = new String[listRisk.length];
                for (int i=0;i<listRisk.length;i++) {
                    item [i]=listRisk[i];
                }

                new AlertDialog.Builder(WriteRiskActivity.this)
                        .setTitle("เลือกสถานที่เกิดเหตุ")
                        .setItems(item, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                twr4.setText(item[which]);
                            }
                        })
                        .show();
            }
        });

        bwr4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://10.0.2.2/riskAndroid/listDep.php";
                String resultServer  = callServer.getHttpGet(url);
                String [] listRisk = resultServer.split(",");//รูปแบบการใช้ \r \n ในการตัด
                final  String[] item;
                item = new String[listRisk.length];
                for (int i=0;i<listRisk.length;i++) {
                    item [i]=listRisk[i];
                }

                new AlertDialog.Builder(WriteRiskActivity.this)
                        .setTitle("เลือกหน่วยงานที่เกี่ยวข้อง")
                        .setItems(item, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                twr5.setText(item[which]);
                            }
                        })
                        .show();
            }
        });

        bwr5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = twr4.getText().toString().trim();
                String [] takePlace = text1.split(":");
                String text2 = twr5.getText().toString().trim();
                String [] resDep = text2.split(":");
                Intent intent = new Intent(WriteRiskActivity.this,WriteRisk2Activity.class);
                intent.putExtra("userID", userID);//  การส่งค่าผ่าหน้า activity
                intent.putExtra("userName", userName);
                intent.putExtra("userDep", userDep);
                intent.putExtra("userMDep", userMDep);
                intent.putExtra("userStatus", userStatus);
                intent.putExtra("cate", cate);
                intent.putExtra("subCate", subCate);
                intent.putExtra("cateName",cateName);
                intent.putExtra("takeDate",twr2.getText().toString().trim());
                intent.putExtra("takeTime",twr3.getText().toString().trim());
                intent.putExtra("takePlace",takePlace[0]);
                intent.putExtra("hn",etwr4.getText().toString().trim());
                intent.putExtra("an",etwr5.getText().toString().trim());
                intent.putExtra("takeOther",etwr6.getText().toString().trim());
                intent.putExtra("resDep",resDep[0]);
                startActivity(intent);
            }
        });
    }

    public void showDialogOnButtonClick(){
        bwr1 = (Button) findViewById(R.id.buttonWr1);
        bwr2 = (Button) findViewById(R.id.buttonWr2);

        bwr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
        bwr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TDIALOG_ID);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DIALOG_ID)
            return new DatePickerDialog(this , dpickerListner , year_dp1,month_db1,day_dp1);
        if(id == TDIALOG_ID)
            return new TimePickerDialog(this , tpickerLostner , hour_tp1,minute_tp1,false);
            return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_dp1 = year;
            month_db1 = month+1;
            day_dp1 = dayOfMonth;
            Toast.makeText(WriteRiskActivity.this,year_dp1+"-"+month_db1+"-"+day_dp1,Toast.LENGTH_LONG).show();
            twr2.setText(year_dp1+"-"+checkDigit(month_db1)+"-"+checkDigit(day_dp1));
        }
    };

    private TimePickerDialog.OnTimeSetListener tpickerLostner = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_tp1 = hourOfDay;
            minute_tp1 = minute;
            Toast.makeText(WriteRiskActivity.this,hour_tp1+":"+minute_tp1,Toast.LENGTH_LONG).show();
            twr3.setText(checkDigit(hour_tp1)+":"+checkDigit(minute_tp1));
        }
    };
    public String checkDigit(int number)
    {
        return number<=9?"0"+number:String.valueOf(number);
    }
}
