import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Utilizator extends JDialog{
    private JPanel panel1;
    private JButton iesireButton1;
    private JButton deconectareButton;
    private JButton setariButton;
    private JButton veziFilmeleDisponibileButton;
    private JButton filmeleTaleButton;
    private JButton sugestiiReclamatiiButton;
    private JButton adaugaORecenziePentruButton;
    private JButton recenziiFilmeDisponibileButton;
    private JButton recenziiFilmeIndisponibileButton;

    /**
     * Clasa destinata utilizatorului
     *
     * @param parent
     * @param us
     */


    public Utilizator(JFrame parent, User us) {

        super(parent);

        setTitle("Cont utilizator");
        setContentPane(panel1);
        setMinimumSize(new Dimension(650,700));
        setModal(true);
        setLocationRelativeTo(parent);

        deconectareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm lg = new LoginForm(null);
                dispose();
            }
        });
        iesireButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Unume = us.username;
                Setari s = new Setari(null, Unume);
            }
        });

        sugestiiReclamatiiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Unume = us.username;
                Reclm r = new Reclm(null,Unume);

            }

        });

        adaugaORecenziePentruButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Unume = us.username;
                AddRec ar= new AddRec(null,Unume);

            }
        });

        recenziiFilmeDisponibileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Unume = us.username;
                Rec_D ar= new Rec_D(null,Unume);
            }
        });

        recenziiFilmeIndisponibileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Unume = us.username;
                Rec_I ar = new Rec_I(null,Unume);

            }
        });

        veziFilmeleDisponibileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String u = us.username;
                Ut_fil uf = new Ut_fil(null,u);
            }
        });

        filmeleTaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String u = us.username;
                Fil_Tale f = new Fil_Tale(null,u);

            }
        });

        setVisible(true);

    }
}
