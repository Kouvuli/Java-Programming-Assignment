package com.example.qlsv;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentList {
    private List<Student> studentList;

    public StudentList() {
        studentList=new ArrayList<>();
    }
    public void clone(StudentList src){
        for (int i=0;i<src.getSize();i++){
            studentList.add(src.getStudentList().get(i));
        }
    }
    public boolean isExist(String id){
        for (Student student: studentList
             ) {
            if (student.getMHS().trim().equals(id.trim())) {
                return true;
            }
        }
        return false;
    }
    public List<Student> getStudentList(){
        return studentList;
    }
    public int getSize() {
        return studentList.size();
    }

    public boolean updateStudent(String MHS){
        for (int i=0;i<studentList.size();i++){
            if (studentList.get(i).getMHS().trim().equals(MHS.trim())){
                Student student=new Student();
                student.inputStudent();
                if (student.getMHS().trim().equals(MHS.trim())){
                    studentList.set(i,student);
                    return true;
                }
                if (this.isExist(student.getMHS())){
                    return false;
                }
                studentList.set(i,student);
                return true;
            }
        }
        return false;
    }
    public void addStudent(){
        Student newStudent=new Student();
        newStudent.inputStudent();
        if (this.isExist(newStudent.getMHS())){
            System.out.println("Ma HS da ton tại!");
            return;
        }
        studentList.add(newStudent);
    }
    public boolean deleteStudent(String MHS){

        for (int i=0;i<studentList.size();i++){
            if (studentList.get(i).getMHS().trim().equals(MHS.trim())){
                studentList.remove(i);
                return true;
            }
        }
        return false;
    }
    public void inputStudentList(){
        System.out.println("Nhap danh sach HS");
        System.out.println("Nhap so luong HS:");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for (int i = 1; i <= n; i++) {
            System.out.println("Hoc sinh "+i);
            Student student=new Student();
            student.inputStudent();
            studentList.add(student);
        }
    }
    public void printStudentList(){
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(i+1);
            studentList.get(i).printStudent();
        }
    }
    public void writeFileReadable(String file_name){
        try{
            File fout = new File(file_name);
            if(!fout.exists())
                try {
                    fout.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            FileOutputStream fos=new FileOutputStream(fout);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_16);
            System.out.println("Ghi ra file: "+fout.getAbsolutePath());
            for (int i=0;i<studentList.size();i++) {
                osw.write("MHS:"+studentList.get(i).getMHS()+
                        "\nTen HS:"+studentList.get(i).getTenHS()+
                        "\nDiem:"+studentList.get(i).getDiem()+
                        "\nHinh Anh:"+studentList.get(i).getHinhAnh()+
                        "\nDia Chi:"+studentList.get(i).getDiaChi()+
                        "\nGhi Chu:"+studentList.get(i).getDiaChi()+
                        "\n------------------------------\n");
            }
            osw.close();
            fos.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeFile(String file_name){
        try {
            File fout = new File(file_name);
            if(!fout.exists())
                try {
                    fout.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            FileOutputStream fos=new FileOutputStream(fout);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(studentList.size());
            System.out.println("Ghi ra file: "+fout.getAbsolutePath());
            for (Student student:studentList
            ) {
                oos.writeObject(student);
            }
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readFile(String file_name) {
        try{
            File fin = new File(file_name);
            if(!fin.exists()) {
                System.out.println("File khong ton tai");
                return;
            }
            FileInputStream fis = new FileInputStream(fin);
            ObjectInputStream ois = new ObjectInputStream(fis);
            int studentCount=ois.readInt();
            for (int i=0;i<studentCount;i++) {
                Student student = (Student) ois.readObject();
                studentList.add(student);
            }
            fis.close();
            ois.close();
            System.out.println("Thanh cong!");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }


    }
}
