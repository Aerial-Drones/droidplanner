package com.droidplanner.widgets.graph;

import android.content.Context;
import android.view.ScaleGestureDetector;

import com.droidplanner.widgets.graph.series.StaticSeries;

public class ChartScaleHandler {
	public interface OnScaleListner {
		public void onScaleListner();
	}

	private OnScaleListner listner;
	protected ScaleGestureDetector scaleDetector;
	public ChartScale scaleY = new ChartScale(180, 10, 180);
	public ChartScale scaleX = new ChartScale(400,100, 800);

	public ChartScaleHandler(Context context, OnScaleListner listner) {
		scaleDetector = new ScaleGestureDetector(context,
				new ChartScaleListener());
		this.listner = listner;
	}

	public void autoScale(StaticSeries dataSeries) {
		autoScale(dataSeries, 1);
	}

	public void autoScale(StaticSeries dataSeries, double overScale) {
		scaleY.setMax(dataSeries.getMaxValue()*overScale);
		scaleY.setRange(scaleY.getMax());
	}
	
	class ChartScaleListener extends
			ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			if (detector.getCurrentSpanY()>detector.getCurrentSpanX()) {
				scaleY.scale(detector.getScaleFactor());				
			}else{
				scaleX.scale(detector.getScaleFactor());
			}
			listner.onScaleListner();
			return true;
		}
	}

}