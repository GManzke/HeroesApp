package dev.manzke.heroesapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import dev.manzke.heroesapp.Model.HeroModel;
import dev.manzke.heroesapp.Network.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_name);
        imageView = findViewById(R.id.image);


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

                    textView.setText("Code: " + response.code() +"\n");
                    textView.append("Message: " + response.message());

                    return;
                }

                HeroModel heroModel = response.body();
                String content = "";
                String path = "";
                content += "name: " + heroModel.getHeroConfig().getHeroCharacters().get(0).getName();
                path = heroModel.getHeroConfig().getHeroCharacters().get(0).getThumbnail().getPath() + "." +
                        heroModel.getHeroConfig().getHeroCharacters().get(0).getThumbnail().getExtension();
                path = path.replace("http", "https");

                Picasso.get().load(path).into(imageView);

                textView.setText(content);

            }

            @Override
            public void onFailure(Call<HeroModel> call, Throwable t) {

                textView.setText(t.getMessage());
            }
        });
    }
}
