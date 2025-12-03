package enumeraciones;

public enum DepartamentURV {
    DAFITS("Departament d’Antropologia, Filosofia i Treball Social"),
    DCMB("Departament de Ciències Mèdiques Bàsiques"),
    DDP("Departament de Dret Públic"),
    DEEEA("Departament d'Enginyeria Electrònica, Elèctrica i Automàtica"),
    DEM("Departament d'Enginyeria Mecànica"),
    DEAA("Departament d'Estudis Anglesos i Alemanys"),
    DFC("Departament de Filologia Catalana"),
    DGEO("Departament de Geografia"),
    DHHA("Departament d'Història i Història de l'Art"),
    DMC("Departament de Medicina i Cirurgia"),
    DP("Departament de Psicologia"),
    DQFI("Departament de Química Física i Inorgànica"),
    DBB("Departament de Bioquímica i Biotecnologia"),
    DDPPF("Departament de Dret Privat, Processal i Financer"),
    DECON("Departament d’Economia"),
    DEIM("Departament d’Enginyeria Informàtica i Matemàtiques"),
    DEQ("Departament d’Enginyeria Química"),
    DEC("Departament d’Estudis de Comunicació"),
    DFR("Departament de Filologies Romàniques"),
    DGE("Departament de Gestió d’Empreses"),
    DINF("Departament d’Infermeria"),
    DPED("Departament de Pedagogia"),
    DQAQO("Departament de Química Analítica i Química Orgànica");

    private String nomDepartament;
    DepartamentURV(String nomDepartament){
        this.nomDepartament = nomDepartament;
    }

    public String getNomCampus(){
        return nomDepartament;
    }
}
