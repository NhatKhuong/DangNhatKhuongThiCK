package com.example.a19438561_dangnhatkhuong_androiexam;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "khoahoc")
public class KhoaHoc implements Serializable {
    @PrimaryKey
    @NonNull
    private String id;
    private String tenKhoaHoc;
    private double thoiLuong;
    private String loaiKhoaHoc;
    private String date;

    public KhoaHoc(@NonNull String id, String tenKhoaHoc, double thoiLuong, String loaiKhoaHoc, String date) {
        this.id = id;
        this.tenKhoaHoc = tenKhoaHoc;
        this.thoiLuong = thoiLuong;
        this.loaiKhoaHoc = loaiKhoaHoc;
        this.date = date;
    }

    public KhoaHoc(String tenKhoaHoc, double thoiLuong, String loaiKhoaHoc, String date) {
        this.tenKhoaHoc = tenKhoaHoc;
        this.thoiLuong = thoiLuong;
        this.loaiKhoaHoc = loaiKhoaHoc;
        this.date = date;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public double getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(double thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getLoaiKhoaHoc() {
        return loaiKhoaHoc;
    }

    public void setLoaiKhoaHoc(String loaiKhoaHoc) {
        this.loaiKhoaHoc = loaiKhoaHoc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
