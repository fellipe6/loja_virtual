package dev.lojavirtual.loja_virtual;


import dev.lojavirtual.loja_virtual.util.ValidaCNPJ;
import dev.lojavirtual.loja_virtual.util.ValidaCPF;

import java.util.InputMismatchException;

public class TesteCPFCNPJ {

    public static void main(String[] args) {
        boolean isCnpj = ValidaCNPJ.isCNPJ("35.478.035/0001-15");
        System.out.println("Cnpj válido : "  + isCnpj);

        boolean isCpf = ValidaCPF.isCPF("014.575.483-99");

        System.out.println("CPF válido : "  + isCpf);
    }
}