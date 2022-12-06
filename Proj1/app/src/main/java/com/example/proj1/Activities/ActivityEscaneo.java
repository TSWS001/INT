package com.example.proj1.Activities;

        import android.Manifest;
        import android.app.Activity;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.ContextCompat;

        import com.example.proj1.Classes.Product;
        import com.example.proj1.R;


public class ActivityEscaneo extends AppCompatActivity {

    private static final int CODIGO_PERMISOS_CAMARA = 1, CODIGO_INTENT = 2;
    private boolean permisoCamaraConcedido = false, permisoSolicitadoDesdeBoton = false;
    private TextView tvCodigoLeido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaneado);
        verificarYPedirPermisosDeCamara();

        tvCodigoLeido = findViewById(R.id.tvCodigoLeido);
        if (permisoCamaraConcedido) {
            escanear();
        }

        Button btnEscanear = findViewById(R.id.btnEscanear);
        btnEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!permisoCamaraConcedido) {
                    Toast.makeText(ActivityEscaneo.this, "Por favor permite que la app acceda a la cámara", Toast.LENGTH_SHORT).show();
                    permisoSolicitadoDesdeBoton = true;
                    verificarYPedirPermisosDeCamara();
                    return;
                }
                escanear();
            }
            });

        ImageView btnback_arrow= findViewById(R.id.back_icon);
        btnback_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityEscaneo.this,MainActivity.class));
            }
        });

        Button btnaceptar = findViewById(R.id.btnaceptar_escaneado);
        btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(ActivityEscaneo.this,ActivityCaducidad.class);
                //get data from server, and send it to ActivityCaducidad with an Intent
//                String[] array_str = new String[] {"ESPAGUETIS-0004","Espaguetis",""};
//                int[] array_int = new int[] {5,100,88,10};
                Product product = new Product("ESPAGUETIS-0004","Espaguetis",
                        "",5,100,88,10);
                i.putExtra("product", product);

                startActivity(i);
            }
        });
    }

    private void escanear() {
        Intent i = new Intent(ActivityEscaneo.this, ActivityEscanear.class);
        startActivityForResult(i, CODIGO_INTENT);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_INTENT) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    //Ahora lee el codigo de barras y guarda el texto
                    String codigo = data.getStringExtra("codigo");
                    tvCodigoLeido.setText(codigo);

                }
            }
        }
    }
//    public String analisi_resultado_camara(String str){
//        int i;
//        char c;
//        String res="";
//
//        str="ATUN-CLARO-0007";
//        String[] parts= str.split("-");
//
//        for (i=0; i<parts.length; i++){
//            c=parts[i].charAt(0);
//            if(Character.isLetter(c)){
//                res=res+parts[i]+" ";
//            }
//        }
//        return res.substring(0,res.length()-1);//es posible que sea -2
//    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CODIGO_PERMISOS_CAMARA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Escanear directamten solo si fue pedido desde el botón
                    if (permisoSolicitadoDesdeBoton) {
                        escanear();
                    }
                    permisoCamaraConcedido = true;
                } else {
                    permisoDeCamaraDenegado();
                }
                break;
        }
    }

    private void verificarYPedirPermisosDeCamara() {
        int estadoDePermiso = ContextCompat.checkSelfPermission(ActivityEscaneo.this, Manifest.permission.CAMERA);
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            // En caso de que haya dado permisos ponemos la bandera en true y llamar al metodo
            permisoCamaraConcedido = true;
        } else {
            // Si no, pedimos permisos. Ahora mira onRequestPermissionsResult
            ActivityCompat.requestPermissions(ActivityEscaneo.this, new String[]{Manifest.permission.CAMERA},
                    CODIGO_PERMISOS_CAMARA);
        }
    }


    private void permisoDeCamaraDenegado() {
        // Esto se llama cuando el usuario hace click en "Denegar" o
        // cuando lo denegó anteriormente
        Toast.makeText(ActivityEscaneo.this, "No puedes escanear si no das permiso", Toast.LENGTH_SHORT).show();
    }
}
