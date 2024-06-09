package matheus.cavalari.produtoApi.application;

import matheus.cavalari.produtoApi.domain.Produto;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IProdutoService {
    Produto salvarProduto(Produto produto);
    List<Produto> listarProdutos();
    Produto getProduto(Long id);

    Page<Produto> listarProdutosPaginados(int page, int size);
}
