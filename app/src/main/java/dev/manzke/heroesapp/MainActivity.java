package dev.manzke.heroesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.manzke.heroesapp.model.HeroCharacters;
import dev.manzke.heroesapp.model.HeroModel;
import dev.manzke.heroesapp.network.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnHeroListener {

    private static final String TAG = "MainActivity";

    private HeroModel heroModel;
    private final ArrayList<String> mImageUrls = new ArrayList<String>();
    private final ArrayList<String> mNames = new ArrayList<String>();
    private final ArrayList<String> mDescription = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initImageBitmap();
    }

    private void initImageBitmap() {
        Log.e(TAG, "initImageBitmap: called.");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com:443/v1/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);

        Call<HeroModel> call = apiService.getHeroes("100", "-name");

        call.enqueue(new Callback<HeroModel>() {
            @Override
            public void onResponse(Call<HeroModel> call, Response<HeroModel> response) {
                if (!response.isSuccessful()) {
                    Log.e(TAG, "OnResponse Error: called.");

                    mImageUrls.add("https://unowp.com/wp-content/uploads/2017/08/white-screen-of-death.png");
                    mNames.add("Code: " + response.code() +"\n" + "Message: " + response.message());

                    return;
                }

                heroModel = response.body();
                Log.e(TAG, "Body sent to heroModel: called.");

                for(HeroCharacters character : heroModel.getHeroConfig().getHeroCharacters()){

                    String path = character.getThumbnail().getPath() + "." + character.getThumbnail().getExtension();
                    path = path.replace("http", "https");

                    mImageUrls.add(path);
                    mNames.add(character.getName());
                    mDescription.add(character.getDescription());


                }

                initRecyclerView();

            }

            @Override
            public void onFailure(Call<HeroModel> call, Throwable t) {
                Log.e(TAG, "onFailture: called.");
            }
        });


    }

    private void initRecyclerView() {
        Log.e(TAG, "initRecyclerView: init RecyclerView.");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mImageUrls, mNames, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onHeroListener(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("image_url", mImageUrls.get(position));
        intent.putExtra("name", mNames.get(position));
        intent.putExtra("description", mDescription.get(position));

        startActivity(intent);

    }
}
