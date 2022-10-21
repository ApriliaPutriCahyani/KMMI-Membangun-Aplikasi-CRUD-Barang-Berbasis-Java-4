/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataKaryawan;

import Koneksi.Koneksi;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author april
 */
public class Karyawan extends Frame {
    //  Buat Objek Frame

    public JFrame frame = new JFrame();

    //  Buat Objek Label
    public JLabel lblNIK = new JLabel("NIK Karyawan");
    public JLabel lblNama = new JLabel("Nama Karyawan");
    public JLabel lblJabatan = new JLabel("Jabatan");
    public JLabel lblAgama = new JLabel("Agama");
    public JLabel lblNorekening = new JLabel("No Rekening");
    public JLabel lblAlamat = new JLabel("Alamat");

//  Buat Objek TextField
    public JTextField txtNIK;
    public JTextField txtNama;
    public JTextField txtJabatan;
    public JTextField txtAgama;
    public JTextField txtNorekening;
    public JTextField txtAlamat;

//  Buat Button simpan
    JButton btnSimpan = new JButton("Simpan");
    JButton btnUbah = new JButton("Ubah");
    JButton btnDelete = new JButton("Hapus");
    JButton btnNew = new JButton("Baru");
    JButton btnPDF = new JButton("Report PDF");

    //step table 1
    public JTable table = new JTable();
    public JScrollPane scroll = new JScrollPane(table);
    public String[] columnNames = {"NIK Karyawan", "Nama Karyawan", "Jabatan", "Agama", "No Rekening","Alamat"};
    public DefaultTableModel model = new DefaultTableModel();
    // langkah  1
    public Connection koneksi = Koneksi.getKoneksi();

    // step 1
    public static void main(String[] args) {
        Karyawan frame = new Karyawan();
        // Show frame
        frame.setVisible(true);
    }

    // step 2
    public Karyawan() {
        createForm();
        listKaryawan();
        buttonKlik();
        tabelGetRowForEditData();
    }

// step 3
    public void createForm() {
        txtNIK = new JTextField();
        txtNama = new JTextField();
        txtJabatan = new JTextField();
        txtAgama = new JTextField();
        txtNorekening = new JTextField();
        txtAlamat = new JTextField();
//  Set judul frame
        frame.setTitle("Data Karyawan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//  Set ukuran frame
        frame.setSize(1000, 700);

//  Set Posisi frame berada di tengah layar
        frame.setLocationRelativeTo(null);

//  [Optional] Set tombol maximize menjadi disabled
        frame.setResizable(false);

//  Set program agar program berhenti ketika tombol close di klik di frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//  Set Visible frame  agar Frame muncul ketika program di running
        frame.setVisible(true);

//  Set Layout Frame
        frame.setLayout(null);

//  Memasukkan komponen Label, TextField dan Button ke dalam Frame
        frame.add(lblNIK);
        frame.add(lblNama);
        frame.add(lblJabatan);
        frame.add(lblAgama);
        frame.add(lblNorekening);
        frame.add(lblAlamat);

        frame.add(txtNIK);
        frame.add(txtNama);
        frame.add(txtJabatan);
        frame.add(txtAgama);
        frame.add(txtNorekening);
        frame.add(txtAlamat);

        frame.add(btnSimpan);
        frame.add(btnUbah);
        frame.add(btnDelete);
        frame.add(btnNew);
        frame.add(btnPDF);

//  Menentukan posisi komponen Label, TextField dan Button di dalam Frame menggunakan koordinat X dan Y
        lblNIK.setBounds(20, 20, 100, 20);
        lblNama.setBounds(20, 50, 100, 20);
        lblJabatan.setBounds(20, 80, 100, 20);
        lblAgama.setBounds(20, 110, 100, 20);
        lblNorekening.setBounds(20, 140, 100, 20);
        lblAlamat.setBounds(20, 170, 100, 20);

        btnSimpan.setBounds(310, 20, 100, 20);
        btnUbah.setBounds(310, 50, 100, 20);
        btnDelete.setBounds(310, 80, 100, 20);
        btnNew.setBounds(310, 110, 100, 20);
        btnPDF.setBounds(310, 140, 100, 20);

        txtNIK.setBounds(120, 20, 130, 20);
        txtNama.setBounds(120, 50, 130, 20);
        txtJabatan.setBounds(120, 80, 130, 20);
        txtAgama.setBounds(120, 110, 130, 20);
        txtNorekening.setBounds(120, 140, 130, 20);
        txtAlamat.setBounds(120, 170, 170, 50);

        // step 2
        frame.add(scroll);
        table.setModel(model);
        scroll.setBounds(20, 250, 600, 300);

    }

    // step 2
    public void listKaryawan() {
        // untuk set row 0 default,jika di call oleh method simpan , refresh data
        model.setNumRows(0);
// step 1 : query
        String sql = "select NIK , Nama , Jabatan, Agama, Norekening, Alamat from karyawan";
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);

        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        try {

            // set header tabel 
            model.setColumnIdentifiers(columnNames);

            Statement st = koneksi.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //object dari resultset menampung hasilnya
            ResultSet rs = st.executeQuery(sql);

            int i = 0;
            //selama masih ada data (next)
            // step 2: get data query
            while (rs.next()) {
                int NIK = rs.getInt("NIK");
                String Nama = rs.getString("Nama");
                String Jabatan = rs.getString("Jabatan");
                String Agama = rs.getString("Agama");
                int Norekening = rs.getInt("Norekening");
                String Alamat = rs.getString("Alamat");
                // step 3: tampilkan pada objek tabel
                model.addRow(new Object[]{NIK, Nama, Jabatan, Agama, Norekening, Alamat});
                i++;
            }

            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eror get table=" + ex, "Pesan", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }

    }

    public void simpanDataKaryawan() {
        try {

            String query = "INSERT INTO Karyawan (NIK ,Nama ,Jabatan,Agama,Norekening,Alamat) VALUES(?,?,?,?,?,?)";
            PreparedStatement prepare = koneksi.prepareStatement(query);

            prepare.setInt(1, Integer.parseInt(txtNIK.getText()));
            prepare.setString(2, txtNama.getText());
            prepare.setString(3, txtJabatan.getText());
            prepare.setString(4, txtAgama.getText());
            prepare.setInt(5, Integer.parseInt(txtNorekening.getText()));
            prepare.setString(6, txtAlamat.getText());
            
            // execute query : 
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            prepare.close();

            //panggil table / refresh
            listKaryawan();
            kosongkanTextfield();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan=" + ex, "Pesan", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }

    }

    public void kosongkanTextfield() {
        txtNIK.setText("");
        txtNama.setText("");
        txtJabatan.setText("");
        txtAgama.setText("");
        txtNorekening.setText("");
        txtAlamat.setText("");
        txtNIK.setEnabled(true);

    }

    public void buttonKlik() {
        btnSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent act) {
                if (chekValidasi()) {
                    if (chekDUplikatKode()) {
                        simpanDataKaryawan();
                    } else {
                        JOptionPane.showMessageDialog(null, "Kode " + txtNIK.getText() + " sudah ada. Silahkan isi kode lain.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan Lengkapi data", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

            btnUbah.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent act) {
        if (chekValidasi()) {
            ubahDataKaryawan();
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan Lengkapi data", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
});
    
    btnDelete.addActionListener(new ActionListener() {
        @Override
    public void actionPerformed(ActionEvent act) {
        if (chekValidasi()) {
            int n = JOptionPane.showConfirmDialog(
                    null,
                    "Apakah yakin ingin menghapus kode "+txtNIK.getText() ,
                    "",
                    JOptionPane.YES_NO_OPTION);

            if(n == JOptionPane.YES_OPTION)
            {
                deleteDataKaryawan();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan Lengkapi data", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
});
    
    btnNew.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent act) {
        kosongkanTextfield();
    }
});

        String pdfFilename = "D:\\NetbeansReportPdf\\DataKaryawan.pdf";
        btnPDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent act) {
                int n = JOptionPane.showConfirmDialog(
                        null,
                        "Apakah yakin ingin cetak report pada path " + pdfFilename,
                        "",
                        JOptionPane.YES_NO_OPTION);

                if (n == JOptionPane.YES_OPTION) {
                    ReportPdf ReportPdf = new ReportPdf();
                    ReportPdf.createPDF(pdfFilename, table);
                      JOptionPane.showMessageDialog(null, "Sukses generate PDF", "Error",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "ok, ga jadi generate pdf", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

    }

    public boolean chekValidasi() {
        if (txtNIK.getText().equals("")
                || txtNama.getText().equals("")
                || txtJabatan.getText().equals("")
                || txtAgama.getText().equals("")
                || txtNorekening.getText().equals("") 
                || txtAlamat.getText().equals("")) {
            return false;

        }
        return true;
    }

    public boolean chekDUplikatKode() {
        try {
            String sql = "select NIK, Nama, Jabatan, Agama, Norekening, Alamat from karyawan where nik ='" + txtNIK.getText() + "'";
            Statement st = koneksi.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);
            if (rs.first()) {
                // gagal insert
                System.out.println("gagal insert");
                return false;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "chek kode eror=" + ex, "Pesan", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
        return true;
    }
    
    public void tabelGetRowForEditData() {
    try {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                txtNIK.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
                txtNama.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                txtJabatan.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                txtAgama.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                txtNorekening.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
                txtAlamat.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
                txtNIK.setEnabled(false);
            }
        });


    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "get row table", "Pesan", JOptionPane.ERROR_MESSAGE);
        System.out.println(ex);
    }
}
    public void ubahDataKaryawan(){
           try {
    String query = "UPDATE karyawan SET Nama='" + txtNama.getText() + "', Jabatan='" + txtJabatan.getText() + "', Agama='" + txtAgama.getText() + "', Norekening='" + txtNorekening.getText() + "' , Alamat='" + txtAlamat.getText() + "' " +
            " WHERE NIK='" + txtNIK.getText() + "'";
    PreparedStatement prepare = koneksi.prepareStatement(query);
    prepare.executeUpdate();
    JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Pesan", JOptionPane.INFORMATION_MESSAGE);
    prepare.close();

    //panggil table / refresh
    listKaryawan();
    kosongkanTextfield();
} catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Data gagal diubah=" + ex, "Pesan", JOptionPane.ERROR_MESSAGE);
    System.out.println(ex);
}

    }

    public void deleteDataKaryawan(){
        try {
    String query = "DELETE FROM karyawan WHERE NIK='"+txtNIK.getText()+"'";
    PreparedStatement prepare = koneksi.prepareStatement(query);
    prepare.execute();
    JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Pesan", JOptionPane.INFORMATION_MESSAGE);
    prepare.close();

    //panggil table / refresh
    listKaryawan();
    kosongkanTextfield();
} catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Data gagal dihapus=" + ex, "Pesan", JOptionPane.ERROR_MESSAGE);
    System.out.println(ex);
}

    }
}
