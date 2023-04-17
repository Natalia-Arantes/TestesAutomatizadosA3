public class FuncionarioTerceirizado extends Funcionario implements RegrasPagamento {
  private double despesasAdicionais;

  public FuncionarioTerceirizado(String nome,
                                 int horasTrabalhadas,
                                 int valorHora, double despesasAdicionais) throws IllegalAccessException {
    super(nome, horasTrabalhadas, valorHora);
    this.despesasAdicionais = validaDespesasAdicionais(despesasAdicionais);
  }

  public FuncionarioTerceirizado() {}

  public double getDespesasAdicionais() {
    return despesasAdicionais;
  }

  public void setDespesasAdicionais(double despesasAdicionais) throws IllegalAccessException {
    this.despesasAdicionais = validaDespesasAdicionais(despesasAdicionais);
  }

  @Override
  public double calcularPagamento() throws IllegalAccessException{
    double pagamento = (super.getValorHora() * super.getHorasTrabalhadas()) + (despesasAdicionais * 1.1);
    if(pagamento < 1320.00){
      throw new IllegalAccessException("O pagamento não pode ser menor que R$ 1320,00");
    }
    return pagamento;
  }
}
