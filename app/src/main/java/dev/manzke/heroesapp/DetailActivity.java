package dev.manzke.heroesapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("name") && getIntent().hasExtra("description")) {

            String imageUrl = getIntent().getStringExtra("image_url");
            String name = getIntent().getStringExtra("name");
            String description = getIntent().getStringExtra("description");

            setImage(imageUrl, name, description);
        }
    }

    private void setImage(String pImageUrl, String pName, String pDescription) {
        ImageView image = findViewById(R.id.image_detail);
        TextView name = findViewById(R.id.text_detail_name);
        TextView description = findViewById(R.id.text_detail_description);

        name.setText(pName);
        description.setText(pDescription);
        Picasso.get().load(pImageUrl).into(image);

    }
}
