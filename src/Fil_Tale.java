import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectStreamException;
import java.sql.*;

public class Fil_Tale extends JDialog{
    private JPanel panel1;
    private JButton inapoiButton;
    private JTable table1;


    /**
     * Clasa pentru a vizualiza filmele detinute de utilizator
     *
     * @param parent
     * @param us
     */

    public Fil_Tale(JFrame parent, String us) {

        super(parent);
        setTitle("Filmele tale");
        setContentPane(panel1);
        setMinimumSize(new Dimension(730, 650));
        setModal(true);
        setLocationRelativeTo(parent);



        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cump", "root", "Iancudehd882001@");

            PreparedStatement postef = connection.prepareStatement("CREATE TABLE if not exists `filme_disponibile`.`f_d` (`id` INT NOT NULL,`titlu` VARCHAR(45) NULL,`an` INT NULL,`regia` VARCHAR(45) NULL,`actori` VARCHAR(45) NULL,`gen` VARCHAR(45) NULL,`nota` INT NULL,`des` VARCHAR(95) NULL,`data` VARCHAR(45) NULL,`pret` INT NULL, PRIMARY KEY (`id`));");
            postef.executeUpdate();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from cump.achiz");

            while (resultSet.next()) {

                String u = resultSet.getString("user");
                if(u.equals(us)){
                    String t = resultSet.getString("titlu");
                    Object[][] data = {{t}};
                    table1.setModel(new DefaultTableModel(
                            data,
                            new String[]{"Titlu"}
                    ));
                }
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