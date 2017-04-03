package mytwistedidea.wordpress.com.prayukti;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import static android.content.Context.MODE_PRIVATE;

public class Home extends Fragment {

    final int  NO_OF_TIMES = 2;
    final String MY_PREFS_NAME = "noofhelpcheck";
    int no = 0;
    final String fbURL = "https://www.facebook.com/prayuktihith/?fref=ts";
    final String ytURL = "https://www.youtube.com/playlist?list=PLaNj9RtluzIjmZvSsC6zQLvKpH4E29yz3";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_frag_home,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");

//        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences(MY_PREFS_NAME, 0); // 0 - for private mode
//        SharedPreferences.Editor editor = pref.edit();
//        no=pref.getInt("noof",0);
//        if(no != NO_OF_TIMES){
//            no++;
//            popUpHelp();
//            editor.putInt("noof", no);
////            editor.putInt("phone1", );
//            editor.commit();
//        }

        ImageView ivtobeanim = (ImageView) view.findViewById(R.id.ivPrayukriLogoHome);

        Animation hyperspaceJump = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_zoom_out);
        ivtobeanim.startAnimation(hyperspaceJump);


        com.github.clans.fab.FloatingActionButton fab = (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.fab_facebook);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(getActivity(),WebViewing.class);
                intent.putExtra("url", fbURL);
                intent.putExtra("website","Prayukti @fb");
                startActivity(intent);
            }
        });
        com.github.clans.fab.FloatingActionButton fab1 = (com.github.clans.fab.FloatingActionButton)
                view.findViewById(R.id.fab_youtube);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(getActivity(),WebViewing.class);
                intent.putExtra("url", ytURL);
                intent.putExtra("website","Prayukti @youtube");
                startActivity(intent);
            }
        });
    }

    private void popUpHelp() {

    }
}
