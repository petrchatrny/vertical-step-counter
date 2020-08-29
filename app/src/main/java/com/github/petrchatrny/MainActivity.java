package com.github.petrchatrny;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    VerticalStepCounterView stepper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> data = new ArrayList<>();
        data.add("Lorem ipsum dolor sit amet, et tincidunt aliquet odio tempus tellus, commodo mauris integer. Fermentum ac vivamus at non, montes placerat suspendisse vestibulum vehicula ornare et, vehicula duis velit vehicula gravida aenean. Nisl et sapien viverra. Dui curabitur enim curabitur turpis, pellentesque ac interdum tincidunt, nunc ultricies.");
        data.add("Tortor erat urna felis cursus, sit iaculis nulla enim ac arcu phasellus. Nisl amet vel, faucibus ligula sapien eu convallis, aptent iaculis et, metus mauris elementum deserunt metus dignissim elit, velit rhoncus vestibulum a. Nulla eget fringilla vestibulum velit vivamus placerat, ante posuere massa, facilisis luctus ultricies vel ipsum vivamus euismod. Faucibus ultricies diam rhoncus. Ac mattis ullamcorper pulvinar pede nostra nunc, congue morbi. A nisl, metus et lorem ut ut, metus proin dictum dictum et vulputate lorem. Mi commodo malesuada in pede sed sagittis. In euismod mi, ut proin odio suscipit quis etiam, tellus mauris diam quisque dolor nec.");
        data.add("Lorem ipsum dolor sit amet, et tincidunt aliquet odio tempus tellus, commodo mauris integer. Fermentum ac vivamus at non, montes placerat suspendisse vestibulum vehicula ornare et, vehicula duis velit vehicula gravida aenean. Nisl et sapien viverra. Dui curabitur enim curabitur turpis, pellentesque ac interdum tincidunt, nunc ultricies.");
        data.add("Sagittis est ante. In molestias, posuere sodales arcu egestas viverra, maecenas vel integer rutrum, eu eros risus facilisi erat viverra. Ac mi, vestibulum quia integer tincidunt tincidunt rutrum lorem, aenean in tristique.");
        data.add("Lorem ipsum dolor sit amet, et tincidunt aliquet odio tempus tellus, commodo mauris integer. Fermentum ac vivamus at non, montes placerat suspendisse vestibulum vehicula ornare et, vehicula duis velit vehicula gravida aenean. Nisl et sapien viverra. Dui curabitur enim curabitur turpis, pellentesque ac interdum tincidunt, nunc ultricies.");
        data.add("And its done");

        stepper = findViewById(R.id.stepper);
        stepper.setStepperAdapter(new StepCounterAdapter(data, data.size()));

    }
}