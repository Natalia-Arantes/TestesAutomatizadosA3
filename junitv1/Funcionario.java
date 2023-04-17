public class Funcionario implements RegrasPagamento {
  private String nome;
  private int horasTrabalhadas;
  private int valorHora;

  public Funcionario(String nome, int horasTrabalhadas, int valorHora) throws IllegalAccessException {
    this.nome = nome;
    this.horasTrabalhadas = validaHorasTrabalhadas(horasTrabalhadas);
    this.valorHora = validaValorHora(valorHora);
  }

  public Funcionario(){}

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getHorasTrabalhadas() {
    return horasTrabalhadas;
  }

  public void setHorasTrabalhadas(int horasTrabalhadas) throws IllegalAccessException {
    this.horasTrabalhadas = validaHorasTrabalhadas(horasTrabalhadas);
  }

  public int getValorHora() {
    return valorHora;
  }

  public void setValorHora(int valorHora) throws IllegalAccessException {
    this.valorHora = validaValorHora(valorHora);
  }

  @Override
  public double calcularPagamento() throws IllegalAccessException {
    double pagamento = valorHora * horasTrabalhadas;
    if(pagamento < 1320.00){
      throw new IllegalAccessException("O pagamento não pode ser menor que R$ 1320,00");
    }

    return pagamento;
  }

  @Override
  public int validaHorasTrabalhadas(int horasTrabalhadas) throws IllegalAccessException {
    if(horasTrabalhadas > 40){
      throw new IllegalAccessException("O funcionário não pode trabalhar mais que 40 horas");
    }
    return horasTrabalhadas;
  }

  @Override
  public int validaValorHora(int valorHora) throws IllegalAccessException {
    double valorHoraMinimo = 1320.00 * 0.04;
    double valorHoraMaximo = 1320.00 * 0.1;

    if (valorHora < valorHoraMinimo || valorHora > valorHoraMaximo){
      throw new IllegalAccessException(String.format("O valor hora precisa ser entre %.2f e %.2f", valorHoraMinimo, valorHoraMaximo));
    }

    return valorHora;
  }

  @Override
  public double validaDespesasAdicionais(double despesas) throws IllegalAccessException {
    if(despesas > 1000){
      throw new IllegalAccessException("O valor das despesas adicionais não pode ultrapassar R$ 1000.00");
    }

    return despesas;
  }
}
