package todo.ui.fragment.list.helper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public class trann extends ItemTouchHelper.SimpleCallback {

    private RecyclerView recyclerView;
    private List<MyButton> buttonList;
    private GestureDetector gestureDetector ;
    private int swipePosition =-1;
    private float swipeThreshold = 0.5f;
    private Map<Integer,List<MyButton>> buttonBuffer;
    private Queue<Integer> removerQueue ;
    private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            for (MyButton button : buttonList){
                if (button.onClick(e.getX(),e.getY())){
                    break;
                }
            }
            return true;
        }
    };
    private View.OnTouchListener onTouchListener = new View.OnTouchListener(){

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
           if (swipePosition<0) return false;
            Point point = new Point((int) motionEvent.getRawX(),(int)motionEvent.getRawX());

            RecyclerView.ViewHolder swipeViewHolder = recyclerView.findViewHolderForAdapterPosition(swipePosition);
            View swipedItem = swipeViewHolder.itemView;
            Rect rect = new Rect();
            swipedItem.getGlobalVisibleRect(rect);

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN||motionEvent.getAction()==MotionEvent.ACTION_UP||
            motionEvent.getAction()== MotionEvent.ACTION_MOVE){
                if (rect.top<point.y && rect.bottom > point.y){
                    //gestureDetector.onTouchEvent(e);
                }else {
                    removerQueue.add(swipePosition);
                    swipePosition=-1;
                    reconverSwiperItem();
                }
            }
            return false;
        }
    };

    private void reconverSwiperItem() {

    }

    public trann(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    private class MyButton {
        private String text ;
        private int imageResID,textSize,color,pos;
        private RectF clickRegion ;
        private MYButtonCLickListener listener;
        private Context context;
        private Resources resources;

        public MyButton(Context context,String text, int imageResID,
                        int textSize, int color, int pos,
                        RectF clickRegion, MYButtonCLickListener listener
                        ) {
            this.text = text;
            this.imageResID = imageResID;
            this.textSize = textSize;
            this.color = color;
            this.pos = pos;
            this.clickRegion = clickRegion;
            this.listener = listener;
            this.context = context;
            resources = context.getResources();
        }
        public boolean onClick(float x , float y){
            if (clickRegion!=null&& clickRegion.contains(x,y)){
                listener.onClick(pos);
                return true;
            }
            return false;
        }
        public void onDraw(Canvas c , RectF rectF , int pos){
            Paint p = new Paint();
            p.setColor(color);
            c.drawRect(rectF,p);
            //..text
            p.setColor(Color.WHITE);
            p.setTextSize(textSize);

            Rect r = new Rect();
            float cHeight = rectF.height();
            float cWidth = rectF.width();
            p.setTextAlign(Paint.Align.LEFT);
            p.getTextBounds(text,0,text.length(),r);
            float x =0 ,y =0;
            if (imageResID==0){
                x=cWidth/2f - r.width()/2f -r.left;
                y=cHeight/2f +r.height()/2f - r.bottom;
                c.drawText(text,rectF.left+x,rectF.top+y,p);
            }else {
                Drawable d = ContextCompat.getDrawable(context,imageResID);
                //Bitmap bitmap = drawableToBitmap(d);
                //c.drawBitmap(bitmap,(rectF.left+rectF.right)/2,(rectF.top+rectF.bottom)/2,p);
            }
            clickRegion = rectF;
            this.pos = pos;
        }
    }

   // private Bitmap drawableToBitmap(Drawable d) {
//        if (d instanceof BitmapDrawable){
//            return ((BitmapDrawable)d).getBitmap();
//            Bitmap bitmap = Bitmap.createBitmap(d.getIntrinsicWidth(),
//                    d.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(bitmap);
//            d.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
//            d.draw(canvas);
//            return bitmap;
//        }
//    return Drawable.class;

}
