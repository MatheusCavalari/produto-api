package matheus.cavalari.produtoApi.adapter.in;

import matheus.cavalari.produtoApi.application.IProdutoService;
import matheus.cavalari.produtoApi.domain.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProdutoControllerTest {
    @Mock
    private IProdutoService produtoService;

    @InjectMocks
    private ProdutoController  produtoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarProduto() {
        Produto produto = new Produto();
        when(produtoService.salvarProduto(produto)).thenReturn(produto);

        ResponseEntity<?> responseEntity = produtoController.criarProduto(produto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        verify(produtoService, times(1)).salvarProduto(produto);
    }

    @Test
    void listarProdutos() {
        List<Produto> produtos = Arrays.asList(new Produto(), new Produto());
        when(produtoService.listarProdutos()).thenReturn(produtos);

        ResponseEntity<?> responseEntity = produtoController.listarProdutos();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        verify(produtoService, times(1)).listarProdutos();
    }

    @Test
    void getProduto() {
        Produto produto = new Produto();
        when(produtoService.getProduto(1L)).thenReturn(produto);

        ResponseEntity<?> responseEntity = produtoController.getContrato(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        verify(produtoService, times(1)).getProduto(1L);
    }

    @Test
    void listarProdutosPaginados() {
        Produto produto = new Produto();
        List<Produto> produtos = Arrays.asList(produto);
        Page<Produto> produtoPage = new PageImpl<>(produtos);
        when(produtoService.listarProdutosPaginados(0, 1)).thenReturn(produtoPage);

        ResponseEntity<?> responseEntity = produtoController.listarProdutosPaginados(0, 1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        verify(produtoService, times(1)).listarProdutosPaginados(0, 1);
    }
}
