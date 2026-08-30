package com.example.vanchin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.graphics.Path;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
        setTitle("Тест");
    }

    // Внутренний класс DrawView 
    class DrawView extends View {
        private Paint p;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int width = canvas.getWidth();
            int height = canvas.getHeight();

            // Размер куба (половина от меньшей стороны экрана)
            int cubeSize = Math.min(width, height) / 2;
            int centerX = width / 2;
            int centerY = height / 2;

            // Координаты передней грани (квадрат)
            int left = centerX - cubeSize / 2;
            int top = centerY - cubeSize / 2;
            int right = centerX + cubeSize / 2;
            int bottom = centerY + cubeSize / 2;

            Paint p = new Paint();
            p.setStyle(Paint.Style.FILL);

            // Передняя грань (красная)
            p.setColor(Color.RED);
            canvas.drawRect(left, top, right, bottom, p);

            // Верхняя грань (зелёная) — параллелограмм
            Path topFace = new Path();
            topFace.moveTo(left, top);
            topFace.lineTo(left + cubeSize / 3, top - cubeSize / 3);
            topFace.lineTo(right + cubeSize / 3, top - cubeSize / 3);
            topFace.lineTo(right, top);
            topFace.close();
            p.setColor(Color.GREEN);
            canvas.drawPath(topFace, p);

            // Боковая правая грань (синяя) — параллелограмм
            Path rightFace = new Path();
            rightFace.moveTo(right, top);
            rightFace.lineTo(right + cubeSize / 3, top - cubeSize / 3);
            rightFace.lineTo(right + cubeSize / 3, bottom - cubeSize / 3);
            rightFace.lineTo(right, bottom);
            rightFace.close();
            p.setColor(Color.BLUE);
            canvas.drawPath(rightFace, p);

            // Обводка чёрным для чёткости 
            p.setStyle(Paint.Style.STROKE);
            p.setColor(Color.BLACK);
            p.setStrokeWidth(3);
            canvas.drawRect(left, top, right, bottom, p);
            canvas.drawPath(topFace, p);
            canvas.drawPath(rightFace, p);
        }

    }
}
