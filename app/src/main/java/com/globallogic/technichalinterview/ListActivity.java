package com.globallogic.technichalinterview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.globallogic.technichalinterview.connectivity.ApiService;
import com.globallogic.technichalinterview.connectivity.ApiUtils;
import com.globallogic.technichalinterview.connectivity.Notebook;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NotebooksAdapter notebooksAdapter;
    List<Notebook> notebooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        notebooks = new ArrayList<>();

        getNotebooks();
        activateRecyclerView();

    }

    private void getNotebooks(){
        ApiService apiService = ApiUtils.getApiService();
        apiService.getNotebooks().enqueue(new Callback<List<Notebook>>() {
            @Override
            public void onResponse(Call<List<Notebook>> call, Response<List<Notebook>> response) {
                if (response.body() != null){
                    notebooks = response.body();
                    notebooksAdapter.updateNotebooksList(notebooks);
                } else {
                    Toast.makeText(getBaseContext(), "Something went wrong on Response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Notebook>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Failed to get Notebooks", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void activateRecyclerView(){
        recyclerView = findViewById(R.id.recycler_notebooks);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notebooksAdapter = new NotebooksAdapter(this, notebooks);
        notebooksAdapter.setOnItemClickListener(new NotebooksAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtra("NOTEBOOK", notebooks.get(position));
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(notebooksAdapter);


    }
}
