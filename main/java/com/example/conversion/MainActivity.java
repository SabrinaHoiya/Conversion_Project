package com.example.conversion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    RadioButton dinarToEuro, euroToDinar;
    EditText entree;
    TextView sortie;
    Button conversion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        entree= (EditText) findViewById(R.id.monnaie);
        sortie= (TextView) findViewById(R.id.result);

        euroToDinar= (RadioButton) findViewById(R.id.conv1);
        dinarToEuro = (RadioButton) findViewById(R.id.conv2);

        conversion = (Button) findViewById(R.id.convertir);

        sortie.setText("Resultat: ");

        conversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertir(v);
            }
        });

        registerForContextMenu(euroToDinar);

        euroToDinar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.showContextMenu();
                return true;
            }
        });

    }

    public void convertir(View v){
        if (entree.getText().toString().equals("")) {
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Champs Manquant");
            alertDialog.setMessage("Vous devez insérer une valeur à convertir !!!");
            alertDialog.setIcon(R.drawable.alert);
            alertDialog.setButton(alertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }
        else{
            float valeurInitiale = Float.valueOf(entree.getText().toString());
            float resultat= 0;
            if (euroToDinar.isChecked()){
                resultat = convEuroToDinar(valeurInitiale);
                sortie.setText("Resultat: "+ String.valueOf(resultat));
            }
            else if (dinarToEuro.isChecked()){
                resultat = convDinarToEuro(valeurInitiale);
                sortie.setText("Resultat: "+ String.valueOf(resultat));
            }
            else {
                Toast.makeText(getApplicationContext(), "choisir la type de conversion", LENGTH_LONG).show();
            }

        }
    }

    private float convEuroToDinar (float valeurEuro){ return (float) (valeurEuro * 2.95);}
    private float  convDinarToEuro (float valeurDinar){ return (float) (valeurDinar * 0.34);}

    public void onCreateContextMenu (ContextMenu menu, View v , ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,1,0 , "Conversion euro => dinar");
        menu.add(0,2,0 , "Conversion dinar => euro");
        menu.add(0,3,0 , "Conversion C <=> F");
        menu.add(0,4,0 , "Conversion F <=> C");
        menu.add(0,5,0,"Quitter");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "0.3", LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(this, "2.9919", LENGTH_LONG).show();
                break;
            case 3:
                Intent i = new Intent(MainActivity.this, ConvertionTemperatureActivity.class);
                startActivity(i);
                break;
            case 4:
                Intent j = new Intent(MainActivity.this, ConvertionTemperatureActivity.class);
                startActivity(j);
                break;
            case 5:
                this.finish();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,1,0, "Conversion C <=> F");
        menu.add(0,2,0, "Quitter");
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                Intent i = new Intent(MainActivity.this, ConvertionTemperatureActivity.class);
                startActivity(i);
                break;
            case 2:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}