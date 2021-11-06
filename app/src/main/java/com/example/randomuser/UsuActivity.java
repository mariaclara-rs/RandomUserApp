package com.example.randomuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class UsuActivity extends AppCompatActivity {
    private Button btn_voltar;
    private TextView tv_nome, tv_sobrenome, tv_nac, tv_genero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu);

        setarDados();

        btn_voltar = findViewById(R.id.btn_voltar);
        btn_voltar.setOnClickListener(e -> finish());
    }

    private void setarDados() {
        Intent intent = getIntent();

        tv_nome = findViewById(R.id.tv_nome);
        tv_nome.setText(intent.getStringExtra("nome"));

        tv_sobrenome = findViewById(R.id.tv_sobrenome);
        tv_sobrenome.setText(intent.getStringExtra("sobrenome"));

        tv_genero = findViewById(R.id.tv_genero);
        tv_genero.setText(intent.getStringExtra("genero"));

        tv_nac = findViewById(R.id.tv_nac);
        tv_nac.setText(intent.getStringExtra("nacionalidade"));

        new carregarImagem(findViewById(R.id.iv_img)).execute(intent.getStringExtra("img"));
    }

    private class carregarImagem extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public carregarImagem(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                System.out.println("Error"+ e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}