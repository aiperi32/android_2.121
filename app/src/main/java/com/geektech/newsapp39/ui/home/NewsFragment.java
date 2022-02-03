package com.geektech.newsapp39.ui.home;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.geektech.newsapp39.App;
import com.geektech.newsapp39.R;
import com.geektech.newsapp39.databinding.FragmentNewsBinding;
import com.geektech.newsapp39.models.News;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class NewsFragment extends Fragment {
    private FragmentNewsBinding binding;
    private News news;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });


    }


    private void save() {
        Bundle bundle = new Bundle();
        String title = binding.editText.getText().toString().trim();
        if (title.isEmpty()) {
            Toast.makeText(getContext(), "Заполни поле", Toast.LENGTH_SHORT).show();
        }
        if (news == null) {
            long createdAd = System.currentTimeMillis();
            ZonedDateTime dateTime = Instant.ofEpochMilli(createdAd).atZone(ZoneId.of("Asia/Bishkek"));
            String format = dateTime.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy"));
            news = new News(title, format);
            App.getAppDatabase().newsDao().insert(news);

        }

        bundle.putSerializable("news", news);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
        navController.navigateUp();
    }


}