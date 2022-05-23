package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // récup listview
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        UserAccount francois = new UserAccount("François", "admin");
        UserAccount germain = new UserAccount("Germain", "user");
        UserAccount cedric = new UserAccount("Cédric", "guest", false);
        UserAccount manu = new UserAccount("Manu","guest");

        List<UserAccount> listCli = new ArrayList<UserAccount>();
        listCli.add(francois);
        listCli.add(germain);
        listCli.add(cedric);
        listCli.add(manu);

        listView.setAdapter(new CustomListAdaptater(listCli, this));
    }
}