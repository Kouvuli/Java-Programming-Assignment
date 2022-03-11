package com.example.qlsv;

import java.io.Serializable;
import java.util.Scanner;

public class Student implements Serializable {
    String MHS;
    String TenHS;
    String Diem;
    String HinhAnh;
    String DiaChi;
    String GhiChu;
    public Student(){

    }
    public Student(String MHS, String tenHS, String diem, String hinhAnh, String diaChi, String ghiChu) {
        this.MHS = MHS;
        TenHS = tenHS;
        Diem = diem;
        HinhAnh = hinhAnh;
        DiaChi = diaChi;
        GhiChu = ghiChu;
    }

    public String getMHS() {
        return MHS;
    }

    public String getTenHS() {
        return TenHS;
    }

    public String getDiem() {
        return Diem;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setMHS(String MHS) {
        this.MHS = MHS;
    }

    public void setTenHS(String tenHS) {
        TenHS = tenHS;
    }

    public void setDiem(String diem) {
        Diem = diem;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }
    public void inputStudent(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Ma HS:");
        this.MHS=sc.nextLine();
        System.out.println("Ten HS:");
        this.TenHS=sc.nextLine();
        System.out.println("Diem:");
        this.Diem = sc.nextLine();
        System.out.println("Hinh anh:");
        this.HinhAnh=sc.nextLine();
        System.out.println("Dia chi:");
        this.DiaChi=sc.nextLine();
        System.out.println("Ghi chu:");
        this.GhiChu=sc.nextLine();
    }
    public void printStudent(){
        System.out.println("MHS:"+MHS+"\nTen HS:"+TenHS+"\nDiem:"+Diem+"\nHinh Anh:"+HinhAnh+"\nDia Chi:"+DiaChi+"\nGhi Chu:"+GhiChu);
    }
}
