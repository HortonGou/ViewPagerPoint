# ViewPagerPoint
A simple component to add point to viewpager.
# How to use it.
in xml
~~~
 ViewPagerPoint pagerPoint= (ViewPagerPoint) findViewById(R.id.point_viewpager);
 pagerPoint.setViewPager(viewPager);
 
 //You can change point how to layout on view by this method.
  pagerPoint.setPointGravity(ViewPagerPoint.POINT_GRAVITY_CENTER);
~~~
