package com.example.smnaggregator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class HashtagArrayAdapter extends ArrayAdapter<Hashtag> {

    private static int counter = 0;

    private List<Hashtag> hashtagList;
    private final LayoutInflater inflater;
    private final int layoutResource;

    private final ListView hashtagListView;


    public HashtagArrayAdapter(@NonNull Context context, int resource, @NonNull List<Hashtag> objects, ListView listView) {
        super(context, resource, objects);
        hashtagList = objects;
        layoutResource = resource;
        inflater = LayoutInflater.from(context);
        hashtagListView= listView;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        counter++;
        Log.d("ADAPTER", "get view in adapter just called. counter = "+counter);
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
            Log.w("VIEW_HOLDER", "View Holder Created");
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Hashtag currentTweet = hashtagList.get(position);

        viewHolder.text.setText(currentTweet.getText()+"");
        viewHolder.url.setText(currentTweet.getUrl()+"");

        return convertView;
    }

    private static class ViewHolder{

        final TextView text;
        final TextView url;


        ViewHolder(View view){

            text = view.findViewById(R.id.tweetText);
            url = view.findViewById(R.id.tweetUrl);

        }
    }

    @Override
    public int getCount() {
        return hashtagList.size();
    }

    public void setHashtagList(List<Hashtag> hashtagList) {
        this.hashtagList = hashtagList;
        hashtagListView.setAdapter(this);
    }
}