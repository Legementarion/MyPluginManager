package com.lego.mypluginmanager.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lego.mypluginmanager.domain.entity.PluginEntity;
import com.lego.mypluginmanager.R;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.PluginsViewHolder> {

    private List<PluginEntity> pluginData = new ArrayList<>();
    private PluginClickListener listener;

    @NonNull
    @Override
    public PluginsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PluginsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plugin, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PluginsViewHolder holder, int position) {
        holder.bind(pluginData.get(position));
    }

    @Override
    public int getItemCount() {
        return pluginData.size();
    }

    public void setPlugins(List<PluginEntity> plugins) {
        pluginData = plugins;
    }

    public void addPluginData(PluginEntity newPlugin) {
        pluginData.add(newPlugin);
        notifyDataSetChanged();
    }

    public void setPluginClickListener(PluginClickListener newListener) {
        listener = newListener;
    }

    public void checkingDelete(String packageName) {
        while (pluginData.iterator().hasNext()) {
            if (pluginData.iterator().next().getPluginName().equals(packageName)) {
                pluginData.iterator().remove();
                break;
            }
        }
        notifyDataSetChanged();
    }

    class PluginsViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView pluginName;
        private Switch pluginSwitcher;

        PluginsViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            pluginName = view.findViewById(R.id.tvPluginName);
            pluginSwitcher = view.findViewById(R.id.swPlugin);
        }

        public void bind(final PluginEntity data) {
            pluginName.setText(data.getPluginName());
            pluginSwitcher.setChecked(data.isPluginEnable());
            pluginSwitcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    data.setPluginEnable(isChecked);
                    listener.onPluginSwitch(data.getPluginName(), isChecked);
                }
            });
        }
    }

    public interface PluginClickListener {
        void onPluginSwitch(String pluginName, Boolean isChecked);
    }
}
