import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.beans.Transient;

import static org.junit.jupiter.api.Assertions.*;

public class CalculoDeSalarioTest {

  @Test
  @DisplayName("Instanciar funcionário com todas as regras válidas")
  public void funcionarioComRegrasValidas() throws IllegalAccessException {

    String nomeEsperado = "Jurandir";
    int valorHoraEsperado = 67;
    int horasTrabalhadasEsperadas = 38;

    Funcionario funcionario = new Funcionario("Jurandir", 38, 67);

    assertEquals(nomeEsperado, funcionario.getNome());
    assertEquals(valorHoraEsperado, funcionario.getValorHora());
    assertEquals(horasTrabalhadasEsperadas, funcionario.getHorasTrabalhadas());
  }

  @Test
  @DisplayName("Instanciar funcionário com horas trabalhadas a mais do que o permitido")
  public void funcionarioComHorasTrabalhadasAlemDoPermitido() throws IllegalAccessException {
    IllegalAccessException horasTrabalhadasExecption = assertThrows(IllegalAccessException.class,
      () -> new Funcionario("Adailton", 4000, 67));

    assertEquals("O funcionário não pode trabalhar mais que 40 horas" , horasTrabalhadasExecption.getMessage());
  }

  @Test
  @DisplayName("Instanciar funcionário com valor hora maior do que o permitido")
  public void funcionarioComValorHoraAlemDoPermitido() throws IllegalAccessException {
    IllegalAccessException valorHoraExecption = assertThrows(IllegalAccessException.class,
      () -> new Funcionario("Jefferson", 36, 200));

    assertEquals("O valor hora precisa ser entre 52,80 e 132,00", valorHoraExecption.getMessage());
  }

  @Test
  @DisplayName("Alterar o valor hora de um funcionário")
  public void alteraValorHoraVálido() throws IllegalAccessException {
    int valorHora = 87;
    Funcionario funcionario = new Funcionario("Hilbert", 36, 67);
    assertEquals(67, funcionario.getValorHora());

    funcionario.setValorHora(valorHora);
    assertEquals(valorHora, funcionario.getValorHora());
  }

  @Test
  @DisplayName("Alterar o valor hora INVÁLIDO para um funcionário")
  public void alteraValorHoraInválido() throws IllegalAccessException {
    int valorHora = 5000;
    Funcionario funcionario = new Funcionario("Renatinho", 36, 67);
    assertEquals(67, funcionario.getValorHora());

    IllegalAccessException valorHoraExecption = assertThrows(IllegalAccessException.class, () ->
      funcionario.setValorHora(valorHora));

    assertEquals("O valor hora precisa ser entre 52,80 e 132,00", valorHoraExecption.getMessage());
  }


  @Test
  @DisplayName("Alterar as horas trabalhadas de um funcionário")
  public void alteraHorasTrabalhadasValidas() throws IllegalAccessException {
    int horasTrabalhadas = 28;
    Funcionario funcionario = new Funcionario("Roogério", 12, 100);
    assertEquals(12, funcionario.getHorasTrabalhadas());

    funcionario.setHorasTrabalhadas(horasTrabalhadas);
    assertEquals(horasTrabalhadas, funcionario.getHorasTrabalhadas());
  }

  @Test
  @DisplayName("Alterar valor de horas trabalhadas INVÁLIDO para um funcionário")
  public void alteraHorasTrabalhadasInválidas() throws IllegalAccessException {
    int horasTrabalhadas = 70000;
    Funcionario funcionario = new Funcionario("Jeremias", 36, 67);
    assertEquals(36, funcionario.getHorasTrabalhadas());

    IllegalAccessException horasTraalhadasException = assertThrows(IllegalAccessException.class, () ->
      funcionario.setHorasTrabalhadas(horasTrabalhadas));

    assertEquals("O funcionário não pode trabalhar mais que 40 horas", horasTraalhadasException.getMessage());
  }

  @Test
  @DisplayName("Calcula o salário de um funcionário")
  public void calculaSalario() throws IllegalAccessException {
    double salarioEsperado = 2730.00;
    Funcionario funcionario = new Funcionario("Pablo", 39, 70);
    assertEquals(salarioEsperado, funcionario.calcularPagamento());
  }

  @Test
  @DisplayName("Calcula o salário INVÁLIDO de um funcionário")
  public void calculaSalarioInválido() throws IllegalAccessException {
    Funcionario funcionario = new Funcionario("Daniel", 1, 70);
    IllegalAccessException illegalAccessException = assertThrows(IllegalAccessException.class, () ->
      funcionario.calcularPagamento());

    assertEquals("O pagamento não pode ser menor que R$ 1320,00" , illegalAccessException.getMessage());
  }

  @Test
  @DisplayName("Instanciar funcionário terceirizado com despesa válida")
  public void despesaValidaParaTerceirizado() throws IllegalAccessException {
    FuncionarioTerceirizado funcionario = new FuncionarioTerceirizado("Daniel", 1, 70, 900);
    assertEquals(900 ,funcionario.getDespesasAdicionais());
  }

  @Test
  @DisplayName("Instanciar funcionário terceirizado com despesa inválida")
  public void despesaInvalidaParaTerceirizado() throws IllegalAccessException {

    IllegalAccessException despesaInvalidaException = assertThrows(IllegalAccessException.class, () ->
      new FuncionarioTerceirizado("Robson", 1, 70, 1001));

    assertEquals("O valor das despesas adicionais não pode ultrapassar R$ 1000.00",
      despesaInvalidaException.getMessage());
  }

  @Test
  @DisplayName("Altera funcionário terceirizado com despesa válida")
  public void alteraDespesaValidaParaTerceirizado() throws IllegalAccessException {
    double despesaEsperada = 999;
    FuncionarioTerceirizado funcionario = new FuncionarioTerceirizado("Richard", 1, 70, 800);

    funcionario.setDespesasAdicionais(despesaEsperada);
    assertEquals(despesaEsperada ,funcionario.getDespesasAdicionais());
  }

  @Test
  @DisplayName("Instanciar funcionário terceirizado com despesa inválida")
  public void alteraDespesaInvalidaParaTerceirizado() throws IllegalAccessException {
    double despesaEsperada = 9000;
    FuncionarioTerceirizado funcionario = new FuncionarioTerceirizado("Richard", 1, 70, 800);

    IllegalAccessException despesaInvalidaException = assertThrows(IllegalAccessException.class, () ->
      funcionario.setDespesasAdicionais(despesaEsperada));

    assertEquals("O valor das despesas adicionais não pode ultrapassar R$ 1000.00",
      despesaInvalidaException.getMessage());
  }

  @Test
  @DisplayName("Calcula salário do terceirizado")
  public void calculaSlarioTerceirizado() throws IllegalAccessException {
    double salarioEsperado = (40 * 70) + (800 * 1.1);
    FuncionarioTerceirizado funcionario = new FuncionarioTerceirizado("Richard", 40, 70, 800);
    assertEquals(salarioEsperado, funcionario.calcularPagamento());

  }


}
