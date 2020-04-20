package mx.itson.potroeats.recyclercarrito;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mx.itson.potroeats.R;

public class Carritoadapter extends RecyclerView.Adapter<Carritoadapter.CarritoViewHolder> {
    private ArrayList<Carritoitem> mCarritoList;

    public static class CarritoViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public CarritoViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
        }
    }

    public Carritoadapter(ArrayList<Carritoitem> carritoList) {
        mCarritoList = carritoList;
    }

    @Override
    public CarritoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_carrito, parent, false);
        CarritoViewHolder evh = new CarritoViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(CarritoViewHolder holder, int position) {
        Carritoitem currentItem = mCarritoList.get(position);

        Picasso.get().load(currentItem.getImageResource()).fit().centerCrop().into(holder.mImageView);
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mCarritoList.size();
    }
}