package matheus.cavalari.produtoApi.aplication;

import matheus.cavalari.produtoApi.adapter.out.ProdutoRepository;
import matheus.cavalari.produtoApi.domain.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService implements IProdutoService {
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
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
}
