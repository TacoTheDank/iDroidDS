package com.opendoorstudios.ds4droid;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SliderPreference extends DialogPreference implements OnSeekBarChangeListener {

    private static final int defaultValue = 10;
    private int currentValue;
    private TextView currentValueDisplay;

    public SliderPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected View onCreateDialogView() {

        currentValue = getPersistedInt(defaultValue);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_slider_noimg, null);

        ((TextView) view.findViewById(R.id.min_value_noimg)).setText("0");
        ((TextView) view.findViewById(R.id.max_value_noimg)).setText("100");
        currentValueDisplay = view.findViewById(R.id.current_value_noimg);
        currentValueDisplay.setText(String.valueOf(currentValue));

        SeekBar seek = view.findViewById(R.id.seek_bar_noimg);
        seek.setMax(100);
        seek.setProgress(currentValue);
        seek.setOnSeekBarChangeListener(this);

        return view;
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (!positiveResult) {
            return;
        }

        if (shouldPersist()) {
            persistInt(currentValue);
        }

        notifyChanged();
    }

    @Override
    public CharSequence getSummary() {

        String summary = super.getSummary().toString();
        int value = getPersistedInt(currentValue);
        return summary + " (currently " + value + ")";
    }

    public void onProgressChanged(SeekBar seek, int value, boolean fromTouch) {
        currentValueDisplay.setText(String.valueOf(currentValue = value));
    }

    @Override
    public void onStartTrackingTouch(SeekBar paramSeekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar paramSeekBar) {
        // TODO Auto-generated method stub

    }

}
