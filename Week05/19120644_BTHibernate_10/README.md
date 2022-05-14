Tên: Lê Đức Tâm
MSSV: 19120644
BT HIbernate


Để chạy chương trình:
Cách 1:
Source code ban đầu không có import thư viên javafx nên khi sử dụng cần tải về và import tay vào

Thêm vào VM options ở debug/run Configuraion:
--module-path java-fx directory/libs --add-modules=javafx.controls,javafx.fxml,javafx.graphics

Phiên bản java là 17.0.1 cần lưu ý sửa lại thư viện hay cài phiên đúng phiên bản java khi chạy thử chương trình

Rồi build chạy chương trình

Cách 2: Chạy file Java-Course-Attendance.jar trong folder Jar


DB Sử dụng là: PostgreSQL

Link github: https://github.com/Kouvuli/Course-Attendance