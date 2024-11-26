package com.example.interfaceestoque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO produtos (nome, valor, peso, qnt) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getValor());
            stmt.setInt(3, produto.getPeso());
            stmt.setInt(4, produto.getQnt());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> buscarTodosProdutos() {
        String sql = "SELECT * FROM produtos";
        List<Produto> produtos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getString("nome"),
                        rs.getDouble("valor"),
                        rs.getInt("peso"),
                        rs.getInt("qnt")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public void removerProduto(String nome) {
        String sql = "DELETE FROM produtos WHERE nome = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produtos SET valor = ?, peso = ?, qnt = ? WHERE nome = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, produto.getValor());
            stmt.setInt(2, produto.getPeso());
            stmt.setInt(3, produto.getQnt());
            stmt.setString(4, produto.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
