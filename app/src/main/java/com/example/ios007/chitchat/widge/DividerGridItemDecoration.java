package com.example.ios007.chitchat.widge;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by ios007 on 2017/7/5.
 */

public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {
    //分割线的颜色
    private final Drawable mDivider;
    //横向分割线的高与纵向分割线的宽
    private final int hSize,wSize;
    //分割的模式
    private final int mOrientation;
    //网状分割线
    public static final int GRID = 111;
    //列数
    int spanCount;

    public DividerGridItemDecoration(Resources resources, @ColorRes int color, @DimenRes int hsize, @DimenRes int wsize, int orientation) {
        mDivider = new ColorDrawable(resources.getColor(color));
        hSize = resources.getDimensionPixelSize(hsize);
        wSize = resources.getDimensionPixelSize(wsize);
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            drawHorizontal(parent,c);
        } else if (mOrientation == LinearLayoutManager.VERTICAL){
            drawVertical(parent,c);
        }else if(mOrientation==GRID){
            drawGrid(parent,c);
        }
    }

    /***
     * 画方格
     * @param parent
     * @param c
     */
    public void drawGrid(RecyclerView parent,Canvas c){
        int left,right,top,bottom;
        //父控件顶部的线
        left=parent.getPaddingLeft();
        top=parent.getPaddingTop();
        right=parent.getWidth();
        bottom=hSize;
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
        //父控件左边的线
        left=parent.getPaddingLeft();
        top=parent.getPaddingTop();
        right=wSize;
        bottom=parent.getHeight();
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
        final int childCount = parent.getChildCount();
        for(int i=0;i<childCount;i++){
            final View child = parent.getChildAt(i);
            //右边的线
            left = child.getRight();
            right = left + wSize;
            top=parent.getPaddingTop();
            bottom=child.getBottom()+hSize;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
            //底部的线
            left=parent.getPaddingLeft();
            right=child.getRight()+wSize;
            top = child.getBottom();
            bottom = top + hSize;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 画水平分割线
     * @param parent
     * @param c
     */
    public void drawVertical(RecyclerView parent,Canvas c){
        int left,right,top,bottom;
        //父控件左边的线
        left=parent.getPaddingLeft();
        top=parent.getPaddingTop();
        right=wSize;
        bottom=parent.getHeight();
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            left = child.getRight();
            right = left + wSize;
            top=parent.getPaddingTop();
            bottom=child.getBottom()+hSize;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 画竖直分割线
     *
     * @param parent
     * @param c
     */

    public void drawHorizontal(RecyclerView parent,Canvas c){
        int left,right,top,bottom;
        //父控件顶部的线
        left=parent.getPaddingLeft();
        top=parent.getPaddingTop();
        right=parent.getWidth();
        bottom=hSize;
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
        final int childCount = parent.getChildCount();
        for(int i=0;i<childCount;i++){
            final View child = parent.getChildAt(i);
            //底部的线
            left=parent.getPaddingLeft();
            right=child.getRight()+wSize;
            top = child.getBottom();
            bottom = top + hSize;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 获得显示列数
     * @param parent
     * @return
     */
    private int getSpanCount(RecyclerView parent) {
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    /**
     * 是不是第一行
     * @param pos
     * @param spanCount
     * @return
     */
    private boolean isFirstRaw(int pos,int spanCount) {
        if(pos<spanCount&& pos>0 && (pos!=(spanCount-1))){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 是不是第一列
     * @param pos
     * @param spanCount
     * @return
     */
    private boolean isFirstColum(int pos, int spanCount) {
        if (pos % spanCount == 0) {
            return true;
        }else{
            return false;
        }
    }
    /**
     * 是不是最后一列
     * @param pos
     * @param spanCount
     * @return
     */
    private boolean isLastColum(int pos, int spanCount) {
        if (pos % spanCount == (spanCount-1)) {
            return true;
        }else{
            return false;
        }
    }
    /**
     * 计算并空出中间划线位置
     * @param outRect
     * @param itemPosition
     * @param parent
     */
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        spanCount= getSpanCount(parent);
        if (itemPosition == 0 ) {
            outRect.set(wSize, hSize, wSize/2, hSize/2);
        }else if(itemPosition==(spanCount-1)){
            outRect.set(wSize/2, hSize, wSize, hSize/2);
        } else if (isFirstRaw(itemPosition, spanCount)) {
            //如果是第一行则空出顶部
            outRect.set(wSize/2, hSize, wSize/2, hSize/2);
        } else if (isFirstColum(itemPosition, spanCount)) {
            //如果是第一列则空出左边
            outRect.set(wSize, hSize/2, wSize/2, hSize/2);
        } else if(isLastColum(itemPosition, spanCount)){
            //如果是最后一列则空出右边
            outRect.set(wSize/2, hSize/2, wSize, hSize/2);
        }else {
            outRect.set(wSize/2, hSize/2, wSize/2, hSize/2);
        }
    }
}