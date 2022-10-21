/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author april
 */
public class Koneksi {  
    public static void main (String[] args){
        getKoneksi();
    }
    
private static Connection koneksi;
    public static Connection getKoneksi() {
        if (koneksi == null) {
            try {
                //sesuaikan dengan port yang digunakan dan database yang dibuat
                String url = "jdbc:mysql://localhost:3306/tugaskaryawan";
                //sesuaikan dengan user mysql
                String user = "root";
                 //sesuaikan dengan password mysql
                String password = "";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection(url,user,password);
                System.out.println("koneksi sukses");
            }catch(Exception ex){
                System.out.println("koneksi eror="+ex);
            }
        }
        return koneksi;
}
}