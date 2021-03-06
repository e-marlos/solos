package domain.fosforo;

import domain.Elementos;
import lombok.Getter;

@Getter
public class CorrecaoFosforo {
    private final double quantidadeAplicacaoKgPorHectare;
    private final double custoPorHectare;
    private final double kgHaBeneficioPrimario;
    private final double kgHaBeneficioSecundario;
    private final Elementos beneficioPrimario;
    private final Elementos beneficioSecundario;
    private final double kgPorHectare;

    public CorrecaoFosforo(TipoFosforo tipo, double valorPorTonelada, double teorAlvo, double eficiencia, double teorAtual) {
        kgPorHectare = getKgHectare(teorAtual, teorAlvo, eficiencia, tipo.getTeorP205());
        custoPorHectare = valorPorTonelada * kgPorHectare / 1000;
        quantidadeAplicacaoKgPorHectare = kgPorHectare;
        kgHaBeneficioPrimario = kgPorHectare * tipo.getFatorBeneficioPrimario();
        kgHaBeneficioSecundario = kgPorHectare * tipo.getFatorBeneficioSecundario();
        beneficioPrimario = tipo.getBeneficioPrimario();
        beneficioSecundario = tipo.getBeneficioSecundario();
    }

    private static double getKgHectare(double teorAtual, double teorDesejado, double eficiencia, double teorP2O5) {
        double teorAdicionar = teorDesejado - teorAtual;
        double teorEmKgPorHectare = teorAdicionar > 0 ? teorAdicionar * 2 : 0;
        double kgPorHectareP2O5 = teorEmKgPorHectare * 2.29;
        double kgPorHectareP2O5ComEficiencia = kgPorHectareP2O5 / eficiencia;
        return kgPorHectareP2O5ComEficiencia / teorP2O5;
    }
}