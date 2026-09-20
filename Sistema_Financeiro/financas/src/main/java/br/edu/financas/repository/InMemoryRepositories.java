package br.edu.financas.repository;
import br.edu.financas.domain.*; import java.util.*;
public class InMemoryRepositories implements Repositories {
    private final Map<UUID,Conta> c=new HashMap<>();
    private final Map<UUID,Categoria> g=new HashMap<>();
    private final Map<UUID,Lancamento> l=new HashMap<>();
    private final Map<UUID,Orcamento> o=new HashMap<>();
    private final Map<UUID,Meta> m=new HashMap<>();
    private final Set<String> k=new HashSet<>();
@Override
public Map<UUID,Conta> contas(){return c;}
@Override
public Map<UUID,Categoria> categorias(){return g;}
@Override
public Map<UUID,Lancamento> lancamentos(){return l;}
@Override
public Map<UUID,Orcamento> orcamentos(){return o;
}@Override
public Map<UUID,Meta> metas(){return m;}
@Override
public Set<String> chavesTransferencia(){return k;}
}
