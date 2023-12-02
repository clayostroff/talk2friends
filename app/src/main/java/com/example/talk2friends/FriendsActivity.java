package com.example.talk2friends;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {

    private ListView friendsListView;
    private List<Profile> friendsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        friendsListView = findViewById(R.id.friendsListView);
        friendsList = createSampleFriends(); // Replace this with your actual friend list

        FriendsAdapter friendsAdapter = new FriendsAdapter();
        friendsListView.setAdapter(friendsAdapter);

        Button addFriendsButton = findViewById(R.id.addFriendsButton);
        addFriendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FriendsActivity.this, "Add Friends button clicked", Toast.LENGTH_SHORT).show();
                // Implement your logic for adding friends here
            }
        });
    }

    // Sample method to create a list of friends
    private List<Profile> createSampleFriends() {
        List<Profile> sampleFriends = new ArrayList<>();

        // Creating three sample profiles
        sampleFriends.add(new Profile("John Doe", "john@example.com", "password", "25", true, false, "Sports"));
        sampleFriends.add(new Profile("Alice Smith", "alice@example.com", "password", "30", true, false, "Movies"));
        sampleFriends.add(new Profile("Bob Johnson", "bob@example.com", "password", "28", false, true, "Music"));

        return sampleFriends;
    }

    private class FriendsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return friendsList.size();
        }

        @Override
        public Object getItem(int position) {
            return friendsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.friend_list_item, null);

            // Set friend name
            TextView friendNameTextView = view.findViewById(R.id.friendNameTextView);
            friendNameTextView.setText(friendsList.get(position).getName());

            // Set remove friend button click listener
            Button removeFriendButton = view.findViewById(R.id.removeFriendButton);
            removeFriendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeFriend(position);
                }
            });

            return view;
        }
    }

    // Method to remove a friend from the list
    private void removeFriend(int position) {
        friendsList.remove(position);
        ((BaseAdapter) friendsListView.getAdapter()).notifyDataSetChanged();
        Toast.makeText(this, "Friend removed", Toast.LENGTH_SHORT).show();
    }
}
