package com.example.tinkhomework3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class WorkerListAdapter extends RecyclerView.Adapter<WorkerListAdapter.TaskViewHolder>
        implements ItemTouchHelperEvents {
    // Tag used for debugging/logging
    public static final String TAG = "WorkerListAdapter";
    // Constants used to improve performance

    private final Context context;
    private final LayoutInflater inflater;
    private final int actionLayout;
    private List<Worker> workerList;

    public WorkerListAdapter(List<Worker> workerList, Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.actionLayout = R.layout.list_item_worker;
        this.workerList = workerList;

    }

    public void addWorker(Worker worker) {
        workerList.add(worker);
        notifyItemInserted(workerList.size() - 1);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(actionLayout, parent, false);

        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final TaskViewHolder holder, int position) {
        // fill the fields of one list item
        Worker worker = workerList.get(position);

        holder.tv_item_id.setText(String.valueOf(worker.getId()));
        holder.tv_item_name.setText(worker.getName());
        holder.iv_item_photo.setImageDrawable(context.getDrawable(worker.getPhoto()));
        holder.tv_item_age.setText(worker.getAge());
        holder.tv_item_position.setText(worker.getPosition());
    }

    @Override
    public int getItemCount() {
        return workerList.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(workerList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        workerList.remove(position);
        notifyItemRemoved(position);
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_item_id;
        public TextView tv_item_name;
        public ImageView iv_item_photo;
        public TextView tv_item_age;
        public TextView tv_item_position;

        TaskViewHolder(View itemView) {
            super(itemView);
            tv_item_id = (TextView) itemView.findViewById(R.id.tv_item_id);
            tv_item_name = (TextView) itemView.findViewById(R.id.tv_item_name);
            iv_item_photo = (ImageView) itemView.findViewById(R.id.iv_item_photo);
            tv_item_age = (TextView) itemView.findViewById(R.id.tv_item_age);
            tv_item_position = (TextView) itemView.findViewById(R.id.tv_item_position);
        }
    }

}
