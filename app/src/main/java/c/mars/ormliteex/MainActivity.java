package c.mars.ormliteex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.t)
    TextView t;
    private Random r=new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Timber.plant(new Timber.DebugTree());

        Db.init(this);
        ld();
    }

    @OnClick(R.id.a)
    void a(){
        It it=new It("it", r.nextInt(100));
        Db.getInstance().add(it);
        t.append("added: " + it.toString() + "\n");
    }

    @OnClick(R.id.r)
    void r(){
        Db.getInstance().removeAll();
        t.setText("removed all");
    }

    @OnClick(R.id.ld)
    void ld(){
        t.setText("loaded:\n");
        List<It> its=Db.getInstance().getAll();
        for(It it:its){
            t.append(it.toString()+"\n");
        }
    }
}
