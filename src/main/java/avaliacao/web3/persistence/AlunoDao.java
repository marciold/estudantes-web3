package avaliacao.web3.persistence;

import avaliacao.web3.model.Aluno;
import jakarta.persistence.EntityManager;

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
}
