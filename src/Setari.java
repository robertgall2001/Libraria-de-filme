import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Setari extends JDialog {
    private JPanel panelSetari;
    private JButton modificatiButton;
    private JButton anulareButton;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JPasswordField passwordField3;
    private JTable table1;
    private JTextField textField1;


    /**
     * Clasa destinata setarilor conturilor
     *
     * @param parent
     * @param u
     */


    public Setari(JFrame parent, String u) {

        super(parent);

     //   System.out.println("utilizatorul: " + u + " tocmai a intrat in setari");
        setTitle("Setari");
        setContentPane(panelSetari);
        setMinimumSize(new Dimension(650, 700));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/conturi_utilizatori", "root", "Iancudehd882001@");

            Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery("select * from conturi_utilizatori.conturi_ut");

            ResultSet resultSet = statement.executeQuery("select * from conturi_utilizatori.conturi_ut");
            while(resultSet.next()) {
                String s = resultSet.getString("username");
                if(s.equals(u))
                {

                    String pre = resultSet.getString("prenume");
                    String n = resultSet.getString("nume");

                    String tel = resultSet.getString("phone");
                    String a = resultSet.getString("adresa");
                    String e = resultSet.getString("email");

                    Object[][] data = {{n,pre,u,e,tel,a}};

                    table1.setModel(new DefaultTableModel(
                            data,
                            new String[]{"Nume","Prenume","Nume de utilizator","Mail","Telefon","Adresa"}
                    ));

                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        anulareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        modificatiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/conturi_utilizatori", "root", "Iancudehd882001@");

                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from conturi_utilizatori.conturi_ut");

                    while(resultSet.next()) {
                        String s = resultSet.getString("username");
                        if(s.equals(u))
                        {
                            int i = resultSet.getInt("id");
                            //System.out.println("id: " + i);

                            String nume = textField8.getText();
                            String prenume = textField1.getText();
                            String email = textField5.getText();
                            String phone = textField6.getText();
                            String us = textField7.getText();
                            String adresa = textField4.getText();
                            String pV1 = resultSet.getString("parola");
                            String pV2=String.valueOf(passwordField1.getPassword());

                            String pN1=String.valueOf(passwordField2.getPassword());
                            String pN2=String.valueOf(passwordField3.getPassword());


                            if(! nume.isEmpty()){
                                PreparedStatement posted;
                                posted = connection.prepareStatement("update conturi_utilizatori.conturi_ut set nume='"+nume+"' where id="+i);
                                posted.executeUpdate();
                            }

                            if(! prenume.isEmpty()){
                                PreparedStatement posted;
                                posted = connection.prepareStatement("update conturi_utilizatori.conturi_ut set prenume='"+prenume+"' where id="+i);
                                posted.executeUpdate();
                            }

                            if(! us.isEmpty() && us.length()>5){

                                PreparedStatement posted;
                                posted = connection.prepareStatement("update conturi_utilizatori.conturi_ut set username='"+us+"' where id="+i);
                                posted.executeUpdate();
                            }

                            if(! phone.isEmpty() && phone.length()>7){

                                PreparedStatement posted;
                                posted = connection.prepareStatement("update conturi_utilizatori.conturi_ut set phone='"+phone+"' where id="+i);
                                posted.executeUpdate();
                            }

                            if(! email.isEmpty() && email.length()>5){

                                PreparedStatement posted;
                                posted = connection.prepareStatement("update conturi_utilizatori.conturi_ut set email='"+email+"' where id="+i);
                                posted.executeUpdate();
                            }

                            if(! adresa.isEmpty() && adresa.length()>3){

                                PreparedStatement posted;
                                posted = connection.prepareStatement("update conturi_utilizatori.conturi_ut set adresa='"+adresa+"' where id="+i);
                                posted.executeUpdate();
                            }

                            if(pV1.equals(pV2) && pN1.equals(pN2)){
                                PreparedStatement posted;
                                posted = connection.prepareStatement("update conturi_utilizatori.conturi_ut set parola='"+pN1+"' where id="+i);
                                posted.executeUpdate();
                            }
                            else{
                                    //JOptionPane.showMessageDialog(null,"Nu exista un astfel de nume de utilizator","Incercati din nou",JOptionPane.ERROR_MESSAGE);
                                if(! pN1.equals(pN2))
                                    JOptionPane.showMessageDialog(null,"Cele doua parole nu coincid","Incercati din nou",JOptionPane.ERROR_MESSAGE);

                                if((!pV2.isEmpty()) && ! pV1.equals(pV2)){
                                    JOptionPane.showMessageDialog(null,"Parola veche gresita","Incercati din nou",JOptionPane.ERROR_MESSAGE);
                                }

                            }

                        }

                    }
                } catch (SQLException ef) {
                    ef.printStackTrace();
                }
        dispose();
            }
        });

        setVisible(true);
    }

}
