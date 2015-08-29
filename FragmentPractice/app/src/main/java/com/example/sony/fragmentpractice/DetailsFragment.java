package com.example.sony.fragmentpractice;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    // constant array of information about each turtle
    private static final String[] TURTLE_DETAILS = {
            "Leonardo, or Leo, is one of the four protagonists of the Teenage Mutant Ninja Turtles comics and all related media. In the Mirage/Image Comics, all four turtles wear red bandanas, but in other versions, he wears a blue bandana. His signature weapons are two ninjato. Throughout the various media, he is often depicted as the eldest and leader of the four turtles, as well as the most disciplined. He is named after Leonardo da Vinci. In the 2012 series, he is the only turtle who harbors strong romantic affections for Karai, considering her his love interest.",
            "Michelangelo, Mike or Mikey (as he is usually called), is a fictional character and one of the four protagonists of the Teenage Mutant Ninja Turtles comics and all related media. His mask is typically portrayed as orange outside of the Mirage/Image Comics and his weapons are dual nunchucks, though he has also been portrayed using other weapons, such as a grappling hook, manriki-gusari, tonfa, and a three section staff (in some action figures).",
            "Donatello, often shortened to Don, Donny or Donnie, is a fictional character and one of the four protagonists of the Teenage Mutant Ninja Turtles comics and all related media. He is co-creator Peter Laird's favorite Turtle. In the Mirage/Image Comics, all four turtles wear red bandanas, but in other versions he wears a purple bandana. His primary signature weapon is his effective b? staff. In all media, he is depicted as the smartest and second-in-command of the four turtles. Donnie often speaks in technobabble with a natural aptitude for science and technology. He is named after the Italian sculptor Donatello.",
            "Raphael, or Raph, is a fictional character and one of the four protagonists of the Teenage Mutant Ninja Turtles comics and all related media. In the Mirage/Image Comics, all four turtles wear red bandanas over their eyes, but unlike his brothers in other versions, he is the only one who keeps the red bandana. Raphael wields twin sai as his primary weapon. (In the Next Mutation series, his sai stick together to make a staff-like weapon.) He is generally the most likely to experience extremes of emotion, and is usually depicted as being aggressive, sullen, maddened, and rebellious. The origin of Raphael's anger is not always fully explored, but in some incarnations appears to stem partly from the realization that they are the only creatures of their kind and ultimately alone. He also has a somewhat turbulent relationship with his older brother Leonardo because Leonardo is seen as the group's leader. Raphael is named after the 16th-century Italian painter Raphael. In 2011 Raphael placed 23rd on IGN's Top 100 Comic Book Heroes, a list that did not feature any of his brothers."
    };

    public DetailsFragment(){}

    /*
     * This method initializes the fragment's layout.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    /*
     *  This method gets call after the containing actvity is done being created.
     *  This is where we will attach event listeners and do other initialization.
     */
    @Override
    public void
    onActivityCreated(Bundle saved_instance_state)
    {
        super.onActivityCreated(saved_instance_state);

        //obtain the turtle id to show from activity's intent
        Intent intent = getActivity().getIntent();
        int id = intent.getIntExtra("turtle_id", R.id.leo);
        setTurtleId(id);
    }

    /*
     *  Sets the detail showing to correspond to the chosen turtle (ID).
     */
    public void
    setTurtleId(int id)
    {
        int index;
        if(id == R.id.leo)
        {
            index = 0;
        }
        else if(id == R.id.mike)
        {
            index = 1;
        }
        else if(id == R.id.don)
        {
            index = 2;
        }
        else if(id == R.id.raph)
        {
            index = 3;
        }
        else
        {
            index = 0;
        }

        String description = TURTLE_DETAILS[index];
        EditText edit_text = (EditText) getActivity().findViewById(R.id.turtle_info_edit_text);
        edit_text.setText(description);
    }
}
