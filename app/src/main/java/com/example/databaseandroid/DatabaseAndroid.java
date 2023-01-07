package com.example.databaseandroid;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseAndroid extends Activity {
    DatabaseManager dm;
    EditText nama, hobi;
    Button addBtn;
    TableLayout tabel4data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dm = new DatabaseManager(this);
        tabel4data = (TableLayout) findViewById(R.id.tabel_data);
        nama = (EditText) findViewById(R.id.inNama);
        hobi = (EditText) findViewById(R.id.inHobi);
        addBtn = (Button) findViewById(R.id.btnAdd);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpKamuta();
            }
        });
        updateTable();
    }

    protected void simpKamuta() {
        try {
            dm.addRow(nama.getText().toString(), hobi.getText().toString());
            Toast.makeText(getBaseContext(),
                    nama.getText().toString() + ", berhasil disimpan",
                    Toast.LENGTH_SHORT).show();
            updateTable();
            kosongkanField();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "gagal simpan, " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    protected void kosongkanField() {
        nama.setText("");
        hobi.setText("");
    }

    protected void updateTable() {
        // TODO Auto-generated method stub
        while (tabel4data.getChildCount() > 1) {
            tabel4data.removeViewAt(1);
        }
        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();//
        for (int posisi = 0; posisi < data.size(); posisi++) {
            TableRow tabelBaris = new TableRow(this);
            ArrayList<Object> baris = data.get(posisi);

            TextView idTxt = new TextView(this);
            idTxt.setText(baris.get(0).toString());
            tabelBaris.addView(idTxt);

            TextView namaTxt = new TextView(this);
            namaTxt.setText(baris.get(1).toString());
            tabelBaris.addView(namaTxt);

            TextView hobiTxt = new TextView(this);
            hobiTxt.setText(baris.get(2).toString());
            tabelBaris.addView(hobiTxt);

            tabel4data.addView(tabelBaris);
        }
    }
}
