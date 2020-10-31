package dam.jesuitasrioja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int TEXT_REQUEST = 1;
    int cantidad = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_CANCELED) {
                String toast = data.getStringExtra("Toast");
                Toast toast2 = Toast.makeText(this, toast, Toast.LENGTH_SHORT);
                toast2.show();
            }
            if (resultCode == RESULT_OK) {
                startActivity(data);
            }
        }
    }
    public void decrement(View view) {
        cantidad--;
        TextView cambiarTextoDec = findViewById(R.id.textView_count);
        cambiarTextoDec.setText(String.valueOf(cantidad));
    }

    public void increment(View view) {
        cantidad++;
        TextView cambiarTextoInc = findViewById(R.id.textView_count);
        cambiarTextoInc.setText(String.valueOf(cantidad));
    }


    public void submitOrder(View view) {

        double precioUnidad = 1.50;

        EditText TextoEditado = findViewById(R.id.editText_enter);
        String texto = TextoEditado.getText().toString();
        String texto_cantidad = texto + getString(R.string.you_ordered) + cantidad + getString(R.string.coffee);

        CheckBox nata = findViewById(R.id.check_whipped_cream);
        boolean seleccionarNata = nata.isChecked();

        CheckBox chocolate = findViewById(R.id.check_chocolate);
        boolean seleccionarChocolate = chocolate.isChecked();

        if (seleccionarNata) {
            texto_cantidad += getString(R.string.whipped_cream);
            precioUnidad += 0.50;
            if (seleccionarChocolate) {
                texto_cantidad += getString(R.string.and);
            }
        }

        if (seleccionarChocolate) {
            texto_cantidad += getString(R.string.chocolate);
            precioUnidad += 0.50;
        }

        double precioTotal = precioUnidad * cantidad;
        String texto_final = texto_cantidad + "\n" + precioTotal;

        EditText correo = findViewById(R.id.editText_email);
        String correo2 = correo.getText().toString();


        Intent intent = new Intent(this, ShowToast.class);
        intent.putExtra("correo", correo2);
        intent.putExtra("texto", texto_final);
        startActivityForResult(intent, TEXT_REQUEST);
    }


}