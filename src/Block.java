package src;

import java.time.LocalTime;
import java.util.Date;

public class Block {

    String uc,turma,dia_sem,sala;
    int maxSala,nInscritos;
    String horaInicioUC,horaFimUC;

    String dataAula;

    public Block(String uc, String turma, String dia_sem, String sala, int maxSala, int nInscritos, String horaInicioUC, String horaFimUC, String dataAula) {
        this.uc = uc;
        this.turma = turma;
        this.dia_sem = dia_sem;
        this.sala = sala;
        this.maxSala = maxSala;
        this.nInscritos = nInscritos;
        this.horaInicioUC = horaInicioUC;
        this.horaFimUC = horaFimUC;
        this.dataAula = dataAula;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getDia_sem() {
        return dia_sem;
    }

    public void setDia_sem(String dia_sem) {
        this.dia_sem = dia_sem;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getMaxSala() {
        return maxSala;
    }

    public void setMaxSala(int maxSala) {
        this.maxSala = maxSala;
    }

    public int getnInscritos() {
        return nInscritos;
    }

    public void setnInscritos(int nInscritos) {
        this.nInscritos = nInscritos;
    }

    public String getHoraInicioUC() {
        return horaInicioUC;
    }

    public void setHoraInicioUC(String horaInicioUC) {
        this.horaInicioUC = horaInicioUC;
    }

    public String getHoraFimUC() {
        return horaFimUC;
    }

    public void setHoraFimUC(String horaFimUC) {
        this.horaFimUC = horaFimUC;
    }

    public String getDataAula() {
        return dataAula;
    }

    public void setDataAula(String dataAula) {
        this.dataAula = dataAula;
    }
}