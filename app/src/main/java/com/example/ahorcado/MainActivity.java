package com.example.ahorcado;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Logica.Ahorcado;

public class MainActivity extends AppCompatActivity {
    private TableLayout palabra;
    private TextView categoria;
    private ImageView imagen;
    private TableLayout teclado;
    private int numImage;
    Ahorcado ahorcado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.palabra = findViewById(R.id.palabras);
        this.categoria = findViewById(R.id.categoria);
        this.imagen = findViewById(R.id.imagen);
        this.teclado = findViewById(R.id.teclado);
       iniciar();
    }

    private void iniciar() {
        ahorcado = new Ahorcado();
        this.categoria.setText(ahorcado.getTipo());
        this.categoria.setGravity(Gravity.CENTER);
        this.imagen.setImageResource(R.drawable.inicial);
        this.numImage=1;
        if (this.palabra.getChildCount() > 0){
            this.palabra.removeAllViews();
        }
        if (this.teclado.getChildCount() > 0){
            this.teclado.removeAllViews();
        }
        pintarPalabra(ahorcado.getItem());
        pintarTeclado();
    }

    private void pintarTeclado() {
        TableRow linea = new TableRow(this);
        linea.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        this.teclado.addView(linea);
        for(int i=1; i<=26; i++){
            if(i==6 || i==12 || i== 18 || i==24){
                linea = new TableRow(this);
                linea.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                this.teclado.addView(linea);
            }
            Button button = new Button(this);
            int l = i+96;
            char m = (char) l;
           button.setText(Character.toString(m));
          button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = (Button) v;
                    int id=0;
                    boolean dato = true;
                    do{
                        id = ahorcado.getPalabra((String) b.getText(),id);
                        if(id!=0){
                            TextView tv = findViewById(id);
                            tv.setText(Character.toString(ahorcado.getItem().charAt(id-1)));
                            b.setEnabled(false);
                            dato=false;
                            if (ahorcado.res() == 0) {
                                crearDialogo("Ganaste");

                            }
                        }
                    }while(id!=0);
                    if(dato){
                        incorrecto();
                    }

                }
            });

                linea.addView(button);


        }
    }

    private void incorrecto() {
        if(this.numImage==1){
            this.imagen.setImageResource(R.drawable.uno);
            this.numImage=2;
        }else{
            if(this.numImage==2){
                this.imagen.setImageResource(R.drawable.dos);
                this.numImage=3;
            }else{
                if(this.numImage==3){
                    this.imagen.setImageResource(R.drawable.tres);
                    this.numImage=4;
                }else{
                    if(this.numImage==4){
                        this.imagen.setImageResource(R.drawable.cuatro);
                        this.numImage=5;
                    }else{
                        if(this.numImage==5){
                            this.imagen.setImageResource(R.drawable.cinco);
                            crearDialogo("Perdiste");
                        }
                    }
                }
            }
        }
        Toast t = Toast.makeText(getApplicationContext(), "Incorrecto", Toast.LENGTH_SHORT);
        t.show();
    }
    private void crearDialogo(String mensaje){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setMessage(mensaje+"\nLa palabra es: "+ahorcado.getItem()+"\nDesea jugar nuevamente?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        iniciar();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog titulo = dialogo.create();
        titulo.setTitle("Juego Teminado");
        titulo.show();
    }

    private void pintarPalabra(String palabra) {
        TableRow linea = new TableRow(this);
        linea.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        linea.setGravity(Gravity.CENTER);
        this.palabra.addView(linea);
        for(int i =0; i<palabra.length(); i++){
            TextView tv = new TextView(this);
            tv.setText("-");
            tv.setTextColor(Color.parseColor("#070000"));
            tv.setId(i+1);
           // Toast t = Toast.makeText(getApplicationContext(), "ID"+tv.getId(), Toast.LENGTH_SHORT);
            // t.show();
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
          layoutParams.gravity = Gravity.RIGHT;
          layoutParams.setMargins(10, 10, 10, 10); // (left, top, right, bottom)
           tv.setLayoutParams(layoutParams);
            tv.setTextSize(18);
            linea.addView(tv);

        }


    }


}
