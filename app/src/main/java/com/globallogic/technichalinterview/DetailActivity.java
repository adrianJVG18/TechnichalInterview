package com.globallogic.technichalinterview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.globallogic.technichalinterview.connectivity.Notebook;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView detailTitle;
    TextView detailDescription;
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();

    }

    private void init(){
        Notebook notebook = ((Notebook) getIntent().getSerializableExtra("NOTEBOOK")) ;

        detailTitle = findViewById(R.id.detail_title);
        detailDescription = findViewById(R.id.detail_description);
        detailImage = findViewById(R.id.detail_image);

        assert notebook != null;
        detailTitle.setText(notebook.getTitle());
        detailDescription.setText(notebook.getDescription());
        Picasso.get()
                .load(notebook.getImage())
                .error(R.drawable.ic_laptop_mac_black_24dp)
                .into(detailImage);


    }
}
