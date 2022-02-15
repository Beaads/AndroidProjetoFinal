package com.beatriz.projetofinalandroid.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beatriz.projetofinalandroid.R;
import com.beatriz.projetofinalandroid.model.CheckList;
import com.beatriz.projetofinalandroid.ui.activity.ListaCheckList;

import java.util.List;

public class Adapter extends RecyclerView.Adapter {
    private Context context;

    private OnItemClickListener onItemClickListener;

    public Adapter(ListaCheckList listaCheckList, List<CheckList> todosCheck) {
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


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
                onItemClickListener.onItemClick(checkList);
            });
        }
}
}
