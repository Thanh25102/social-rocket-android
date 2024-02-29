package tech.mobile.socialrocket.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tech.mobile.socialrocket.databinding.FragmentHomeBinding;
import tech.mobile.socialrocket.ui.adapter.TodoAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TodoAdapter todoAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        final Button button = binding.button;

        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        button.setOnClickListener(v -> homeViewModel.getRemoteTodos());


        RecyclerView recyclerView = binding.recyclerView;
        todoAdapter = new TodoAdapter(new ArrayList<>());
        recyclerView.setAdapter(todoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        homeViewModel.getTodos().observe(getViewLifecycleOwner(),
                todoApiRes -> todoAdapter.setTodoList(todoApiRes));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}