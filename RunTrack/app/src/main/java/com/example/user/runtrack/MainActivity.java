package com.example.user.runtrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by user on 17/12/2016.
 */
public class MainActivity extends AppCompatActivity {
    TextView progressMessageTextView;
    TextView recentRunTextView;
    TextView recentDateTextView;
    TextView recentDetailTextView;
    TextView totalRunTextView;
    TextView totalNormalRunTextView;
    TextView totalChallengesTextView;
    TextView totalDistanceTextView;
    TextView totalTimeTextView;
    TextView totalPaceTextView;
    TextView scoreTextView;
    TextView playedTextView;
    TextView completeTextView;
    TextView failedTextView;
    ImageView challengesButton;
    ImageView allRunButton;
    ImageView homeButton;
    ImageView addRunButton;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == R.id.add_run) {
            Intent intent = new Intent(MainActivity.this, NewRun.class);
            this.startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.all_runs) {
            Intent intent = new Intent(MainActivity.this, AllRuns.class);
            this.startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.challenge_select) {
            Intent intent = new Intent(MainActivity.this, ChallengeSelect.class);
            this.startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.home){
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Logo in action bar
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View mCustomView = getSupportActionBar().getCustomView();

        //Creating database
        final DBHandler db = ((MainApplication) getApplication()).db;
        //Creating messages array list
        Message message = new Message();

        progressMessageTextView = (TextView)findViewById(R.id.progress_message);
        recentRunTextView = (TextView)findViewById(R.id.recent_run);
        recentDateTextView = (TextView)findViewById(R.id.recent_date);
        recentDetailTextView = (TextView)findViewById(R.id.recent_run_details);
        totalRunTextView = (TextView)findViewById(R.id.total_runs);
        totalNormalRunTextView = (TextView)findViewById(R.id.total_normal_runs);
        totalChallengesTextView = (TextView)findViewById(R.id.total_challenges);
        totalDistanceTextView = (TextView)findViewById(R.id.total_distance);
        totalTimeTextView = (TextView)findViewById(R.id.total_time);
        totalPaceTextView = (TextView)findViewById(R.id.total_pace);
        scoreTextView = (TextView)findViewById(R.id.points_score);
        playedTextView = (TextView)findViewById(R.id.played);
        completeTextView = (TextView)findViewById(R.id.complete);
        failedTextView = (TextView)findViewById(R.id.failed);
        allRunButton = (ImageView)findViewById(R.id.all_runs);
        challengesButton = (ImageView) findViewById(R.id.challenges);
        homeButton = (ImageView)findViewById(R.id.home_button);
        addRunButton = (ImageView)findViewById(R.id.addrun_button);

        int savedScoreFromPreferences = SavedScorePreferences.getStoredScore(this);
        final int score = savedScoreFromPreferences;

        int savedPlayedFromPreferences = SavedPlayedPreferences.getStoredPlayed(this);
        final int played = savedPlayedFromPreferences;

        int savedCompleteFromPreferences = SavedCompletePreferences.getStoredComplete(this);
        final int complete = savedCompleteFromPreferences;

        int savedFailedFromPreferences = SavedFailedPreferences.getStoredFailed(this);
        final int failed = savedFailedFromPreferences;



        progressMessageTextView.setText(message.getMessage());
        recentRunTextView.setText("LAST RUN: " + db.getLastRun().getRunTitle());
        recentDateTextView.setText("LAST RAN: " + daysSinceLast(db) + " days");
        recentDetailTextView.setText("TIME: " + db.getLastRun().getTime());

        totalRunTextView.setText("RUNS \n" + db.getTotalRun());
        totalNormalRunTextView.setText("NORMAL \n" + (db.getTotalRun() - savedCompleteFromPreferences));
        totalChallengesTextView.setText("CHALLENGES\n" + savedCompleteFromPreferences);
        totalDistanceTextView.setText("DISTANCE \n" + db.getTotalDistance()+ " km");
        totalTimeTextView.setText("TIME \n" + db.getTotalTime());
        totalPaceTextView.setText("AVG. PACE\n" + db.getTotalPace());
        scoreTextView.setText("CHALLENGE POINTS: " + savedScoreFromPreferences);
        playedTextView.setText("PLAYED \n" + savedPlayedFromPreferences);
        completeTextView.setText("COMPLETE \n" + savedCompleteFromPreferences);
        failedTextView.setText("FAILED \n" + savedFailedFromPreferences);

        allRunButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, AllRuns.class);
                startActivity(intent);
            }
        });

        challengesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, ChallengeSelect.class);
                startActivity(intent);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        addRunButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, NewRun.class);
                startActivity(intent);
            }
        });

    }


    public int daysSinceLast(DBHandler db) {
        DateNow dateNow = new DateNow();
        int lastRunYear = db.getLastRun().getYear();
        int lastRunMonth = db.getLastRun().getMonth();
        int lastRunDay = db.getLastRun().getDay();

        int yearDiff = dateNow.getYearNow() - lastRunYear;
        int monthDiff = dateNow.getMonthNow() - lastRunMonth;
        int dayDiff = dateNow.getDayNow() - lastRunDay;

        int daysSinceLast = (yearDiff * 365) + (monthDiff * 30) + dayDiff;
        return daysSinceLast;
    }
}