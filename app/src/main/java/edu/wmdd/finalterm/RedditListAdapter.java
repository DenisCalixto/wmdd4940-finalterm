package edu.wmdd.finalterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class RedditListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<RedditItem> itemsArrayList;

    public RedditListAdapter(Context context, ArrayList<RedditItem> itemsArrayList) {

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return itemsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsArrayList.get(position);
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
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reddit_item_layout, null, true);

            holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
        holder.txtTitle.setText(itemsArrayList.get(position).getTitle());

        return convertView;
    }

    private class ViewHolder {

        protected TextView txtTitle;
    }

}