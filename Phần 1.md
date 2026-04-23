Lỗi xảy ra vì không sử dụng transaction.

Luồng thực tế:

Cập nhật Order → đã được Hibernate ghi xuống DB (do auto flush)
Xảy ra exception
Không có rollback()
Session đóng

Kết quả:

Order đã chuyển sang PAID
Wallet chưa bị trừ tiền

Nguyên nhân cốt lõi:

Không có beginTransaction()
Không có commit() và rollback()

→ Mất tính toàn vẹn dữ liệu vì các thao tác không nằm trong cùng một transaction.


Các lệnh Transaction đang bị thiếu:

beginTransaction() – để bắt đầu giao dịch
commit() – để xác nhận lưu toàn bộ thay đổi
rollback() – để hủy toàn bộ khi có lỗi

→ Thiếu 3 bước này làm transaction không hoàn chỉnh, dẫn đến dữ liệu bị cập nhật dở dang.