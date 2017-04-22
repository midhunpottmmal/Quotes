package myoracle.com.quotes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Midhun on 22-04-2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Quote> quoteList;

    public DataAdapter(ArrayList<Quote> quoteList) {
        this.quoteList = quoteList;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.quotes_card_cell, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_country.setText(this.quoteList.get(i).getQuote());
        viewHolder.qt_qu.setText(this.quoteList.get(i).getAuthor());
    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_country;
        private TextView qt_qu;
        public ViewHolder(View view) {
            super(view);
            qt_qu= (TextView) view.findViewById(R.id.qt_qu);
            tv_country = (TextView)view.findViewById(R.id.tv_country);
        }
    }

}