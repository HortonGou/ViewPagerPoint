package me.ght.viewpagerpoint;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by ght on 2016/12/4.
 */

public class ViewPagerPoint extends LinearLayout {
    public static final int POINT_GRAVITY_CENTER=0;
    public static final int POINT_GRAVITY_FILL=1;
    int pointGravity=POINT_GRAVITY_CENTER;
    public ViewPagerPoint(Context context) {
        super(context);
        init();
    }

    public void setPointGravity(int pointGravity) {
        this.pointGravity = pointGravity;
    }

    public ViewPagerPoint(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewPagerPoint(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewPagerPoint(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
//        setOrientation(HORIZONTAL);
    }
    public void setViewPager(ViewPager viewPager){
        if (viewPager==null){
            return;
        }
        removeAllViews();
        int count = viewPager.getAdapter().getCount();
        for (int i = 0; i < count; i++) {
            ImageView imageView=new ImageView(getContext());
            imageView.setImageResource(R.drawable.selector_white_point);
            if (pointGravity == POINT_GRAVITY_CENTER){
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
                if (i!= count-1){
                    params.setMargins(0,0,(int) getResources().getDimension(R.dimen.default_padding),0);
                }
                imageView.setLayoutParams(params);
            }else {
                int height= LayoutParams.WRAP_CONTENT;
                int width= LayoutParams.WRAP_CONTENT;
                if (getOrientation()==HORIZONTAL){
                    width=0;
                }else {
                    height=0;
                }
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(width,height);
                params.weight=1;
                imageView.setLayoutParams(params);
            }


            addView(imageView);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (getChildCount()>position){
                    for (int i = 0; i < getChildCount(); i++) {
                        getChildAt(i).setPressed(false);
                    }
                   getChildAt(position).setPressed(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
       getChildAt(viewPager.getCurrentItem()).setPressed(true);
    }
}
