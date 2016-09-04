package com.atto.developers.atto.Adapter;

/**
 * Created by Tacademy on 2016-08-26.
 */
<<<<<<< Updated upstream
public class RecyclerMakerAdapter extends RecyclerView.Adapter<RecyclerMakerViewHolder> implements RecyclerMakerViewHolder.OnMakerItemClickListener {

    List<MakerData> items = new ArrayList<>();


    public void add(MakerData makerData) {
        items.add(makerData);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerMakerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_maker, parent, false);
        RecyclerMakerViewHolder holder = new RecyclerMakerViewHolder(view);
        holder.setOnMakerItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerMakerViewHolder holder, int position) {
        holder.setMakerData(items.get(position));

    }

    public interface OnAdapterItemClickLIstener {
        public void onAdapterItemClick(View view, MakerData makerData, int position);
    }

    OnAdapterItemClickLIstener listener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickLIstener listener) {
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onMakerItemClick(View view, MakerData makerData, int position) {

        if (listener != null) {
            listener.onAdapterItemClick(view, makerData, position);
        }

    }
=======
public class RecyclerMakerAdapter {
>>>>>>> Stashed changes
}
