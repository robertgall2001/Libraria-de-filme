import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Locale;
import java.util.Random;

import static javax.swing.JOptionPane.*;

public class Cumparare extends JDialog{
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JPasswordField passwordField1;
    private JButton confirmatiButton;
    private JButton inapoiButton;


    /**
     * Clasa destinata achizitionarii de filme
     *
     * @param parent
     * @param us
     */
    public Cumparare(JFrame parent, String us) {

        super(parent);
        setTitle("Achizitionare filme");
        setContentPane(panel1);
        setMinimumSize(new Dimension(730, 650));
        setModal(true);
        setLocationRelativeTo(parent);


        comboBox1.setModel(new DefaultComboBoxModel(new String[]{"1 - Ianuarie", "2 - Februarie", "3 - Martie", "4 - Aprilie", "5 - Mai", "6 - Iunie", "7 - Iulie", "8 - August", "9 - Septembrie", "10 - Octombrie", "11 - Noiembrie", "12 - Decembrie"}));

        comboBox2.setModel(new DefaultComboBoxModel(new String[]{"2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037"}));


        Object[][] data = {
                {"THE PIANO", 1993, "Jane Campion", "Anna Paquin, Sam Neill", "Comedie", "Aduna sosete pentru a se putea casatori", 7 + " RON"},
                {"SINOPSIS FORREST GUMP", 1994, "Robert Zemeckis", "Tom Hanks", "Comedie", "Povestea incepe cu o pana cazand ...", 8 + " RON"},
                {"TITANIC", 1997, "James Cameron", "Leonardo DiCaprio, Kate Winslet", "Drama", "Titanicul este o poveste de dragoste..", 20 + " RON"},
                {"SE7EN", 1995, "David Fincher", "Brad Pitt, Morgan Freeman", "Crima", "Un criminal în serie cu o minte sclipitoare..", 15 + " RON"}

        };

        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"Titlu", "An", "Regia", "Actori", "Gen", "Descriere", "Pret"}
        ));


        inapoiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        confirmatiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String film = textField1.getText();
                int ok = 0;
int pret = 0;
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filme_disponibile", "root", "Iancudehd882001@");

                    PreparedStatement postef = connection.prepareStatement("CREATE TABLE if not exists `filme_disponibile`.`f_d` (`id` INT NOT NULL,`titlu` VARCHAR(45) NULL,`an` INT NULL,`regia` VARCHAR(45) NULL,`actori` VARCHAR(45) NULL,`gen` VARCHAR(45) NULL,`nota` INT NULL,`des` VARCHAR(95) NULL,`data` VARCHAR(45) NULL,`pret` INT NULL, PRIMARY KEY (`id`));");
                    postef.executeUpdate();

                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from filme_disponibile.f_d;");

                    while (resultSet.next()) {

                        String f = resultSet.getString("titlu");
                        if (f.toLowerCase().equals(film.toLowerCase()))
                            ok = 1;
                        pret = resultSet.getInt("pret");

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


                if (ok == 1) {


                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cump", "root", "Iancudehd882001@");

                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("select * from cump.achiz;");

                        while (resultSet.next()) {

                            String f = resultSet.getString("titlu");
                            String username = resultSet.getString("user");

                            if(film.toLowerCase().equals(f.toLowerCase()))
                                if(username.equals(us)) {
                                    showMessageDialog(null,"Acest film a fost cumparat deja de catre dumneavoastra","Incercati alt film",ERROR_MESSAGE);
                                    return;
                                }

                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }








                    String nr1 = textField2.getText();
                    String nr2 = textField3.getText();
                    String nr3 = textField4.getText();
                    String nr4 = textField5.getText();

                    String cvv = String.valueOf(passwordField1.getPassword());

                    if (nr1.length() != 4 || nr2.length() != 4 || nr3.length() != 4 || nr4.length() != 4 || cvv.length() != 3) {
                        showMessageDialog(null,"Campurile nu au fost completate corespunzator","Incercati din nou",ERROR_MESSAGE);
                    }
                    else
                    {
                        try {

                            Random rand = new Random();
                            int id = rand.nextInt(1000000000);
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cump", "root", "Iancudehd882001@");

                            PreparedStatement posted = connection.prepareStatement("insert into cump.achiz(id,titlu,user,pret) values ('" + id + "','" + film + "','" + us+ "','" +pret+ "')");
                            posted.executeUpdate();
                            showMessageDialog(null,"Tranzactie aprobata cu succes","",JOptionPane.ERROR_MESSAGE);
                            dispose();

                        }catch (SQLException er) {
                            er.printStackTrace();
                        }
                    }

                } else {
                    showMessageDialog(Cumparare.this, "Acest film nu este disponibil", "Incearca din nou", ERROR_MESSAGE);
                }
            }

        });

        setVisible(true);
    }

}
