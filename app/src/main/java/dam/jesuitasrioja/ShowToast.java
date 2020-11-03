package dam.jesuitasrioja;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ShowToast extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtoast);

        Intent intent = getIntent();
        String textoExtra = intent.getStringExtra("texto");
        String correo = intent.getStringExtra("correo");

        TextView texto = findViewById(R.id.textView_mensaje);
        texto.setText(textoExtra + "\n" + getString(R.string.your_email_is) + correo);
    }



    public void enviarCorreo(View view) {
        Intent intent = getIntent();
        String texto = intent.getStringExtra("texto");
        String[] correo = {intent.getStringExtra("correo")};
        String mensaje = getString(R.string.mensaje);

        Intent intent2 = new Intent();
        intent2.setAction(Intent.ACTION_SENDTO);
        intent2.setData(Uri.parse("mail to:"));
        intent2.putExtra(Intent.EXTRA_TEXT, texto);
        intent2.putExtra(Intent.EXTRA_EMAIL, correo);
        intent2.putExtra(Intent.EXTRA_SUBJECT, mensaje);

        setResult(RESULT_OK, intent2);
        finish();
    }

    public void cancelar(View view) {
        String cancelar = getString(R.string.correct);
        Intent intent = new Intent();
        intent.putExtra("Toast", cancelar);
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
