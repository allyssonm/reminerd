package com.magnificus.reminerd.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.magnificus.reminerd.Entities.CategoryEntity;
import com.magnificus.reminerd.R;

import java.util.List;

/**
 * Created by allysson on 17/06/17.
 */

public class CategorySpinnerAdapter extends BaseAdapter {

    private final Context context;
    private final List<CategoryEntity> categoryEntityList;

    public CategorySpinnerAdapter(Context context, List<CategoryEntity> categoryEntityList) {
        this.context = context;
        this.categoryEntityList = categoryEntityList;
    }

    @Override
    public int getCount() {
        return categoryEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoryEntity category = categoryEntityList.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;

        if(view == null) {
            view = inflater.inflate(R.layout.adapter_row_spinner_category, parent, false);
        }

        View adapterColor = (View) view.findViewById(R.id.adapter_row_spinner_category_color);
        adapterColor.setBackgroundColor(Color.parseColor(category.getColorEntity().getHexadecimal()));

        TextView adapterName = (TextView) view.findViewById(R.id.adapter_row_spinner_category_name);
        adapterName.setText(category.getName());

        return view;
    }
}
