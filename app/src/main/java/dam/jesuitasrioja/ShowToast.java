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
        String subject = intent.getStringExtra("texto");
        String[] correo = {intent.getStringExtra("correo")};
        String message = getString(R.string.message);
        Intent sendIntent = new Intent();

        sendIntent.setAction(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("mailto:"));
        sendIntent.putExtra(Intent.EXTRA_TEXT, subject);
        sendIntent.putExtra(Intent.EXTRA_EMAIL, correo);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, message);

        setResult(RESULT_OK, sendIntent);
        finish();
    }

    public void cancelar(View view) {
        String toast = getString(R.string.toast);
        Intent intent = new Intent();
        intent.putExtra("Toast", toast);
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
