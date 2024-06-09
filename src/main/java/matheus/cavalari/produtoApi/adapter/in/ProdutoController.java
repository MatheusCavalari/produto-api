package matheus.cavalari.produtoApi.adapter.in;

import matheus.cavalari.produtoApi.aplication.IProdutoService;
import matheus.cavalari.produtoApi.domain.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProdutoController {
    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
    private final IProdutoService produtoService;

    @Autowired
    public ProdutoController(IProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/produto")
    public ResponseEntity<?> criarProduto(@RequestBody Produto produtoDto) {

        try {
            Produto produto = produtoService.salvarProduto(produtoDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(produto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o produto");
        }
    }

    @GetMapping("/produtos")
    public ResponseEntity<?> listarProdutos() {
        try {
            List<Produto> produtos = produtoService.listarProdutos();
            if (produtos.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else
                return ResponseEntity.ok(produtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao listar os produtos");
        }
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<?> getContrato(@PathVariable Long id) {
        try {
            Produto produto = produtoService.getProduto(id);
            if (produto != null) {
                return ResponseEntity.ok(produto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado para o ID informado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter o contrato");
        }
    }

    @GetMapping("/produtos/paginados")
    public ResponseEntity<?> listarProdutosPaginados(@RequestParam int page, @RequestParam int size) {
        try {
            Page<Produto> produtosPage = produtoService.listarProdutosPaginados(page, size);
            if (produtosPage.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(produtosPage);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao listar os produtos paginados");
        }
    }

}
