package usuaris;

public class UsaUsuariPDI {

    public static void main(String[] args) {
        //Crear un objeto UsuariPDI con datos de prueba.
        UsuariPDI pdi = new UsuariPDI("jlopez", "jlopez", "PDI", "ETSE", "Secelades");

        //Imprimir el objeto para ver que toString funciona correctamente.
        System.out.println("---Informació de l'usuari PDI---");
        System.out.println(pdi);

        // Comprobación individual de métodos.
        System.out.println("\n---Comprobación idnividual de método---");
        System.out.println("Alias: " + pdi.getAlies());                 
        System.out.println("Email complet: " + pdi.getEmailComplet());  
        System.out.println("Tipus d'usuari: " + pdi.tipusUsuari());     
        System.out.println("Dades extra: " + pdi.dadesExtra()); 
        System.out.println("Departament: " + pdi.getdepartament()); 
        System.out.println("Campus: " + pdi.getCampus());
    }
}
