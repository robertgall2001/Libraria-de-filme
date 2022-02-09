import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static javax.swing.JOptionPane.showMessageDialog;



public class Administrator extends JDialog{
    private JPanel admPanel;
    private JButton setariButton;
    private JButton deautentificareButton;
    private JButton conturiBlocateButton;
    private JButton editeazaFilmeDisponibileButton;
    private JButton adaugaFilmeDisponibileButton;
    private JButton iesireButton;
    private JButton deblocareButton;
    private JButton blocareButton;
    private JTextField textField1;
    private JButton stergeToateFilmeleDisponibileButton;


    /**
     * Clasa pentru contul de tip administrator
     * @param parent
     * @param us
     */


    public Administrator(JFrame parent,User us){
        super(parent);
System.out.println("Tocmai s a autentificat utilizatorul cu numele : " + us.username);


        setTitle("Cont administrator");
        setContentPane(admPanel);
        setMinimumSize(new Dimension(650,700));
        setModal(true);
        setLocationRelativeTo(parent);


        iesireButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
        deautentificareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm lg = new LoginForm(null);
                dispose();

            }
        });

        blocareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                String user = textField1.getText();
                int ok=0;

                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/conturi_utilizatori", "root", "Iancudehd882001@");
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from conturi_utilizatori.conturi_ut");

                    while(resultSet.next()){

                        String us = resultSet.getString("username");

                        if(us.equals(user)){
                            id= resultSet.getInt("id");
                            System.out.println("id: " + id);
                            ok=1;
                            tabel_blocare(id);

                             PreparedStatement posted;
                            posted = connection.prepareStatement("delete from conturi_utilizatori.conturi_ut where id="+id);
                            posted.executeUpdate();
                }

                        }

                    } catch (SQLException exception) {
                    exception.printStackTrace();
                }

                if (ok==0)
                    JOptionPane.showMessageDialog(null,"Nu exista un astfel de nume de utilizator","Incercati din nou",JOptionPane.ERROR_MESSAGE);

            }
        });

        deblocareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                String user = textField1.getText();
                int ok=0;

                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/conturi_blocate", "root", "Iancudehd882001@");
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from conturi_blocate.conturi_bl");

                    while(resultSet.next()){

                        String us = resultSet.getString("username");

                        if(us.equals(user)){
                            id= resultSet.getInt("id");
                            System.out.println("id: " + id);
                            ok=1;
                            tabel_DEblocare(id);

                            PreparedStatement posted;
                            posted = connection.prepareStatement("delete from conturi_blocate.conturi_bl where id="+id);
                            posted.executeUpdate();
                        }

                    }

                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

                if (ok==0)
                    JOptionPane.showMessageDialog(null,"Nu exista un astfel de nume de utilizator printre cele blocate","Incercati din nou",JOptionPane.ERROR_MESSAGE);

            }
        });

        setariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Unume = us.username;
                Setari s = new Setari(null, Unume);
            }
        });

        adaugaFilmeDisponibileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String me = us.username;
                AdaugareFilme af = new AdaugareFilme(null, me);

            }
        });


        editeazaFilmeDisponibileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditareFilme ef = new EditareFilme(null);

            }
        });


        stergeToateFilmeleDisponibileButton.addActionListener(new ActionListener() {
            Connection connection = null;


            public void actionPerformed(ActionEvent e) {
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filme_disponibile","root","Iancudehd882001@");

                    PreparedStatement poste = connection.prepareStatement("CREATE TABLE if not exists `filme_disponibile`.`f_d` (`id` INT NOT NULL,`titlu` VARCHAR(45) NULL,`an` INT NULL,`regia` VARCHAR(45) NULL,`actori` VARCHAR(45) NULL,`gen` VARCHAR(45) NULL,`nota` INT NULL,`des` VARCHAR(95) NULL,`data` VARCHAR(45) NULL,`pret` INT NULL, PRIMARY KEY (`id`));");
                    poste.executeUpdate();

                     PreparedStatement posted = connection.prepareStatement("drop table filme_disponibile.f_d;");
                    posted.executeUpdate();

                    PreparedStatement postef = connection.prepareStatement("CREATE TABLE if not exists `filme_disponibile`.`f_d` (`id` INT NOT NULL,`titlu` VARCHAR(45) NULL,`an` INT NULL,`regia` VARCHAR(45) NULL,`actori` VARCHAR(45) NULL,`gen` VARCHAR(45) NULL,`nota` INT NULL,`des` VARCHAR(95) NULL,`data` VARCHAR(45) NULL,`pret` INT NULL, PRIMARY KEY (`id`));");
                    postef.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        setVisible(true);
    }

    public void tabel_blocare(int id){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/conturi_utilizatori", "root", "Iancudehd882001@");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from conturi_utilizatori.conturi_ut");


                while(resultSet.next()) {

                    int di = resultSet.getInt("id");
                    if(di==id){
                        String username,parola;
                        username = resultSet.getString("username");
                        System.out.println("username:" + username);
                        parola = resultSet.getString("parola");
                        String statut = resultSet.getString("statut");
                        String nume =  resultSet.getString("nume");
                        String phone = resultSet.getString("phone");
                        String adrs = resultSet.getString("adresa");
                        String mail = resultSet.getString("email");

                        try {
                            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/conturi_blocate", "root", "Iancudehd882001@");

                            PreparedStatement posted = connection.prepareStatement("insert into conturi_blocate.conturi_bl(id,username,parola,phone,email,nume,adresa,statut) values ('" + id + "','" + username + "','" + parola + "','" + phone + "','" + mail + "','" + nume + "','" + adrs + "','" + statut + "')");
                            posted.executeUpdate();
                            showMessageDialog(this,"Utilizatorul a fost blocat cu succes","",JOptionPane.ERROR_MESSAGE);

                        }catch (SQLException e) {
                            e.printStackTrace();
                        }




                    }

                }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }



    public void tabel_DEblocare(int id){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/conturi_blocate", "root", "Iancudehd882001@");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from conturi_blocate.conturi_bl");


            while(resultSet.next()) {

                int di = resultSet.getInt("id");
                if(di==id){
                    String username,parola;
                    username = resultSet.getString("username");
                    System.out.println("username:" + username);
                    parola = resultSet.getString("parola");
                    String statut = resultSet.getString("statut");
                    String nume =  resultSet.getString("nume");
                    String phone = resultSet.getString("phone");
                    String adrs = resultSet.getString("adresa");
                    String mail = resultSet.getString("email");

                    try {
                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/conturi_utilizatori", "root", "Iancudehd882001@");

                        PreparedStatement posted = connection.prepareStatement("insert into conturi_utilizatori.conturi_ut(id,username,parola,phone,email,nume,adresa,statut) values ('" + id + "','" + username + "','" + parola + "','" + phone + "','" + mail + "','" + nume + "','" + adrs + "','" + statut + "')");
                        posted.executeUpdate();
                        showMessageDialog(this,"Utilizatorul a fost deblocat cu succes","",JOptionPane.ERROR_MESSAGE);

                    }catch (SQLException e) {
                        e.printStackTrace();
                    }


                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }





}
