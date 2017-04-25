package myoracle.com.quotes;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Midhun on 20-04-2017.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>{

    private List<Categories> categoriesList;

    @Override
    public CategoriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new CategoriesAdapter.MyViewHolder(itemView);
    }




    @Override
    public void onBindViewHolder(CategoriesAdapter.MyViewHolder holder, int position) {
        Categories categories = categoriesList.get(position);

          holder.categories.setText(categories.getCategory());
          holder.genre.setText(categories.getGenre());

          holder.qIcon.setBackgroundResource(getIconIds(categories.getcIcon()));
    }

    private int getIconIds(String imageName) {
        int image = R.drawable.angry;

        if(imageName.toString().equals("laugh.png"))
            image = R.drawable.laugh;
        else if(imageName.toString().equals("examicon.png"))
            image = R.drawable.examicon;
        else if(imageName.toString().equals("heartbreak.png"))
            image = R.drawable.heartbreak;
        else if(imageName.toString().equals("whatsicon.png"))
            image = R.drawable.whatsicon;
        else if(imageName.toString().equals("love.png"))
            image = R.drawable.love;
        else if(imageName.toString().equals("life.png"))
            image = R.drawable.life;
        else if(imageName.toString().equals("creative.png"))
            image = R.drawable.creative;
        else if(imageName.toString().equals("sad.png"))
            image = R.drawable.sad;
        else if(imageName.toString().equals("sad.png"))
            image = R.drawable.sad;
        else if(imageName.toString().equals("sex.png"))
            image = R.drawable.sex;
        else if(imageName.toString().equals("lust.png"))
            image = R.drawable.lust;
        else if(imageName.toString().equals("funny.png"))
            image = R.drawable.laugh;
        else if(imageName.toString().equals("wellbeing.png"))
            image = R.drawable.wellbeing;
        else if(imageName.toString().equals("happy.png"))
            image = R.drawable.happy;
        else if(imageName.toString().equals("motivate.png"))
            image = R.drawable.motivate;
        else if(imageName.toString().equals("attitude.png"))
            image = R.drawable.attitude;

        return image;
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView categories;
        public TextView qIcon;
        public TextView genre;

        public MyViewHolder(View view) {
            super(view);
            categories = (TextView) view.findViewById(R.id.listCategori);
            genre= (TextView)view.findViewById(R.id.genre);
            qIcon= (TextView)view.findViewById(R.id.qIcon);
        }
    }

    public CategoriesAdapter(List<Categories> categoriesList) {

        this.categoriesList = categoriesList;
    }
}

