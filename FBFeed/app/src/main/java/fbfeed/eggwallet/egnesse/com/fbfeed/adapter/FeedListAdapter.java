package fbfeed.eggwallet.egnesse.com.fbfeed.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import fbfeed.eggwallet.egnesse.com.fbfeed.R;
import fbfeed.eggwallet.egnesse.com.fbfeed.activity.FeedImageView;
import fbfeed.eggwallet.egnesse.com.fbfeed.app.AppController;
import fbfeed.eggwallet.egnesse.com.fbfeed.dto.FeedItemDTO;

/**
 * Created by Dell on 1/3/2016.
 */
public class FeedListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<FeedItemDTO> feedItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public FeedListAdapter(Activity activity, List<FeedItemDTO> feedItems){
        this.activity = activity;
        this.feedItems = feedItems;
    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return feedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            convertView = inflater.inflate(R.layout.feed_item, null);
        if(imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        TextView name = (TextView)convertView.findViewById(R.id.name);
        TextView timestamp = (TextView)convertView.findViewById(R.id.timestamp);
        TextView statusMsg = (TextView)convertView.findViewById(R.id.txtStatusMsg);
        TextView url = (TextView)convertView.findViewById(R.id.txtUrl);
        NetworkImageView profilePic = (NetworkImageView)convertView.findViewById(R.id.profilePic);
        FeedImageView feedImageView = (FeedImageView)convertView.findViewById(R.id.feedImage1);

        FeedItemDTO item = feedItems.get(position);

        name.setText(item.getName());

        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(Long.parseLong(item.getTimeStamp()), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        timestamp.setText(timeAgo);

        if(!TextUtils.isEmpty(item.getStatus())){
            statusMsg.setVisibility(View.VISIBLE);
        }
        else{
            statusMsg.setVisibility(View.GONE);
        }
        if(item.getUrl() != null){
            url.setText(Html.fromHtml("<a href =\""+item.getUrl() + "\">" + item.getUrl() + "</a>"));
            url.setMovementMethod(LinkMovementMethod.getInstance());
            url.setVisibility(View.VISIBLE);
        }
        else{
            url.setVisibility(View.GONE);
        }

        profilePic.setImageUrl(item.getProfilePic(), imageLoader);

        if(item.getImage() != null){
            feedImageView.setImageUrl(item.getImage(), imageLoader);
            feedImageView.setVisibility(View.VISIBLE);
            feedImageView.setResponseObserver(new FeedImageView.ResponseObserver() {
                @Override
                public void onError() {
                }

                @Override
                public void onSuccess() {
                }
            });
        }
        else{
            feedImageView.setVisibility(View.GONE);
        }
        return convertView;
    }
}
