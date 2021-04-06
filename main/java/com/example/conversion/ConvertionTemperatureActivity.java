package com.example.conversion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class ConvertionTemperatureActivity extends AppCompatActivity {

    RadioButton celcusToF, fToCelcus;
    EditText entree;
    TextView sortie;
    Button conversion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertion_temperature);

        entree= (EditText) findViewById(R.id.temperature);
        sortie= (TextView) findViewById(R.id.result);

        celcusToF= (RadioButton) findViewById(R.id.conv1);
        fToCelcus = (RadioButton) findViewById(R.id.conv2);

        conversion = (Button) findViewById(R.id.convertir);

        sortie.setText("Resultat: ");

        conversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertir(v);
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
            if (celcusToF.isChecked()){
                resultat = convCelcusToF(valeurInitiale);
                sortie.setText("Resultat: "+ String.valueOf(resultat));
            }
            else if (fToCelcus.isChecked()){
                resultat = convFToCelcus(valeurInitiale);
                sortie.setText("Resultat: "+ String.valueOf(resultat));
            }
            else {
                Toast.makeText(getApplicationContext(), "choisir la type de conversion", LENGTH_LONG).show();
            }

        }
    }

    private float convCelcusToF (float tc){ return (float) ((9/5)*tc+32);}
    private float  convFToCelcus (float tf){ return (float) ((5/9)*tf-32);}

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,1,0, "Conversion euro <=> DT");
        menu.add(0,2,0, "Quitter");
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                Intent i = new Intent(ConvertionTemperatureActivity.this, MainActivity.class);
                startActivity(i);
                break;
            case 2:
                this.finish();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}