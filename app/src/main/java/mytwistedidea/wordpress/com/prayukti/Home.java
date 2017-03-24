package mytwistedidea.wordpress.com.prayukti;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Home extends Fragment {

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
        com.github.clans.fab.FloatingActionButton fab = (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.fab_facebook);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(getActivity(),WebViewing.class);
                intent.putExtra("url", fbURL);
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
                startActivity(intent);
            }
        });
    }
}
