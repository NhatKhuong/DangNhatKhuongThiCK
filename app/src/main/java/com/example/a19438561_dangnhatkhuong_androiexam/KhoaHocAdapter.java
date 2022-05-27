package com.example.a19438561_dangnhatkhuong_androiexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class KhoaHocAdapter extends BaseAdapter {

    private Context context;
    private int idLayout;
    private List<KhoaHoc> list;

    public KhoaHocAdapter(Context context, int idLayout, List<KhoaHoc> list) {
        this.context = context;
        this.idLayout = idLayout;
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list.size() != 0){
            return list.size();
        }
        return  0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(idLayout,viewGroup,false);
        }

        KhoaHoc khoaHoc = list.get(i);
        TextView txtTenKhoaHoac= view.findViewById(R.id.txtTenKhoaHoc);
        TextView txtThoiLuong = view.findViewById(R.id.txtThoiLuong);
        TextView txtLoaiKhoaHoc = view.findViewById(R.id.txtLoaiKhoaHoc);
        TextView txtThoiGian = view.findViewById(R.id.txtNgay);

        txtTenKhoaHoac.setText(khoaHoc.getTenKhoaHoc());
        txtLoaiKhoaHoc.setText(khoaHoc.getLoaiKhoaHoc());
        txtThoiLuong.setText(String.valueOf(khoaHoc.getThoiLuong()));
        txtThoiGian.setText(khoaHoc.getDate());


        return view;
    }
}
