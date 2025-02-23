package avaliacao.web3.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true, nullable = false)
    private String ra;
    private String email;
    private BigDecimal nota1;
    private BigDecimal nota2;
    private BigDecimal nota3;
    private BigDecimal media;
    private StatusAprovacao status;

    public Aluno() {
    }

    public Aluno(String nome, String ra, String email, BigDecimal nota1, BigDecimal nota2, BigDecimal nota3) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.media = calcularMedia();
        this.status = gerarStatusAprovacao();
    }

    private BigDecimal calcularMedia() {
        BigDecimal soma = nota1.add(nota2).add(nota3);
        return soma.divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);
    }

    private StatusAprovacao gerarStatusAprovacao() {
        BigDecimal soma = nota1.add(nota2).add(nota3);
        double media= soma.doubleValue()/3;

        if(media >= 6) return StatusAprovacao.APROVADO;

        if(media >= 4) return StatusAprovacao.RECUPERACAO;

        return StatusAprovacao.REPROVADO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getNota1() {
        return nota1;
    }

    public void setNota1(BigDecimal nota1) {
        this.nota1 = nota1;
    }

    public BigDecimal getNota2() {
        return nota2;
    }

    public void setNota2(BigDecimal nota2) {
        this.nota2 = nota2;
    }

    public BigDecimal getNota3() {
        return nota3;
    }

    public void setNota3(BigDecimal nota3) {
        this.nota3 = nota3;
    }

    public BigDecimal getMedia() {
        return media;
    }

    public StatusAprovacao getStatus() {
        return status;
    }


}
