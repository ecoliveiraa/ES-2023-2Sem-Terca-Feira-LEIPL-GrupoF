package src.testes.src.test;

import org.junit.jupiter.api.Test;
import src.Bloco;
import src.Horario;
import src.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static src.Utils.readFile;

class UtilsTest {

    @Test
    public void testHorarioTOJSON() throws IOException {
        // Cria um objeto de Horario com alguns blocos
        Horario horario = new Horario("Horario de teste");
        horario.addToHor(new Bloco("Curso", "Turno", "UC1", "Turma", "Segunda", "Sala1", 50, 30, "08:00", "10:00", "01/01/2023"));
        horario.addToHor(new Bloco("Curso", "Turno", "UC2", "Turma", "Terça", "Sala2", 30, 15, "10:00", "12:00", "02/01/2023"));

        // Chama o método horarioTOJSON para gerar o arquivo JSON
        Utils.horarioTOJSON(horario, "horario_teste");

        // Verifica se o arquivo foi criado
        File arquivo = new File("horario_teste.json");
        assertTrue(arquivo.exists());

        // Deleta o arquivo para não deixar resíduos no sistema de arquivos
        arquivo.delete();
    }




    @Test
    void horarioToCSV() throws IOException {
        // Cria um novo Horario

        Horario horario = new Horario("Horario de teste");
        horario.addToHor(new Bloco("Curso", "Turno", "UC1", "Turma", "Segunda", "Sala1", 50, 30, "08:00", "10:00", "01/01/2023"));
        horario.addToHor(new Bloco("Curso", "Turno", "UC2", "Turma", "Terça", "Sala2", 30, 15, "10:00", "12:00", "02/01/2023"));


        // Testa se o arquivo CSV é criado corretamente
        try {
            Utils.horarioToCSV(horario, "horario_teste");
            assertTrue(true);
        } catch (IOException e) {
            fail("Não deveria ter lançado exceção: " + e.getMessage());
        }

        // Testa se o arquivo CSV contém as informações corretas
        String csvContent = "UC,Turma,Dia,Sala,Curso,Turno,Inicio,Fim,DataAula,MaxSala,Inscritos\n" +
                "UC1,Turma,Segunda,Sala1,Curso,Turno,08:00,10:00,01/01/2023,50,30\n" +
                "UC2,Turma,Terça,Sala2,Curso,Turno,10:00,12:00,02/01/2023,30,15\n";
        assertEquals(csvContent, readFile("horario_teste.csv"));

        // Apaga o arquivo criado
        Utils.deleteFile("horario_teste.csv");
    }

    @Test
    void csvToHorarioTest() throws IOException {
        String arquivoCSV = "horario_teste.csv";
        Horario horario = Utils.csvToHorario(arquivoCSV);

        // Verifica se o número de blocos do horário é o esperado
        assertEquals(2, horario.horario.size());

        // Verifica se os dados do primeiro bloco estão corretos
        Bloco bloco1 = horario.horario.get(0);
        assertEquals("Curso", bloco1.getCurso());
        assertEquals("Turno", bloco1.getTurno());
        assertEquals("UC1", bloco1.getUc());
        assertEquals("Turma", bloco1.getTurma());
        assertEquals("Segunda", bloco1.getDia_sem());
        assertEquals("Sala1", bloco1.getSala());
        assertEquals(50, bloco1.getMaxSala());
        assertEquals(30, bloco1.getnInscritos());
        assertEquals("08:00", bloco1.getHoraInicioUC());
        assertEquals("10:00", bloco1.getHoraFimUC());
        assertEquals("01/01/2023", bloco1.getDataAula());

        // Verifica se os dados do segundo bloco estão corretos
        Bloco bloco2 = horario.horario.get(1);
        assertEquals("Curso", bloco2.getCurso());
        assertEquals("Turno", bloco2.getTurno());
        assertEquals("UC2", bloco2.getUc());
        assertEquals("Turma", bloco2.getTurma());
        assertEquals("Terça", bloco2.getDia_sem());
        assertEquals("Sala2", bloco2.getSala());
        assertEquals(40, bloco2.getMaxSala());
        assertEquals(15, bloco2.getnInscritos());
        assertEquals("10:00", bloco2.getHoraInicioUC());
        assertEquals("12:00", bloco2.getHoraFimUC());
        assertEquals("02/01/2023", bloco2.getDataAula());
    }

    @Test
    void parseJson() {
        // Criar um arquivo de teste JSON
        String jsonStr = "{\"horarios\":[{\"uc\":\"mat\",\"turma\":\"A1\",\"dia_sem\":\"Seg\",\"sala\":\"A101\",\"curso\":\"Eng. Civil\",\"turno\":\"Manhã\",\"horaInicioUC\":\"08:00\",\"horaFimUC\":\"10:00\",\"dataAula\":\"01/02/2023\",\"maxSala\":40,\"nInscritos\":30},{\"uc\":\"fis\",\"turma\":\"B1\",\"dia_sem\":\"Ter\",\"sala\":\"B201\",\"curso\":\"Eng. Elétrica\",\"turno\":\"Noite\",\"horaInicioUC\":\"19:00\",\"horaFimUC\":\"21:00\",\"dataAula\":\"02/02/2023\",\"maxSala\":50,\"nInscritos\":45}]}";
        String filename = "test.json";
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(jsonStr);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Executar o método parseJson com o arquivo de teste
        Horario horario = Utils.parseJson(filename);

        // Verificar se o objeto Horario contém a lista de blocos esperada
        assertEquals(2, horario.horario.size());
        Bloco bloco1 = horario.horario.get(0);
        assertEquals("mat", bloco1.getUc());
        assertEquals("A1", bloco1.getTurma());
        assertEquals("Seg", bloco1.getDia_sem());
        assertEquals("A101", bloco1.getSala());
        assertEquals("Eng. Civil", bloco1.getCurso());
        assertEquals("Manhã", bloco1.getTurno());
        assertEquals("08:00", bloco1.getHoraInicioUC());
        assertEquals("10:00", bloco1.getHoraFimUC());
        assertEquals("01/02/2023", bloco1.getDataAula());
        assertEquals(40, bloco1.getMaxSala());
        assertEquals(30, bloco1.getnInscritos());
        Bloco bloco2 = horario.horario.get(1);
        assertEquals("fis", bloco2.getUc());
        assertEquals("B1", bloco2.getTurma());
        assertEquals("Ter", bloco2.getDia_sem());
        assertEquals("B201", bloco2.getSala());
        assertEquals("Eng. Elétrica", bloco2.getCurso());
        assertEquals("Noite", bloco2.getTurno());
        assertEquals("19:00", bloco2.getHoraInicioUC());
        assertEquals("21:00", bloco2.getHoraFimUC());
        assertEquals("02/02/2023", bloco2.getDataAula());
        assertEquals(50, bloco2.getMaxSala());
        assertEquals(45, bloco2.getnInscritos());

        // Remover o arquivo de teste
        File file = new File(filename);
        file.delete();
    }

    @Test
    public void testGetOverCrowded() {

        Horario  horario = new Horario("Horário 1");

        Bloco bloco1 = new Bloco("curso1", "manhã", "uc1", "turma1", "segunda", "sala1", 30, 35, "8h", "10h", "2023-06-12");
        Bloco bloco2 = new Bloco("curso1", "manhã", "uc2", "turma2", "terça", "sala2", 20, 18, "10h", "12h", "2023-06-13");
        Bloco bloco3 = new Bloco("uc3", "turma3", "quarta", "sala3", 15, 10, "14h", "16h", "2023-06-14", "manhã");
        Bloco bloco4 = new Bloco("uc4", "turma4", "quinta", "sala4", 40, 50, "16h", "18h", "2023-06-15", "tarde");

        horario.addToHor(bloco1);
        horario.addToHor(bloco2);
        horario.addToHor(bloco3);
        horario.addToHor(bloco4);

        ArrayList<Integer> result = Utils.getOverCrowded(horario);
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(3);

        assertEquals(expected, result);
    }

    @Test
    void testGetOverCrowded_NoOvercrowded() {
        // Create a new Horario object
        Horario horario = new Horario("Horario 1");

        // Add some Bloco objects to the horario
        Bloco bloco1 = new Bloco("curso1", "manhã", "uc1", "turma1", "segunda", "sala1", 30, 20, "8h", "10h", "2023-06-12");
        Bloco bloco2 = new Bloco("curso1", "manhã", "uc2", "turma2", "terça", "sala2", 20, 18, "10h", "12h", "2023-06-13");
        Bloco bloco3 = new Bloco("uc3", "turma3", "quarta", "sala3", 15, 10, "14h", "16h", "2023-06-14", "manhã");
        horario.addToHor(bloco1);
        horario.addToHor(bloco2);
        horario.addToHor(bloco3);

        // Call the getOverCrowded method
        ArrayList<Integer> index = Utils.getOverCrowded(horario);

        // Assert that the index array is empty
        assertEquals(0, index.size());
    }


    @Test
    public void testGetOverlap_NoOverlap() {
        // Create blocks with no overlap
        Bloco bloco1 = new Bloco("curso1", "manhã", "uc1", "turma1", "segunda", "sala1", 30, 20, "08:00:00", "09:30:00", "2023-06-12");
        Bloco bloco2 = new Bloco("curso1", "manhã", "uc2", "turma2", "terça", "sala2", 20, 18, "08:00:00", "09:30:00", "2023-06-13");
        Bloco bloco3 = new Bloco("curso1", "manhã", "uc3", "turma2", "quarta", "sala3", 20, 18, "08:00:00", "09:30:00", "2023-06-13");



        // Add blocks to the schedule
        Horario horario = new Horario();
        horario.addToHor(bloco1);
        horario.addToHor(bloco2);
        horario.addToHor(bloco3);

        // Ensure that no overlap is returned
        ArrayList<Integer> expected = new ArrayList<>();
        ArrayList<Integer> actual = Utils.getOverlap(horario);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetOverlap_Overlap() {
        // Create blocks with overlap
        Bloco bloco1 = new Bloco("curso1", "manhã", "uc1", "turma1", "segunda", "sala1", 30, 20, "08:00:00", "09:30:00", "2023-06-12");
        Bloco bloco2 = new Bloco("curso1", "manhã", "uc2", "turma2", "segunda", "sala1", 20, 18, "08:00:00", "09:30:00", "2023-06-12");
        Bloco bloco3 = new Bloco("curso1", "manhã", "uc3", "turma2", "terça", "sala2", 20, 18, "08:00:00", "09:30:00", "2023-06-13");
        Bloco bloco4 = new Bloco("curso1", "manhã", "uc4", "turma2", "terça", "sala2", 20, 18, "08:00:00", "09:30:00", "2023-06-13");

        // Add blocks to the schedule
        Horario horario = new Horario();
        horario.addToHor(bloco1);
        horario.addToHor(bloco2);
        horario.addToHor(bloco3);
        horario.addToHor(bloco4);

        // Ensure that the expected overlap is returned
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        ArrayList<Integer> actual = Utils.getOverlap(horario);
        assertEquals(expected, actual);
    }


}
