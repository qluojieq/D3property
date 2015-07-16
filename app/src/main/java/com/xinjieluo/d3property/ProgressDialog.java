package com.xinjieluo.d3property;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by jie.luo on 2015/7/6.
 */
public class ProgressDialog extends Dialog {
//    public ProgressDialog(Context context, String strMessage) {
//        this(context, R.style.CustomProgressDialog, strMessage);
//    }

    public ProgressDialog(Context context, String strMessage) {
        super(context,R.style.CustomDialog);
        this.setContentView(R.layout.activity_main);
        this.getWindow().getAttributes().gravity = Gravity.CENTER;
        final ImageView tv = (ImageView)findViewById(R.id.tv_3D);
        final  Rotate3d1 rotate = new Rotate3d1();
        rotate.setDuration(3000);
        rotate.setRepeatCount(1000);
        tv.measure(0, 0);
        rotate.setCenter(tv.getMeasuredWidth() / 2, tv.getMeasuredHeight() / 2);
        rotate.setFillAfter(true); // 使动画结束后定在最终画面，如果不设置为true，则将会回到初始画面
        tv.startAnimation(rotate);
        TextView tvMsg = (TextView) this.findViewById(R.id.id_tv_loadingmsg);
        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (!hasFocus) {
            dismiss();
        }
    }

}
class Rotate3d1 extends Animation {
    private float mCenterX = 0;
    private float mCenterY = 0;

    public void setCenter(float centerX, float centerY) {
        mCenterX = centerX;
        mCenterY = centerY;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        Matrix matrix = t.getMatrix();
        Camera camera = new Camera();
        camera.save();
        camera.rotateY(360 * interpolatedTime);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-mCenterX, -mCenterY);
        matrix.postTranslate(mCenterX, mCenterY);
    }
}
