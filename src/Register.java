import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Register extends JDialog{
    private JTextField tfNAME;
    private JTextField tfEMAIL;
    private JTextField tfPHONE;
    private JTextField tfADRESA;
    private JTextField tfUSER;
    private JPasswordField tfPAR;
    private JPasswordField tfCONFPAR;
    private JButton inregistareButton;
    private JButton cancelButton;
    private JPanel registerpanel;
    private JTextField textField1;


    /**
     * Clasa destinata inregistratii unui cont pentru un nou utilizator
     * @param parent
     */


    public Register(JFrame parent){
        super(parent);

        setTitle("Create a new account");
        setContentPane(registerpanel);
        setMinimumSize(new Dimension(600,400));
        setModal(true);
        setLocationRelativeTo(parent);


        inregistareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm lg = new LoginForm(null);
                dispose();
            }
        });
        setVisible(true);
    }

    public static Boolean check_ex(String username) {
        File f1= new File("src/username");
        List<String> list = new ArrayList<>();

        try {
            Scanner sc = new Scanner(f1);
            while(sc.hasNextLine()) {
                String cuv = sc.nextLine();
                list.add(cuv);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for(String i:list){
            if(i.equals(username)) {
                return true;
            }
        }

        return false;
    }

    public void registerUser(){
        String name = tfNAME.getText();
        String email = tfEMAIL.getText();
        String phone = tfPHONE.getText();
        String us = tfUSER.getText();
        String adresa = tfADRESA.getText();
        String parola = String.valueOf(tfPAR.getPassword());
        String conf  = String.valueOf(tfCONFPAR.getPassword());
        String prenume = textField1.getText();
        int ok=1;


        if(prenume.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty() || us.isEmpty() || adresa.isEmpty() || parola.isEmpty() || conf.isEmpty()){
            JOptionPane.showMessageDialog(this,"Completati toate campurile","Incercati din nou",JOptionPane.ERROR_MESSAGE);
            ok=0;
            return;
        }

        if(! email.contains("@") || ! email.contains(".com")){
            JOptionPane.showMessageDialog(this,"Adresa de mail invalida","Incercati din nou",JOptionPane.ERROR_MESSAGE);
            ok=0;
            return;
        }

        int lungime_parola = parola.length();

        if(lungime_parola<8){
            JOptionPane.showMessageDialog(this,"Parola trebuie sa coincida cel putin 8 caractere","Incercati din nou",JOptionPane.ERROR_MESSAGE);
            ok=0;
            return;

        }

        if(!parola.equals(conf)){
            JOptionPane.showMessageDialog(this,"Cele doua parole nu coincid","Incercati din nou",JOptionPane.ERROR_MESSAGE);
            ok=0;
            return;
        }


        if(check_ex(us)==true){
            JOptionPane.showMessageDialog(this,"Acest nume de utilizator este folosit deja","Incercati din nou",JOptionPane.ERROR_MESSAGE);
            ok=0;
            return;
        }

        if(ok==1) {
        try {
            FileWriter fstr = new FileWriter("src/username",true);
            BufferedWriter myW = new BufferedWriter(fstr);
            myW.newLine();
            myW.append(us);
            myW.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

            Random rand = new Random();
            int rand_int1 = rand.nextInt(1000000000);

String stat = "Utilizator";
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/conturi_utilizatori","root","Iancudehd882001@");

                PreparedStatement posted = connection.prepareStatement("insert into conturi_utilizatori.conturi_ut(id,username,parola,phone,email,nume,adresa,statut,prenume) values ('"+rand_int1+"','"+us+"','"+parola+"','"+phone+"','"+email+"','"+name+"','"+adresa+"','"+stat+"','"+prenume+"')");
                posted.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            LoginForm l = new LoginForm(null);
            //dispose();
        }
    }

}
