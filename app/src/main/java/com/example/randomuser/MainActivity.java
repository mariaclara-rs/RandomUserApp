package com.example.randomuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Spinner sp_genero, sp_nacionalidade;
    private Button bt_buscar;
    private String genero, nacionalidade = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp_genero = findViewById(R.id.sp_genero);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.generos,
                        android.R.layout.simple_spinner_item);
        sp_genero.setAdapter(adapter);

        sp_nacionalidade = findViewById(R.id.sp_nacionalidade);
        List<String> nacionalidades = new ArrayList();
        nacionalidades.addAll(Arrays.asList(
                "","AU - Australia","BR - Brasil","CA - Canada","CH - Suiça",
                "DE - Alemanha","DK - Dinamarca", "ES - Espanha", "FI - Finlandia","FR - França",
                "GB - Reino Unido","IE - Irlanda","IR - Irã", "NO - Noruega","NL - Holanda",
                "NZ - Nova Zelândia","TR - Turquia", "US - Estados Unidos"
        ));
        ArrayAdapter<String> adapter2 = new
                ArrayAdapter(this,android.R.layout.simple_spinner_item,nacionalidades);
        sp_nacionalidade.setAdapter(adapter2);


        bt_buscar = findViewById(R.id.bt_buscar);
        bt_buscar.setOnClickListener(e-> chamarWS(result -> trocarActivity(result)));
    }

    private void definirGenero() {
        genero = sp_genero.getSelectedItem().toString();
        if(genero.equals("Masculino"))
            genero = "male";
        else if(genero.equals("Feminino"))
            genero = "female";
    }

    private void definirNacionalidade() {
        nacionalidade = sp_nacionalidade.getSelectedItem().toString();
        if(!nacionalidade.isEmpty())
            nacionalidade = sp_nacionalidade.getSelectedItem().toString().substring(0,2);
    }

    public interface ApiCallback{
        void onSuccess(Result result);
    }

    private void chamarWS(final ApiCallback callback) {
        definirGenero();
        definirNacionalidade();

        Call <Results> call = new RetrofitConfig().getUsuService().carregarUsu(
                "gender,name,nat,picture",nacionalidade,genero,""
        );

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                callback.onSuccess(response.body().getResults().get(0));
            }

            @Override
            public void onFailure(Call <Results> call, Throwable t) {
                //tv_retorno.setText("Erro!");
            }
        });
    }

    private void trocarActivity(Result result) {
        Intent intent = new Intent(this,UsuActivity.class);

        //seta dados de uma activity em outra
        intent.putExtra("nome", result.getName().getFirst());
        intent.putExtra("sobrenome", result.getName().getLast());
        intent.putExtra("genero", result.getGender());
        intent.putExtra("nacionalidade", result.getNat());
        intent.putExtra("img", result.getPicture().getLarge());

        startActivity(intent);
    }
}