# Projeto de Notas em Android com Jetpack Compose

Este é um projeto de aplicativo Android simples, desenvolvido como parte de um exercício de aprendizado. O objetivo do aplicativo é permitir o lançamento de três notas de um aluno, calcular a média das notas e exibir o status de aprovação.

## Funcionalidades

- **Cadastro do Nome do Aluno:** Permite a inserção do nome completo do aluno.
- **Lançamento de Notas:** Campo para inserir três notas parciais (TP1, TP2, TP3).
- **Cálculo da Média:** A média aritmética das três notas é calculada e exibida automaticamente.
- **Avaliação de Desempenho:** Exibe o status final do aluno com base na média:
    - **Reprovado:** Média < 6.0
    - **Aprovado:** Média ≥ 6.0 e ≤ 9.0
    - **Ótimo Aproveitamento:** Média > 9.0
- **Limpeza de Dados:** Um botão para limpar todos os campos e recomeçar.

## Tecnologias Utilizadas

- **Linguagem:** Kotlin
- **Interface Gráfica:** Jetpack Compose (a biblioteca de UI moderna do Android)
- **Gerenciamento de Estado:** Funções reativas do Compose, como `remember` e `mutableStateOf`, para garantir que a UI seja atualizada automaticamente.
- **Estrutura de Dados:** O projeto utiliza uma `data class` para modelar a entidade `Aluno`, com uma `MutableList<Double>` para gerenciar as notas.

---

*Desenvolvido por Ruth da Silva Mendonça*