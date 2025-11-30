package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaFisicaRepo {
    private ArrayList<PessoaFisica> lista = new ArrayList<>();

    public void inserir(PessoaFisica p) {
        lista.add(p);
    }

    public void alterar(PessoaFisica p) {
        excluir(p.getId());
        lista.add(p);
    }

    public void excluir(int id) {
        lista.removeIf(p -> p.getId() == id);
    }

    public PessoaFisica obter(int id) {
        return lista.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public ArrayList<PessoaFisica> obterTodos() {
        return lista;
    }

    public void persistir(String arquivo) throws Exception {
        FileOutputStream fos = new FileOutputStream(arquivo);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(lista);
        oos.close();
    }

    public void recuperar(String arquivo) throws Exception {
        FileInputStream fis = new FileInputStream(arquivo);
        ObjectInputStream ois = new ObjectInputStream(fis);
        lista = (ArrayList<PessoaFisica>) ois.readObject();
        ois.close();
    }
}