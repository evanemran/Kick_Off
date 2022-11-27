package com.evanemran.kickoff.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.evanemran.kickoff.R;
import com.evanemran.kickoff.models.MatchDataFly;
import com.makeramen.roundedimageview.RoundedImageView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {

    Context context;
    List<MatchDataFly> match;

    public SliderAdapter(Context context, List<MatchDataFly> match) {
        this.context = context;
        this.match = match;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item, parent, false);
        return new Holder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        MatchDataFly item = match.get(position);

        viewHolder.textView_homeName.setText(item.getHome_team().getCountry());
        viewHolder.textView_awayName.setText(item.getAway_team().getCountry());

        viewHolder.home_score.setText(item.getHome_team().getGoals() == null ? "0" : item.getHome_team().getGoals().toString());
        viewHolder.away_score.setText(item.getAway_team().getGoals() == null ? "0" : item.getAway_team().getGoals().toString());


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        DateTimeFormatter dateF = DateTimeFormatter.ofPattern("EEE, d MMM", Locale.ENGLISH);
        DateTimeFormatter timeF = DateTimeFormatter.ofPattern("hh:mm", Locale.ENGLISH);
        DateTimeFormatter ampmF = DateTimeFormatter.ofPattern("a", Locale.ENGLISH);

        String formattedDate = LocalDateTime.parse(item.getDatetime(), dateTimeFormatter)
                .atOffset(ZoneOffset.UTC)
                .atZoneSameInstant(ZoneId.systemDefault())
                .format(dateF);

        String formattedTime = LocalDateTime.parse(item.getDatetime(), dateTimeFormatter)
                .atOffset(ZoneOffset.UTC)
                .atZoneSameInstant(ZoneId.systemDefault())
                .format(timeF);

        String formattedAmPm = LocalDateTime.parse(item.getDatetime(), dateTimeFormatter)
                .atOffset(ZoneOffset.UTC)
                .atZoneSameInstant(ZoneId.systemDefault())
                .format(ampmF);

        viewHolder.match_date.setText(formattedDate);
        viewHolder.match_time.setText(formattedTime);
        viewHolder.match_time_ampm.setText(formattedAmPm);

        Picasso.get().load("https://countryflagsapi.com/png/" + item.getHome_team().getName()).placeholder(R.drawable.flag_placeholder).into(viewHolder.imageViewHome);
        Picasso.get().load("https://countryflagsapi.com/png/" + item.getAway_team().getName()).placeholder(R.drawable.flag_placeholder).into(viewHolder.imageViewAway);


    }

    @Override
    public int getCount() {
        return match.size();
    }

    public class Holder extends SliderViewAdapter.ViewHolder {

        RoundedImageView imageViewHome, imageViewAway;
        TextView textView_homeName, textView_awayName, match_time, match_date, match_time_ampm, home_score, away_score;

        public Holder(View itemView) {
            super(itemView);
            imageViewHome = itemView.findViewById(R.id.imageView_homeImage);
            imageViewAway = itemView.findViewById(R.id.imageView_awayImage);
            textView_homeName = itemView.findViewById(R.id.textView_homeName);
            textView_awayName = itemView.findViewById(R.id.textView_awayName);
            match_time = itemView.findViewById(R.id.match_time);
            match_date = itemView.findViewById(R.id.match_date);
            match_time_ampm = itemView.findViewById(R.id.match_time_ampm);
            home_score = itemView.findViewById(R.id.home_score);
            away_score = itemView.findViewById(R.id.away_score);

        }
    }
}
