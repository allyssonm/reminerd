package com.magnificus.reminerd.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.magnificus.reminerd.Entities.CategoryEntity;
import com.magnificus.reminerd.Entities.ColorEntity;
import com.magnificus.reminerd.R;

import java.util.List;

/**
 * Created by gui on 01/06/17.
 */

public class CategoriesAdapter extends ArrayAdapter<CategoryEntity> {

    public CategoriesAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CategoriesAdapter(Context context, int resource, List<CategoryEntity> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());

            view = inflater.inflate(R.layout.row_category,null);
        }

        CategoryEntity category = getItem(position);

        if (category != null) {

            View color_display = (View) view.findViewById(R.id.row_category_color);
            TextView tv_name = (TextView) view.findViewById(R.id.row_category_name);

            ColorEntity categoryColor = category.getColorEntity();

            if (color_display != null && categoryColor != null) {
                GradientDrawable bg = (GradientDrawable) color_display.getBackground();
                bg.setColor(Color.parseColor(categoryColor.getHexadecimal()));
            }

            if (tv_name != null)
                tv_name.setText(category.getName());

        }

        return view;
    }
}
