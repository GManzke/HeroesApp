package dev.manzke.heroesapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<String> mName = new ArrayList<>();
    private Context mContext;
    private OnHeroListener mOnHeroListener;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mImage, ArrayList<String> mName, OnHeroListener mOnHeroListener){
        Log.e(TAG, "RecyclerViewAdapter constructor: called.");

        this.mContext = mContext;
        this.mImage = mImage;
        this.mName = mName;
        this.mOnHeroListener = mOnHeroListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder: called.");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hero_item, parent, false);
        ViewHolder holder = new ViewHolder(view, mOnHeroListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder: called.");

        Picasso.get().load(mImage.get(position)).into(holder.image);
        holder.name.setText(mName.get(position));

    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: called.");

        return mName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image;
        TextView name;
        RelativeLayout parentLayout;
        OnHeroListener onHeroListener;

        public ViewHolder(@NonNull View itemView, OnHeroListener onHeroListener) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.text_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            this.onHeroListener = onHeroListener;
            itemView.setOnClickListener(this);

            Log.e(TAG, "ViewHolder constructor: called.");
            
        }

        @Override
        public void onClick(View v) {
            onHeroListener.onHeroListener(getAdapterPosition());
        }
    }

    public interface OnHeroListener {
        void onHeroListener(int position);
    }
}
