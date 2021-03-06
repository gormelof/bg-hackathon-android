package hackathon.baggage.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import hackathon.baggage.R;
import hackathon.baggage.response.travels.Datum;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.TravelViewHolder> {
    private static final String TAG = TravelAdapter.class.getSimpleName().toUpperCase();

    private List<Datum> mDataList;
    private LayoutInflater mInflater;
    private Context mContext;

    public TravelAdapter(Context context, List<Datum> dataList) {
        mContext = context;
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


        try {
            Picasso.with(mContext).load(mDataList.get(position).getUser().getPhoto()).into(holder.mPhoto);
            holder.mName.setText(mDataList.get(position).getUser().getName());
            holder.mWeight.setText(Integer.toString(mDataList.get(position).getWeight()));
            holder.mFrom.setText(mDataList.get(position).getFrom());
            holder.mTo.setText(mDataList.get(position).getTo());
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
        }

        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
            Date date = format.parse(mDataList.get(position).getDate());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            String year = Integer.toString(calendar.get(Calendar.YEAR));
            String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
            String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
            // String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
            // String minute = Integer.toString(calendar.get(Calendar.MINUTE));

            // PrettyTime prettyTime = new PrettyTime(new Locale("tr-TR"));

            holder.mDate.setText(day + "/" + month + "/" + year);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class TravelViewHolder extends RecyclerView.ViewHolder {
        ImageView mPhoto;
        TextView mName, mWeight, mFrom, mTo, mDate;

        public TravelViewHolder(View itemView) {
            super(itemView);
            mPhoto = (ImageView) itemView.findViewById(R.id.iv_row_travel_photo);
            mName = (TextView) itemView.findViewById(R.id.tv_row_travel_name);
            mWeight = (TextView) itemView.findViewById(R.id.tv_row_travel_weight);
            mFrom = (TextView) itemView.findViewById(R.id.tv_row_travel_from);
            mTo = (TextView) itemView.findViewById(R.id.tv_row_travel_to);
            mDate = (TextView) itemView.findViewById(R.id.tv_row_travel_date);
        }
    }
}
