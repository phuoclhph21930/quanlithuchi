package com.example.appquanlichitieu_android.Apdapter;

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
import com.example.appquanlichitieu_android.DAO.Category_Autumn_DAO;
import com.example.appquanlichitieu_android.R;
import com.example.appquanlichitieu_android.modelQuanLi.Category_Autumn_Model;
import com.example.appquanlichitieu_android.modelQuanLi.ExpensesModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DM_Thu_ADapter extends RecyclerView.Adapter<DM_Thu_ADapter.ViewHolder> {
    private Context context;
    private List<Category_Autumn_Model> list_item;
    private Category_Autumn_DAO lisAutumnDao;
    public DM_Thu_ADapter(Context context, List<Category_Autumn_Model> list_item) {
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
lisAutumnDao = new Category_Autumn_DAO(context);
        holder.tvtenDanhMuc.setText(model.getDanhmucThu());
        Picasso.get().load(model.getImageThu()).into(holder.img_itemDM);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_iconDM.class);
                intent.putExtra("itemId", model.getIdCatalogThu());
                intent.putExtra("image", model.getImageThu());
                intent.putExtra("category", model.getDanhmucThu());
                Toast.makeText(context, "Chỉnh sửa danh mục" + model.getDanhmucThu(), Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });



    }
    public void updateData(List<Category_Autumn_Model> newData) {
        list_item.clear();
        list_item.addAll(newData);
        notifyDataSetChanged();
    }
//    private void editItem(Category_Autumn_Model selectedItem) {
//        // Lấy thông tin từ đối tượng selectedItem và thực hiện chỉnh sửa
//        int selectedItemId = selectedItem.getIdCatalogThu();
//        String selectedImage = selectedItem.getImageThu();
//        String selectedCategory = selectedItem.getDanhmucThu();
//
//        // Thực hiện các thao tác chỉnh sửa dựa trên thông tin lấy được
//        // Ví dụ: mở màn hình chỉnh sửa và truyền các giá trị vào đó
//        Intent intent = new Intent(context, EditItemActivity.class);
//        intent.putExtra("itemId", selectedItemId);
//        intent.putExtra("image", selectedImage);
//        intent.putExtra("category", selectedCategory);
//        context.startActivity(intent);
//    }
public void delete(Category_Autumn_Model model,ViewHolder holder){
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
                    if(lisAutumnDao.delete(model.getIdCatalogThu())>0){
                        List<Category_Autumn_Model> updatedData = lisAutumnDao.getAll(); // Thay yourSQLQuery bằng truy vấn SQL để lấy dữ liệu mới
                        updateData(updatedData);
                        holder.delete_item.setVisibility(View.GONE);
                        Toast.makeText(context, "Bạn đã xóa thành công", Toast.LENGTH_SHORT).show();
                    }

                }
            });
    builder.create().show();

}
    @Override
    public int getItemCount() {
        return list_item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_itemDM;
        private TextView tvtenDanhMuc,delete_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_itemDM = itemView.findViewById(R.id.item_image_danhmuc_expenses);
            tvtenDanhMuc = itemView.findViewById(R.id.item_ten_Danhmuc_expexses);

        }
    }
}
