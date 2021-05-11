package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    dataUser dataUser;
    Button btn_add,btn_remove,btn_edt;
    EditText pt;
    ListView lv;
    ArrayAdapter adapter;


    ArrayList idList;
    ArrayList nameList;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         dataUser = new dataUser(this,"userdb.sqlite",null,1);

          nameList= new ArrayList<>();
        idList = new ArrayList();
          getNameList();



         btn_edt = findViewById(R.id.btnCancel);
         btn_add =findViewById(R.id.btnAdd);
         btn_remove =findViewById(R.id.btnRemove);
         pt = findViewById(R.id.pt);
         lv= findViewById(R.id.lvname);

         btn_add.setOnClickListener(new View.OnClickListener(){


             @Override
             public void onClick(View v) {
                 dataUser.addUser(new user(pt.getText().toString()));
                 getNameList();
                 adapter.notifyDataSetChanged();
             }
         });

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataUser.removeUser((int)idList.get(index));
                getNameList();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,
                        "Succesful", Toast.LENGTH_SHORT).show();

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>
                                            adapterView, View view, int i, long l) {
                 index = i;
            }
        });




         adapter = new ArrayAdapter(this,
                 android.R.layout.simple_list_item_1,nameList);
        lv.setAdapter(adapter);
    }
    private ArrayList getNameList(){
       nameList.clear();
        idList.clear();

        for (Iterator iterator = dataUser.getAll().iterator();iterator.hasNext();){
            user user = (user)iterator.next();

            nameList.add(user.getName());
            idList.add(user.getId());
        }

        return nameList;
    }
}