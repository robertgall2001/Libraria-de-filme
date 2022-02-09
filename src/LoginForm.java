import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


/**
 * Aceasta clasa este folosita pentru conectare
 *
 * @author Robi
 */

public class LoginForm extends JDialog{
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton autentificareButton;
    private JPanel btnL;
    private JButton cancelButton;
    private JPanel LogINPanel;
    private JButton inregistrareButton;
    JSpinner d= new JSpinner(new SpinnerNumberModel(90, 20, 300, 1));


    public LoginForm(JFrame parent){

        super(parent);

        setTitle("Autentificare");
        setContentPane(LogINPanel);
        setMinimumSize(new Dimension(600,400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        autentificareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String us = textField1.getText();
                String pas = String.valueOf(passwordField1.getPassword());

            user = aut(us,pas);

            if(user!=null) {
                if(user.statut.equals("Administrator")){
                   // System.out.println("Utilizatorul " + user.name+" tocmai s a autentificat");
                    Administrator n = new Administrator(null,user);
                    //System.out.println("Utilizatorul " + user.name+" tocmai s a autentificat");
                    dispose();
                }
                else
                {
                    Utilizator ul = new Utilizator(null,user);
                    dispose();
                }
            }else
            {
                JOptionPane.showMessageDialog(LoginForm.this, "Username sau parola gresita","Incearca din nou",JOptionPane.ERROR_MESSAGE);
            }

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        inregistrareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register myForm = new Register(null);

            }
        });

        setVisible(true);
    }

        public User user;
    private User aut(String u, String p){

        User user=null;
        String s="";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/conturi_utilizatori", "root", "Iancudehd882001@");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from conturi_utilizatori.conturi_ut");

            while (resultSet.next()) {

                String username,parola;
                username = resultSet.getString("username");
                parola = resultSet.getString("parola");
                String statut = resultSet.getString("statut");
                String nume =  resultSet.getString("nume");
                String phone = resultSet.getString("phone");
                String adrs = resultSet.getString("adresa");
                String mail = resultSet.getString("email");

                if(username.equals(u) && parola.equals(p)) {
                    s = statut;
                    user = new User(username,parola,phone,mail,nume,statut,adrs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {

        LoginForm l = new LoginForm(null);
        User user = l.user;
        if(user!=null){
            System.out.println("Autentificare reusita");
        }
        else
            System.out.println("Autentificare esuata");

    }

}