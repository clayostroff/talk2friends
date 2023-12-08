package com.example.talk2friends;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecommendFriendsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewRecommendedFriends;
    private TextView textViewRecommendFriendsTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_friends);

        recyclerViewRecommendedFriends = findViewById(R.id.recyclerViewRecommendedFriends);
        textViewRecommendFriendsTitle = findViewById(R.id.textViewRecommendFriendsTitle);

        List<Profile> recommendedFriendsList = getRecommendedFriends();
        setupRecyclerView(recommendedFriendsList);
    }

    public List<Profile> getRecommendedFriends() {
        Profile currentUser = getCurrentUser();
        List<Profile> allUsers = getAllUsers();
        List<Profile> potentialFriends = new ArrayList<>(allUsers);
        potentialFriends.remove(currentUser);

        return determineFriendRecommendations(currentUser, potentialFriends);
    }

    private Profile getCurrentUser() {
        return new Profile("Current User", "current.user@example.com", "password123", "30", true, false, "Music, Movies, Sports");
    }

    private List<Profile> getAllUsers() {
        List<Profile> allUsers = new ArrayList<>();
        allUsers.add(new Profile("Alice", "alice@example.com", "password123", "25", true, false, "Music, Art"));
        allUsers.add(new Profile("Bob", "bob@example.com", "password123", "28", false, true, "Sports, Technology"));
        allUsers.add(new Profile("Charlie", "charlie@example.com", "password123", "32", true, false, "Movies, Books"));
        allUsers.add(getCurrentUser());

        return allUsers;
    }

    private List<Profile> determineFriendRecommendations(Profile currentUser, List<Profile> potentialFriends) {
        List<Profile> recommendedFriends = new ArrayList<>();
        for (Profile potentialFriend : potentialFriends) {
            if (sharesInterests(currentUser, potentialFriend)) {
                recommendedFriends.add(potentialFriend);
            }
        }
        return recommendedFriends;
    }

    private boolean sharesInterests(Profile user1, Profile user2) {
        List<String> user1Interests = Arrays.asList(user1.getInterests().split(", "));
        List<String> user2Interests = Arrays.asList(user2.getInterests().split(", "));
        for (String interest : user1Interests) {
            if (user2Interests.contains(interest)) {
                return true;
            }
        }
        return false;
    }

    public void setupRecyclerView(List<Profile> recommendedFriendsList) {
        RecommendedFriendsAdapter adapter = new RecommendedFriendsAdapter(recommendedFriendsList);
        recyclerViewRecommendedFriends.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewRecommendedFriends.setAdapter(adapter);
    }

    private class RecommendedFriendsAdapter extends RecyclerView.Adapter<RecommendedFriendsAdapter.ViewHolder> {
        private List<Profile> recommendedFriendsList;

        RecommendedFriendsAdapter(List<Profile> recommendedFriendsList) {
            this.recommendedFriendsList = recommendedFriendsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_recommended_friend, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Profile friend = recommendedFriendsList.get(position);
            holder.textViewFriendName.setText(friend.getName());
            holder.textViewFriendInterests.setText("Interests: " + friend.getInterests());
        }

        @Override
        public int getItemCount() {
            return recommendedFriendsList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView textViewFriendName;
            TextView textViewFriendInterests;

            ViewHolder(View itemView) {
                super(itemView);
                textViewFriendName = itemView.findViewById(R.id.textViewFriendName);
                textViewFriendInterests = itemView.findViewById(R.id.textViewFriendInterests);
            }
        }
    }
}
