# Bilbo's Library — Sistema de Recomendação de Livros

Aplicação web desenvolvida em TypeScript com React para busca e recomendação de livros, utilizando a API pública do Google Books.

Desenvolvida como projeto prático para a disciplina de Paradigmas de Linguagens de Programação.

## Tecnologias utilizadas

- TypeScript — linguagem principal, com tipagem estática
- React 18 — biblioteca para construção da interface
- Vite — ferramenta de build e servidor de desenvolvimento
- Google Books API — fonte de dados de livros em tempo real

## Como executar

### Pré-requisitos

- Node.js instalado (versão 18 ou superior)
- npm disponível no terminal

### Passos

```bash
# Instalar dependências
npm install

# Iniciar servidor de desenvolvimento
npm run dev
```

Acesse a aplicação em: http://localhost:5173

## Funcionalidades

- Busca de livros por título, autor ou tema
- Exploração por categorias: Romance, Fantasia, Ficção Científica, Mistério, Terror, Biografia, História e Autoajuda
- Exibição de detalhes do livro: descrição, avaliação, número de páginas e data de publicação
- Recomendações automáticas baseadas no gênero e autor do livro selecionado

## Estrutura do projeto

```
src/
├── components/
│   ├── BookCard.tsx       # Componente de exibição do card de livro
│   └── BookModal.tsx      # Modal com detalhes e recomendações
├── services/
│   └── booksApi.ts        # Integração com a Google Books API
├── types/
│   └── index.ts           # Definições de tipos TypeScript
├── App.tsx                # Componente principal da aplicação
└── styles.css             # Estilização global
```

## Referências

- SEBESTA, Robert W. Concepts of Programming Languages. 10. ed. Pearson, 2012.
- Documentação oficial do TypeScript: https://www.typescriptlang.org/docs
- Documentação oficial do React: https://react.dev
- Google Books API: https://developers.google.com/books
