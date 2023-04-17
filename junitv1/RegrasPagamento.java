public interface RegrasPagamento {
  double calcularPagamento() throws IllegalAccessException;
  int validaHorasTrabalhadas(int horasTrabalhadas) throws IllegalAccessException;
  int validaValorHora(int valorHora) throws IllegalAccessException;

}
