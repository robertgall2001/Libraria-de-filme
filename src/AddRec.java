import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Locale;
import java.util.Random;

public class AddRec extends JDialog{
    private JPanel panel1;
    private JRadioButton b1;
    private JRadioButton b2;
    private JButton adaugaButton;
    private JButton inapoiButton;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;

    /**
     * Clasa pentru adaugarea de recenzii din partea utilizatorului
     *
     *
     * @param parent
     * @param u
     */


    public AddRec(JFrame parent, String u){
        super(parent);

        setTitle("Adauga recenzie filme");
        setContentPane(panel1);
        setMinimumSize(new Dimension(700,600));
        setModal(true);
        setLocationRelativeTo(parent);

        //        comboBox1.setModel(new DefaultComboBoxModel(new String[]{"Comedie","Drama","Dragoste","Actiune","SF","Fantezie","Crima"}));
        comboBox1.setModel(new DefaultComboBoxModel(new String[]{"10","9","8","7","6","5","4","3","2","1"}));


        inapoiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        adaugaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if(b1.isSelected())
                {
                    //ESTE

                    int ok = 0;

                    String tit = textField1.getText();

                    try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filme_disponibile", "root", "Iancudehd882001@");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from filme_disponibile.f_d");

                        while (resultSet.next()) {
                            String titlu = null;

                                 titlu = resultSet.getString("titlu");

                                 if(titlu.toLowerCase().equals(tit.toLowerCase())) {
                                        ok=1;

                                     Random rand = new Random();
                                     int id = rand.nextInt(1000000000);

                                     String nota= (String) comboBox1.getSelectedItem();
                                     Connection conn = null;
                                     try {
                                         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rec_f_d","root","Iancudehd882001@");

                                         PreparedStatement posted = connection.prepareStatement("insert into rec_f_d.r_f_d(id,titlu,rec,nota,username) values ('"+id+"','"+titlu.toUpperCase()+"','"+textField2.getText()+"','"+Integer.parseInt(nota)+"','"+u+"')");
                                         posted.executeUpdate();
                                     } catch (SQLException ex) {
                                         ex.printStackTrace();
                                     }

                            }

                        }
                    } catch (SQLException ef) {
                            ef.printStackTrace();
                        }

                    if(ok==0){
                        JOptionPane.showMessageDialog(null,"Acest film nu este disponibil","Incercati din nou",JOptionPane.ERROR_MESSAGE);
                    }


                }

                else if(b2.isSelected()) {

                    //NU ESTE

                    String tit = textField1.getText();
                    String nota= (String) comboBox1.getSelectedItem();

                    Random rand = new Random();
                    int id = rand.nextInt(1000000000);

                    Connection conn = null;
                    try {
                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rec_f_i","root","Iancudehd882001@");

                        PreparedStatement posted = conn.prepareStatement("insert into rec_f_i.r_f_i(id,titlu,rec,nota,username) values ('"+id+"','"+tit.toUpperCase()+"','"+textField2.getText()+"','"+Integer.parseInt(nota)+"','"+u+"')");
                        posted.executeUpdate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }


                }

                else {
                    JOptionPane.showMessageDialog(null,"Alege unul din cele doua campuri","Incercati din nou",JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
        });

        setVisible(true);
    }
}
