package com.example.qlsv;

import java.util.*;

public class QLSV {
    public static void main(String[] args) {
        StudentList list=new StudentList();
        list.readFile("default.dat");//Khai báo dữ liệu ban đầu cho danh sách HS từ file mặc định
        int selection;

        do{
            System.out.println("------------MENU------------");
            System.out.println("Chon 1 de them hoc sinh");
            System.out.println("Chon 2 de xoa hoc sinh");
            System.out.println("Chon 3 de chinh sua hoc sinh");
            System.out.println("Chon 4 de xem DS HS co MHS tang dan");
            System.out.println("Chon 5 de xem DS HS co MHS giam dan");
            System.out.println("Chon 6 de xem DS HS co Diem tang dan");
            System.out.println("Chon 7 de xem DS HS co Diem giam dan");
            System.out.println("Chon 8 de export ra file");
            System.out.println("Chon 9 de import file");
            System.out.println("Chon 10 de in ra danh sach HS");
            Scanner sc=new Scanner(System.in);
            System.out.println("Nhap lua chon cua ban:");
            selection=sc.nextInt();
            switch (selection) {
                case 1:
                    list.addStudent();
                    break;
                case 2: {
                    System.out.println("Nhap ma HS muon xoa:");
                    Scanner scanner = new Scanner(System.in);
                    String MaHS = scanner.nextLine();
                    while (!list.isExist(MaHS)){
                        System.out.println("Nhap lai ma HS:");
                        MaHS=scanner.nextLine();
                    }
                    if (list.deleteStudent(MaHS)) {
                        System.out.println("Thanh cong");
                    } else {
                        System.out.println("That bai");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Nhap ma HS muon chinh sua:");
                    Scanner scanner = new Scanner(System.in);
                    String MaHS = scanner.nextLine();
                    while (!list.isExist(MaHS)){
                        System.out.println("Nhap lai ma HS:");
                        MaHS=scanner.nextLine();
                    }
                    if ( list.updateStudent(MaHS)) {
                        System.out.println("Thanh cong");
                    } else {
                        System.out.println("That bai");
                    }
                    break;
                }
                case 4:{
                    StudentList studentList=new StudentList();
                    studentList.clone(list);
                    Collections.sort(studentList.getStudentList(), new Comparator<Student>() {
                        @Override
                        public int compare(Student stu1, Student stu2) {
                            int a = Integer.parseInt(stu1.getMHS().trim());
                            int b = Integer.parseInt(stu2.getMHS().trim());
                            return a > b ? 1 : a < b ? -1 : 0;

                        }
                    });
                    System.out.println("Danh sach MHS tang dan");
                    studentList.printStudentList();
                    break;
                }
                case 5:{
                    StudentList studentList=new StudentList();
                    studentList.clone(list);
                    Collections.sort(studentList.getStudentList(), new Comparator<Student>() {
                        @Override
                        public int compare(Student stu1, Student stu2) {
                            int a = Integer.parseInt(stu1.getMHS().trim());
                            int b = Integer.parseInt(stu2.getMHS().trim());
                            return a < b ? 1 : a > b ? -1 : 0;

                        }
                    });
                    System.out.println("Danh sach MHS giam dan");
                    studentList.printStudentList();
                    break;
                }
                case 6:{
                    StudentList studentList=new StudentList();
                    studentList.clone(list);
                    Collections.sort(studentList.getStudentList(), new Comparator<Student>() {
                        @Override
                        public int compare(Student stu1, Student stu2) {
                            double a = Double.parseDouble(stu1.getDiem().trim());
                            double b = Double.parseDouble(stu2.getDiem().trim());
                            return a > b ? 1 : a < b ? -1 : 0;
                        }
                    });
                    System.out.println("Danh sach Diem tang dan");
                    studentList.printStudentList();
                    break;
                }
                case 7:{
                    StudentList studentList=new StudentList();
                    studentList.clone(list);
                    Collections.sort(studentList.getStudentList(), new Comparator<Student>() {
                        @Override
                        public int compare(Student stu1, Student stu2) {
                            double a = Double.parseDouble(stu1.getDiem().trim());
                            double b = Double.parseDouble(stu2.getDiem().trim());
                            return a < b ? 1 : a > b ? -1 : 0;
                        }
                    });
                    System.out.println("Danh sach Diem giam dan");
                    studentList.printStudentList();
                    break;
                }
                case 8:{

                    System.out.println("Chon 1 de in ra file doc duoc");
                    System.out.println("Chon 2 de in ra file doc duoc kieu nhi phan");
                    Scanner scanner=new Scanner(System.in);
                    int a=scanner.nextInt();
                    if(a==1){
                        list.writeFileReadable("data.csv");
                    }
                    else if(a==2){
                        list.writeFile("data.csv");
                    }
                    else{
                        System.out.println("Nhap sai lua chon!");
                    }
                    break;
                }
                case 9:
                    list.readFile("input.csv");
                    break;
                case 10:
                    list.printStudentList();
                    break;
            }

        }while (selection<=10&&selection>=1);


    }


}

