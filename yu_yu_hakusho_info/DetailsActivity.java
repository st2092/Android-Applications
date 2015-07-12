package com.example.sony.yuyuhakusho;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Intent;

public class DetailsActivity extends ActionBarActivity {
    // Constant array of data about each of the character.
    private static final String[] CHARACTER_INFO_DETAILS = {
            "Yusuke Urameshi is the main protagonist of the manga & anime series YuYu Hakusho. He is a Spirit Detective who is tasked with protecting Human World from various supernatural threats over the course of the series. His closest friends and greatest allies include Kuwabara, Kurama, Hiei, Keiko, Genkai, Koenma and Botan. He is also the de facto leader of Team Urameshi and the general person to look to when the going gets tough.\n" +
                    "\n" +
                    "At the start of the series, Yusuke is a teenage delinquent with little to no positive qualities to his character. Over the course of the series, he starts becoming a better person, changing into an almost altruistic defender of the Human World.",
            "Kazuma Kuwabara, more commonly known as Kuwabara, is one of the main protagonists of the series, along with Yusuke Urameshi, Kurama & Hiei. He is the younger brother of Shizuru Kuwabara. He also seeks to become Yukina's lover.",
            "Hiei, also known as, Jaganshi Hiei (Jaganshi is an epithet meaning \"Master of the Evil Eye\"), is a main character in the anime and manga series YuYu Hakusho by Yoshihiro Togashi. Hiei's seiy? is provided by Nobuyuki Hiyama. Hiei's English voice actors are Chuck Huber in the FUNimation dub of the television series, Kirk Thornton in the dub of YuYu Hakusho: The Movie, and James Stanley in the second movie, YuYu Hakusho: Poltergeist Report.[1] He is called \"Hiei of the Evil Eye\" in the Viz manga and Vincent in the Filipino adaptation as he was voiced by Montreal Repuyan.",
            "Yoko Kurama, also known as Shuichi Minamino, is a main protagonist in the anime/manga series of YuYu Hakusho. The word Yoko, despite being portrayed as a name in the dub, actually literally translates to \"demon fox\" in Japanese. " + "\n\n" + "Yoko Kurama is the name of the original, silver-haired fox demon. After he was wounded by being chased, he put his spirit into the newly born human baby, Shuichi Minamino. It is at this point, and later through aging, that Kurama's psyche is in control. A theme throughout the series is his acceptance as a human being & his balancing of the two personalities. He can switch between both bodies almost at will after the Dark Tournament saga. Even though he is commonly seen in his human form (Minamino), he is referred to as Kurama.",
            "Keiko Yukimura. Kayko in the English dub, is the love interest of Yusuke Urameshi. Her first name means the blessed one, glad, or rejoicing child. It can also mean firefly child depending on the language. The meaning of Japanese names is found in the kanji and not in the sounds as several kanjis can have the same sound, in the light of that the meaning of Keiko's first name is firefly child, though other variations (made with other kanjis) can have different meanings. Her last name means snowy village, and is occasionally spelled Ukimura instead.",
            "Botan is a guide to the Spirit World and close friend to Yusuke Urameshi. She is a spirit charged with ferrying souls of the deceased to the Spirit World to face their final judgment. As opposed to the idea of there only being a single one, she is one of many grim reapers. "
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // obtain the character id out of the intent that the main activity used to load this activity
        Intent intent = getIntent();
        int char_id = intent.getIntExtra("YuYuHakusho_char_id", R.id.yusuke); // default value is yusuke
        String char_info_str = "";

        //determine which character was chosen
        if (char_id == R.id.yusuke) //yusuke
        {
            char_info_str = CHARACTER_INFO_DETAILS[0];
        }
        else if (char_id == R.id.kuwabara) // kuwabara
        {
            char_info_str = CHARACTER_INFO_DETAILS[1];
        }
        else if (char_id == R.id.hiei) //hiei
        {
            char_info_str = CHARACTER_INFO_DETAILS[2];
        }
        else if (char_id == R.id.kurama) // kurama
        {
            char_info_str = CHARACTER_INFO_DETAILS[3];
        }
        else if (char_id == R.id.keiko) // keiko
        {
            char_info_str = CHARACTER_INFO_DETAILS[4];
        }
        else if (char_id == R.id.botan) // botan
        {
            char_info_str = CHARACTER_INFO_DETAILS[5];
        }

        // set the text view to display corresponding character info
        TextView text_view = (TextView) findViewById(R.id.char_info_text_view);
        text_view.setText(char_info_str);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
