// -----------------------------------------------------------------
    // MÈTODES PER GESTIONAR AFORAMENT I LLISTA D'ESPERA
    // -----------------------------------------------------------------

    /**
     * Comprova si una activitat ha arribat al seu límit de places.
     * @param idActivitat Nom de l'activitat.
     * @param placesMaximes Aforament màxim de l'activitat.
     * @return true si està plena (o hi ha llista d'espera), false si queda lloc.
     */
    public boolean activitatEstaPlena(String idActivitat, int placesMaximes) {
        // Si placesMaximes és molt gran (ex: Integer.MAX_VALUE per Online), mai estarà plena
        if (placesMaximes == Integer.MAX_VALUE) return false;
        
        return comptarInscripcionsActivitat(idActivitat) >= placesMaximes;
    }

    /**
     * Retorna quantes persones hi ha en llista d'espera per a una activitat.
     * @param idActivitat Nom de l'activitat.
     * @param placesMaximes Aforament màxim de l'activitat.
     * @return 0 si no hi ha ningú en espera, o el número de persones que sobren.
     */
    public int getNumEnEspera(String idActivitat, int placesMaximes) {
        int totalInscrits = comptarInscripcionsActivitat(idActivitat);
        
        if (totalInscrits > placesMaximes) {
            return totalInscrits - placesMaximes;
        }
        return 0; // No hi ha ningú en espera
    }

    /**
     * Retorna ARRAY NOMÉS amb les inscripcions que estan EN ESPERA.
     * (Les que han arribat després d'omplir les places).
     */
    public Inscripcio[] getLlistaEspera(String idActivitat, int placesMaximes) {
        // 1. Obtenim tots els inscrits ordenats per arribada
        Inscripcio[] tots = getInscripcionsActivitat(idActivitat);
        int numEspera = getNumEnEspera(idActivitat, placesMaximes);
        
        if (numEspera == 0) return new Inscripcio[0]; // Array buit

        // 2. Creem l'array de resultats
        Inscripcio[] espera = new Inscripcio[numEspera];
        
        // 3. Copiem només els que sobren (a partir de l'índex 'placesMaximes')
        // Exemple: si 20 places, el 21è està a l'índex 20.
        for (int i = 0; i < numEspera; i++) {
            espera[i] = tots[placesMaximes + i];
        }
        
        return espera;
    }

    /**
     * Retorna ARRAY NOMÉS amb les inscripcions ADMESES (dins del límit).
     */
    public Inscripcio[] getAdmesos(String idActivitat, int placesMaximes) {
        Inscripcio[] tots = getInscripcionsActivitat(idActivitat);
        int total = tots.length;
        
        // Si n'hi ha menys que el límit, tots són admesos. Si més, tallem al límit.
        int numAdmesos = (total < placesMaximes) ? total : placesMaximes;
        
        Inscripcio[] admesos = new Inscripcio[numAdmesos];
        for (int i = 0; i < numAdmesos; i++) {
            admesos[i] = tots[i];
        }
        
        return admesos;
    }
