package hackathon.baggage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.TravelViewHolder> {

    ArrayList<Travel> mDataList;
    LayoutInflater mInflater;
    Context mContext;
    public TravelAdapter(Context context, ArrayList<Travel> dataList) {
        mContext  = context;
        mInflater = LayoutInflater.from(context);
        mDataList = dataList;
    }

    @Override
    public TravelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_travel, parent, false);
        TravelViewHolder travelViewHolder = new TravelViewHolder(view);
        return travelViewHolder;
    }

    @Override
    public void onBindViewHolder(TravelViewHolder holder, int position) {
        Picasso.with(mContext).load(mDataList.get(position).getUser().getProfileImageUrl()).into(holder.mProfilePhoto);
        holder.mUsername.setText(mDataList.get(position).getUser().getName());
        holder.mBaggageCapacity.setText(mDataList.get(position).baggageCapacity);
        holder.mSourceLocation.setText(mDataList.get(position).getSource());
        holder.mDestinationLocation.setText(mDataList.get(position).getDestination());
        holder.mTravelDate.setText(mDataList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class TravelViewHolder extends RecyclerView.ViewHolder{
        ImageView mProfilePhoto;
        TextView mUsername, mBaggageCapacity, mSourceLocation, mDestinationLocation, mTravelDate;

        public TravelViewHolder(View itemView) {
            super(itemView);
            mProfilePhoto = (ImageView) itemView.findViewById(R.id.iv_profile_photo);
            mUsername = (TextView) itemView.findViewById(R.id.tv_username);
            mBaggageCapacity = (TextView) itemView.findViewById(R.id.tv_baggage_capacity);
            mSourceLocation = (TextView) itemView.findViewById(R.id.tv_source_location);
            mDestinationLocation = (TextView) itemView.findViewById(R.id.tv_destination_location);
            mTravelDate = (TextView) itemView.findViewById(R.id.tv_travel_date);

        }
    }
}
