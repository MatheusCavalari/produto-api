package matheus.cavalari.produtoApi.application;

import matheus.cavalari.produtoApi.adapter.out.ProdutoRepository;
import matheus.cavalari.produtoApi.application.ProdutoService;
import matheus.cavalari.produtoApi.domain.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProdutoServiceTest {
    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks

    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salvarProduto() {
        Produto produto = new Produto();
        when(produtoRepository.save(produto)).thenReturn(produto);

        Produto savedProduto = produtoService.salvarProduto(produto);

        assertNotNull(savedProduto);
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void listarProdutos() {
        List<Produto> produtos = Arrays.asList(new Produto(), new Produto());
        when(produtoRepository.findAll()).thenReturn(produtos);

        List<Produto> result = produtoService.listarProdutos();

        assertEquals(2, result.size());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    void getProduto() {
        Produto produto = new Produto();
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        Produto result = produtoService.getProduto(1L);

        assertNotNull(result);
        assertEquals(produto, result);
        verify(produtoRepository, times(1)).findById(1L);
    }

    @Test
    void listarProdutosPaginados() {
        Produto produto = new Produto();
        List<Produto> produtos = Arrays.asList(produto);
        Page<Produto> produtoPage = new PageImpl<>(produtos);
        Pageable pageable = PageRequest.of(0, 1);
        when(produtoRepository.findAll(pageable)).thenReturn(produtoPage);

        Page<Produto> result = produtoService.listarProdutosPaginados(0, 1);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(produtoRepository, times(1)).findAll(pageable);
    }
}
