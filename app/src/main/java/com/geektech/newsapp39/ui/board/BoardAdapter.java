package com.geektech.newsapp39.ui.board;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.newsapp39.R;
import com.geektech.newsapp39.databinding.ItemBoardPvBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private ItemBoardPvBinding binding;
    private int[] ListImg = {R.raw.cub,R.raw.avstronavt,R.raw.shop,R.raw.cub};
    private String titleList[] = {"Title1","Title2","Title3","Title4"};
    private String subtitleList[] = {"SubTitle1","SubTitle2","SubTitle3","SubTitle4"};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = ItemBoardPvBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(position);

        if (position == 3){
            binding.btnStart.setVisibility(View.VISIBLE);

        }else {
            binding.btnStart.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemBoardPvBinding itemView) {
            super(binding.getRoot());
        }

        public void onBind(int position) {

            binding.titleTv.setText(titleList[position]);
            binding.subtitleTv.setText(subtitleList[position]);

            binding.boardIv.setAnimation(ListImg[position]);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            },2000);

            binding.btnStart.setOnClickListener(v -> {
                NavController navController = Navigation.findNavController((Activity) v.getContext(),
                        R.id.nav_host);
                navController.navigateUp();
            });


        }
    }
}
