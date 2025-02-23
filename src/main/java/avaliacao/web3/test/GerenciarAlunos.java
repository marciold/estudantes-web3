package avaliacao.web3.test;

import avaliacao.web3.model.Aluno;
import avaliacao.web3.persistence.AlunoDao;
import avaliacao.web3.utils.JPAUtils;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class GerenciarAlunos {
    private static final EntityManager em = JPAUtils.getEntityManager();
    private static final AlunoDao alunoDao = new AlunoDao(em);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao;
        do {
            System.out.println("\n** CADASTRO DE ALUNOS **");
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
                    cadastrarAluno();
                    break;
                case 2:
                    //todo
                    break;
                case 3:
                    //todo
                    break;
                case 4:
                    //todo
                    break;
                case 5:
                    //todo falta mostrar a media e status, deixei mostrando oq tem no banco para testar
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
        System.out.print("Nota 1: ");
        BigDecimal nota1 = scanner.nextBigDecimal();
        System.out.print("Nota 2: ");
        BigDecimal nota2 = scanner.nextBigDecimal();
        System.out.print("Nota 3: ");
        BigDecimal nota3 = scanner.nextBigDecimal();
        scanner.nextLine();

        Aluno aluno = new Aluno(nome, ra, email, nota1, nota2, nota3);

        em.getTransaction().begin();

        alunoDao.cadastrarAluno(aluno);

        em.getTransaction().commit();

        System.out.println("Aluno cadastrado com sucesso!");
    }

    private static void listarAlunos(){
        List<Aluno> alunos = alunoDao.listarAlunos();
        for (Aluno aluno : alunos) {
            System.out.println("-------------------------------");
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("RA: " + aluno.getRa());
            System.out.println("Email: " + aluno.getEmail());
            System.out.println("Nota 1: " + aluno.getNota1());
            System.out.println("Nota 2: " + aluno.getNota2());
            System.out.println("Nota 3: " + aluno.getNota3());
            System.out.println("Status: " + aluno.getStatus());
        }
    }

}
