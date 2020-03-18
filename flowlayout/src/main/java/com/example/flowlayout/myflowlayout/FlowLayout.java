package com.example.flowlayout.myflowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.flowlayout.R;

/**
 * 自定义流式布局的类
 * <p>
 * typedArray.recycle();的作用?
 * 答:TypedArray的使用场景之一，就是上述的自定义View，会随着 Activity的每一次Create而Create，
 * 因此，需要系统频繁的创建array，对内存和性能是一个不小的开销，
 * 如果不使用池模式，每次都让GC来回收，很可能就会造成OutOfMemory。
 */
public class FlowLayout extends ViewGroup {

    private int mHSpace = 20;//每个子控件左右的间距,默认为20px
    private int mVSpace = 20;//每一行上下的间距,默认为20px
    private int mMaxLines = 0;//显示的最大行数,<1表示不限制
    private int mMaxColums = 0;//显示的最大列数,<1表示不限制

    //构造方法
    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        mHSpace = typedArray.getDimensionPixelSize(R.styleable.FlowLayout_h_space, dpToPx(10));
        mVSpace = typedArray.getDimensionPixelSize(R.styleable.FlowLayout_v_space, dpToPx(10));
        mMaxLines = typedArray.getInteger(R.styleable.FlowLayout_maxlines, -1);
        mMaxColums = typedArray.getInt(R.styleable.FlowLayout_maxcolums, mMaxColums);
        typedArray.recycle();//释放该实例，从而使其可被其他模块复用
    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 拿到父容器推荐的宽和高以及计算模式
        // View根据该规格从而决定自己的大小。
        // MeasureSpec(测量规格)由俩部分组成，一部分是SpecMode(测量模式)，另一部分是SpecSize（规格大小）。
        // View的MeasureSpec由父容器和自己布局参数共同决定
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //测量孩子的大小
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int lines = 1; // 定义行数,默认1行
        int colums = 0;//定义列数,默认0列(无数据)
        int contentLeft = getPaddingLeft();//获得view的左内边距
        int contentTop = getPaddingTop();//获得view的上边框(上内边距)
        int contentRight = sizeWidth - getPaddingRight();//控件宽度-右内边距 = 剩余宽度
        // 初始化(left: 左内边距/top: 上内边距/lineStartIndex: /lineEndIndex: )
        int left = contentLeft, top = contentTop;
        int lineStartIndex = 0, lineEndIndex = 0;

        //循环得到所有的子控件
        int childCount = getChildCount();//获得ViewGroup组内子元素的数量
        for (int i = 0; i < childCount; i++) {
            lineEndIndex = i;
            // 拿到每一个孩子
            View view = getChildAt(i);
            // 子控件(原始)的测量宽度
            int childMeasuredWidth = view.getMeasuredWidth();
            // 如果满足说明这一个已经一行放不下了
            //左内边距 + 子控件宽度 > 剩余宽度 || 到了最大列数(确保最大列数>=1即有数据)
            if (left + childMeasuredWidth > contentRight || (mMaxColums >= 1 && colums >= mMaxColums)) {
                // 如果到了最大的行数,就跳出循环
                if (mMaxLines >= 1 && mMaxLines <= lines) {
                    break;//该break跳出循环语句
                }
                lineStartIndex = i;
                //高度累加(子控件的最大高度 + 垂直间距)
                top += findMaxChildMaxHeight(lineStartIndex, lineEndIndex) + mVSpace;
                //宽度重置(左内边距)
                left = contentLeft;
                lines++;//增加行数
                colums = 0;//重置列数
            }
            //宽度累加(子控件宽度+水平间距)
            left += childMeasuredWidth + mHSpace;
            colums++;//增加列数
        }

        // 计算出全部的高度
        //父控件的高度 = paddingTop + paddingBottom + 行高*行数 + 行间距*(行数-1)
        //在这里measureHeight是行高(一行的最大高度)
        int measureHeight = top + findMaxChildMaxHeight(lineStartIndex, getChildCount() - 1) + getPaddingBottom();

        if (modeHeight == MeasureSpec.EXACTLY) {
            //父控件指定子View的大小
            setMeasuredDimension(sizeWidth, sizeHeight);
        } else if (modeHeight == MeasureSpec.AT_MOST) {
            //子View具体大小没有尺寸限制，但是存在上限，上限一般为父View大小
            setMeasuredDimension(sizeWidth, measureHeight > sizeHeight ? sizeHeight : measureHeight);
        } else {
            //父控件没有给子view任何限制，子View可以设置为任意大小
            setMeasuredDimension(sizeWidth, measureHeight);
        }
    }

    /**
     * 布局
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        //开始安排孩子的位置
        int contentLeft = getPaddingLeft();
        int contentTop = getPaddingTop();
        int contentRight = getWidth() - getPaddingRight();

        // 初始化
        int left = contentLeft;
        int top = contentTop;
        int lineStartIndex = 0;//
        int lineEndIndex = 0;//

        // 几行
        int lines = 1;
        int colums = 0;

        //循环所有的孩子
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            lineEndIndex = i;//??
            // 获取到每一个孩子
            View view = getChildAt(i);
            // 每一个孩子的测量宽度
            int childMeasuredWidth = view.getMeasuredWidth();

            // 如果满足说明这一个已经一行放不下了
            if (left + childMeasuredWidth > contentRight || (mMaxColums >= 1 && colums >= mMaxColums)) {
                // 如果到了最大的行数,就跳出,top就是当前的
                if (mMaxLines >= 1 && mMaxLines <= lines) {
                    break;
                }
                lineStartIndex = i;
                top += findMaxChildMaxHeight(lineStartIndex, lineEndIndex) + mVSpace;
                left = contentLeft;
                lines++;
                colums = 0;
            }

            //int dy = (mChildMaxHeight - view.getMeasuredHeight()) / 2;

            //安排孩子的位置(左,上,右,下四个坐标值)
            view.layout(left, top, left + childMeasuredWidth, top + view.getMeasuredHeight());

            left += view.getMeasuredWidth() + mHSpace;
            colums++;

        }

    }

    /**
     * 绘制
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    //找到当前行上子控件的最大高度(即行高)
    private int findMaxChildMaxHeight(int startIndex, int endIndex) {
        int result = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            View view = getChildAt(i);
            //[2,6,8,9]得到最大值
            if (view.getMeasuredHeight() > result) {
                result = view.getMeasuredHeight();
            }
        }
        return result;
    }

    /**
     * dp的单位转换为px
     *
     * @param i
     * @return
     */
    private int dpToPx(int i) {
        return Math.round(getResources().getDisplayMetrics().density * i);
    }

    public void setHSpace(int hSpace) {
        this.mHSpace = hSpace;
    }

    public void setVSpace(int vSpace) {
        this.mVSpace = vSpace;
    }
}
