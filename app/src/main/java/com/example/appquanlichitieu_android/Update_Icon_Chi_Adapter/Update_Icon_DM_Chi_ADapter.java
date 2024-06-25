package com.example.appquanlichitieu_android.Update_Icon_Chi_Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanlichitieu_android.Activity.Update_iconDM;
import com.example.appquanlichitieu_android.Apdapter.DM_Thu_ADapter;
import com.example.appquanlichitieu_android.DAO.CatalogSpendDAO;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.UpdateDMChi.Update_DM_Chi;
import com.example.appquanlichitieu_android.UpdateDMChi.Update_icon_DMChi;
import com.example.appquanlichitieu_android.modelQuanLi.Category_Autumn_Model;
import com.example.appquanlichitieu_android.modelQuanLi.ExpensesModel;
import com.example.appquanlichitieu_android.modelQuanLi.catalogSpendModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Update_Icon_DM_Chi_ADapter extends RecyclerView.Adapter<Update_Icon_DM_Chi_ADapter.ViewHolder> {
    private Context context;
    private List<catalogSpendModel> list_item;
    private CatalogSpendDAO catalogSpendDAO;

    public Update_Icon_DM_Chi_ADapter(Context context, List<catalogSpendModel> list_item) {
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
        catalogSpendModel model = list_item.get(position);
        catalogSpendDAO = new CatalogSpendDAO(context);
        holder.tvtenDanhMuc.setText(model.getCatalog());
        Picasso.get().load(model.getImageSpend()).into(holder.img_itemDM);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_icon_DMChi.class);
                intent.putExtra("itemId", model.getIdCatalog());
                intent.putExtra("image", model.getImageSpend());
                intent.putExtra("category", model.getCatalog());
                Toast.makeText(context, "Chỉnh sửa danh mục" + model.getCatalog(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Chỉnh sua id " + model.getIdCatalog(), Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });


    }
    public void delete(catalogSpendModel model, Update_Icon_DM_Chi_ADapter.ViewHolder holder){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo")
                .setMessage("Bạn có muốn xóa danh mục này?")
                .setNegativeButton("Cencel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(catalogSpendDAO.delete(model.getIdCatalog())>0){
                            List<catalogSpendModel> updatedData = catalogSpendDAO.getAll();
                            updateData(updatedData);
                            Toast.makeText(context, "Bạn đã xóa danh mục "+model.getCatalog(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        builder.create().show();

    }
    public void updateData(List<catalogSpendModel> newData) {
        list_item.clear();
        list_item.addAll(newData);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return list_item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_itemDM;
        private TextView tvtenDanhMuc,delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_itemDM = itemView.findViewById(R.id.item_image_danhmuc_expenses);
            tvtenDanhMuc = itemView.findViewById(R.id.item_ten_Danhmuc_expexses);

        }
    }

}
