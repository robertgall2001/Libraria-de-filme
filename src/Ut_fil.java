import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ut_fil extends JDialog{
    private JPanel panel1;
    private JTable table1;
    private JButton achizitioneazaFilmButton;
    private JButton inapoiButton;

    /**
     * Clasa destinata achiz filmelor
     *
     * @param parent
     * @param us
     */

    public Ut_fil(JFrame parent, String us) {


        super(parent);
        setTitle("Filme disponibile");
        setContentPane(panel1);
        setMinimumSize(new Dimension(700,600));
        setModal(true);
        setLocationRelativeTo(parent);

        Object [][] data = {
                {"THE PIANO",1993,"Jane Campion", "Anna Paquin, Sam Neill", "Comedie", "Aduna sosete pentru a se putea casatori",7+" RON"},
                {"SINOPSIS FORREST GUMP",1994,"Robert Zemeckis", "Tom Hanks", "Comedie", "Povestea incepe cu o pana cazand ...",8+" RON"},
                {"TITANIC",1997,"James Cameron", "Leonardo DiCaprio, Kate Winslet", "Drama", "Titanicul este o poveste de dragoste..", 20+" RON"},
                {"SE7EN",1995, "David Fincher", "Brad Pitt, Morgan Freeman", "Crima", "Un criminal in serie cu o minte sclipitoare..",15+" RON"}

        };


        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"Titlu","An","Regia","Actori","Gen","Descriere","Pret"}
        ));

        inapoiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
        achizitioneazaFilmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cumparare c = new Cumparare(null,us);

            }
        });
        setVisible(true);
    }
}
