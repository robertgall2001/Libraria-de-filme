/**
 * Aceasta clasa este destinata obiectului de tip Film
 *
 * @author Robi
 */

public class Film {


     String titlu;
    String regia;
    String actori;
    String gen;
    String descriere;
    int nota;
    int an;
    int pret;


    /**
     *
     * @param u
     * @param an
     * @param r
     * @param a
     * @param gen
     * @param nota
     * @param d
     * @param pret
     */
    public Film(String u, int an, String r, String a, String gen, int nota, String d, int pret) {
        this.titlu=u;
        this.an=an;
        this.regia=r;
        this.actori=a;
        this.gen = gen;
        this.nota = nota;
        this.descriere = d;
        this.pret = pret;
    }

    public Film() {

    }

    public String getTitlu() {
        return titlu;
    }

    public int getAn() {
        return an;
    }

    public String getRegia() {
        return regia;
    }

    public String getActori() {
        return actori;
    }

    public String getGen() {
        return gen;
    }

    public int getNota() {
        return nota;
    }

    public String getDescriere() {
        return descriere;
    }

}