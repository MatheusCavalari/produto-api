package matheus.cavalari.produtoApi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import matheus.cavalari.produtoApi.enums.Maturidade;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricaoBreve")
    private String descricaoBreve;

    @Column(name = "descricaoCompleta")
    private String descricaoCompleta;

    @Column(name = "publico_alvp")
    private String publicoAlvo;

    @Column(name = "maturidade")
    private Maturidade maturidade;

    @Column(name = "quantidade_usuarios_ativos")
    private Long quantidadeUsuariosAtivos;

    @Column(name = "documetacao_consumo")
    private String documetacaoConsumo;



}
