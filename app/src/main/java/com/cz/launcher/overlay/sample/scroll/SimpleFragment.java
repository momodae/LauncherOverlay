package com.cz.launcher.overlay.sample.scroll;

import android.os.Bundle;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cz.android.sample.api.Exclude;
import com.cz.launcher.overlay.sample.R;

@Exclude
public class SimpleFragment extends Fragment {
    public static Fragment newInstance(int index){
        Bundle argument=new Bundle();
        argument.putInt("index",index);
        Fragment fragment=new SimpleFragment();
        fragment.setArguments(argument);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scroll_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView titleTextView=view.findViewById(R.id.titleTextView);
        titleTextView.setText("Process:"+ Process.myPid());

        final TextView textView=view.findViewById(R.id.textView);
        Bundle arguments = getArguments();
        final int index = arguments.getInt("index", 0);
        textView.setText("Page:"+index);
        View testButton=view.findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Click event from page:"+index,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
