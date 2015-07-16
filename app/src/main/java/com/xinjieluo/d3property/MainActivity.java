package com.xinjieluo.d3property;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.app.Activity;
import android.graphics.Camera;
import android.graphics.Matrix;
public class MainActivity extends Activity {
    ProgressDialog progressDialog=null;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView tv = (ImageView)findViewById(R.id.tv_3D);
        final  Rotate3d rotate = new Rotate3d();
        rotate.setDuration(3000);
        rotate.setRepeatCount(100);
        tv.measure(0, 0);
        rotate.setCenter(tv.getMeasuredWidth() / 2, tv.getMeasuredHeight() / 2);
        rotate.setFillAfter(true); // 使动画结束后定在最终画面，如果不设置为true，则将会回到初始画面
//        tv.startAnimation(rotate);
        progressDialog = new ProgressDialog(this, "loading.....");
        progressDialog.show();
    }

}
class Rotate3d extends Animation {
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
