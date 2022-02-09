import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Reclm extends JDialog{
    private JPanel panel1;
    private JButton trimiteButton;
    private JButton inapoiButton;
    private JTextField textField1;

    /**
     * Clasa dedicata pentru reclamatii si sugestii
     *
     * @param parent
     * @param u
     */

    public Reclm(JFrame parent, String u){
        super(parent);

        setTitle("Probleme");
        setContentPane(panel1);
        setMinimumSize(new Dimension(500,400));
        setModal(true);
        setLocationRelativeTo(parent);

        inapoiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        trimiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String rec = textField1.getText();

                    Random rand = new Random();
                    int id = rand.nextInt(1000000000);


                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sug_rec","root","Iancudehd882001@");

                    PreparedStatement posted = connection.prepareStatement("insert into sug_rec.s_r(id,nume_ut,s_r) values ('"+id+"','"+u+"','"+rec+"')");
                    posted.executeUpdate();
                } catch (SQLException ez) {
                    ez.printStackTrace();
                }
                dispose();
            }
        });
        setVisible(true);
    }
}
