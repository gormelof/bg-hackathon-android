package hackathon.baggage.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import hackathon.baggage.R;
import hackathon.baggage.activities.BaseActivity;
import hackathon.baggage.response.requests.Datum;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {
    private static final String TAG = TravelAdapter.class.getSimpleName().toUpperCase();

    private List<Datum> mDataList;
    private LayoutInflater mInflater;
    private Context mContext;

    public RequestAdapter(Context context, List<Datum> dataList) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDataList = dataList;
    }

    @Override
    public RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_travel, parent, false);
        RequestViewHolder requestViewHolder = new RequestViewHolder(view);
        return requestViewHolder;
    }

    @Override
    public void onBindViewHolder(RequestViewHolder holder, int position) {
        holder.mInfo.setText("You have a pending request.");
        if (Objects.equals(mDataList.get(position).getTravel().getUser(), BaseActivity.USER_ID)) {
            holder.mInfoPhoto.setImageResource(R.drawable.passenger_with_baggage);
        } else if (Objects.equals(mDataList.get(position).getPack().getUser(), BaseActivity.USER_ID)) {
            holder.mInfoPhoto.setImageResource(R.drawable.icons8_package);
        }

        /*
        try {
            Picasso.with(mContext).load(mDataList.get(position).getUser().getPhoto()).into(holder.mInfoPhoto);
            holder.mName.setText(mDataList.get(position).getUser().getName());
            holder.mWeight.setText(Integer.toString(mDataList.get(position).getWeight()));
            holder.mFrom.setText(mDataList.get(position).getFrom());
            holder.mTo.setText(mDataList.get(position).getTo());
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
        }
        */

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class RequestViewHolder extends RecyclerView.ViewHolder {
        ImageView mInfoPhoto;
        TextView mInfo;

        public RequestViewHolder(View itemView) {
            super(itemView);
            mInfoPhoto = (ImageView) itemView.findViewById(R.id.iv_row_request_info_image);
            mInfo = (TextView) itemView.findViewById(R.id.tv_row_travel_name);
        }
    }
}
