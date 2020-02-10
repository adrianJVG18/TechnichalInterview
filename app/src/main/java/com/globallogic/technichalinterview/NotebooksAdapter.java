package com.globallogic.technichalinterview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.globallogic.technichalinterview.connectivity.Notebook;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.util.List;

public class NotebooksAdapter extends RecyclerView.Adapter<NotebooksAdapter.ViewHolder> {

    private List<Notebook> notebooks;
    private LayoutInflater inflater;
    private static ClickListener clickListener;

    NotebooksAdapter(Context context, List<Notebook> notebooks) {
        this.inflater = LayoutInflater.from(context);
        this.notebooks = notebooks;
    }

    @NonNull
    @Override
    public NotebooksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_notebook, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = notebooks.get(position).getTitle();
        String description = notebooks.get(position).getDescription();
        String imageUrl = notebooks.get(position).getImage();

        holder.itemTitle.setText(title);
        holder.itemDescription.setText(description);
        Picasso.get()
                .load(imageUrl)
                .error(R.drawable.ic_laptop_mac_black_24dp)
                .into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return notebooks.size();
    }

    public void updateNotebooksList(List<Notebook> notebooks){
        this.notebooks = notebooks;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView itemImage;
        TextView itemTitle;
        TextView itemDescription;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.laptop_title);
            itemDescription = itemView.findViewById(R.id.laptop_description);
            itemImage = itemView.findViewById(R.id.laptop_image);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        NotebooksAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

}
