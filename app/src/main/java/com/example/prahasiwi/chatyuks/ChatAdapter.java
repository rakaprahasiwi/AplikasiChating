package com.example.prahasiwi.chatyuks;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PRAHASIWI on 03/11/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.CAdapter> {
    JSONArray jsonArray;

    public ChatAdapter(JSONArray jsonArray){
        this.jsonArray = jsonArray;
    }

    @Override
    public ChatAdapter.CAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat,parent,false);
        return new CAdapter(view);
    }

    @Override
    public void onBindViewHolder(ChatAdapter.CAdapter holder, int position) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            holder.imageUser.setImageResource(jsonObject.getInt("GambarPengirim"));
            holder.namaPe.setText(jsonObject.getString("NamaPengirim"));
            holder.kontPesan.setText(jsonObject.getString("KontenPesan"));
            holder.tglKirim.setText(jsonObject.getString("Tanggalan"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class CAdapter extends RecyclerView.ViewHolder {
        ImageView imageUser;
        TextView namaPe, kontPesan, tglKirim;
        public CAdapter(View itemView) {
            super(itemView);
            imageUser = (ImageView) itemView.findViewById(R.id.img_chat);
            namaPe = (TextView) itemView.findViewById(R.id.txtNama);
            kontPesan = (TextView) itemView.findViewById(R.id.pesannya);
            tglKirim = (TextView) itemView.findViewById(R.id.txtTanggal);
        }
    }
}
