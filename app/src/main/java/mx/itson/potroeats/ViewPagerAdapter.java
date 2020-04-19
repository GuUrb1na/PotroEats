package mx.itson.potroeats;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.ViewPager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.squareup.picasso.Picasso;


public class ViewPagerAdapter extends PagerAdapter {

    //Toda esta clase es la que se genera cuando inportas el ViewPagerAdapter que se usa para el deslizamiento de imagenes
    //en la actividad cuenta
    private Context context;
    private String[] imageUrls;

    ViewPagerAdapter(Context context, String[] imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return imageUrls.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        Picasso.get()
                .load(imageUrls[position])
                .fit()
                .centerCrop()
                .into(imageView);
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
