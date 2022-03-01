package com.beatriz.projetofinalandroid.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beatriz.projetofinalandroid.R;
import com.beatriz.projetofinalandroid.model.CheckList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.CheckListViewHolder> implements Filterable {
    private final List<CheckList> checkLists;
    private final Context context;
    private OnItemClickListener onItemClickListener;
    List<CheckList> checkListAll;

    public Adapter(Context context, List<CheckList> checkLists) {
        this.context = context;
        this.checkLists = checkLists;
        this.checkListAll = new ArrayList<>(checkLists);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Adapter.CheckListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_checklist, parent, false);
        return new CheckListViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckListViewHolder holder, int position) {
        CheckList checkList = checkLists.get(position);
        holder.vincula(checkList);
    }

    @Override
    public int getItemCount() {
        return checkLists.size();
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<CheckList> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(checkListAll);
            } else {
                for (CheckList checkList : checkListAll) {
                    if (checkList.getKmVeiculo().toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                            checkList.getMotorista().toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                            checkList.getData().toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                            checkList.getPlaca().toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                            checkList.getHora().toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                            checkList.getSaidaRetorno().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(checkList);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            checkLists.clear();
            checkLists.addAll((Collection<? extends CheckList>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class CheckListViewHolder extends RecyclerView.ViewHolder {

        private final TextView data;
        private final TextView hora;
        private final TextView saidaRetorno;
        private final TextView placa;
        private final TextView motorista;
        private final TextView kmVeiculo;
        private CheckList checkList;

        public CheckListViewHolder(View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.item_checklist_data);
            hora = itemView.findViewById(R.id.item_checklist_hora);
            saidaRetorno = itemView.findViewById(R.id.item_checklist_saidaRetorno);
            placa = itemView.findViewById(R.id.item_checklist_placa);
            motorista = itemView.findViewById(R.id.item_checklist_motorista);
            kmVeiculo = itemView.findViewById(R.id.item_checklist_kmVeiculo);

            itemView.setOnClickListener((view) -> {
                onItemClickListener.onItemClick(checkList, getAdapterPosition());
            });
        }

        public void vincula(CheckList checkList) {
            this.checkList = checkList;
            preencheCampo(checkList);
        }

        private void preencheCampo(CheckList checkList) {
            data.setText(checkList.getData());
            hora.setText(checkList.getHora());
            saidaRetorno.setText(checkList.getSaidaRetorno());
            placa.setText(checkList.getPlaca());
            motorista.setText(checkList.getMotorista());
            kmVeiculo.setText(checkList.getKmVeiculo());
        }
    }
}
