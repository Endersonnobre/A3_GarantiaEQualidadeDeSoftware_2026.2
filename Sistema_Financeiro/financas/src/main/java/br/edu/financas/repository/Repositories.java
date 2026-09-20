package br.edu.financas.repository;
import br.edu.financas.domain.*; import java.util.*;
public interface Repositories {Map<UUID,Conta> contas();Map<UUID,Categoria> categorias();Map<UUID,Lancamento> lancamentos();Map<UUID,Orcamento> orcamentos();Map<UUID,Meta> metas();Set<String> chavesTransferencia();}
