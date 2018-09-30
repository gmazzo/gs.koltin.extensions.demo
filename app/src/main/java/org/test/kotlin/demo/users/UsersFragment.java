package org.test.kotlin.demo.users;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.test.kotlin.demo.R;
import org.test.kotlin.demo.model.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class UsersFragment extends Fragment {

    public static UsersFragment create() {
        return new UsersFragment();
    }

    {
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.<RecyclerView>findViewById(R.id.recycler).setAdapter(new UsersAdapter(this::onUserSelected));
    }

    private void onUserSelected(User user) {
        getFragmentManager().beginTransaction() // TODO podría hacerse genérico
                .replace(getId(), UsersEditFragment.create(user))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fragment_users, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                getFragmentManager().beginTransaction() // TODO podría hacerse genérico
                        .replace(getId(), UsersEditFragment.create())
                        .addToBackStack(null)
                        .commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
