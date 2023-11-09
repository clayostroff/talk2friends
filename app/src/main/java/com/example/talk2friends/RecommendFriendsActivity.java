package com.example.talk2friends;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
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

        // Assuming you have a method to get the list of recommended friends
        List<Profile> recommendedFriendsList = getRecommendedFriends();
        setupRecyclerView(recommendedFriendsList);
    }

    private List<Profile> getRecommendedFriends() {
        // Placeholder for getting recommended friends based on shared interests
        // You will need to implement this method based on your backend logic
        return new ArrayList<>();
    }

    private void setupRecyclerView(List<Profile> recommendedFriendsList) {
        RecommendedFriendsAdapter adapter = new RecommendedFriendsAdapter(recommendedFriendsList);
        recyclerViewRecommendedFriends.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewRecommendedFriends.setAdapter(adapter);
    }

    // Inner class for RecyclerView.Adapter
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
