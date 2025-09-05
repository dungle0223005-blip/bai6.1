# Sử dụng Tomcat 9 với JDK 17
FROM tomcat:9.0-jdk17-temurin

# Xóa ứng dụng mặc định
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy file WAR đã build sẵn vào thư mục webapps
COPY dist/ch06_ex1_email.war /usr/local/tomcat/webapps/ROOT.war

# Mở port 8080
EXPOSE 8080

# Khởi động Tomcat
CMD ["catalina.sh", "run"]
