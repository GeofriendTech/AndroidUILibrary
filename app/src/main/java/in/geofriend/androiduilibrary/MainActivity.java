package in.geofriend.androiduilibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import in.geofriend.androiduilibrary.table.TableUIBuilderActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayoutCompat listContainer = findViewById(R.id.listContainer);
        for(String item : Constants.DEMOS) {
            ViewGroup layout = (ViewGroup) getLayoutInflater().inflate(R.layout.demo_list_item, null);
            TextView textView = layout.findViewById(R.id.demo_name);
            textView.setText(item);
            listContainer.addView(layout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClicked(item);
                }
            });
        }
    }

    private void onItemClicked(String item) {
        Intent intent = null;
        switch (item) {
            case Constants.TABLE_UI_BUILDER:
                intent = new Intent(this, TableUIBuilderActivity.class);
                break;
        }
        startActivity(intent);
    }
}