package myoracle.com.quotes;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Categories> categoriesList = new ArrayList<Categories>();
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private CategoriesAdapter categoriesAdapter;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        categoriesAdapter = new CategoriesAdapter(categoriesList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(categoriesAdapter);

        toolbar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(toolbar);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Categories categories = categoriesList.get(position);
                Intent intent;
                intent = new Intent(context, QuotesCardActivity.class);
                intent.putExtra("quotes", (Serializable) categories.getQuoteList());
                intent.putExtra("categoryTitle", categories.getCategory());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        createCategoryList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Toast.makeText(getApplicationContext(),"Please share this app with 3 contacts help us to grow :)", Toast.LENGTH_SHORT).show();

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hai");
                sendIntent.setType("text/plain");
                Intent.createChooser(sendIntent, "Share via");
                startActivity(sendIntent);
                return true;
            case R.id.action_rate_us:
                launchMarket();
                return true;
            case R.id.action_privacy_policy:
                android.app.FragmentManager fragmentManager =getFragmentManager();
                PrivacyDialogFragment privacyDialogFragment = new PrivacyDialogFragment();
                privacyDialogFragment.show(fragmentManager, "Sample Fragment");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createCategoryList() {

        try {
            JSONObject quotesJsonObject = new JSONObject(readJsonRaw());
            JSONArray categoriesJsonArray = quotesJsonObject.getJSONArray("Categories");

            for (int index = 0; index < categoriesJsonArray.length(); index++) {
                JSONObject categoriesObj = categoriesJsonArray.getJSONObject(index);
                JSONArray quotesJsonArray = categoriesObj.getJSONArray("quotes");
                List<Quote> quoteList = new ArrayList<Quote>();
                for (int j = 0; j < quotesJsonArray.length(); j++) {
                    JSONObject quotesObject = quotesJsonArray.getJSONObject(j);

                    Quote quote = new Quote(quotesObject.getString("quote"), quotesObject.getString("quoteauthor"));
                    quoteList.add(quote);
                }
                this.categoriesList.add(new Categories(categoriesObj.getString("name"), index, quoteList, categoriesObj.getString("icon")));
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }

        categoriesAdapter.notifyDataSetChanged();
    }

    private String readJsonRaw() {

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.quotes);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String inputJsonString = new String(buffer, "UTF-8");
            return inputJsonString;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void launchMarket() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
        }
    }

}