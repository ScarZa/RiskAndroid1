package th.go.rploei.riskandroid1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class WriteActivity extends AppCompatActivity {
    String [] list;
    ArrayList<String> listData;
    ArrayAdapter<String> adapter;
    //String [] cols;
    //SearchView sw1;
    ListView lw1;
    EditText ew1;
    TextView tw1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        Intent it = getIntent();//get ค่าที่ถูกส่งมา
        final String userID = it.getStringExtra("userID");
        final String userName = it.getStringExtra("userName");
        final String userDep = it.getStringExtra("userDep");
        final String userMDep = it.getStringExtra("userMDep");
        final String userStatus = it.getStringExtra("userStatus");

        tw1 = (TextView) findViewById(R.id.textViewW1);
        ew1 = (EditText) findViewById(R.id.txtsearch);
        lw1 = (ListView) findViewById(R.id.listViewW1);
        lw1.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        tw1.setText(userName);
        initList();
        ew1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    initList();
                }
                else {
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        lw1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

// Button 1, 2, 3, 4
                int position = i;
                String text = (String)lw1.getItemAtPosition(position);
                String [] risk = text.split(":");
                Toast.makeText(getApplicationContext(),"Position : "+position+"\n"+"Select Item : "+risk[0]+" : "+risk[1],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WriteActivity.this,WriteRiskActivity.class);
                intent.putExtra("userID", userID);//  การส่งค่าผ่าหน้า activity
                intent.putExtra("userName", userName);
                intent.putExtra("userDep", userDep);
                intent.putExtra("userMDep", userMDep);
                intent.putExtra("userStatus", userStatus);
                intent.putExtra("cate", ""+risk[0]+"");
                intent.putExtra("subCate", ""+risk[1]+"");
                intent.putExtra("cateName",""+risk[2]+"");
                startActivity(intent);

//                Intent intent = new Intent(WriteActivity.this,WriteRiskActivity.class);
////                intent.putExtra("userID", userID);//  การส่งค่าผ่าหน้า activity
////                intent.putExtra("userName", userName);
////                intent.putExtra("userDep", userDep);
////                intent.putExtra("userMDep", userMDep);
////                intent.putExtra("userStatus", userStatus);
//                intent.putExtra("cate", risk[0]);
//                intent.putExtra("subcate", risk[1]);
//                startActivity(intent);

                //Toast.makeText(getApplicationContext(),"Position : "+position+"\n"+"Select Item : "+risk[1],Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void searchItem(String textToSearch){
        for (String item : list){
            if (!item.contains(textToSearch)){
                listData.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void initList(){
        String url = "http://10.0.2.2/riskAndroid/listRisk(json).php";
        String resultServer  = callServer.getHttpGet(url);
        String [] listRisk = resultServer.split(",");//รูปแบบการใช้ \r \n ในการตัด

        list = new String[listRisk.length];
        for (int i=0;i<listRisk.length;i++) {
            list [i]=listRisk[i];
        }
        listData = new ArrayList<>(Arrays.asList(list));
        adapter = new ArrayAdapter<>(this, R.layout.listrisk_item, R.id.riskitem, listData);
        lw1.setAdapter(adapter);
        /*String [] listRisk = resultServer.split("\\r?\\n");//รูปแบบการใช้ \r \n ในการตัด

        listData = new ArrayList<>();
        for (int i=0;i<listRisk.length;i++){
            list = "";
            cols = listRisk[i].split(",");
            for (int I=0;I<cols.length;I++) {
                list += cols[I];
                if (I<cols.length-1) {
                    list += ":";
                }
            }
            listData.add(list);
        }
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);

        lw1.setAdapter(adapter);*/

    }
}
