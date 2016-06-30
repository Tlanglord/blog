package com.qiang.blog.anim;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class ScalePageTransformer implements PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    @Override
    public void transformPage(View page, float position) {
	int pageWidth = page.getWidth();

	if (position < -1) { // [-Infinity,-1)
	    page.setAlpha(0);
	} else if (position <= 0) { // [-1,0] a
	    // page
	    // 当前页
	    page.setAlpha(1);
	    page.setTranslationX(0);
	    page.setScaleX(1);
	    page.setScaleY(1);
	} else if (position <= 1) { // (0,1] 
	    
	    //下一页动画
	    page.setAlpha(1 - position);
	    // Counteract the default slide transition
	    page.setTranslationX(pageWidth * -position);
	    // Scale the page down (between MIN_SCALE and 1)
	    float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)
		    * (1 - Math.abs(position));
	    page.setScaleX(scaleFactor);
	    page.setScaleY(scaleFactor);

	} else { // (1,+Infinity]
	    page.setAlpha(0);
	}
    }
}
