package com.geektech.newsapp39.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geektech.newsapp39.App;
import com.geektech.newsapp39.R;
import com.geektech.newsapp39.databinding.FragmentHomeBinding;
import com.geektech.newsapp39.models.News;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NewsAdapter adapter;
    private News news;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        adapter = new NewsAdapter();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(App.getAppDatabase().newsDao().getAll() != null) {
            adapter.addItems(App.getAppDatabase().newsDao().getAll());
        }
        binding.fab.setOnClickListener(view1 -> {
            openFragment();
        });
        getParentFragmentManager().setFragmentResultListener("rk_news",
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        news = (News) result.getSerializable("news");
                        adapter.addNewsItem(news);
                        Log.e("TAG", "task =  " + news.getTitle());
                    }
                });
        initRv();
    }


    private void initRv() {
        binding.newsRu.setAdapter(adapter);
        adapter.setOnItemClickListener(new NewsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int pos) {
//                Toast.makeText(requireActivity(), adapter.getNews(pos).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(int pos) {
                new AlertDialog.Builder(getContext()).setTitle("Delete").setMessage("You are sure?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                news = adapter.getNews(pos);
                                adapter.deleteNews(pos);
                                App.getAppDatabase().newsDao().delete(news);

                            }
                        }).show();
            }
        });
    }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host);
        navController.navigate(R.id.newsFragment);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ((item.getItemId() == R.id.action_sort)) {
            adapter.setNewsList(App.getAppDatabase().newsDao().sortAll());
            adapter.notifyDataSetChanged();
            Toast.makeText(getContext(), "gtdghfh", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}



