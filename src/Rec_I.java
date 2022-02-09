import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Rec_I extends JDialog{
    private JPanel panel1;
    private JTable table1;
    private JButton inapoiButton;


    /**
     * Clasa destinata recenzilor filmelor indisponibile momentan
     *
     *
     * @param parent
     * @param u
     */

    public Rec_I(JFrame parent, String u) {

                super(parent);
                setTitle("Recenzii filme indisponibile");
                setContentPane(panel1);
                setMinimumSize(new Dimension(700,600));
                setModal(true);
                setLocationRelativeTo(parent);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rec_f_d", "root", "Iancudehd882001@");

            Statement statement = connection.createStatement();


            ResultSet resultSet = statement.executeQuery("select * from rec_f_i.r_f_i");
            while(resultSet.next()) {
                String s = resultSet.getString("username");
                String titlu=resultSet.getString("titlu");
                String rec = resultSet.getString("rec");
                int nota = resultSet.getInt("nota");

                Object [][] data = {
                        {"Scary Movie","Superb",8,"ion.pop"},
                        {"Scary Movie 3","Grozav",10,"andrei.dumitru"},
                        {"Scary Movie 2","Excelent",9,"andreea.pacurar"},
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
