import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Rec_D extends JDialog{
    private JPanel panel1;
    private JTable table1;
    private JButton inapoiButton;


    /**
     * Clasa destinata recenziilor filmelor puse la dispozitia utlizatorilor
     *
     * @param parent
     * @param us
     */

    public Rec_D(JFrame parent, String us) {
        super(parent);
        setTitle("Recenzii filme disponibile");
        setContentPane(panel1);
        setMinimumSize(new Dimension(700,600));
        setModal(true);
        setLocationRelativeTo(parent);


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rec_f_d", "root", "Iancudehd882001@");

            Statement statement = connection.createStatement();


            ResultSet resultSet = statement.executeQuery("select * from rec_f_d.r_f_d");
            while(resultSet.next()) {
                String s = resultSet.getString("username");
                String titlu=resultSet.getString("titlu");
                String rec = resultSet.getString("rec");
                int nota = resultSet.getInt("nota");

                Object [][] data = {
                        {"TITANIC","Superb",7,"ion.pop"},
                        {"SE7EN","Grozav",9,"andrei.dumitru"},
                        {"TITANIC","O placere, ori de cate ori revad acest film",8,"mirela.pop"},
                        {titlu,rec,nota,s}
                };

                table1.setModel(new DefaultTableModel(
                        data,
                        new String[]{"Titlu","Descriere","Nota","Utilizator"}
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        inapoiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}
