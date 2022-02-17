package com.example.deleteitemsrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> mListUsers;
    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public UserAdapter(List<User> mListUsers) {
        this.mListUsers = mListUsers;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        User user = mListUsers.get(position);
        if (user == null) {
            return;
        }

        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(user.getId()));
        holder.tvUserName.setText(user.getName());

        holder.layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListUsers.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {

        if (mListUsers != null ) {
            return mListUsers.size();
        }

        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private SwipeRevealLayout swipeRevealLayout;
        private LinearLayout layoutDelete;
        private TextView tvUserName;



        public UserViewHolder(@NonNull View itemView) {
            super(itemView);


            swipeRevealLayout = itemView.findViewById(R.id.swipeRevealLayout);
            layoutDelete = itemView.findViewById(R.id.layoutDelete);
            tvUserName = itemView.findViewById(R.id.tvUserName);

        }
    }

}
