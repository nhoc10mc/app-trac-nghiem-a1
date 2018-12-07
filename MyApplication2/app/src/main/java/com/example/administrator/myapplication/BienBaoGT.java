package com.example.administrator.myapplication;

public class BienBaoGT {
    private int _id;
    private String ynghia;
    private String image;
    private String loai;

    public BienBaoGT(int _id, String ynghia, String image, String loai) {
        this._id = _id;
        this.ynghia = ynghia;
        this.image = image;
        this.loai = loai;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getYnghia() {
        return ynghia;
    }

    public void setYnghia(String ynghia) {
        this.ynghia = ynghia;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
}
