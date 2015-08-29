package com.example.sony.fragmentpractice;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;

public class MainFragment extends Fragment {
    //"request code" use for identifying sub-activities that return results
    private static final int REQUEST_CODE_DETAILS_ACTIVITY = 1234;

    /*
     * This method initializes the fragment's layout.
     */
    @Override
    public View
    onCreateView(LayoutInflater inflater, ViewGroup view_group, Bundle save_instance_state)
    {
        return inflater.inflate(R.layout.fragment_main, view_group, false);
    }

    /*
     *  This method gets call after the containing activity is done being created.
     *  This is where we will set up event listners and other initializations.
     */
    @Override
    public void
    onActivityCreated(Bundle save_instance_state)
    {
        super.onActivityCreated(save_instance_state);

        // attach event listener for the radio button group
        RadioGroup radio_group = (RadioGroup) getActivity().findViewById(R.id.turtle_group);
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void
            onCheckedChanged(RadioGroup radio_group, int checked_id)
            {
                updateTurtleImage();
            }
        });

        // attach click event listener to image button
        ImageButton image_button = (ImageButton) getActivity().findViewById(R.id.turtle_image_button);
        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void
            onClick(View v)
            {
                showDetailsAboutTurtle();
            }
        });
    }

    /*
     *  This function shows more detail about the currently selected turtle.
     *  In portrait mode, this pops up as a second activity.
     *  In landscape mode, this updates the DetailFragment within the same activity.
     */
    public void
    showDetailsAboutTurtle()
    {
        RadioGroup radio_group = (RadioGroup) getActivity().findViewById(R.id.turtle_group);
        int id = radio_group.getCheckedRadioButtonId();

        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE)
        {
            // show in the same activity
            DetailsFragment detail_fragment;
            detail_fragment = (DetailsFragment) getFragmentManager().findFragmentById(R.id.details_fragment);
            detail_fragment.setTurtleId(id);
        }
        else // portrait orientation
        {
            // launch details as its own activity
            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            intent.putExtra("turtle_id", id);
            startActivityForResult(intent, REQUEST_CODE_DETAILS_ACTIVITY);
        }
    }

    /*
     *  Updates which turtle image is showing based on which radio button is
     *  currently checked.
     */
    private void
    updateTurtleImage()
    {
        ImageButton image_button = (ImageButton) getActivity().findViewById(R.id.turtle_image_button);
        RadioGroup radio_group = (RadioGroup) getActivity().findViewById(R.id.turtle_group);
        int checked_id = radio_group.getCheckedRadioButtonId();

        if(checked_id == R.id.leo)
        {
            image_button.setImageResource(R.drawable.tmntleo);
        }
        else if(checked_id == R.id.don)
        {
            image_button.setImageResource(R.drawable.tmntdon);
        }
        else if(checked_id == R.id.mike)
        {
            image_button.setImageResource(R.drawable.tmntmike);
        }
        else if(checked_id == R.id.raph)
        {
            image_button.setImageResource(R.drawable.tmntraph);
        }

        // if it is landscape mode, launch another fragment to show detail
        if(getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE)
        {
            showDetailsAboutTurtle();
        }
    }
}
