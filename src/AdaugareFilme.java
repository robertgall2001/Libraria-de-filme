import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Locale;
import java.util.Random;

public class AdaugareFilme extends JDialog{
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton adaugaButton;
    private JButton anulareButton;
    private JTextField textField7;
    private JComboBox comboBox1;

    /**
     *
     * Clasa pentru adaugarea de filme din partea administratorului
     *
     * @param Parent
     * @param un
     */

    public AdaugareFilme(JFrame Parent, String un){
        super(Parent);
        setTitle("Adauga film nou");
        setContentPane(panel1);
        setMinimumSize(new Dimension(650, 600));
        setModal(true);
        setLocationRelativeTo(Parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        comboBox1.setModel(new DefaultComboBoxModel(new String[]{"Comedie","Drama","Dragoste","Actiune","SF","Fantezie","Crima"}));


        anulareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        adaugaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Random rand = new Random();
                int id = rand.nextInt(1000000000);


                String gen= (String) comboBox1.getSelectedItem();
                String titlu = textField1.getText();
                String regia = textField3.getText();
                String actori = textField4.getText();
                String descriere = textField6.getText();
                String an = textField2.getText();
                String nota = textField5.getText();
                String pret = textField7.getText();


                Film f = new Film(titlu,Integer.parseInt(an),regia,actori,gen,Integer.parseInt(nota),descriere, Integer.parseInt(pret));

                java.util.Date date=new java.util.Date();
                String d = String.valueOf(date);

                int ok=0;
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filme_disponibile", "root", "Iancudehd882001@");

                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from filme_disponibile.f_d;");

                    while (resultSet.next()) {

                        String tit = resultSet.getString("titlu");
                        if(tit.toLowerCase().equals(titlu.toLowerCase()))
                        {
                            ok=1;
                            JOptionPane.showMessageDialog(null,"Acest film este disponibil deja","Incercati din nou",JOptionPane.ERROR_MESSAGE);
                        }

                    }
                } catch (SQLException et) {
                    et.printStackTrace();
                }


                if(ok==0){
                    Connection connection = null;
                    try {
                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filme_disponibile","root","Iancudehd882001@");

                        PreparedStatement poste = connection.prepareStatement("CREATE TABLE if not exists `filme_disponibile`.`f_d` (`id` INT NOT NULL,`titlu` VARCHAR(45) NULL,`an` INT NULL,`regia` VARCHAR(45) NULL,`actori` VARCHAR(45) NULL,`gen` VARCHAR(45) NULL,`nota` INT NULL,`des` VARCHAR(95) NULL,`data` VARCHAR(45) NULL,`pret` INT NULL, PRIMARY KEY (`id`));");
                        poste.executeUpdate();


//                       Statement statement = connection.createStatement();
//                    ResultSet resultSet = statement.executeQuery("CREATE TABLE if not exists `filme_disponibile`.`f_d` `id` INT NOT NULL,`titlu` VARCHAR(45) NULL,`an` INT NULL,`regia` VARCHAR(45) NULL,`actori` VARCHAR(45) NULL,`gen` VARCHAR(45) NULL,`nota` INT NULL,`des` VARCHAR(95) NULL,`data` VARCHAR(45) NULL,`pret` INT NULL, PRIMARY KEY (`id`));");

                        PreparedStatement posted = connection.prepareStatement("insert into filme_disponibile.f_d(id,titlu,an,regia,actori,gen,nota,des,data,pret) values ('"+id+"','"+titlu.toUpperCase()+"','"+an+"','"+regia+"','"+actori+"','"+gen+"','"+nota+"','"+descriere+"','"+date+"','"+pret+"')");
                        posted.executeUpdate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
                dispose();
            }
        });

        setVisible(true);
    }

}
