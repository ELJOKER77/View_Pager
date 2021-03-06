
package com.example.eljoker.ViewPager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.eljoker.ViewPager.Models.AzkarModel;
import com.example.eljoker.ViewPager.Models.LeafNode;
import com.example.eljoker.ViewPager.Models.SubNode;

import java.util.ArrayList;


public class Azkar_View_Pager extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    ArrayList<AzkarModel> azkar_models;
    ArrayList<LeafNode> leafNodes;
    AzkarModel azkarModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azkar__view__pager);
        azkar_models = new ArrayList<>();
        leafNodes = new ArrayList<>();
        Intent intent = getIntent();
        // get Azkar moodel th\t we was send it from azkar adapter
        azkarModel = intent.getParcelableExtra("azkar_model");
        Log.i("azkarModel",azkarModel.getName());

/*
        for (int i = 0; i < 10; i++) {
            leafNodes.add(new LeafNode( intent.getStringExtra("value") + i,
                    intent.getStringExtra("desc") + i));
        }*/
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), azkar_models);

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_azkar__view__pager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void back(View view) {
        Intent intent = new Intent(this, Azkar_Main.class);
        startActivity(intent);
    }

    public static class PlaceholderFragment extends Fragment
    {
        int counter = 1;
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            RelativeLayout relativeLayout_ViewPager, relative_value;
            View rootView = inflater.inflate(R.layout.fragment_azkar__view__pager, container, false);

            final ImageView counter_img;
            counter_img = rootView.findViewById(R.id.counter_img);
            final TextView azkar_View_Pager_title, txt_value, txt_description,
                    txt_iteration, txt_counter, txt_allViews, txt_CurrentPosition, txt_counter_vis;

            final AzkarModel azkarModel = new AzkarModel();
            azkar_View_Pager_title = rootView.findViewById(R.id.azkar_View_Pager_title);
            azkar_View_Pager_title.setText(azkarModel.getName());
            txt_value = rootView.findViewById(R.id.value);
            txt_description = rootView.findViewById(R.id.description);
            txt_iteration = rootView.findViewById(R.id.itiration);
            txt_counter = rootView.findViewById(R.id.counter);
            txt_allViews = rootView.findViewById(R.id.allViews);
            txt_CurrentPosition = rootView.findViewById(R.id.currentPosition);

            Intent intent = getActivity().getIntent();

            int subNodeSize = getArguments().getInt("subNodeSize");
            txt_allViews.setText(String.valueOf(subNodeSize));
            txt_value.setText(getArguments().getString("value"));
            txt_description.setText(getArguments().getString("description"));
            final int itiration = getArguments().getInt("iteration");
            txt_iteration.setText(String.valueOf(itiration));
            txt_CurrentPosition.setText(String.valueOf(getArguments().getInt("currntPosition")+1));
            txt_counter.setText(String.valueOf(counter));
            txt_counter_vis = rootView.findViewById(R.id.counter_vis);

            counter_img.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if (counter == itiration)
                    {
                        counter_img.setEnabled(false);
                    }
                    else
                        {
                        txt_counter.setVisibility(View.GONE);
                        counter++;
                        txt_counter_vis.setText(Integer.toString(counter));
                         }

                  /*  for (int counter = 1; counter <= itiration; counter++)
                    {
                        txt_counter_vis.setText(String.valueOf(counter));

                    }*/
                }
            });

            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        //ArrayList<LeafNode> list = new ArrayList<>();
        ArrayList<AzkarModel> azkar_models;
        ArrayList<LeafNode> leafNodes;

        public SectionsPagerAdapter(FragmentManager fm, ArrayList<AzkarModel> azkar_models) {
            super(fm);
            this.azkar_models = azkar_models;
        }


        @Override
        public Fragment getItem(int position)
        {
            Log.i("position", String.valueOf(position));

            String description, value;
                int iteration;
                value = azkarModel.getSubNodes().get(position).getLeafNode().getValue();
                Log.i("valuelog", value);
                description = azkarModel.getSubNodes().get(position).getLeafNode().getDescription();
                iteration = azkarModel.getSubNodes().get(position).getIteration();
                int subNodeSize = azkarModel.getSubNodes().size()+1;
                String value1 = value + "  Desc = " + description;

                Log.i("getItem", value1);

                PlaceholderFragment placeholderFragment = new PlaceholderFragment();
                Bundle bundle = new Bundle();
                bundle.putString("value", value1);
                bundle.putString("value", value);
                bundle.putString("description", description);
                bundle.putInt("iteration", iteration);
                bundle.putInt("currntPosition", position);
                bundle.putInt("subNodeSize", subNodeSize);


            placeholderFragment.setArguments(bundle);

            return placeholderFragment;

        }

        @Override
        public int getCount()
        {
            System.out.println("size== " + azkarModel.getSubNodes().size()+1);
            // Show 3 total pages.

            /*int i = 90;
            Intent intent = getIntent();
            int leafNode = intent.getIntExtra("leafNode", i);
            System.out.println("leafNode ==" + leafNode);*/

            return azkarModel.getSubNodes().size()+1;
        }
    }
}


        /*for (int i = 0; i < 10; i++)

        {
        String description, value;
        int iteration;
        value = azkarModel.getSubNodes().get(i).getLeafNode().getValue();
        description = azkarModel.getSubNodes().get(i).getLeafNode().getDescription();
        iteration = azkarModel.getSubNodes().get(i).getIteration();
        String value1 = value + "  Desc = " + description;
        Bundle bundle = new Bundle();
        bundle.putString("value", value);
        bundle.putString("desc", description);
        bundle.putInt("itiration", iteration);
        Log.i("data", value1);
        }
*/