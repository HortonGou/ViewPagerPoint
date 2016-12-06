package me.ght.viewpagerpoint;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LayoutDirection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    ViewPagerPoint pagerPoint ;
    int count =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyViewPagerAdapter());
        pagerPoint= (ViewPagerPoint) findViewById(R.id.point_viewpager);
        pagerPoint.setViewPager(viewPager);
        findViewById(R.id.on_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) pagerPoint.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
                pagerPoint.setLayoutParams(params);
            }
        });
        findViewById(R.id.on_bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) pagerPoint.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
                pagerPoint.setLayoutParams(params);
            }
        });
        findViewById(R.id.on_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pagerPoint.setPointGravity(ViewPagerPoint.POINT_GRAVITY_CENTER);
                pagerPoint.setViewPager(viewPager);
            }
        });
        findViewById(R.id.on_fill).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pagerPoint.setPointGravity(ViewPagerPoint.POINT_GRAVITY_FILL);
                pagerPoint.setViewPager(viewPager);
            }
        });
        findViewById(R.id.add_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                viewPager.getAdapter().notifyDataSetChanged();
            }
        });
    }

    private class MyViewPagerAdapter extends PagerAdapter {
        ArrayList<View> views = new ArrayList<>();

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
            ImageView imageView = (ImageView) container.findViewById(R.id.imageview);
            if (imageView != null) {
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
            TextView textView = (TextView) container.findViewById(R.id.textview);
            if (textView != null ) {
                textView.setText("it is just a demo!");
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = null;
            if (position%2 == 0) {
                if (views.size() > 0&&views.size()>position && views.get(position) != null) {
                    view = views.get(position);
                } else {
                    view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_image, container, false);
                    views.add(view);
                }
                container.addView(view);
            } else if (position%2 == 1) {
                if (views.size() > 1&&views.size()>position&& views.get(position) != null) {
                    view = views.get(position);
                } else {
                    if (views.size() == 0) {
                        views.add(null);
                    }
                    view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_text, container, false);
                    views.add(view);
                }
                container.addView(view);
            }

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView(views.get(position));
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }


}
