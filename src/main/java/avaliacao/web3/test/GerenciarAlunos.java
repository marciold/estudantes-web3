package avaliacao.web3.test;

import avaliacao.web3.model.Aluno;
import avaliacao.web3.persistence.AlunoDao;
import avaliacao.web3.utils.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;


public class GerenciarAlunos {
    private static final EntityManager em = JPAUtils.getEntityManager();
    private static final AlunoDao alunoDao = new AlunoDao(em);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao;
        do {
            System.out.println("\n** GERENCIAMENTO DE ALUNOS **");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Excluir aluno");
            System.out.println("3 - Alterar aluno");
            System.out.println("4 - Buscar aluno pelo nome");
            System.out.println("5 - Listar alunos");
            System.out.println("6 - FIM");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\nCadastrar aluno: \n");
                    cadastrarAluno();
                    break;
                case 2:
                    System.out.println("\nExcluir aluno: \n");
                    removerAlunoPorNome();
                    break;
                case 3:
                    System.out.println("\nAlterar aluno: \n");
                    alterarAlunoPorNome();
                    break;
                case 4:
                    System.out.println("\nBuscar aluno pelo nome: ");
                    listarAlunoPorNome();
                    break;
                case 5:
                    System.out.println("\nListar alunos: ");
                    listarAlunos();
                    break;
                case 6:
                    System.out.println("Fim do programa.");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 6);
        em.close();
    }


    private static void cadastrarAluno() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("RA: ");
        String ra = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        float nota1 = -1;
        float nota2 = -1;
        float nota3 = -1;

        while(nota1 < 0 || nota1 > 10){
            System.out.print("Nota 1: ");
            nota1 = scanner.nextFloat();
        }
        while(nota2 < 0 || nota2 > 10){
            System.out.print("Nota 2: ");
            nota2 = scanner.nextFloat();
        }
        while(nota3 < 0 || nota3 > 10){
            System.out.print("Nota 3: ");
            nota3 = scanner.nextFloat();
        }


        scanner.nextLine();

        Aluno aluno = new Aluno(nome, ra, email, BigDecimal.valueOf(nota1),
                BigDecimal.valueOf(nota2),  BigDecimal.valueOf(nota3));

        em.getTransaction().begin();

        alunoDao.cadastrarAluno(aluno);

        em.getTransaction().commit();

        System.out.println("Aluno cadastrado com sucesso!");
    }

    private static void printInfo(Aluno aluno) {
        System.out.println("-------------------------------");
        System.out.println("Nome: " + aluno.getNome());
        System.out.println("RA: " + aluno.getRa());
        System.out.println("Email: " + aluno.getEmail());
        System.out.println("Nota 1: " + aluno.getNota1());
        System.out.println("Nota 2: " + aluno.getNota2());
        System.out.println("Nota 3: " + aluno.getNota3());
        System.out.println("Media: " + aluno.getMedia());
        System.out.println("Status: " + aluno.getStatus());
    }

    private static void listarAlunos(){
        List<Aluno> alunos = alunoDao.listarAlunos();

        if (alunos.isEmpty()){
            System.out.println("Não há alunos cadastrados no sistema.");
            return;
        }
        for (Aluno aluno : alunos) {
            printInfo(aluno);
        }
    }

    private static void listarAlunoPorNome() {
        System.out.println("Informe o nome do aluno: ");
        String nome = scanner.nextLine();
        try{
            Aluno aluno = alunoDao.listarPorNome(nome);
            if (aluno == null)
                System.out.println("Nenhum aluno encontrado!\n");
            else
                printInfo(aluno);
        }
        catch (NoResultException e) {
            System.err.println("\n\nNenhum aluno encontrado");
        }

    }

    private static void removerAlunoPorNome(){
        em.getTransaction().begin();
        System.out.println("Informe o nome do aluno: ");
        String nome = scanner.nextLine();

        boolean maybeAluno = alunoDao.removerAluno(nome);
        if (maybeAluno) {
            System.out.println("Aluno removido!\n");
            em.getTransaction().commit();
        }
        else
            System.out.println("Aluno não encontrado!\n");

    }

    private static void alterarAlunoPorNome(){
        System.out.println("Informe o nome do aluno: ");
        String estudante = scanner.nextLine();
        Aluno alunoResultado = alunoDao.listarPorNome(estudante);
        if (alunoResultado == null) {
            System.out.println("Nenhum aluno encontrado!\n");
            return ;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("RA: ");
        String ra = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        float nota1 = -1;
        float nota2 = -1;
        float nota3 = -1;

        while(nota1 < 0 || nota1 > 10){
            System.out.print("Nota 1: ");
            nota1 = scanner.nextFloat();
        }
        while(nota2 < 0 || nota2 > 10){
            System.out.print("Nota 2: ");
            nota2 = scanner.nextFloat();
        }
        while(nota3 < 0 || nota3 > 10){
            System.out.print("Nota 3: ");
            nota3 = scanner.nextFloat();
        }
        
        em.getTransaction().begin();
        
        alunoDao.alterarDados(estudante, nome, email, ra, BigDecimal.valueOf(nota1),
                BigDecimal.valueOf(nota2),  BigDecimal.valueOf(nota3));

        em.getTransaction().commit();

        System.out.println("\nAluno alterado com sucesso!");

    }



}
