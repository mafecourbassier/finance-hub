package com.financehub.service;

import com.financehub.model.Category;
import com.financehub.model.Transaction;
import com.financehub.repository.CategoryRepository;
import com.financehub.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Criar transação
    public Transaction createTransaction(Transaction t) {

        validateTransaction(t);

        // Buscar categoria para garantir que existe
        Category category = categoryRepository.findById(t.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        t.setCategory(category);

        return transactionRepository.save(t);
    }

    // Listar todas
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    // Buscar por ID
    public Transaction getById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));
    }

    // Atualizar
    public Transaction update(Long id, Transaction updated) {

        validateTransaction(updated);

        Transaction existing = getById(id);

        existing.setDescription(updated.getDescription());
        existing.setAmount(updated.getAmount());
        existing.setType(updated.getType());
        existing.setPaymentMethod(updated.getPaymentMethod());
        existing.setDate(updated.getDate());
        existing.setStatus(updated.getStatus());
        existing.setRecurring(updated.isRecurring());

        // valida categoria
        Category category = categoryRepository.findById(updated.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        existing.setCategory(category);

        return transactionRepository.save(existing);
    }

    // Deletar
    public void delete(Long id) {
        Transaction t = getById(id);
        transactionRepository.delete(t);
    }

    // metodo de validação completo
    private void validateTransaction(Transaction t) {

        if (t.getAmount() == null || t.getAmount() <= 0) {
            throw new RuntimeException("O valor deve ser maior que zero.");
        }

        if (t.getType() == null ||
                (!t.getType().equals("INCOME") && !t.getType().equals("EXPENSE"))) {
            throw new RuntimeException("Tipo inválido. Use INCOME ou EXPENSE.");
        }

        if (t.getStatus() == null ||
                (!t.getStatus().equals("PAID") && !t.getStatus().equals("PENDING"))) {
            throw new RuntimeException("Status inválido. Use PAID ou PENDING.");
        }

        if (t.getDate() == null) {
            throw new RuntimeException("A data é obrigatória.");
        }

        if (t.getCategory() == null || t.getCategory().getId() == null) {
            throw new RuntimeException("Categoria obrigatória.");
        }
    }
}
