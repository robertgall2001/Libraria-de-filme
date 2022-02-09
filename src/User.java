/**
 * Aceasta clasa este dedicata pentru cpnturile de utilizatori
 *
 *
 * @author Robi
 */

public class User {

    String username;
    String parola;
    String phone;
    String email;
    String name;
    String statut;
    String adresa;

    /**
     *
     * @param u
     * @param p
     * @param ph
     * @param e
     * @param nume
     * @param statut
     * @param adresa
     */
    public User(String u, String p, String ph, String e, String nume, String statut, String adresa) {
        this.username=u;
        this.parola=p;
        this.phone=ph;
        this.email=e;
        this.name =  nume;
        this.statut = statut;
        this.adresa = adresa;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
        /**
         * Metoda de get()
         */
    }

    public String getUsername() {
        return username;
    }

    public String getParola() {
        return parola;
    }

    public String getName() {
        return name;
    }

    public String getStatut() {
        return statut;
    }

    public String getAdresa() {
        return adresa;
    }

    public String toString() {
        return "Contul lui " + name + " a fost inregistrat cu succes " +"\n\n";
    }
}
