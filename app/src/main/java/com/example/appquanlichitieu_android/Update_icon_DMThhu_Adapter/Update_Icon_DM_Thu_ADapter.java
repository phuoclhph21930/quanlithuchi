package com.example.appquanlichitieu_android.Update_icon_DMThhu_Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanlichitieu_android.Activity.Update_iconDM;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.modelQuanLi.Category_Autumn_Model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Update_Icon_DM_Thu_ADapter extends RecyclerView.Adapter<Update_Icon_DM_Thu_ADapter.ViewHolder> {
    private Context context;
    private List<Category_Autumn_Model> list_item;

    public Update_Icon_DM_Thu_ADapter(Context context, List<Category_Autumn_Model> list_item) {
        this.context = context;
        this.list_item = list_item;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_update_expenses, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category_Autumn_Model model = list_item.get(position);

        holder.tvtenDanhMuc.setText(model.getDanhmucThu());
        Picasso.get().load(model.getImageThu()).into(holder.img_itemDM);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_iconDM.class);
                intent.putExtra("itemId", model.getIdCatalogThu());
                intent.putExtra("image", model.getImageThu());
                intent.putExtra("category", model.getDanhmucThu());
                Toast.makeText(context, "Chỉnh sửa danh mục " + model.getDanhmucThu(), Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list_item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_itemDM;
        private TextView tvtenDanhMuc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_itemDM = itemView.findViewById(R.id.item_image_danhmuc_expenses);
            tvtenDanhMuc = itemView.findViewById(R.id.item_ten_Danhmuc_expexses);


        }
    }
}
