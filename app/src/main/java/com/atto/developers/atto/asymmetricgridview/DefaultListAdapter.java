package com.atto.developers.atto.asymmetricgridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.atto.developers.atto.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class DefaultListAdapter extends ArrayAdapter<DemoItem> implements DemoAdapter {

    private final LayoutInflater layoutInflater;

    public DefaultListAdapter(Context context, List<DemoItem> items) {
        super(context, 0, items);
        layoutInflater = LayoutInflater.from(context);
    }

    public DefaultListAdapter(Context context) {
        super(context, 0);
        layoutInflater = LayoutInflater.from(context);
    }

    int[] images = {R.drawable.sample_rectangle_image1, R.drawable.sample_rectangle_image2, R.drawable.sample_rectangle_image3,
            R.drawable.sample_rectangle_image4, R.drawable.sample_rectangle_image5, R.drawable.sample_rectangle_image6, R.drawable.sample_rectangle_image7,
            R.drawable.sample_rectangle_image8, R.drawable.sample_rectangle_image9, R.drawable.sample_rectangle_image10
            ,R.drawable.sample_rectangle_image11, R.drawable.sample_rectangle_image12};

    Random r = new Random();
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        View v;

        DemoItem item = getItem(position);
        boolean isRegular = getItemViewType(position) == 0;

        if (convertView == null) {
            v = layoutInflater.inflate(
                    isRegular ? R.layout.adapter_item : R.layout.adapter_item_odd, parent, false);
        } else {
            v = convertView;
        }

        ImageView imageView;
        if (isRegular) {
            imageView = (ImageView) v.findViewById(R.id.imageView);

        } else {
            imageView = (ImageView) v.findViewById(R.id.imageView_odd);
        }

        imageView.setImageResource(images[r.nextInt(12)]);
        //imageView.setText(String.valueOf(item.getPosition()));

        return v;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? 1 : 0;
    }

    public void appendItems(List<DemoItem> newItems) {
        addAll(newItems);
        notifyDataSetChanged();
    }

    public void setItems(List<DemoItem> moreItems) {
        clear();
        appendItems(moreItems);
    }
}