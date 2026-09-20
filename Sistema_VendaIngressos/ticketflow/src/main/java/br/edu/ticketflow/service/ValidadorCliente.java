package br.edu.ticketflow.service;
import br.edu.ticketflow.domain.Cliente;
import br.edu.ticketflow.exception.RegraNegocioException;
import java.time.*;
import java.util.regex.Pattern;
public final class ValidadorCliente {
  private static final Pattern EMAIL=Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
  public void validar(Cliente c, LocalDate hoje){
    if(c.nome().isBlank()) throw new RegraNegocioException("Nome obrigatório");
    if(!EMAIL.matcher(c.email()).matches()) throw new RegraNegocioException("E-mail inválido");
    if(c.cpf().replaceAll("\\D","").length()!=11) throw new RegraNegocioException("CPF inválido");
    if(Period.between(c.nascimento(),hoje).getYears()<16) throw new RegraNegocioException("Cliente menor de 16 anos");
  }
}
