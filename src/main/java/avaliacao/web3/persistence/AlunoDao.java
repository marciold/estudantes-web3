package avaliacao.web3.persistence;

import avaliacao.web3.model.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.math.BigDecimal;
import java.util.List;

public class AlunoDao {
    private final EntityManager em;

    public AlunoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrarAluno(Aluno aluno) {
        em.persist(aluno);
    }

    public List<Aluno> listarAlunos() {
        String jpql = "SELECT a FROM Aluno a";
        return em.createQuery(jpql, Aluno.class).getResultList();
    }

    public Aluno listarPorNome(String nome){
        try {
            String jpql = "select a from Aluno a where a.nome = :n";
            return em.createQuery(jpql, Aluno.class)
                    .setParameter("n", nome)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public boolean removerAluno(String nome){
        Aluno aluno = listarPorNome(nome);
        if(aluno != null){
            em.remove(aluno);
            return true;
        }
        return false;
    }

    public void alterarDados(String estudante, String nome, String email, String ra , BigDecimal nota1
            , BigDecimal nota2, BigDecimal nota3){
        Aluno aluno = listarPorNome(estudante);

        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setRa(ra);
        aluno.setNota1(nota1);
        aluno.setNota2(nota2);
        aluno.setNota3(nota3);

    }
}
