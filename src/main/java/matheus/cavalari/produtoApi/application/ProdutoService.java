package matheus.cavalari.produtoApi.application;

import jakarta.annotation.PostConstruct;
import matheus.cavalari.produtoApi.adapter.out.ProdutoRepository;
import matheus.cavalari.produtoApi.domain.Produto;
import matheus.cavalari.produtoApi.enums.Maturidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProdutoService implements IProdutoService {
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostConstruct
    private void init() {
        Produto produto1 = Produto.builder()
                .nome("XCr2 - Mainframe")
                .descricaoBreve("API - Consulta pagamentos Itaú")
                .descricaoCompleta("Consulta de pagamentos Itaú, utilizando o sistema XCr2.")
                .publicoAlvo("Pagamentos")
                .maturidade(Maturidade.EVOLUINDO)
                .quantidadeUsuariosAtivos(5000L)
                .documetacaoConsumo("www.confluence.com/docs/XCr2Mainframe")
                .build();

        Produto produto2 = Produto.builder()
                .nome("SUG8 - Mainframe")
                .descricaoBreve("API - Consulta pagamentos Ext")
                .descricaoCompleta("Consulta de pagamentos Externos, utilizando o sistema SUG8.")
                .publicoAlvo("Pagamentos")
                .maturidade(Maturidade.ESTAVEL)
                .quantidadeUsuariosAtivos(3000L)
                .documetacaoConsumo("www.confluence.com/docs/SUG8Mainframe")
                .build();

        Produto produto3 = Produto.builder()
                .nome("YFU9 - Mainframe")
                .descricaoBreve("API - Consulta Títulos")
                .descricaoCompleta("Consulta de títuloas liquidados, utilizando o sistema YFU9")
                .publicoAlvo("tITULOS")
                .maturidade(Maturidade.EM_UTILIZACAO)
                .quantidadeUsuariosAtivos(2000L)
                .documetacaoConsumo("www.confluence.com/docs/YFU9Mainframe")
                .build();
        Produto produto4 = Produto.builder()
                .nome("COR10 - Mainframe")
                .descricaoBreve("API - Consulta Títulos")
                .descricaoCompleta("Consulta de títuloas inconsistidos, utilizando o sistema COR10")
                .publicoAlvo("Crianças e profissionais criativos")
                .maturidade(Maturidade.EM_UTILIZACAO)
                .quantidadeUsuariosAtivos(2000L)
                .documetacaoConsumo("www.confluence.com/docs/COR10Mainframe")
                .build();

        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4));
    }

    @Override
    public Produto salvarProduto(Produto produto) {
        produtoRepository.save(produto);
        return produto;
    }

    @Override
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto getProduto(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Page<Produto> listarProdutosPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return produtoRepository.findAll(pageable);
    }
}
