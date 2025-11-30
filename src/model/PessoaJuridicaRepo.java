package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> lista = new ArrayList<>();

    public void inserir(PessoaJuridica p) {
        lista.add(p);
    }

    public void alterar(PessoaJuridica p) {
        excluir(p.getId());
        lista.add(p);
    }

    public void excluir(int id) {
        lista.removeIf(p -> p.getId() == id);
    }

    public PessoaJuridica obter(int id) {
        return lista.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public ArrayList<PessoaJuridica> obterTodos() {
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
        lista = (ArrayList<PessoaJuridica>) ois.readObject();
        ois.close();
    }
}