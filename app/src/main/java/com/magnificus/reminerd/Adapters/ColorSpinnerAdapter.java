package com.magnificus.reminerd.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.magnificus.reminerd.Entities.ColorEntity;
import com.magnificus.reminerd.R;

import java.util.List;

/**
 * Created by allysson on 17/06/17.
 */

public class ColorSpinnerAdapter extends BaseAdapter {

    private final Context context;
    private final List<ColorEntity> colorEntityList;

    public ColorSpinnerAdapter(Context context, List<ColorEntity> colorEntityList) {
        this.context = context;
        this.colorEntityList = colorEntityList;
    }

    @Override
    public int getCount() {
        return colorEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return colorEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ColorEntity color = colorEntityList.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;

        if(view == null) {
            view = inflater.inflate(R.layout.adapter_row_spinner_color, parent, false);
        }

        View adapterColor = (View) view.findViewById(R.id.adapter_row_spinner_color);
        adapterColor.setBackgroundColor(Color.parseColor(color.getHexadecimal()));

        TextView adapterName = (TextView) view.findViewById(R.id.adapter_row_spinner_name);
        adapterName.setText(color.getName());

        return view;
    }
}
