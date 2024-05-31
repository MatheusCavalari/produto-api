package matheus.cavalari.produtoApi.aplication;

import matheus.cavalari.produtoApi.domain.Produto;

import java.util.List;


public interface IProdutoService {
    Produto salvarProduto(Produto produto);
    List<Produto> listarProdutos();
}
