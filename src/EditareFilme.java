import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EditareFilme extends JDialog{
    private JPanel panel1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField1;
    private JButton modificatiButton;
    private JButton anulareButton;
    private JButton stergeButton;
    private JButton anulareButton1;
    private JTextField textField8;
    private JTextField textField9;
    private JComboBox comboBox1;
    private JTable table1;
    private JScrollPane ScrlP;

    /**
     * Clasa destinata editarii filmelor destinata administratorului
     *
     *
     * @param parent
     */

    public EditareFilme(JFrame parent){
        super(parent);

        setTitle("Editare film");
        setContentPane(panel1);

        setMinimumSize(new Dimension(650, 700));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        comboBox1.setModel(new DefaultComboBoxModel(new String[]{"Comedie","Drama","Dragoste","Actiune","SF","Fantezie","Crima"}));

        Object [][] data = {
                {"SINOPSIS THE SHAWSHANK REDEMPTION", 1994, "Frank Darabont", "Morgan Freeman, Tim Robbins", "Drama", "Anul 1947. Andy Dufresne, un tanar bancher", 10+" RON"},
                {"THE PIANO",1993,"Jane Campion", "Anna Paquin, Sam Neill", "Comedie", "Aduna sosete pentru a se putea casatori",7+" RON"},
                {"SINOPSIS FORREST GUMP",1994,"Robert Zemeckis", "Tom Hanks", "Comedie", "Povestea incepe cu o pana cazand ...",8+" RON"},
                {"TITANIC",1997,"James Cameron", "Leonardo DiCaprio, Kate Winslet", "Drama", "Titanicul este o poveste de dragoste..", 20+" RON"},
                {"SE7EN",1995, "David Fincher", "Brad Pitt, Morgan Freeman", "Crima", "Un criminal în serie cu o minte sclipitoare..",15+" RON"}

        };

        //'Aduna sosete pentru a se putea casatori'

        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"Titlu","An","Regia","Actori","Gen","Descriere","Pret"}
        ));

        anulareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        anulareButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             dispose();
            }
        });

        modificatiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String denumire_film = textField9.getText();



 try {



                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filme_disponibile", "root", "Iancudehd882001@");


     PreparedStatement poste = connection.prepareStatement("CREATE TABLE if not exists `filme_disponibile`.`f_d` (`id` INT NOT NULL,`titlu` VARCHAR(45) NULL,`an` INT NULL,`regia` VARCHAR(45) NULL,`actori` VARCHAR(45) NULL,`gen` VARCHAR(45) NULL,`nota` INT NULL,`des` VARCHAR(95) NULL,`data` VARCHAR(45) NULL,`pret` INT NULL, PRIMARY KEY (`id`));");
     poste.executeUpdate();

                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from filme_disponibile.f_d;");

                    while(resultSet.next()) {
                        String s = resultSet.getString("titlu");
                        if(s.toLowerCase().equals(denumire_film.toLowerCase())) {
                            int i = resultSet.getInt("id");

                            String  an, reg, act, desc, pret;
                            String t = textField1.getText();
                            an=textField2.getText();
                            reg=textField3.getText();
                            act=textField4.getText();
                            String gen = (String) comboBox1.getSelectedItem();
                            desc=textField6.getText();
                            pret=textField7.getText();



                            PreparedStatement p;
                            p = connection.prepareStatement("update filme_disponibile.f_d set gen='"+gen+"' where id="+i);
                            p.executeUpdate();

                            if(! t.isEmpty()){
                                PreparedStatement posted;
                                posted = connection.prepareStatement("update filme_disponibile.f_d set titlu='"+t+"' where id="+i);
                                posted.executeUpdate();
                            }

                            if(! an.isEmpty()){
                                PreparedStatement posted;
                                posted = connection.prepareStatement("update filme_disponibile.f_d set an='"+Integer.parseInt(an)+"' where id="+i);
                                posted.executeUpdate();
                            }

                            if(! reg.isEmpty()){
                                PreparedStatement posted;
                                posted = connection.prepareStatement("update filme_disponibile.f_d set regia='"+reg+"' where id="+i);
                                posted.executeUpdate();
                            }

                            if(! desc.isEmpty()){
                                PreparedStatement posted;
                                posted = connection.prepareStatement("update filme_disponibile.f_d set des='"+desc+"' where id="+i);
                                posted.executeUpdate();
                            }

                            if(! act.isEmpty()){
                                PreparedStatement posted;
                                posted = connection.prepareStatement("update filme_disponibile.f_d set actori='"+act+"' where id="+i);
                                posted.executeUpdate();
                            }

                            if(! pret.isEmpty()){
                                PreparedStatement posted;
                                posted = connection.prepareStatement("update filme_disponibile.f_d set pret='"+Integer.parseInt(pret)+"' where id="+i);
                                posted.executeUpdate();
                            }

                        }
                    }
                 } catch (SQLException ef) {
                    ef.printStackTrace();
                }



            }
        });
        stergeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id;
                String user = textField8.getText();
                int ok = 0;

                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filme_disponibile", "root", "Iancudehd882001@");
                    PreparedStatement poste = connection.prepareStatement("CREATE TABLE if not exists `filme_disponibile`.`f_d` (`id` INT NOT NULL,`titlu` VARCHAR(45) NULL,`an` INT NULL,`regia` VARCHAR(45) NULL,`actori` VARCHAR(45) NULL,`gen` VARCHAR(45) NULL,`nota` INT NULL,`des` VARCHAR(95) NULL,`data` VARCHAR(45) NULL,`pret` INT NULL, PRIMARY KEY (`id`));");
                    poste.executeUpdate();
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from filme_disponibile.f_d;");


                    while (resultSet.next()) {

                        String us = resultSet.getString("titlu");

                        if (us.toLowerCase().equals(user.toLowerCase())) {
                            id = resultSet.getInt("id");
                            ok = 1;
                            PreparedStatement posted;
                            posted = connection.prepareStatement("delete from filme_disponibile.f_d where id=" + id);
                            posted.executeUpdate();
                        }

                    }

                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

                if (ok == 0)
                    JOptionPane.showMessageDialog(null, "Nu exista un astfel de nume de utilizator printre cele blocate", "Incercati din nou", JOptionPane.ERROR_MESSAGE);

            }

        });



        setVisible(true);

    }


}
