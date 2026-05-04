# 📚 BookWise — Sistema de Recomendação de Livros

Aplicação web em TypeScript + React para descobrir e recomendar livros usando a API do Google Books.

## 🚀 Como rodar

```bash
npm install
npm run dev
```

Acesse: http://localhost:5173

## 🛠️ Tecnologias

- **TypeScript** — tipagem estática
- **React 18** — interface de usuário
- **Vite** — bundler rápido
- **Google Books API** — dados de livros em tempo real (gratuita, sem chave)

## ✨ Funcionalidades

- 🔍 Busca por título, autor ou tema
- 📂 Exploração por gênero (Romance, Fantasia, Terror, etc.)
- 📖 Detalhes completos de cada livro
- 💡 Recomendações baseadas no livro selecionado
- ⭐ Avaliações e metadados dos livros

## 📁 Estrutura

```
src/
├── components/
│   ├── BookCard.tsx     # Card de livro
│   └── BookModal.tsx    # Modal de detalhes + recomendações
├── services/
│   └── booksApi.ts      # Integração com Google Books API
├── types/
│   └── index.ts         # Tipos TypeScript
├── App.tsx              # Componente principal
└── styles.css           # Estilos
```
