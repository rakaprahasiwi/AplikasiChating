package com.example.prahasiwi.chatyuks;

import android.content.SharedPreferences;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TambahPesan extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;
    EditText iNama, iPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pesan);

        getSupportActionBar().setTitle("Tambah Pesan");

        iNama = (EditText) findViewById(R.id.inpUser);
        iPesan = (EditText) findViewById(R.id.inpPesan);
        sharedPreferences = getSharedPreferences(MainActivity.preff,0);
        spEditor = sharedPreferences.edit();
    }

    public void kirimPesan(View view) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("NamaPengirim",iNama.getText().toString());
            jsonObject.put("KontenPesan",iPesan.getText().toString());
            jsonObject.put("Tanggalan",new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
            jsonObject.put("GambarPengirim",R.drawable.wajah);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(sharedPreferences.contains("message")) {
            String dataMessage = sharedPreferences.getString("message","");

            try {
                JSONArray jsonArray = new JSONArray(dataMessage);
                jsonArray.put(jsonObject);
                spEditor.putString("message", jsonArray.toString());
                spEditor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            spEditor.putString("message", jsonArray.toString());
            spEditor.apply();
        }

        finish();
    }
}
