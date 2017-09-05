package com.practice.dragdrop;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.targetTv)
    TextView targetTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tv1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tv1.startDrag(ClipData.newPlainText(null, tv1.getText()), new View.DragShadowBuilder(tv1), null, 0);
                return true;
            }
        });
        tv2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tv2.startDrag(ClipData.newPlainText(null, tv2.getText()), new View.DragShadowBuilder(tv2), null, 0);
                return true;
            }
        });





        targetTv.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED: {
                        targetTv.setBackgroundResource(android.R.color.holo_purple);
                        break;
                    }
                    case DragEvent.ACTION_DRAG_ENTERED: {
                        targetTv.setBackgroundResource(android.R.color.holo_orange_light);
                        break;
                    }
                    case DragEvent.ACTION_DRAG_EXITED: {
                        targetTv.setBackgroundResource(android.R.color.darker_gray);
                        break;
                    }
                    case DragEvent.ACTION_DRAG_LOCATION: {
                        Log.e(TAG, "Action is DragEvent.ACTION_DRAG_LOCATION" + dragEvent);
                        break;
                    }

                    case DragEvent.ACTION_DRAG_ENDED: {
                        targetTv.setBackgroundResource(android.R.color.holo_purple);
                        break;
                    }

                    case DragEvent.ACTION_DROP: {
                        targetTv.setText(dragEvent.getClipData().getItemAt(0).getText());
                        break;
                    }

                    default: {
                        break;
                    }
                }


                return true;
            }
        });


    }
}
