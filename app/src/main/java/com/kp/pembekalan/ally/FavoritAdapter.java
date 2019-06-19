package com.kp.pembekalan.ally;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kp.pembekalan.ally.models.FavoriteModel;

import java.util.ArrayList;

/**
 * Created by Greace Situmorang on 6/19/2019.
 */

public class FavoritAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<FavoriteModel> favoriteModelArrayList;

    public FavoritAdapter(FavoriteActivity favoriteActivity, ArrayList<FavoriteModel> favoriteModelArrayList) {
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            convertView = layoutInflater.inflate( R.layout.activity_favorite, null, true );
            holder.txtNama = (TextView) convertView.findViewById( R.id.txtNama );

            convertView.setTag( holder );
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtNama.setText( "Name: " + favoriteModelArrayList.get( position ).getNama() );

        return convertView;
    }

    private class ViewHolder {
        TextView txtNama;
    }
}
