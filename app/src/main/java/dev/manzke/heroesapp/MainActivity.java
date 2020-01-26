package dev.manzke.heroesapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import dev.manzke.heroesapp.Model.HeroModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView text_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_test = findViewById(R.id.text_test);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com:443/v1/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);

        Call<HeroModel> call = apiService.getHeroes();

        call.enqueue(new Callback<HeroModel>() {
            @Override
            public void onResponse(Call<HeroModel> call, Response<HeroModel> response) {
                if (!response.isSuccessful()) {

                    text_test.setText("Code: " + response.code() +"\n");
                    text_test.append("Message: " + response.message());

                    return;
                }

                HeroModel heroModel = response.body();
                String content = "";
                content += "name: " + heroModel.getHeroConfig().getHeroCharacters().get(0).getName();

                text_test.append(content);
            }

            @Override
            public void onFailure(Call<HeroModel> call, Throwable t) {

                text_test.setText(t.getMessage());
            }
        });
    }
}
